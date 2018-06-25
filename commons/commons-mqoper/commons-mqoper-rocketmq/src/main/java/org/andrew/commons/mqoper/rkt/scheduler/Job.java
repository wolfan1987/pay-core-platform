package org.andrew.commons.mqoper.rkt.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务实体，控制定时任务中的轮询次数，
 * 指定访问时间等。
 *
 * @author andrewliu
 */
public class Job implements Delayed {

    private static final Logger logger = LoggerFactory.getLogger(Job.class);

    /**
     * 下一次执行时间，
     * 默认为当前时间。
     */
    private long nextTimestampInMillis = System.currentTimeMillis();

    /**
     * 调度者。
     */
    private final JobScheduler scheduler;

    /**
     * job名称。
     */
    private String name;

    /**
     * job调度周期，单位毫秒。
     */
    private long intervalInMillis;

    /**
     * 回调函数处理。
     */
    private JobCaller caller;

    /**
     * 是否已取消调度。
     */
    private volatile boolean canceled = false;

    /**
     * 是否单次调度。
     */
    private volatile boolean isOneTimeJob = false;

    /**
     * 最大调度数。
     */
    private int maxRunRounds = -1;

    /**
     * 当前job调度次数计数器。
     */
    private AtomicInteger currentRunIndex = new AtomicInteger(1);

    /**
     * job唯一标识。
     */
    private String key;

    /**
     * 构造函数。
     *
     * @param jobScheduler     调度器
     * @param name             job名称
     * @param intervalInMillis 调度周期，单位毫秒
     * @param call             回调函数
     */
    public Job(JobScheduler jobScheduler, String name, long intervalInMillis, JobCaller call) {
        this.scheduler = jobScheduler;
        this.name = name;
        this.intervalInMillis = intervalInMillis;
        this.caller = call;
        this.caller.setJob(this);
    }

    public int getMaxRunRounds() {
        return maxRunRounds;
    }

    public void setMaxRunRounds(int maxRunRounds) {
        this.maxRunRounds = maxRunRounds;
    }

    /**
     * 执行job回调。
     */
    public void execute() {
        try {
            caller.call();
            scheduler.increaseCount();
            currentRunIndex.getAndIncrement();
        } catch (Exception ex) {
            logger.error("job 处理call函数出错", ex);
        }
    }

    public boolean isOutOfRunning() {
        int currentRoundIndex = currentRunIndex.intValue();
        return currentRoundIndex >= maxRunRounds;
    }

    public void cancel() {
        this.canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setNextTimestampInMillis() {
        this.nextTimestampInMillis = System.currentTimeMillis() + this.intervalInMillis;
    }

    public boolean isOneTimeJob() {
        return isOneTimeJob;
    }

    public void setOneTimeJob(boolean oneTimeJob) {
        isOneTimeJob = oneTimeJob;
    }

    public long getIntervalInMillis() {
        return intervalInMillis;
    }

    public void setIntervalInMillis(long intervalInMillis) {
        this.intervalInMillis = intervalInMillis;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(
            nextTimestampInMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return Long.compare(
            this.getDelay(TimeUnit.MILLISECONDS), delayed.getDelay(TimeUnit.MILLISECONDS));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
