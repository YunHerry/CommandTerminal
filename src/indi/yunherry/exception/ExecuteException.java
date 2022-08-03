package indi.yunherry.exception;

/**
 * @author YunHerry
 */
public class ExecuteException extends RuntimeException {
    public ExecuteException(Throwable exception) {
        super(exception);
    }
    public ExecuteException(String message) {
        super(message);
    }
}
