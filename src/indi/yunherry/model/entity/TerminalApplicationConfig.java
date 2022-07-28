package indi.yunherry.model.entity;

import indi.yunherry.TerminalApplication;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.model.dto.Terminal;

/**
 * @author YunHerry
 */

public class TerminalApplicationConfig {
    private static TerminalApplicationConfig terminalApplicationConfig;
    public Terminal terminal;
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
