package indi.yunherry.factory.factory;

import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.DefaultValue;
import indi.yunherry.factory.bean.Engine;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.ClassUntil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
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
            for (Method method : clazz.getDeclaredMethods()) {
                String[] prams = new String[method.getParameters().length];
                int i = 0;
                indi.yunherry.factory.bean.Command command = new indi.yunherry.factory.bean.Command(method.getName(), method,clazz.getName());
                for (Parameter pram : method.getParameters()) {
                    DefaultValue[] defaultValues = pram.getDeclaredAnnotationsByType(DefaultValue.class);
                    for (DefaultValue defaultValue : defaultValues) {
                        if (defaultValue != null && defaultValue.defaultValue().isBlank()) {
                            command.setDefaultArgs(pram.getName(), defaultValue.defaultValue());
                        } else {
                            command.setDefaultArgs(pram.getName(), "");
                        }

                    }
                    prams[i] = pram.getName();
                    i++;
                }
                command.setArgsList(prams);
                TerminalContext.terminalContext.commands.add(command);
            }
            TerminalContext.terminalContext.addBean(clazz);
        }
    }


}
