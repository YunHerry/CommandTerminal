# CommandTerminal
该项目是根据Minecraft服务器的控制台,而出现的.<br>
你可以使用类似于: "/help -arg=xxx" 来调用方法<br>
你可以也可以将其添加进Web之中,为你的Web项目增加一个控制台<br>
目前的功能已经基本完善,你甚至可以通过书写多个参数不同,名字相同的方法,来实现逻辑的不同<br>
目前正在努力对Springboot提供支持

## 安装
Maven
请等待正式版
## 如何开始?
1、在你的主类上标注@TerminalApplication注解,并且在你的主函数中写上TerminalApplication.run(main.class);<br>
2、使用@Command注解标注命令类(Class)即可,目前暂时不兼容Springboot,请勿使用springboot来管理bean<br>
3、请勿在多个命令类中,书写方法名称相同,并且参数数量相同的类,这会导致抛出CommandConflictException
## 你觉得你遇到了一些BUG？
本项目仍在开发测试阶段,或许的确有一些BUG,你可以提交Issues或直接PR,我将会给予您答复,当问题被复现,我会修复这个Bug。
## 如果你对某段代码有更好的想法
欢迎PR,我会认真地考虑您的建议。
