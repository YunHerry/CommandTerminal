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
            Command trueCommand = null;
            while (commandIterator.hasNext()) {
                Command command = commandIterator.next();
                if (command.getName().equals(methodName) && command.getArgsList().length == resolveResult.getMethodArgs().size()) {
                    int size = 0;
                    for (Map.Entry<String, Object> entry : resolveResult.getMethodArgs().entrySet()) {
                        for (Parameter parameter : command.getMethod().getParameters()) {
                            if (entry.getKey().equals(parameter.getName())) {
                                size++;
                            }
                        }
                    }
                    if (size == command.getArgsList().length) {
                        if (trueCommand == null) {
                            trueCommand = command;
                            break;
                        } else {
                            throw new CommandExecutionException("true command size >= 2!");
                        }
                    }
                }
            }
                ArrayList<Object> args = new ArrayList<>();
            assert trueCommand != null;
            for (String key : trueCommand.getArgsList()) {
                    Map<String, Object> defaultArgs = trueCommand.getDefaultArgs();
                    Map<String, Object> resolveArgs = resolveResult.getMethodArgs();
                    if (resolveArgs.get(key) != null) {
                        Object resolveArgValue = resolveArgs.get(key);
                        args.add(trueCommand.getTypeArg(key).getDeclaredConstructor(resolveArgValue.getClass()).newInstance(resolveArgValue));
                    } else {
                        Object defaultArgValue = defaultArgs.get(key);
                        args.add(trueCommand.getTypeArg(key).getDeclaredConstructor(defaultArgValue.getClass()).newInstance(defaultArgValue));
                    }
                }
                trueCommand.getMethod().invoke(TerminalContext.terminalContext.getBeans().get(trueCommand.getClassName()), args.toArray());
        } catch (Exception e) {
            throw new ExecuteException(e);
        }
    }
}
