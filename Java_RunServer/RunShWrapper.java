import java.io.*;
import java.util.concurrent.*;

public class RunShWrapper {
    private static volatile boolean running = true;
    private static Process process;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // 添加关闭钩子确保资源清理
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            if (process != null) {
                process.destroy();
            }
            executor.shutdownNow();
        }));

        int exitCode = -1;

        try {
            // 构建命令
            String[] command = new String[args.length + 1];
            command[0] = "./run.sh";
            System.arraycopy(args, 0, command, 1, args.length);
            
            // 创建进程
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(true);
            process = pb.start();

            // 输出转发线程
            executor.submit(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    if (running) e.printStackTrace();
                } finally {
                    running = false;
                }
            });

            // 输入转发线程
            executor.submit(() -> {
                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(process.getOutputStream()))) {
                    BufferedReader consoleReader = new BufferedReader(
                            new InputStreamReader(System.in));
                    while (running) {
                        if (consoleReader.ready()) {  // 非阻塞检查
                            String line = consoleReader.readLine();
                            if (line == null) break;  // 输入流关闭
                            writer.write(line);
                            writer.newLine();
                            writer.flush();
                        } else {
                            // 短暂睡眠避免忙等待
                            Thread.sleep(100);
                            // 检查进程是否存活
                            if (!process.isAlive()) {
                                running = false;
                                break;
                            }
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    if (running) e.printStackTrace();
                }
            });

            // 等待进程结束
            exitCode = process.waitFor();
            System.out.println("Minecraft server process exited with code: " + exitCode);
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            running = false;
            executor.shutdownNow();
            try {
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("This process will exit with code: " + exitCode);
            System.exit(exitCode);
        }
    }
}