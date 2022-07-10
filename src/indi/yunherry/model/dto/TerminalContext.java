package indi.yunherry.model.dto;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.exception.TerminalReflectException;
import indi.yunherry.factory.bean.Command;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.factory.bean.Resolve;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.model.run.TerminalThread;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalContext {
    public static TerminalContext terminalContext = new TerminalContext();

    public static String hostName;
    private Thread thread;
    public final ArrayList<Resolve> resolvers = new ArrayList<>();
    public final ArrayList<Execute> executes = new ArrayList<>();
    public final ArrayList<Command> commands = new ArrayList<>();
    private final HashMap<String,Object> beans = new HashMap<>();
    public TerminalContext() {
    }
    public TerminalContext(Thread thread) {
        this.thread = thread;
    }
    /**
     * @param resolver 将你的解析器添加至解析器责任链中
     */
    public TerminalContext(Thread thread, Resolve ... resolver) {
        this.thread = thread;
        resolvers.addAll(List.of(resolver));
    }

    public static TerminalContext run() throws Exception {
        hostName = InetAddress.getLocalHost().getHostName();
        terminalContext.thread = new Thread(new TerminalThread());
        InfoPrintExecute.TID = String.valueOf(terminalContext.thread.getId());
        terminalContext.thread.start();
        return terminalContext;
    }
    public HashMap<String, Object> getBeans() {
        return beans;
    }
    public void addBean(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        beans.put(clazz.getName(),clazz.getDeclaredConstructor().newInstance());
    }
}
