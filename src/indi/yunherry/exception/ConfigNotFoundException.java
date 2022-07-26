package indi.yunherry.exception;

/**
 * @author YunHerry
 */
public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException() {
        super();
    }
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
