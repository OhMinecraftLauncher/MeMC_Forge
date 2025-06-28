import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TerminalEmulator {
    public static void main(String[] args) {
        System.out.println("Java Terminal Emulator v1.0");
        System.out.println("Type 'exit' or 'quit' to end the session");
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            System.out.print("$ ");
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Closing terminal emulator...");
                break;
            }
            
            try {
                executeCommand(input);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error executing command: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void executeCommand(String command) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        
        ProcessBuilder processBuilder;
        if (isWindows) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            processBuilder = new ProcessBuilder("sh", "-c", command);
        }
        
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        
        // 读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        
        // 等待命令执行完成
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Command exited with code: " + exitCode);
        }
    }
}