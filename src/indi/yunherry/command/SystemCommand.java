package indi.yunherry.command;

import indi.yunherry.annotation.Command;
import indi.yunherry.annotation.DefaultValue;
import indi.yunherry.model.dto.ResolveResult;

/**
 * @author YunHerry
 */
@Command
public class SystemCommand {
    public void exit() {
        System.exit(0);
    }
    public void help() {

    }
}
