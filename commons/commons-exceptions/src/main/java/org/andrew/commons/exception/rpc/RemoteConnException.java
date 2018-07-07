package org.andrew.commons.exception.rpc;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:43
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class RemoteConnException extends SimpleBaseException {

    public RemoteConnException(Exception exception) {
        super(exception);

    }

    public RemoteConnException(Exception exception, String message) {
        super(exception, message);

    }

    public RemoteConnException(String message) {
        super(message);

    }
}
