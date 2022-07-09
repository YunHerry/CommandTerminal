package indi.yunherry.model.dto;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.exception.TerminalReflectException;
import indi.yunherry.factory.bean.Command;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.factory.bean.Resolve;
import indi.yunherry.log.InfoPrintExecute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalContext {
    public static TerminalContext terminalContext = new TerminalContext();
    protected static Scanner scanner = new Scanner(System.in);
    protected static Pattern nullScanner = Pattern.compile("^[\\w\\u4e00-\\u9fa5]+");
    public static String hostName;
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
        hostName = InetAddress.getLocalHost().getHostName();
        terminalContext.thread = new Thread(() -> {
            String command;
            do {
                command = null;
                System.out.print(hostName + " > ");
                command = scanner.nextLine();
                Matcher matcher = nullScanner.matcher(command);
                if (matcher.find()) {
                    System.out.println( matcher.group());
                    continue;
                }
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
                Iterator<Execute> executeIterator = terminalContext.executes.iterator();
                while (executeIterator.hasNext()) {
                    Execute execute = executeIterator.next();
                    Class<?> executeClass = execute.getClass();
                    Method method = null;
                    try {
                            method = executeClass.getMethod("executeCommand", ResolveResult.class);
                            resolveResult = (ResolveResult) method.invoke(execute,resolveResult);
                    } catch (NoSuchMethodException | IllegalAccessException e) {
                        InfoPrintExecute.errorPrint(new TerminalReflectException("Methods Happen Exception!"));
                        System.exit(-1);
                    } catch (InvocationTargetException invocationTargetException) {
                        InfoPrintExecute.errorPrint(new ParameterParsingException("Not Parameter Parsing"));
                    }
                }
            } while (true);
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
