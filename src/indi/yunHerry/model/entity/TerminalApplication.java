package indi.yunHerry.model.entity;

/**
 * @author YunHerry
 * 基本参数对象,能够对动态的更改拦截器
 */
public class TerminalApplication {
    private Thread thread;
    public TerminalApplication(Thread thread) {
        this.thread = thread;
    }
}
