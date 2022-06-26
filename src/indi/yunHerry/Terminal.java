package indi.yunHerry;

import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;

import java.io.InputStream;
import java.util.Scanner;
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
            System.out.println(Pattern.matches("^/[a-zA-Z0-9]{1,}+", command));
            if (!Pattern.matches("^/[a-zA-Z0-9]{1,}+", command)) {
                throw new ParameterParsingException("无法解析该命令");
            }
            InfoPrintExecute.InfoPrint(command);
        } while (!"exit".equalsIgnoreCase(command));
    }
}
