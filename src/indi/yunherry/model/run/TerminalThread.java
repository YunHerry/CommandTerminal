package indi.yunherry.model.run;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.exception.TerminalReflectException;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.factory.bean.Resolve;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.model.dto.ResolveResult;
import indi.yunherry.model.dto.TerminalContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 */
public class TerminalThread implements Runnable {
    private final Pattern nullScanner = Pattern.compile("^[\\w\\u4e00-\\u9fa5]+");

    @Override
    public void run() {
        String command;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(TerminalContext.hostName + " > ");
            command = scanner.nextLine();
            Matcher matcher = nullScanner.matcher(command);
            if (matcher.find()) {
                System.out.println(matcher.group());
                continue;
            }
            Iterator<Resolve> iterator = TerminalContext.terminalContext.resolvers.iterator();
            ResolveResult resolveResult = null;
            while (iterator.hasNext()) {
                Resolve abstractResolver = iterator.next();
                Class<?> resolveClass = abstractResolver.getResolveClass();
                Method method;
                try {
                    if (resolveResult != null) {
                        method = resolveClass.getMethod("analyze", String.class, ResolveResult.class);
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
            for (Execute execute : TerminalContext.terminalContext.executes) {
                Class<?> executeClass = execute.getClass();
                Method method = null;
                try {
                    method = executeClass.getMethod("executeCommand", ResolveResult.class);
                    resolveResult = (ResolveResult) method.invoke(execute, resolveResult);
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    InfoPrintExecute.errorPrint(new TerminalReflectException("Methods Happen Exception!"));
                    System.exit(-1);
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.getTargetException().printStackTrace();
                    System.out.println("\n");
                }
            }
        } while (true);
    }
}
