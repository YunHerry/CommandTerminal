package indi.yunherry;


import indi.yunherry.model.dto.TerminalApplication;
import indi.yunherry.resolve.ArgsResolve;
import indi.yunherry.resolve.MethodsResolve;
import indi.yunherry.utils.StringCollectionUtil;

import java.util.Scanner;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class Terminal {


    public static void main(String[] args) {
       TerminalApplication terminalApplication= Terminal.run();
    }

    public static TerminalApplication run() {
        return TerminalApplication.run();
    }
}
