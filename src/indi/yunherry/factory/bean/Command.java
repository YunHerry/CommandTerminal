package indi.yunherry.factory.bean;

import java.util.Map;

/**
 * @author YunHerry
 */
public class Command extends Engine {
    private String name;
    private String[] argsList;
    private Map<String,String> defaultArgs;
    public Command(String name, String[] argsList) {
        this.name = name;
        this.argsList = argsList;
    }

}
