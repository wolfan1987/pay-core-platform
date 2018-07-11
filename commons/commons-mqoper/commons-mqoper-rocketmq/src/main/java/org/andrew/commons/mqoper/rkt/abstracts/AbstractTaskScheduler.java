package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.mqoper.api.Task;
import org.andrew.commons.mqoper.api.TaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/11 14:23
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract  class AbstractTaskScheduler extends Thread implements TaskScheduler {

    /**
     * 调度器名称。
     */
    protected  String schedulerName;
    /**
     * 线程是否执行中。
     */
    protected  volatile boolean isRunning = false;
    /**
     * 保存任务的实体的队列。
     */
    private ConcurrentHashMap<String, Task> taskMap= new ConcurrentHashMap<>();
    /**
     * 声明一个无界BlockingQueue用于保存定时任务实体，
     * 实现了compareTo，getDelay用于判断是否到了延时时间。
     */
    private DelayQueue<Task> delayQueueTask = new DelayQueue<>();

    @Override
    public void addTask(Task newTask) {

    }

    @Override
    public Task getTask(String key) {
        return null;
    }
}
