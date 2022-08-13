package indi.yunherry;


import indi.yunherry.configloader.ConfigLoader;
import indi.yunherry.factory.factory.Factory;
import indi.yunherry.model.dto.TerminalContext;

/**
 * @author YunHerry
 * 启动类&测试类
 * LaunchClass&TestClass
 */
public class TerminalApplication {
    public static TerminalContext run(Class<?> clazz) throws Exception {
        Factory.initFactory(clazz);
        ConfigLoader.load(clazz);
        return TerminalContext.run();
    }
}
