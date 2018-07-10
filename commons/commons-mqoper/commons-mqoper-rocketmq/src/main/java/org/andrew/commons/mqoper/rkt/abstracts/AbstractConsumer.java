package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqoper.rkt.entityext.ConsumeMessageExt;
import org.andrew.commons.mqpoer.api.Consumer;
import org.andrew.commons.mqpoer.api.ConsumerExt;
import org.andrew.commons.mqpoer.config.AbstractConfig;
import org.andrew.commons.mqpoer.config.ConsumeConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 10:00
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract class AbstractConsumer<T extends ConsumeMessageExt,C extends AbstractConfig> extends DefaultMQPushConsumer implements Consumer<T,C>, ConsumerExt<T> {

    protected   ConsumeConfig  config;

    private  MessageListenerConcurrently  messageListener;

    public  void setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener =messageListener;
    }

    @Override
    public void doStart() throws ConsumerException {
        if(config!=null){

        }else{
            throw new  ConsumerException("----初始化消费配置出错，ConsumeConfig=null");
        }
    }

    @Override
    public void doShutDown() {
        this.shutdown();
    }

}
