package org.andrew.commons.exception.dao;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/7 11:43
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class DaoCURDException extends SimpleBaseException {

    public DaoCURDException(Exception exception) {
        super(exception);

    }

    public DaoCURDException(Exception exception, String message) {
        super(exception, message);

    }

    public DaoCURDException(String message) {
        super(message);

    }
}
