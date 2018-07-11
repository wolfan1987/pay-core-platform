package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.rkt.entityext.ProduceMessageExt;
import org.andrew.commons.mqoper.api.Producer;
import org.andrew.commons.mqoper.api.ProducerExt;
import org.andrew.commons.mqoper.api.TaskScheduler;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 10:00
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract  class AbstractProducer<T extends ProduceMessageExt,C extends ProduceConfig,R> extends DefaultMQProducer implements Producer<T,R,C>,ProducerExt<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProducer.class);

    protected TaskScheduler  taskScheduler;

    protected  ProduceConfig   config;

    @Override
    public void doStart() throws ProducerException {
        if(config!=null){

        }else{
            throw new ProducerException("----初始化生产配置出错，ConsumeConfig=null");
        }
    }

    @Override
    public void doShutDown() throws ProducerException {
        this.shutdown();
    }

    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }
    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Override
    public void setNameSrv(String nameSrv) {

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
}
