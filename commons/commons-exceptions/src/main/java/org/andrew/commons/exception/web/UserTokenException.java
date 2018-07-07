package org.andrew.commons.exception.web;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:45
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class UserTokenException extends SimpleBaseException {

    public UserTokenException(Exception exception) {
        super(exception);

    }

    public UserTokenException(Exception exception, String message) {
        super(exception, message);

    }

    public UserTokenException(String message) {
        super(message);

    }
}
