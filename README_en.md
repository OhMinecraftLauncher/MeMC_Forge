# WeMC_Forge Project
### Some small tools which can help you run Minecraft Forge Server on wemc.cc
We also provide following other language for README file: **[中文](README.md)**
#### Why do I do this project?
The thing is, I have a friend who wants to use [WeMC](wemc.cc) to open a Minecraft Forge server to play a certain integration package, but he said there is a problem installing Forge on WeMC (specifically, WeMC does not support GUI interface, the Forge installer will run GUI interface by default without parameter passing, which makes it impossible to install Forge), so he asked me for help. I considered that the two small tools I made might help more people, so I opened them up.
#### How to use this project?
> PREREQUISITE
> - Have a WeMC account, and already created a instance.
> - Have a certain foundation and experience of Linux and Minecraft Server.
> - A brain that knows how to think and search for information, and a mouth (hand) that is good at asking questions **CORRECTLY**.
- **FIRST**: Open your instance's file manager.
- **SECOND**: Download the server.jar file from [Java_Console](Java_Console/server.jar)
- **THIRD**: Upload the server.jar file and the Forge installer jar file to your instance(server), remember the filename of the Forge installer, and then launch your instance(server).
- **FOURTH**: Type the following command line by line **(please replace the "[", "]" and the context inside to the content of the target)**, which will install Forge to your instance(server):
``` bash
java -jar [The filename of the Forge installer] --installServer
exit
```
- **FIFTH**: Download and replace the server.jar file from [Java_RunServer](Java_RunServer/server.jar), and upload and replace it.
- **SIXTH**: **ALL DONE!** Reboot your instance(server) and enjoy your Minecraft Forge Server.There won't be any difference compared to running a server in the past from now on.
#### Licence
This project follows the [MIT license](LICENCE) .