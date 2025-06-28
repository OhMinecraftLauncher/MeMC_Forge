# MeMC_Forge Project
## 一些小工具，可以帮助你在 memc.cc 上运行Minecraft Forge服务端
我们同时为 README 文件提供以下其他语言： **[English](README_en)**
#### 为什么要做这个项目？
事情是这样的，我有一个朋友想使用 [MeMC](memc.cc) 开一个Minecraft Forge服务器来游玩某整合包，但他说在MeMC上安装Forge有问题（具体说是MeMC不支持GUI界面，Forge安装器在没有参数传递时默认会运行GUI界面，导致无法安装Forge），所以找我帮忙。我考虑到我制作的这两个小工具或许能帮到更多的人，所以我就开源出来了。
#### 如何使用这个项目？
> 先决条件
> - 拥有一个MeMC的账号，并已经正确创建实例。
> - 有一定的 Linux 和 Minecraft 服务器 相关基础与经验
> - 一个懂得思考和查资料的脑子，以及善于 **正确** “提问”的嘴巴（手）

- **第一步**：打开你的实例文件管理器
- **第二步**：从 [Java_Console](Java_Console/server.jar) 下载 server.jar 文件
- **第三步**：将 server.jar 文件和 Forge 安装器 jar 文件上传到你的实例（服务器），记住 Forge 安装器的文件名，然后启动你的实例（服务器）
- **第四步**：逐行输入以下命令（请将 "[", "]" 及其中的内容替换为目标的内容），这将在你的实例（服务器）上安装 Forge：
``` bash
java -jar [Forge安装器的文件名] --installServer
exit
```
- **第五步**：从 [Java_RunServer](Java_RunServer/server.jar) 下载并替换 server.jar 文件，上传并覆盖原文件
- **第六步**：**大功告成！** 重启你的实例（服务器），享受你的 Minecraft Forge 服务器吧。从现在开始，运行服务器将和以往没有任何区别。