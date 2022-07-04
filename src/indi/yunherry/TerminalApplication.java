package indi.yunherry;


import com.sun.jdi.InterfaceType;
import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.Execute;
import indi.yunherry.annotation.Factory;
import indi.yunherry.annotation.Resolve;
import indi.yunherry.factory.bean.Engine;
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
        List<indi.yunherry.factory.factory.Factory> factory = new ArrayList<>();
        for (Class clazz : classList) {
            Object obj;
           try {
               System.out.println(clazz.getName());
               obj  = clazz.getDeclaredConstructor().newInstance();
           } catch (InstantiationException | NoSuchMethodException exception) {
               continue;
           }
            if(ClassUntil.isSuitableClass(Command.class,clazz, indi.yunherry.command.Command.class)) {
                TerminalContext.terminalContext.commands.add((indi.yunherry.command.Command) obj);
            } else if (ClassUntil.isSuitableClass(Factory.class,clazz, indi.yunherry.factory.factory.Factory.class)) {
                factory.add((indi.yunherry.factory.factory.Factory) obj);
            } else if (ClassUntil.isSuitableClass(Resolve.class,clazz, indi.yunherry.factory.bean.Resolve.class)) {
                TerminalContext.terminalContext.resolvers.add((indi.yunherry.factory.bean.Resolve) obj);
            } else if (ClassUntil.isSuitableClass(Execute.class,clazz, indi.yunherry.factory.bean.Execute.class)) {
                TerminalContext.terminalContext.executes.add((indi.yunherry.factory.bean.Execute) obj);
            }
        }
        System.out.println(TerminalContext.terminalContext.resolvers);
        return TerminalContext.run();
    }
}
