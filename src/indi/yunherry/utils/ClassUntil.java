package indi.yunherry.utils;

import indi.yunherry.annotation.Command;

import java.lang.annotation.Annotation;

/**
 * @author YunHerry
 */
public class ClassUntil {
    public static <A extends Annotation> boolean isSuitableClass(Class<A> suitableAnnotation, Class clazz, Class fatherClass) {
        if (clazz == fatherClass) {
            return true;
        }
        return (fatherClass == null ? false : fatherClass.isAssignableFrom(clazz)) || clazz.getAnnotation(suitableAnnotation) != null;
    }
}
