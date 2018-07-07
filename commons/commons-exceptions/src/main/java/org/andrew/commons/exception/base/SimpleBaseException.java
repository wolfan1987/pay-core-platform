package org.andrew.commons.exception.base;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:40
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class SimpleBaseException extends Exception {

    private Exception exception;

    public SimpleBaseException(Exception exception) {
        this.exception = exception;
    }

    public SimpleBaseException(Exception exception, String message) {
        super(message);
        this.exception = exception;
    }

    public SimpleBaseException(String message) {
        super(message);
    }

    public Exception getException() {
        return exception;
    }
}