package indi.yunherry.configloader;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author YunHerry
 */
public class ConfigLoader {
    private static HashMap<String,Object> properties = new HashMap<>();
    /**
     * 用于加载配置文件
     * */
    public static void load(Class<?> clazz) throws IOException {
        new Yaml();
         try(InputStream inputStream = new FileInputStream(Objects.requireNonNull(clazz.getClassLoader().getResource("terminal.yaml")).getPath())) {
             System.out.println(inputStream);
             properties = new Yaml().loadAs(inputStream, HashMap.class);
         } catch (Exception e) {
             e.printStackTrace();
         }
        System.out.println(properties.get("isLogin"));
    }
}
