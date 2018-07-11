package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ConsumerException;
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

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  抽象消费实现，启动消费监听
 * @Date: Created in 2018/7/10 10:00
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract class AbstractConsumer<T extends ConsumeMessageExt,C extends AbstractConfig> extends DefaultMQPushConsumer implements Consumer<T,C>, ConsumerExt<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractConsumer.class);

    protected TaskScheduler taskScheduler;

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
    public void doShutDown() throws ConsumerException {
        this.shutdown();
    }


    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Override
    public String getNameSrv() {
        return null;
    }

    @Override
    public void setConfigName(String configName) {

    }

    @Override
    public String getConfigName() {
        return null;
    }

    @Override
    public boolean validateConfig() {
        return false;
    }

    @Override
    public boolean validatePullConfig() {
        return false;
    }

    @Override
    public boolean validatePushConfig() {
        return false;
    }

    @Override
    public void setNameSrv(String nameSrv) {

    }
}
