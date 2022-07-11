package indi.yunherry.exception;

/**
 * @author YunHerry
 */
public class InitFactoryException extends RuntimeException{
    public InitFactoryException(String message) {
        super(message);
    }
    public InitFactoryException(Exception e) {
        super(e);
    }
}
