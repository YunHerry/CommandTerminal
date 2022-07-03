package indi.yunherry.model.dto;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.exception.TerminalNotExitException;
import indi.yunherry.exception.TerminalReflectException;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.factory.bean.ResolverBean;
import indi.yunherry.resolve.ArgsResolve;
import indi.yunherry.resolve.MethodsResolve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalContext {
    public static TerminalContext terminalApplication;
    protected static Scanner scanner = new Scanner(System.in);
    private Thread thread;

    public TerminalContext(Thread thread) {
        this.thread = thread;
    }

    public TerminalContext() {
    }

    /**
     * @param resolver 将你的解析器添加至解析器责任链中
     */
    public TerminalContext(Thread thread, ResolverBean resolver) {
        this.thread = thread;
    }

    public static TerminalContext run() throws Exception {
        if (TerminalContext.terminalApplication == null) {
            terminalApplication = new TerminalContext();
        } else {
            InfoPrintExecute.errorPrint(new TerminalNotExitException("Terminal Not Exit!"));
            return terminalApplication;
        }
        terminalApplication.resolvers = new Resolvers(new ResolverBean[]{new MethodsResolve(), new ArgsResolve()});
        terminalApplication.thread = new Thread(() -> {
            String command;
            do {
                command = null;
                System.out.print("> ");
                command = scanner.nextLine();
                Iterator<ResolverBean> iterator = terminalApplication.resolvers.iterator();
                ResolveResult resolveResult = null;
                while (iterator.hasNext()) {
                    ResolverBean abstractResolver = iterator.next();
                    Class<?> resolveClass = abstractResolver.getResolveClass();
                    Method method = null;
                    try {
                        if (resolveResult != null) {
                            method = resolveClass.getMethod("analyze", String.class,ResolveResult.class);
                            resolveResult = (ResolveResult) method.invoke(abstractResolver, command, resolveResult);
                        } else {
                            method = resolveClass.getMethod("analyze", String.class);
                            resolveResult = (ResolveResult) method.invoke(abstractResolver, command);
                        }
                    } catch (NoSuchMethodException | IllegalAccessException e) {
                        InfoPrintExecute.errorPrint(new TerminalReflectException("analyze() Happen Exception!"));
                        System.exit(-1);
                    } catch (InvocationTargetException invocationTargetException) {
                        InfoPrintExecute.errorPrint(new ParameterParsingException("Not Parameter Parsing"));
                    }
                }
                System.out.println(resolveResult.toString());
            } while (!"/exit".equalsIgnoreCase(command));
        });
        terminalApplication.thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e);
        });
        InfoPrintExecute.TID = String.valueOf(terminalApplication.thread.getId());
        terminalApplication.thread.start();
        return terminalApplication;
    }
}
