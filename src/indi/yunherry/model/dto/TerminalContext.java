package indi.yunherry.model.dto;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.exception.TerminalReflectException;
import indi.yunherry.factory.bean.Command;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.factory.bean.Resolve;
import indi.yunherry.log.InfoPrintExecute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalContext {
    public static TerminalContext terminalContext = new TerminalContext();
    protected static Scanner scanner = new Scanner(System.in);
    private Thread thread;
    public final ArrayList<Resolve> resolvers = new ArrayList<>();
    public final ArrayList<Execute> executes = new ArrayList<>();
    public final ArrayList<Command> commands = new ArrayList<>();
    private final HashMap<String,Object> beans = new HashMap<>();
    public TerminalContext(Thread thread) {
        this.thread = thread;
    }

    public TerminalContext() {
    }

    /**
     * @param resolver 将你的解析器添加至解析器责任链中
     */
    public TerminalContext(Thread thread, Resolve resolver) {
        this.thread = thread;
    }

    public static TerminalContext run() throws Exception {
//        if (TerminalContext.terminalContext == null) {
//            terminalContext = new TerminalContext();
//        } else {
//            InfoPrintExecute.errorPrint(new TerminalNotExitException("Terminal Not Exit!"));
//            return terminalContext;
//        }

        terminalContext.thread = new Thread(() -> {
            String command;
            do {
                command = null;
                System.out.print("> ");
                command = scanner.nextLine();
                Iterator<Resolve> iterator = terminalContext.resolvers.iterator();
                ResolveResult resolveResult = null;
                while (iterator.hasNext()) {
                    Resolve abstractResolver = iterator.next();
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
//        terminalApplication.thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
//            System.out.println(t.getName() + ": " + e);
//        });
        InfoPrintExecute.TID = String.valueOf(terminalContext.thread.getId());
        terminalContext.thread.start();
        return terminalContext;
    }
    public HashMap<String, Object> getBeans() {
        return beans;
    }
    public void addBean(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        beans.put(clazz.getName(),clazz.getDeclaredConstructor().newInstance());
    }
}
