package indi.yunherry.factory.factory;

import indi.yunherry.TerminalApplication;
import indi.yunherry.annotation.Command;
import indi.yunherry.factory.bean.Engine;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.ClassUntil;
import indi.yunherry.utils.FindClassUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author h3209
 */
public abstract class Factory {
    protected static ArrayList<Class<?>> classArrayList;

    public static void initFactory(Class<?> urlClazz) {
        ArrayList<Class<?>> classList;
        try {
            classList = FindClassUtil.findClasses(TerminalApplication.class);
            classList.addAll(FindClassUtil.findClasses(urlClazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Iterator<Class<?>> iterator = classList.iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            try {
                if (clazz.isInterface() || clazz.isAnnotation()) {
                    iterator.remove();
                    continue;
                }
                clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException exception) {
                iterator.remove();
                continue;
            }
        }
        classArrayList = classList;
        try {
            ArrayList<Factory> factoryArrayList = getFactory();
            for (Factory factory : factoryArrayList) {
                factory.create(classArrayList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取工厂具体实现类
     *
     * @return Factory 返回具体实现的工厂对象
     */
    private static ArrayList<Factory> getFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Iterator<Class<?>> iterator = classArrayList.iterator();
        ArrayList<Factory> factoryArrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            if (ClassUntil.isSuitableClass(indi.yunherry.annotation.Factory.class, clazz, Factory.class)) {
                factoryArrayList.add((Factory) clazz.getDeclaredConstructor().newInstance());
                iterator.remove();
            }
        }

        return factoryArrayList;
    }

    /**
     * 该方法是用于工厂创建对应对象
     *
     * @param classes 传入Class对象,来判断是否是对应工厂的菜
     */
    protected abstract void create(ArrayList<Class<?>> classes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    public <A extends Annotation> void finds(ArrayList<Class<?>> classes, ArrayList<Class<?>> trueList, Class<A> annotation, Class<?> fatherClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Iterator<Class<?>> iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class clazz = iterator.next();
            if (ClassUntil.isSuitableClass(annotation, clazz, fatherClass)) {
                trueList.add(clazz);
                iterator.remove();
            }
        }
    }
}
