package indi.yunherry.exception;
/**
 * @author YunHerry
 * 终端在为解析器进行向下转型时发生了意外
 * */
public class TerminalReflectException extends RuntimeException {
    public TerminalReflectException(String message) {
        super(message);
    }
}
