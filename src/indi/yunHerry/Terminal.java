package indi.yunHerry;

import indi.yunHerry.log.InfoPrintExecute;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class Terminal {
    protected static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Terminal.run();
    }
    public static void run() {
       String command;
        do {
          command = scanner.nextLine();
          InfoPrintExecute.InfoPrint(command);
        } while (!"exit".equalsIgnoreCase(command));
    }
}
