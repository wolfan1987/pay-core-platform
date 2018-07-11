package org.andrew.commons.exception.task;

import org.andrew.commons.exception.base.SimpleBaseException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/11 11:46
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TaskException extends SimpleBaseException {

    public TaskException(Exception exception) {
        super(exception);
    }

    public TaskException(Exception exception, String message) {
        super(exception, message);

    }
    public TaskException(String message) {
        super(message);

    }
}