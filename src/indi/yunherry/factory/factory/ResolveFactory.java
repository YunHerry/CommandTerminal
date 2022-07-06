package indi.yunherry.factory.factory;

import indi.yunherry.annotation.Command;
import indi.yunherry.factory.bean.Resolve;

import indi.yunherry.factory.factory.Factory;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.ClassUntil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author YunHerry
 * 解析器工厂
 */
@indi.yunherry.annotation.Factory
public class ResolveFactory extends Factory {
    /**
     * 创建解析器Bean
     *
     * @return ResolverBean 返回解析器Bean对象
     */
    @Override
    protected void create(ArrayList<Class> classes) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Class> resolvers = new ArrayList<>();
        finds(classes,resolvers, indi.yunherry.annotation.Resolve.class,Resolve.class);
        for (Class clazz: resolvers) {
            TerminalContext.terminalContext.resolvers.add((Resolve) clazz.getDeclaredConstructor().newInstance());
        }
    }
}
