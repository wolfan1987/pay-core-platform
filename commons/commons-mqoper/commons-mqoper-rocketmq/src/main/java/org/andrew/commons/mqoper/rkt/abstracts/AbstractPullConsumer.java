package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqoper.api.Consumer;
import org.andrew.commons.mqoper.api.ConsumerExt;
import org.andrew.commons.mqoper.api.TaskScheduler;
import org.andrew.commons.mqoper.config.AbstractConfig;
import org.andrew.commons.mqoper.config.ConsumeConfig;
import org.andrew.commons.mqoper.emnus.ConsumeTypeEnum;
import org.andrew.commons.mqoper.rkt.entityext.ConsumeMessageExt;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:34
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract class AbstractPullConsumer<T extends ConsumeMessageExt, C extends AbstractConfig> extends DefaultMQPullConsumer implements Consumer<T, C>, ConsumerExt<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPushConsumer.class);

    protected TaskScheduler taskScheduler;

    protected ConsumeConfig config;

    private MessageListenerConcurrently messageListener;


    public void setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void doStart() throws ConsumerException {

    }

    @Override
    public void doShutDown() throws ConsumerException {
        this.shutdown();
    }

    @Override
    public ConsumeTypeEnum getConsumeType() {
        return ConsumeTypeEnum.PULL;
    }


    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }


}