package indi.yunherry.configloader;

import indi.yunherry.exception.ConfigNotFoundException;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.model.entity.TerminalApplicationConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author YunHerry
 */
public class ConfigLoader {
    private static HashMap properties = new HashMap<>();

    /**
     * 用于加载配置文件
     */
    public static void load(Class<?> clazz) {
        TerminalApplicationConfig terminalApplicationConfig = TerminalContext.terminalContext.terminalApplicationConfig = TerminalApplicationConfig.initTerminalApplicationConfig();
        URL resourceUrl = clazz.getClassLoader().getResource("terminal.yaml");
        String path = null;
        if (resourceUrl == null) {
            InfoPrintExecute.warnPrint(ConfigNotFoundException.class);
        } else {
            path = resourceUrl.getPath();
            System.out.println(path);
            try (InputStream inputStream = new FileInputStream(path)) {
                Yaml yaml = new Yaml();
                properties = yaml.loadAs(inputStream, HashMap.class);
                Field[] fields = terminalApplicationConfig.getClass().getFields();
                for (Field field :fields) {
                    Object value = properties.get(field.getName());
                    if (value != null) {
                        field.setBoolean(terminalApplicationConfig,(boolean) value);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException | ClassCastException classCastException) {
                InfoPrintExecute.warnPrint("load Config happen error");
            } catch (NullPointerException nullPointerException) {
                nullPointerException.printStackTrace();
            }
        }
    }
}
