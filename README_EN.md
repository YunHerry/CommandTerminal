# CommandTerminal

TO [EN](https://github.com/YunHerry/CommandTerminal/blob/master/README_EN.md) [CN](https://github.com/YunHerry/CommandTerminal/blob/master/README.md)

This project was inspired by the Minecraft server command line.

You can call methods with commands like: "/help -arg=xxx".

You can also add this to your web project to add a console function to it.

The current function has been basically perfected, you can write multiple methods with different parameters but the same name to implement different operation logic.

Efforts are currently underway to provide support for Springboot.


## Install
Maven
```
<dependency>
  <groupId>io.github.yunherry</groupId>
  <artifactId>command-terminal</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

Gradle
```
implementation 'io.github.yunherry:command-terminal:1.0.0'
```

## How to Start?

1.Annotate your main class with @TerminalApplication and write TerminalApplication.run(main.class); in your main function.

2.Use the @Command annotation to annotate the command class (Class). Currently, it is not compatible with Springboot. Please don't use springboot to manage bean.

3.Do not write the same class with the same method name and the same number of parameters in multiple command classes, which will cause a CommandConflictException to be thrown.
## Occasional Bugs?
This project is still in development and testing stage, maybe there are some bugs, you can submit Issues, I will give you an answer, when the problem is confirmed, I will fix the problem.
## IFFFF U HAVE BETTER CODES.
PRPRPRPRPRPR!
