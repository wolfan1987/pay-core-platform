package org.andrew.commons.mqoper.rkt.abstracts;


import org.andrew.commons.mqoper.api.Task;
import org.andrew.commons.mqoper.api.TaskCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  抽象Task，定义任务控制相关基本信息
 * @Date: Created in 2018/7/11 11:01
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public  abstract class AbstractTask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTask.class);
    /**
     * 任务名称
     */
    protected   String  taskName;
    /**
     * 任务唯一ID
     */
    protected  String   taskKey;
    /**
     * 任务回调对象
     */
    protected  TaskCaller  taskCaller;

    /**
     * 下一次任务执行时间
     */
    protected long nextExecTime = System.currentTimeMillis();
    /**
     * 任务最多执行次数
     */
    protected  int  maxRunTime;
    /**
     * 是否只执行一次
     */
    protected  boolean  isExecOne;
    /**
     * 已执行次数统计
     */
    protected AtomicInteger runCount = new AtomicInteger(0);
    /**
     *任务是否已被取消
     */
    protected  boolean  isCancleTask = false;
    /**
     * 任务执行间隔时长
     */
    protected  long   exeCycleTime  = 1000;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public TaskCaller getTaskCaller() {
        return taskCaller;
    }

    public void setTaskCaller(TaskCaller taskCaller) {
        this.taskCaller = taskCaller;
    }

    public long getNextExecTime() {
        return nextExecTime;
    }

    /**
     * 设置下一次执行时间（当前时间+执行周期间隔时长)
     */
    public void setNextExecTime() {
        this.nextExecTime = System.currentTimeMillis() + this.exeCycleTime;
    }

    public int getMaxRunTime() {
        return maxRunTime;
    }

    public void setMaxRunTime(int maxRunTime) {
        this.maxRunTime = maxRunTime;
    }

    public boolean isExecOne() {
        return isExecOne;
    }

    public void setExecOne(boolean execOne) {
        isExecOne = execOne;
    }

    public AtomicInteger getRunCount() {
        return runCount;
    }

    public void setRunCount(AtomicInteger runCount) {
        this.runCount = runCount;
    }

    public boolean isCancleTask() {
        return isCancleTask;
    }

    public void setCancleTask(boolean cancleTask) {
        isCancleTask = cancleTask;
    }

    public long getExeCycleTime() {
        return exeCycleTime;
    }

    public void setExeCycleTime(long exeCycleTime) {
        this.exeCycleTime = exeCycleTime;
    }

    /**
     * 已执行次数，是否大于等于最大执行次数
     * @return
     */
    public boolean isOutMaxRunTime() {
        int currentRoundIndex = runCount.intValue();
        return currentRoundIndex >= maxRunTime;
    }

}
