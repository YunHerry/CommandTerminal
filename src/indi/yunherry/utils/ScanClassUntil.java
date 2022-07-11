package indi.yunherry.utils;

import indi.yunherry.annotation.Command;

import java.lang.annotation.Annotation;

/**
 * @author YunHerry
 */
public class ScanClassUntil {
    public static <A extends Annotation> boolean isSuitableClass(Class<A> suitableAnnotation, Class<?> clazz, Class<?> fatherClass) {
        if (clazz == fatherClass) {
            return false;
        }
        return isSuitableClass(clazz,fatherClass) || isSuitableAnnotation(suitableAnnotation,clazz);
    }
    public static boolean isSuitableClass(Class<?> clazz, Class<?> fatherClass) {
        if (clazz == fatherClass) {
            return false;
        }
        return fatherClass != null && fatherClass.isAssignableFrom(clazz);
    }
    public static <A extends Annotation>  boolean isSuitableAnnotation(Class<A> suitableAnnotation, Class<?> clazz) {
        return clazz.getAnnotation(suitableAnnotation) != null;
    }
}
