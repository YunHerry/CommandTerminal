package indi.yunherry.log;

import indi.yunherry.constant.StringColor;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YunHerry
 * 信息打印类
 */
public class InfoPrintExecute {
    public static String TID;
    protected static PrintStream out = System.out;
    protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     *
     * */
    public static void infoPrint(String message) {
         out.println(sdf.format(new Date()) + StringColor.GREEN + " INFO " + StringColor.PURPLE + TID + StringColor.WHITE + " --- [ Terminal ] : " + StringColor.WHITE + message);
    }
    public static void warnPrint(String message) {
        out.println(sdf.format(new Date()) + StringColor.YELLOW + " WARN " +  StringColor.PURPLE + TID + StringColor.WHITE + " --- [ Terminal ] : " +  StringColor.YELLOW + message + StringColor.WHITE);
    }
    public static void errorPrint(Exception exception) {
        out.println(sdf.format(new Date()) + StringColor.RED + " ERROR " +  StringColor.PURPLE + TID + StringColor.WHITE + " --- [ Terminal ] : "  +  StringColor.RED + exception.toString() + StringColor.WHITE);
    }
}
