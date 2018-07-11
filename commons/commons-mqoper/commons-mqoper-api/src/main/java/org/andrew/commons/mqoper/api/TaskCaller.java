package org.andrew.commons.mqoper.api;

import org.andrew.commons.exception.task.TaskException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  任务回调接口
 * @Date: Created in 2018/7/9 16:14
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface TaskCaller {
    /***
     * 執行task
     * @throws TaskException
     */
      void execute() throws TaskException;

    /**
     * 為TaskCaller綁定一個Task
     * @param newTask
     * @throws TaskException
     */
      void  bindTask(Task newTask) throws TaskException;
}
