package indi.yunherry.configloader;

import indi.yunherry.exception.CommandConflictException;
import indi.yunherry.exception.ConfigNotFoundException;
import indi.yunherry.log.InfoPrintExecute;
import indi.yunherry.model.dto.Customer;
import indi.yunherry.model.dto.Terminal;
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
 * 加载配置文件,对配置进行填充
 */
public class ConfigLoader {
    /**
     * 用于加载配置文件
     */
    public static void load(Class<?> clazz) {
        TerminalContext.terminalContext.terminalApplicationConfig = TerminalApplicationConfig.initTerminalApplicationConfig();
        URL resourceUrl = clazz.getClassLoader().getResource("terminal.yaml");
        String path = null;
        if (resourceUrl == null) {
            InfoPrintExecute.warnPrint(ConfigNotFoundException.class);
        } else {
            path = resourceUrl.getPath();
            try (InputStream inputStream = new FileInputStream(path)) {
                Yaml yaml = new Yaml();
                Customer customer = yaml.loadAs(inputStream, Customer.class);
                TerminalContext.terminalContext.terminalApplicationConfig.terminal = customer.terminal;
            } catch (IOException e) {
                throw new CommandConflictException(e.getMessage());
            } catch (ClassCastException classCastException) {
                InfoPrintExecute.warnPrint("load Config happen error");
            } catch (NullPointerException nullPointerException) {
                nullPointerException.printStackTrace();
            }
        }
    }
}
