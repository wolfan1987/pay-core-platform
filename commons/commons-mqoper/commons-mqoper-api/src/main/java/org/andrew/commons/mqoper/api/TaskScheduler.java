package org.andrew.commons.mqoper.api;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  调度器接口,用于在生产或消费完消息之后，对消息作后续处理
 * @Date: Created in 2018/7/7 11:55
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface TaskScheduler extends Runnable {


    /**
     * 添加一个新Task到调度器队列中
     * @param newTask
     */
    void  addTask(Task  newTask);

    /**
     * 从调度器队列获取指定Task
     * @param key   task的key
     * @return
     */
    Task   getTask(String key);

}
