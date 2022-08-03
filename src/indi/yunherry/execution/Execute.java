package indi.yunherry.execution;

import indi.yunherry.exception.CommandConflictException;
import indi.yunherry.exception.CommandExecutionException;
import indi.yunherry.exception.ExecuteException;
import indi.yunherry.factory.bean.Command;
import indi.yunherry.factory.bean.Filter;
import indi.yunherry.model.dto.ResolveResult;
import indi.yunherry.model.dto.TerminalContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author YunHerry
 */
@indi.yunherry.annotation.Execute
public class Execute extends indi.yunherry.factory.bean.Execute {
    public void executeCommand(ResolveResult resolveResult) throws IllegalAccessException {
        try {
            Iterator<Command> commandIterator = TerminalContext.terminalContext.commands.iterator();
            String methodName = resolveResult.getMethodName();
            ArrayList<Command> trueCommand = new ArrayList<>();
            while (commandIterator.hasNext()) {
                Command command = commandIterator.next();
                if (command.getName().equals(methodName) && command.getArgsList().length == resolveResult.getMethodArgs().size()) {
                    int size = 0;
//                    System.out.println(resolveResult.getTypeArgs().get("b").size());
                    //重载方法: String Boolean | String Integer
                    for (Map.Entry<String, List<Class<?>>> entry : resolveResult.getTypeArgs().entrySet()) {
                        for (Parameter parameter : command.getMethod().getParameters()) {
//                            System.out.println("parameter.getName(): " + parameter.getName());
//                            System.out.println("entry.getKey(): " + entry.getKey());
                            if (entry.getKey().equals(parameter.getName())) {
//                                System.out.println("属性名称正确");
                                for (Class<?> clazz : entry.getValue()) {
                                    parameter.getType();
                                    if (clazz == parameter.getType()) {
//                                        System.out.println("属性类型正确");
                                        size++;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (size == command.getArgsList().length) {
                        trueCommand.add(command);
                    }
                }
            }
//            for (Command command : trueCommand) {
//                System.out.println(command.getArgsList());
//            }
//            assert trueCommand.size() > 1;
            if(trueCommand.size() > 2) {
                throw new ExecuteException("Method size > 2!");
            }
            for (Command command : trueCommand) {
                ArrayList<Object> args = new ArrayList<>();
                for (String key : command.getArgsList()) {
                    Map<String, Object> defaultArgs = command.getDefaultArgs();
                    Map<String, Object> resolveArgs = resolveResult.getMethodArgs();
                    if (resolveArgs.get(key) != null) {
                        args.add(resolveArgs.get(key));
                    } else {
                        args.add(defaultArgs.get(key));
                    }
                }
                command.getMethod().invoke(TerminalContext.terminalContext.getBeans().get(command.getClassName()), args.toArray());
                break;
            }
        } catch (Exception e) {
            throw new ExecuteException(e);
        }
    }
}
