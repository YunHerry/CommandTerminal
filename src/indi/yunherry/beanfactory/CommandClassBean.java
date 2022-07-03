package indi.yunherry.beanfactory;

import indi.yunherry.annotation.Factory;
import indi.yunherry.factory.bean.Bean;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author YunHerry
 */
public class CommandClassBean extends Bean {
    private Map<String,Method> methods;
    private Map<String,?> defaultArgs;
}
