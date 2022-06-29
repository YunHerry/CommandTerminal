package indi.yunherry.model.dto;

import indi.yunherry.exception.TerminalNotExitException;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.resolve.AbstractResolver;
import indi.yunherry.resolve.ArgsResolve;
import indi.yunherry.resolve.MethodsResolve;
import indi.yunherry.utils.StringCollectionUtil;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalApplication {
    public static TerminalApplication terminalApplication;
    protected static Scanner scanner = new Scanner(System.in);
    private Thread thread;
    private Resolvers resolvers;
    public TerminalApplication(Thread thread) {
        this.thread = thread;
    }
    public TerminalApplication() {
    }
    /**
     * @param resolver 将你的解析器添加至解析器责任链中
     * */
    public TerminalApplication(Thread thread,AbstractResolver resolver) {
        this.thread = thread;
    }
    public static TerminalApplication run() {
        if (TerminalApplication.terminalApplication == null) {
            terminalApplication = new TerminalApplication();
        } else {
            InfoPrintExecute.ErrorPrint(new TerminalNotExitException("Terminal Not Exit!"));
            return terminalApplication;
        }
        terminalApplication.resolvers = new Resolvers(new AbstractResolver[]{new MethodsResolve(),new ArgsResolve()});
        terminalApplication.thread = new Thread(() -> {
            String command;
            do {
                System.out.print("> ");
                command = scanner.nextLine();
            } while (!"/exit".equalsIgnoreCase(command));
        });
        terminalApplication.thread.start();
        return terminalApplication;
    }
}
