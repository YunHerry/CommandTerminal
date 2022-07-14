package indi.yunherry.factory.factory;

import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.DefaultValue;
import indi.yunherry.annotation.MethodName;
import indi.yunherry.annotation.ScanCommand;
import indi.yunherry.constant.enums.ScanTypeEnum;
import indi.yunherry.model.dto.TerminalContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

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
    protected void create(ArrayList<Class<?>> beans) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Class<?>> commands = new ArrayList<>();
        ScanTypeEnum scanTypeEnum = ScanTypeEnum.SCAN_ALL;
        ScanCommand annotation = TerminalContext.mainClass.getAnnotation(ScanCommand.class);
        if (annotation != null) {
            scanTypeEnum = annotation.model();
        }
        finds(beans, commands, Command.class, indi.yunherry.command.Command.class, scanTypeEnum);
        for (Class<?> clazz : commands) {
            for (Method method : clazz.getDeclaredMethods()) {
                String[] prams = new String[method.getParameters().length];
                int i = 0;
                String methodName = null;
                indi.yunherry.factory.bean.Command command;
                MethodName[] methodAnnotationClass = method.getDeclaredAnnotationsByType(MethodName.class);
                if (methodAnnotationClass.length != 0 && (methodName = methodAnnotationClass[0].methodName()) != null) {
                    command = new indi.yunherry.factory.bean.Command(methodName, method,clazz.getName());
                } else {
                    command = new indi.yunherry.factory.bean.Command(method.getName(), method,clazz.getName());
                }
                for (Parameter pram : method.getParameters()) {
                    DefaultValue[] defaultValues = pram.getDeclaredAnnotationsByType(DefaultValue.class);
                    for (DefaultValue defaultValue : defaultValues) {
                        //Java8 没有isBlack() 请勿"    "写法 当然,你要写也行,我不反对(
                        if (defaultValue != null && String.valueOf(defaultValue.defaultValue()).isBlank()) {
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
