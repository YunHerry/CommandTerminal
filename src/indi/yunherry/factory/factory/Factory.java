package indi.yunherry.factory.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author h3209
 */
public abstract class Factory {
    /**
     * 获取工厂具体实现类
     * @return Factory 返回具体实现的工厂对象
     * */
    public static Factory getFactory(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Factory factory = null;
        Class<?> clazz = Class.forName(className);
        factory = (Factory) clazz.getDeclaredConstructor().newInstance();
        return factory;
    }
}
