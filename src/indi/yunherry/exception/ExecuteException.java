package indi.yunherry.exception;

/**
 * @author YunHerry
 * 执行器执行命令时命令内部出现的问题,将会触发
 */
public class ExecuteException extends RuntimeException {
    public ExecuteException(Throwable exception) {
        super(exception);
    }
    public ExecuteException(String message) {
        super(message);
    }
}
