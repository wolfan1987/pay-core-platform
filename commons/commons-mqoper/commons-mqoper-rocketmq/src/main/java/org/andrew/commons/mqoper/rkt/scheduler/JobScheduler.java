package org.andrew.commons.mqoper.rkt.scheduler;

import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * 队列定时调度器。
 *
 * @author andrewliu
 */
public class JobScheduler extends Thread {

    private static Logger logger = Logger.getLogger(JobScheduler.class);
    /**
     * 调度器名称。
     */
    private String schedulerName;

    /**
     * 线程时候执行中。
     */
    private volatile boolean isRunning = false;

    /**
     * 线程调度次数。
     */
    private int runningRounds = 0;

    /**
     * 保存任务的实体的队列。
     */
    //    private ConcurrentLinkedQueue<Job> jobs = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<String, Job> jobs       = new ConcurrentHashMap<>();
    /**
     * 声明一个无界BlockingQueue用于保存定时任务实体，
     * 实现了compareTo，getDelay用于判断是否到了延时时间。
     */
    private DelayQueue<Job>                delayQueue = new DelayQueue<>();

    public JobScheduler(String name) {
        this.schedulerName = name;
    }

    /**
     * 添加job到调度队列，指定调度次数。
     *
     * @param intervalMillis 队列调度间隔
     * @param caller         回调
     * @param maxRunRounds   调度次数
     * @return 当前job
     * @throws RuntimeException exception
     */
    public Job addJob(String keys, long intervalMillis, JobCaller caller, int maxRunRounds)
        throws RuntimeException {
        return addJob(keys, intervalMillis, caller, false, maxRunRounds);
    }

    /**
     * 添加job到调度队列，指定是否一次调度，false则会重复调度。
     *
     * @param intervalMillis 调度时间间隔
     * @param caller         回调
     * @param isOneTimeJob   是否单次调度
     * @param maxRunRounds   调度次数
     * @return 当前job
     * @throws RuntimeException exception
     */
    public Job addJob(
        String keys, long intervalMillis, JobCaller caller, boolean isOneTimeJob, int maxRunRounds)
        throws RuntimeException {
        Job newJob = new Job(this, schedulerName, intervalMillis, caller);
        if (isOneTimeJob) {
            newJob.setOneTimeJob(true);
            newJob.setNextTimestampInMillis();
        }
        //        this.jobs.add(newJob);
        newJob.setKey(keys);
        newJob.setMaxRunRounds(maxRunRounds);
        this.jobs.put(keys, newJob);
        this.delayQueue.offer(newJob);
        return newJob;
    }

    public Job addJob(String keys, long intervalMillis, JobCaller caller) throws RuntimeException {
        return addJob(keys, intervalMillis, caller, false, -1);
    }

    public Job addOneTimeJob(String keys, long intervalMillis, JobCaller call)
        throws RuntimeException {
        return addJob(keys, intervalMillis, call, true, -1);
    }

    @Override
    public void run() {
        this.isRunning = true;
        while (true) {
            Job job = null;
            boolean runAgain = true;
            try {
                job = delayQueue.take();
                if (job.isCanceled()) {
                    runAgain = false;
                } else {
                    if (!job.isOutOfRunning()) {
                        job.execute();
                    } else {
                        runAgain = false;
                    }
                }
                runAgain = !job.isOneTimeJob() && runAgain;
            } catch (Throwable ex) {
                logger.error(
                    String.format("%s Unexpected Exception when running jobs.", this.getName()),
                    ex);
            } finally {
                if (runAgain) {
                    if (job != null) {
                        job.setNextTimestampInMillis();
                        delayQueue.offer(job);
                    } else {
                        logger.error("job is null");
                    }
                } else {
                    jobs.remove(job.getKey());
                }
            }
        }
    }

    public String getSchedulerName() {
        return schedulerName;
    }

    public void increaseCount() {
        this.runningRounds++;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Job getJob(String keys) {
        return jobs.get(keys);
    }
}
