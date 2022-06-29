package indi.yunherry.exception;

/**
 * @author YunHerry
 * 执行器无法执行该命令
 */
public class CommandExecutionException extends RuntimeException {
    public CommandExecutionException(String message) {
        super(message);
    }
}
