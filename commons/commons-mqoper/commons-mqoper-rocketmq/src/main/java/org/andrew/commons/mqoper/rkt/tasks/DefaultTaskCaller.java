package org.andrew.commons.mqoper.rkt.tasks;

import org.andrew.commons.exception.task.TaskException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractTaskCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  默认任务回调对象
 * @Date: Created in 2018/7/11 11:39
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class DefaultTaskCaller extends  AbstractTaskCaller {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTaskCaller.class);

    @Override
    public void execute() throws TaskException {

    }
}
