package indi.yunHerry;


import indi.yunHerry.model.entity.TerminalApplication;
import indi.yunHerry.resolve.ArgsResolve;
import indi.yunHerry.resolve.MethodsResolve;
import indi.yunHerry.utils.StringCollectionUtil;

import java.util.Scanner;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class Terminal {
    protected static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       TerminalApplication terminalApplication= Terminal.run();
    }

    public static TerminalApplication run() {
        Thread commandTerminal = new Thread(() -> {
            String command;
            do {
                System.out.print("> ");
                command = scanner.nextLine();
                System.out.println("正在执行的方法名称: " + new MethodsResolve().analyze(command));
                System.out.println("正在执行的方法的参数信息: " + StringCollectionUtil.ToMap(new ArgsResolve().analyze(command)));
            } while (!"/exit".equalsIgnoreCase(command));
        });
        commandTerminal.start();
        return new TerminalApplication(commandTerminal);
    }
}
