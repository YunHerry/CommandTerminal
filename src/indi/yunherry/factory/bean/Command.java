package indi.yunherry.factory.bean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YunHerry
 */
public class Command extends Engine {
    private String name;
    private Method method;
    private String className;
    private String[] argsList;
    private Map<String, String> defaultArgs = new HashMap<>();

    public Command(String name, String[] argsList) {
        this.name = name;
        this.argsList = argsList;
    }

    public Command(String name, Method method,String className) {
        this.name = name;
        this.method = method;
        this.className = className;
    }

    public String[] getArgsList() {
        return argsList;
    }

    public void setArgsList(String[] argsList) {
        this.argsList = argsList;
    }

    public Map<String, String> getDefaultArgs() {
        return defaultArgs;
    }

    public void setDefaultArgs(String field, String defaultValue) {
        this.defaultArgs.put(field, defaultValue);
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }
}
