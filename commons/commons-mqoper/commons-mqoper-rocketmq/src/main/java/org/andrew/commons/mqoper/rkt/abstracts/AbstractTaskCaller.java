package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.task.TaskException;
import org.andrew.commons.mqoper.api.Task;
import org.andrew.commons.mqoper.api.TaskCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  抽象TaskCaller，实现通用Caller功能
 * @Date: Created in 2018/7/11 11:47
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract  class AbstractTaskCaller implements TaskCaller {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTaskCaller.class);

    protected Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void bindTask(Task newTask) throws TaskException {
        this.task = newTask;
    }
}
