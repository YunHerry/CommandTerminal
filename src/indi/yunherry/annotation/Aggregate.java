package indi.yunherry.annotation;

import java.util.Iterator;

/**
 * @author YunHerry
 * 被该类标注的类是一个聚合类
 * The class marked by this class is an aggregate class
 * */
public interface Aggregate {
   public abstract Iterator<?> iterator();
}
