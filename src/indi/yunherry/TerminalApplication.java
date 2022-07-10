package indi.yunherry;


import indi.yunherry.factory.factory.Factory;
import indi.yunherry.model.dto.TerminalContext;

/**
 * @author YunHerry
 * 启动类&测试类
 */
public class TerminalApplication {
    public static TerminalContext run(Class<?> clazz) throws Exception {
        Factory.initFactory(clazz);
        return TerminalContext.run();
    }
}
