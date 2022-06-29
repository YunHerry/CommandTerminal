package indi.yunherry.exception;

/**
 * @author YunHerry
 * 终端并未退出,并且请求新建终端线程
 */
public class TerminalNotExitException extends RuntimeException {
    public TerminalNotExitException(String message) {
        super(message);
    }
}
