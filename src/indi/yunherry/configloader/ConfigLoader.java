package indi.yunherry.configloader;

import java.io.InputStream;

/**
 * @author YunHerry
 */
public class ConfigLoader {
    /**
     * 用于加载配置文件
     * */
    public void load(Class<?> clazz) {
         InputStream inputStream = clazz.getClassLoader().getResourceAsStream("../resource/Terminal.yml");
    }
}
