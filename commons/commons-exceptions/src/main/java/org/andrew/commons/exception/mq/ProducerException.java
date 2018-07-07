package org.andrew.commons.exception.mq;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:42
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ProducerException extends SimpleBaseException {

    public ProducerException(Exception exception) {
        super(exception);

    }

    public ProducerException(Exception exception, String message) {
        super(exception, message);

    }

    public ProducerException(String message) {
        super(message);

    }
}
