package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.context.ContextException;
import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.entitys.ProduceResult;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractMQSimpleContext;
import org.andrew.commons.mqoper.api.Consumer;
import org.andrew.commons.mqoper.api.Producer;
import org.andrew.commons.mqoper.config.MQContextEnvConfig;
import org.andrew.commons.mqoper.emnus.LoggerEnums;
import org.andrew.commons.mqoper.entitys.ConsumeMessage;
import org.andrew.commons.mqoper.entitys.ProduceMessage;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 18:39
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class MQSimpleContext extends AbstractMQSimpleContext {

    private static final Logger logger = LoggerFactory.getLogger(MQSimpleContext.class);
    /**
     * key=groupName_topicname(存放所有生产者)
     */
    private ConcurrentMap<String,Producer>  producerConcurrentMap  = new ConcurrentHashMap<>() ;
    /**
     * key=groupName_topicname(存放所有消费者)
     */
    private ConcurrentMap<String,Consumer>  consumerConcurrentMap  = new ConcurrentHashMap<>() ;

    @Override
    public void init(MQContextEnvConfig contextEnvConfig) throws ContextException {
        this.contextName = "defaultMQContextName";
        Assert.notNull(contextEnvConfig,"初始化MQ上下文环境时，contextEnvConfig = null");
        Assert.notNull(contextEnvConfig.getLoggerName(), "loggerName 不能為 NULL");
        initContextLogger(contextEnvConfig);
        startProducerConsumer();
    }

    @Override
    public ProduceResult produce(ProduceMessage produceMessage) throws ProducerException {
        Assert.notNull(produceMessage,"生产消费时，ProduceMessage 为 null");
        String  key = produceMessage.getGroupName().concat("#").concat(produceMessage.getTopic());
        Producer  producer = producerConcurrentMap.get(key);
        if(producer!=null){
          return  (ProduceResult)producer.doSend(produceMessage);
        }
        return null;
    }

    @Override
    public ConsumeMessage consume() throws ConsumerException {

        return null;
    }


    public  void  setProducerMap(Map<String,Producer> producerMap){
        Assert.notNull(producerMap,"初始化MQ上下文环境失败，producerMap == null");
        if(producerMap.size()>0){
            for(String key : producerMap.keySet()){
                producerConcurrentMap.put(key,producerMap.get(key));
            }
        }else{
            Assert.notEmpty(producerMap,"初始化MQ上下文环境失败,producerMap.size=0");
        }
    }

    public  void  setConsumerMap(Map<String,Consumer> comsumerMap){
        Assert.notNull(comsumerMap,"初始化MQ上下文环境失败，comsumerMap == null");
        if(comsumerMap.size()>0){
            for(String key : comsumerMap.keySet()){
               consumerConcurrentMap.put(key,comsumerMap.get(key));
            }
        }else{
            Assert.notEmpty(comsumerMap,"初始化MQ上下文环境失败,comsumerMap.size=0");
        }
    }
    private   void  initLog4j2(){
        try {
            ConfigurationSource  cs = new ConfigurationSource(new FileInputStream(new File("log4j2.xml")));
            Configurator.initialize(null,cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void   initLogback(){

    }

    private  void initContextLogger(MQContextEnvConfig   config){
        String loggerName = null;
        if(config!=null){
            loggerName = config.getLoggerName();
            if(loggerName.equalsIgnoreCase(LoggerEnums.LOGGER4j2_CLASSPATH.getLoggerName())){
                initLog4j2();
            }
            if(loggerName.equalsIgnoreCase(LoggerEnums.LOGBACK_CLASSPATH.getLoggerName())){
                initLogback();
            }
        }else{
            initLog4j2();
        }
    }
    private void  startProducerConsumer(){
        if(producerConcurrentMap.size()>0){
            startProducer();
        }
        if(consumerConcurrentMap.size()>0){
            startConsumer();
        }
    }
    private  void  startProducer(){
        for(Producer  p : producerConcurrentMap.values()){
            try {
                p.setNameSrv(this.namesrvs);
                p.doStart();
            } catch (ProducerException e) {
                e.printStackTrace();
            }
        }
    }

    private void  startConsumer(){
        for(Consumer c : consumerConcurrentMap.values()){
            try {
                c.setNameSrv(this.namesrvs);
                c.doStart();
            } catch (ConsumerException e) {
                e.printStackTrace();
            }
        }
    }

    private  void  stopProducer(){
        for(Producer  p : producerConcurrentMap.values()){
            try {
                p.doShutDown();
            } catch (ProducerException e) {
                e.printStackTrace();
            }
        }
    }

    private  void  stopConsumer(){
        for(Consumer c : consumerConcurrentMap.values()){
            try {
              c.doShutDown();
            } catch (ConsumerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        stopProducer();
        stopConsumer();
    }

}
