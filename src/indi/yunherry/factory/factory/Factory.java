package indi.yunherry.factory.factory;

import indi.yunherry.TerminalApplication;
import indi.yunherry.constant.enums.ScanTypeEnum;
import indi.yunherry.exception.InitFactoryException;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.FindClassUtil;
import indi.yunherry.utils.ScanClassUntil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author YunHerry
 */
public abstract class Factory {
    protected static ArrayList<Class<?>> classArrayList;

    public static void initFactory(Class<?> urlClazz) {
        ArrayList<Class<?>> classList;
        try {
            TerminalContext.mainClass = urlClazz;
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
            throw new InitFactoryException(e);
        }
    }

    /**
     * 获取工厂具体实现类
     *
     * @return Factory 返回具体实现的工厂对象
     */
    private static ArrayList<Factory> getFactory() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Iterator<Class<?>> iterator = classArrayList.iterator();
        ArrayList<Factory> factoryArrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            if (ScanClassUntil.isSuitableClass(indi.yunherry.annotation.Factory.class, clazz, Factory.class)) {
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

    /**
     * @param model 1 只扫描注解,2只扫描继承类 3全部扫描
     */
    public <A extends Annotation> void finds(ArrayList<Class<?>> classes, ArrayList<Class<?>> trueList, Class<A> annotation, Class<?> fatherClass, ScanTypeEnum model) {
        Iterator<Class<?>> iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            if (model == ScanTypeEnum.SCAN_ONLY_ANNOTATION || model == ScanTypeEnum.SCAN_ONLY_FATHER_CLASS ? model == ScanTypeEnum.SCAN_ONLY_ANNOTATION ? ScanClassUntil.isSuitableAnnotation(annotation, clazz) : ScanClassUntil.isSuitableClass(clazz, fatherClass) : ScanClassUntil.isSuitableClass(annotation, clazz, fatherClass)) {
                trueList.add(clazz);
                iterator.remove();
            }
        }
    }
}
