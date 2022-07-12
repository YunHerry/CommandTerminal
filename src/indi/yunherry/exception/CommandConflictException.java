package indi.yunherry.exception;

/**
 * @author YunHerry
 */
public class CommandConflictException extends RuntimeException {
    public CommandConflictException(String message) {
        super(message);
    }
}
