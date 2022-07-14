package indi.yunherry.command;

import indi.yunherry.annotation.Command;

/**
 * @author YunHerry
 */
@Command
public class SystemCommand implements indi.yunherry.command.Command {
    public void exit() {
        System.exit(0);
    }
    public void version() {
        System.out.println("this the Informal version: V1.0.0");
    }
    public void authorInfo() {
        System.out.println("author: Yun\nGithubID: YunHerry");
    }
}
