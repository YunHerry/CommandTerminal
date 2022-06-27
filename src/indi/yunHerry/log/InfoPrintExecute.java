package indi.yunHerry.log;

import indi.yunHerry.constant.StringColor;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YunHerry
 * 信息打印类
 */
public class InfoPrintExecute {
    protected static PrintStream out = System.out;
    protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     *
     * */
    public static void InfoPrint(String message) {
         out.println(sdf.format(new Date()) + StringColor.GREEN + " [INFO] " + StringColor.WHITE + message);
    }
    public static void WarnPrint(String message) {
        out.println(sdf.format(new Date()) + StringColor.YELLOW + " [WARN] " + StringColor.WHITE + message);
    }
    public static void ErrorPrint(Exception exception) {
        out.println(sdf.format(new Date()) + StringColor.RED + " [ERROR] " + exception.toString() + StringColor.WHITE);
    }
}
