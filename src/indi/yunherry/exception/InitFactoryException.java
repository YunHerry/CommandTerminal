package indi.yunherry.exception;

/**
 * @author YunHerry
 * 初始化工厂出现问题,往往因为注册bean失败。
 */
public class InitFactoryException extends RuntimeException{
    public InitFactoryException(String message) {
        super(message);
    }
    public InitFactoryException(Exception e) {
        super(e);
    }
}
