package indi.yunherry.execution;

import indi.yunherry.exception.CommandConflictException;
import indi.yunherry.exception.ExecuteException;
import indi.yunherry.factory.bean.Command;
import indi.yunherry.model.dto.ResolveResult;
import indi.yunherry.model.dto.TerminalContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

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
                    if (trueCommand != null) {
                        throw new CommandConflictException("command size > 1!");
                    } else {
                        trueCommand = command;
                    }
                }
            }
            assert trueCommand != null;
            ArrayList<String> args = new ArrayList<>();
            for (String key : trueCommand.getArgsList()) {
                Map<String, String> defaultArgs = trueCommand.getDefaultArgs();
                Map<String, String> resolveArgs = resolveResult.getMethodArgs();
                if (resolveArgs.get(key).isBlank()) {
                    args.add(resolveArgs.get(key));
                } else {
                    args.add(defaultArgs.get(key));
                }
            }
            trueCommand.getMethod().invoke(TerminalContext.terminalContext.getBeans().get(trueCommand.getClassName()), args.toArray());
        } catch (InvocationTargetException e) {
            throw new ExecuteException(e.getTargetException());
        }
    }
}
