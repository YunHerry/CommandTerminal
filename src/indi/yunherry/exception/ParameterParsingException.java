package indi.yunherry.exception;

/**
 * @author h3209
 * 类型转换错误,出现在类型无法被转换成该方法对应的类型的情况下
 */
public class ParameterParsingException extends Exception {
    public ParameterParsingException(String message) {
        super(message);
    }
}
