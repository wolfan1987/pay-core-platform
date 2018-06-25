package org.andrew.commons.mqoper.rkt.executes.producer;


import org.andrew.commons.mqoper.rkt.api.Producer;
import org.andrew.commons.mqoper.rkt.model.MqMessageVo;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.util.Assert;
import  org.andrew.commons.mqoper.rkt.scheduler.JobScheduler;

/**
 * rocketMq提供者模板。
 *
 * @author andrewliu
 */
public abstract class AbstractRocketMqProducer<T extends MqMessageVo> extends DefaultMQProducer
    implements Producer<T> {
    /**
     * 调度队列。
     */
    JobScheduler jobScheduler;

    /**
     * 是否使用任务调度队列。
     */
    private boolean useJobSchedule = false;

    /**
     * 队列中调度间隔。
     */
    protected final int intervalMillis;

    /**
     * 调度队列中，任务可重发最大次数。
     */
    private int maxRunRounds = 2;

    /**
     * 主题。
     */
    private String topic;

    /**
     * tags用于brocker过滤消息。
     */
    private String tags;

    /**
     * 是否对消息做持久化。
     */
    private boolean persist = true;

    public AbstractRocketMqProducer(int intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    /**
     * 构造函数。
     *
     * @param jobQueueName   采用自定义的定时调度队列，队列名称。
     * @param intervalMillis 调度周期，单位毫秒。
     */
    public AbstractRocketMqProducer(String jobQueueName, int intervalMillis) {
        jobScheduler = new JobScheduler(jobQueueName);
        this.intervalMillis = intervalMillis;
        this.useJobSchedule = true;
    }

    public boolean isUseJobSchedule() {
        return useJobSchedule;
    }

    public JobScheduler getJobScheduler() {
        return jobScheduler;
    }

    public int getMaxRunRounds() {
        return maxRunRounds;
    }

    public void setMaxRunRounds(int maxRunRounds) {
        this.maxRunRounds = maxRunRounds;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isPersist() {
        return persist;
    }

    public void setPersist(boolean persist) {
        this.persist = persist;
    }

    /**
     * 提供者启动函数。
     */
    public void doStart() {
        Assert.notNull(topic, "topic can not be null");
        Assert.notNull(getProducerGroup(), "producerGroup can not be null");
        Assert.notNull(getNamesrvAddr(), "namesrvAddr can not be null");
        this.setVipChannelEnabled(false);
        try {
            this.start();
            if (useJobSchedule) {
                jobScheduler.start();
            }
        } catch (MQClientException ex) {
            throw new RuntimeException("start producer error", ex);
        }
    }

    /**
     * 提供者关闭函数。
     */
    public void doShutdown() {
        this.shutdown();
    }

}
