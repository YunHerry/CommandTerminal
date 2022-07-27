package indi.yunherry.model.entity;

import indi.yunherry.TerminalApplication;
import indi.yunherry.log.InfoPrintExecute;

/**
 * @author YunHerry
 */

public class TerminalApplicationConfig {
    private static TerminalApplicationConfig terminalApplicationConfig;
    public boolean isUseHostIpName = false;
    public boolean isUseHostName = false;
    private TerminalApplicationConfig() {
       InfoPrintExecute.infoPrint("create TerminalApplicationConfig bean");
    };
    public static TerminalApplicationConfig initTerminalApplicationConfig() {
        if (terminalApplicationConfig == null) {
            terminalApplicationConfig = new TerminalApplicationConfig();

        }
        return terminalApplicationConfig;
    }
}
