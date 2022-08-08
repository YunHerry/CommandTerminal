package indi.yunherry.factory.bean;

import indi.yunherry.model.dto.ResolveResult;

import java.lang.reflect.InvocationTargetException;

/**
 * @author YunHerry
 */
public interface Filter {
    /**
     * 过滤方法
     *
     * @return 返回过滤后修改好的数据
     */
    public ResolveResult filter(ResolveResult resolveResult);
}
