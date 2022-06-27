package indi.yunHerry;

import com.sun.source.tree.WhileLoopTree;
import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;
import indi.yunHerry.resolve.ArgsResolveImpl;
import indi.yunHerry.resolve.MethodsResolveImpl;
import indi.yunHerry.resolve.Resolve;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class Terminal {
    protected static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParameterParsingException {
        Terminal.run();
    }

    public static void run() throws ParameterParsingException {
        String command;
        do {
            System.out.print("> ");
            command = scanner.nextLine();
            System.out.println("正在执行的方法名称: " + new MethodsResolveImpl().analyze(command));
            System.out.println("正在执行的方法的参数信息: " + new ArgsResolveImpl().analyze(command));
        } while (!"exit".equalsIgnoreCase(command));
    }
}
