package indi.yunherry.command;

import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.DefaultValue;

/**
 * @author YunHerry
 */
@Command
public class SystemCommand implements indi.yunherry.command.Command {

    public void test(@DefaultValue(defaultValue = "test") String namets, String s) {

    }
}
