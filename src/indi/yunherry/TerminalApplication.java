package indi.yunherry;


import com.sun.jdi.InterfaceType;
import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.Execute;
import indi.yunherry.annotation.Factory;
import indi.yunherry.annotation.Resolve;
import indi.yunherry.factory.bean.CommandBean;
import indi.yunherry.factory.bean.ExecuteBean;
import indi.yunherry.factory.bean.ResolverBean;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.ClassUntil;
import indi.yunherry.utils.FindClassUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class TerminalApplication {


    public static void main(String[] args) throws Exception {
        TerminalContext terminalContext = TerminalApplication.run();
    }

    public static TerminalContext run() throws Exception {
        List<Class> classList = FindClassUtil.findClasses(TerminalApplication.class);
        List<ResolverBean> resolve = new ArrayList<>();
        List<ExecuteBean> execute = new ArrayList<>();
        List<indi.yunherry.factory.factory.Factory> factory = new ArrayList<>();
        List<CommandBean> command = new ArrayList<>();
        for (Class clazz : classList) {
            if (clazz.isInterface()) {
                break;
            }
            Object obj = clazz.getDeclaredConstructor().newInstance();
            if(ClassUntil.isSuitableClass(Command.class,clazz, CommandBean.class)) {
                command.add((CommandBean) obj);
            } else if (ClassUntil.isSuitableClass(Factory.class,clazz, indi.yunherry.factory.factory.Factory.class)) {
                factory.add((indi.yunherry.factory.factory.Factory) obj);
            } else if (ClassUntil.isSuitableClass(Resolve.class,clazz, ResolverBean.class)) {
                resolve.add((ResolverBean) obj);
            } else if (ClassUntil.isSuitableClass(Execute.class,clazz, ExecuteBean.class)) {
                execute.add((ExecuteBean) obj);
            }
        }
        return TerminalContext.run();
    }
}
