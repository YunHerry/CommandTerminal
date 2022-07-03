package indi.yunherry.factory.bean;

import indi.yunherry.factory.bean.Bean;

/**
 * @author YunHerry
 */
public abstract class ExecuteBean extends Bean {
    private CommandBean commandBean;
    public ExecuteBean(CommandBean commandBean) {
        this.commandBean = commandBean;
    }
}
