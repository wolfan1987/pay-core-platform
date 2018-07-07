package org.andrew.commons.exception.service;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:44
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class RemoteInvokeException extends SimpleBaseException {

    public RemoteInvokeException(Exception exception) {
        super(exception);

    }

    public RemoteInvokeException(Exception exception, String message) {
        super(exception, message);

    }

    public RemoteInvokeException(String message) {
        super(message);

    }
}
