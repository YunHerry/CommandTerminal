package indi.yunherry.factory.factory;

import indi.yunherry.annotation.Command;
import indi.yunherry.factory.bean.Engine;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.ClassUntil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author YunHerry
 * 抽象工厂
 */
public class CommandFactory extends Factory {
    /**
     * 创建命令Bean
     *
     * @return CommandBean 返回CommandBean对象
     */
    @Override
    protected void create(ArrayList<Class> beans) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ArrayList<Class> commands = new ArrayList<>();
        finds(beans, commands, Command.class, indi.yunherry.command.Command.class);
        for (Class clazz : commands) {
            System.out.println(clazz.getName());
            for (Method method: clazz.getDeclaredMethods()) {
                String[] prams = new String[method.getParameters().length];
                int i = 0;
                for (Parameter pram: method.getParameters()) {
                     prams[i] = pram.getName();
                     i++;
                }
                TerminalContext.terminalContext.commands.add(new indi.yunherry.factory.bean.Command(method.getName(),prams));
            }
        }
    }


}
