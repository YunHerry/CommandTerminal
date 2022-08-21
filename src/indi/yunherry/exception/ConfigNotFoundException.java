package indi.yunherry.exception;

/**
 * @author YunHerry
 * 配置文件没有被找到错误
 */
public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException() {
        super();
    }
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
