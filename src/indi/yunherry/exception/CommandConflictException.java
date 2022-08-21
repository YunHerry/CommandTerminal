package indi.yunherry.exception;

/**
 * @author YunHerry
 * 命令冲突,往往出现在两个命令的属性名称相同并且名称相同的情况下
 */
public class CommandConflictException extends RuntimeException {
    public CommandConflictException(String message) {
        super(message);
    }
}
