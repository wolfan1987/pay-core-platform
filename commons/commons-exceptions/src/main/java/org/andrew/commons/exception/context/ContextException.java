package org.andrew.commons.exception.context;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/9 11:11
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ContextException extends SimpleBaseException {

    public ContextException(Exception exception) {
        super(exception);

    }

    public ContextException(Exception exception, String message) {
        super(exception, message);

    }

    public ContextException(String message) {
        super(message);

    }
}