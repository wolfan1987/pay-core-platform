package org.andrew.commons.exception.binding;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:27
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class BindingConfigException extends SimpleBaseException {

    public BindingConfigException(Exception exception) {
        super(exception);

    }

    public BindingConfigException(Exception exception, String message) {
        super(exception, message);

    }

    public BindingConfigException(String message) {
        super(message);

    }
}