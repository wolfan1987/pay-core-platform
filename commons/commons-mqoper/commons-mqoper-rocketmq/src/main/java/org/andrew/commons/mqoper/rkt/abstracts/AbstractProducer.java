package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.entitys.ProduceResult;
import org.andrew.commons.mqoper.rkt.entityext.ProduceMessageExt;
import org.andrew.commons.mqoper.api.Producer;
import org.andrew.commons.mqoper.api.ProducerExt;
import org.andrew.commons.mqoper.api.TaskScheduler;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.andrew.commons.mqoper.rkt.model.MQProduceResult;
import org.andrew.commons.mqoper.rkt.model.TestProduceMessage;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description: 基本消息生产抽象实现
 * @Date: Created in 2018/7/10 10:00
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public abstract  class AbstractProducer<T extends ProduceMessageExt,C extends ProduceConfig,R extends ProduceResult> extends DefaultMQProducer implements Producer<T,R,C>,ProducerExt<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProducer.class);

    protected TaskScheduler  taskScheduler;

    protected  ProduceConfig   config;

    protected  Message   sendMessage;

    protected  Thread  taskThread =  null;

    @Override
    public void doStart() throws ProducerException {
        Assert.notNull(this.config,"启动推送消费者失败，ConsumeConfig = Null,请设置ConsumeConfig对象");
        try {
            //先启动mq监听
            this.start();
            //如果有调度器，则同时启动调度器
            if(this.taskScheduler!=null){
                taskThread = new Thread(taskScheduler);
                taskThread.start();
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProduceResult doSend(ProduceMessageExt produceMessage) throws ProducerException {
        try {
            if(preProcessor(produceMessage)){
                SendResult sendResult = this.send(sendMessage);
                MQProduceResult produceResult = new MQProduceResult();
                produceResult.setProduceResult(sendResult);
                return produceResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }finally{
            this.endProcessor(produceMessage);
        }
        return null;
    }

    @Override
    public void doShutDown() throws ProducerException {
        this.shutdown();
        taskThread.interrupt();
    }

    @Override
    public boolean preProcessor(ProduceMessageExt produceMessage) throws ProducerException, UnsupportedEncodingException {
            sendMessage = new Message();
            sendMessage.setTopic(produceMessage.getTopic());
            if(produceMessage.getMsgId()!=null){
                sendMessage.setKeys(produceMessage.getMsgId());
            }
            if(produceMessage.getTags()!=null){
                sendMessage.setTags(produceMessage.getTags());
            }
            if(produceMessage.getListKey()!=null){
                sendMessage.setKeys(produceMessage.getListKey());
            }
            if(produceMessage.getListMessage()!=null){

            }
            if(produceMessage.getUserProperty()!=null){
                Map<String,String> tempMap = produceMessage.getUserProperty();
                for(String  key : tempMap.keySet())
                    sendMessage.putUserProperty(key,tempMap.get(key));
            }
            if(produceMessage.getContent()!=null){
                sendMessage.setBody(produceMessage.getContent().getBytes(this.config.getDefaultCharset()));
            }
        return true;
    }

    @Override
    public boolean endProcessor(ProduceMessageExt produceMessage) throws ProducerException {
        return true;
    }

    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }
    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }


}
