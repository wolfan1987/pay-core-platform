package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqoper.emnus.ConsumeTypeEnum;
import org.andrew.commons.mqoper.rkt.entityext.ConsumeMessageExt;
import org.andrew.commons.mqoper.api.Consumer;
import org.andrew.commons.mqoper.api.ConsumerExt;
import org.andrew.commons.mqoper.api.TaskScheduler;
import org.andrew.commons.mqoper.config.AbstractConfig;
import org.andrew.commons.mqoper.config.ConsumeConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  抽象消费实现，启动消费监听
 * @Date: Created in 2018/7/10 10:00
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract class AbstractPushConsumer<T extends ConsumeMessageExt,C extends AbstractConfig> extends DefaultMQPushConsumer implements Consumer<T,C>, ConsumerExt<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPushConsumer.class);

    protected TaskScheduler taskScheduler;

    protected   ConsumeConfig  config;

    private  MessageListenerConcurrently  messageListener;



    public  void setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener =messageListener;
    }

    @Override
    public void doStart() throws ConsumerException {
        Assert.notNull(this.config,"启动推送消费者失败，ConsumeConfig = Null,请设置ConsumeConfig对象");
        Assert.notNull(this.messageListener,"启动推送消费者失败，MessageListener对象 = NNull,请设置消费MessageListener 对象");
        this.registerMessageListener(messageListener);
    }

    @Override
    public void doShutDown() throws ConsumerException {
        this.shutdown();
    }

    @Override
    public ConsumeTypeEnum getConsumeType() {
        return ConsumeTypeEnum.PUSH;
    }


    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }



}
