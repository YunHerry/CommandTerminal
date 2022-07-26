package indi.yunherry.configloader;

import indi.yunherry.exception.ConfigNotFoundException;
import indi.yunherry.log.InfoPrintExecute;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author YunHerry
 */
public class ConfigLoader {
    private static HashMap<String, Object> properties = new HashMap<>();

    /**
     * 用于加载配置文件
     */
    public static void load(Class<?> clazz) {
        URL resourceUrl = clazz.getClassLoader().getResource("terminal.yaml");
        String path = null;
        if (resourceUrl == null) {
            InfoPrintExecute.warnPrint(ConfigNotFoundException.class);
        } else {
            path = resourceUrl.getPath();
            try (InputStream inputStream = new FileInputStream(path)) {
                System.out.println(inputStream);
                properties = new Yaml().loadAs(inputStream, HashMap.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
