package indi.yunherry.exception;

/**
 * @author h3209
 * 解析器无法解析该命令
 */
public class ParameterParsingException extends Exception {
    public ParameterParsingException(String message) {
        super(message);
    }
}
