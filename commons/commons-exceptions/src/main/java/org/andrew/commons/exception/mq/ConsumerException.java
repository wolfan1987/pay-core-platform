package org.andrew.commons.exception.mq;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:41
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ConsumerException extends SimpleBaseException {

    public ConsumerException(Exception exception) {
        super(exception);

    }

    public ConsumerException(Exception exception, String message) {
        super(exception, message);

    }

    public ConsumerException(String message) {
        super(message);

    }
}
