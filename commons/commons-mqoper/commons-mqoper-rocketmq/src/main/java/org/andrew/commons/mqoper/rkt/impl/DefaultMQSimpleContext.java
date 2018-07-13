package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.binding.BindingConfigException;
import org.andrew.commons.exception.context.ContextException;
import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.config.BindingConfig;
import org.andrew.commons.mqoper.emnus.ConsumeTypeEnum;
import org.andrew.commons.mqoper.entitys.ProduceResult;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractMQSimpleContext;
import org.andrew.commons.mqoper.api.Consumer;
import org.andrew.commons.mqoper.api.Producer;
import org.andrew.commons.mqoper.config.MQContextEnvConfig;
import org.andrew.commons.mqoper.emnus.LoggerEnums;
import org.andrew.commons.mqoper.entitys.ProduceMessage;
import org.andrew.commons.mqoper.rkt.bindings.BindingConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
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
 * @Description:  默认MQ消费上下文对象
 * @Date: Created in 2018/7/10 18:39
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class DefaultMQSimpleContext extends AbstractMQSimpleContext {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMQSimpleContext.class);
    /**
     * key=groupName_topicname(存放所有生产者)
     */
    private ConcurrentMap<String,Producer>  producerConcurrentMap  = new ConcurrentHashMap<>() ;
    /**
     * key=groupName_topicname(存放所有消费者)
     */
    private ConcurrentMap<String,Consumer>  consumerConcurrentMap  = new ConcurrentHashMap<>() ;
    /**
     * 生产者绑定对象
     */
    private BindingConfig producerBinding = BindingConfigFactory.getInstance().getProducerBinding();
    /**
     * 推送消费绑定对象
     */
    private BindingConfig pushConsumerBinding  =   BindingConfigFactory.getInstance().getPushConsumerBinding();
    /**
     * 拉取消费绑定对象
     */
    private BindingConfig pullConsumerBinding  =   BindingConfigFactory.getInstance().getPullConsumerBinding();
    private  MQContextEnvConfig contextEnvConfig = null;

    public DefaultMQSimpleContext(MQContextEnvConfig contextEnvConfig) {
        this.contextEnvConfig = contextEnvConfig;
    }
    public DefaultMQSimpleContext() {}

    @Override
    public void init() throws ContextException {
        this.contextName = "defaultMQContextName";
        Assert.notNull(contextEnvConfig,"初始化MQ上下文环境时，contextEnvConfig = null");
        Assert.notNull(contextEnvConfig.getLoggerName(), "loggerName 不能為 NULL");
        try{
          //  initContextLogger(contextEnvConfig);
            startProducerConsumer();
        } catch (BindingConfigException e) {
            e.printStackTrace();
        } catch (ProducerException e) {
            e.printStackTrace();
        } catch (ConsumerException e) {
            e.printStackTrace();
        }
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
    public  void  setProducerMap(Map<String,Producer> producerMap){
        Assert.notNull(producerMap,"初始化MQ上下文环境失败，producerMap == null");
        if(producerMap.size()>0){
            producerConcurrentMap.putAll(producerMap);
        }else{
            Assert.notEmpty(producerMap,"初始化MQ上下文环境失败,producerMap.size=0");
        }
    }

    @Override
    public  void  setConsumerMap(Map<String,Consumer> comsumerMap){
        Assert.notNull(comsumerMap,"初始化MQ上下文环境失败，comsumerMap == null");
        if(comsumerMap.size()>0){
            consumerConcurrentMap.putAll(comsumerMap);
        }else{
            Assert.notEmpty(comsumerMap,"初始化MQ上下文环境失败,comsumerMap.size=0");
        }
    }
    private  void initContextLogger(MQContextEnvConfig   config){
        String loggerName = null;
        //在java环境下时才初始化日志，web环境时，由容器去初始化
        if(!config.isWebApp() && config!=null){
            loggerName = config.getLoggerName();
            if(loggerName.equalsIgnoreCase(LoggerEnums.LOGGER4j2_CLASSPATH.getLoggerName())){
                initLog4j2();
            }
            if(loggerName.equalsIgnoreCase(LoggerEnums.LOGBACK_CLASSPATH.getLoggerName())){
                initLogback();
            }
        }
    }
    private void  startProducerConsumer() throws BindingConfigException, ProducerException, ConsumerException {
        if(producerConcurrentMap.size()>0){
            startProducer();
        }
        if(consumerConcurrentMap.size()>0){
            startConsumer();
        }
    }
    private  void  startProducer() throws BindingConfigException, ProducerException {

        for(Producer  p : producerConcurrentMap.values()){
            Assert.notNull(p,"启动Producer时，传入的Producer为Null");
            Assert.notNull(p.getConfig(),"初始化 Producer时，ProducerConfig  配置对象 为 NULL.\"");
            //Producer中可以不配置namesrv参数，此时会有Context中的
            if(StringUtils.isEmpty(p.getConfig().getNameSrv())){
                p.getConfig().setNameSrv(contextEnvConfig.getMqNameServers());
            }
            //先对Producer绑定相关参数
            producerBinding.binding(p,p.getConfig());
            p.doStart();
        }
    }

    private void  startConsumer() throws BindingConfigException,ConsumerException {
        for(Consumer c : consumerConcurrentMap.values()){
            Assert.notNull(c,"启动Consumer时，传入的Consumer为Null");
            Assert.notNull(c.getConfig(),"\"初始化 Consumer，ConsumerConfig  配置对象 为 NULL.\"");
            //Consumer中可以不配置namesrv参数，此时会有Context中的
            if(StringUtils.isEmpty(c.getConfig().getNameSrv())){
                c.getConfig().setNameSrv(contextEnvConfig.getMqNameServers());
            }
            //先对Consumer绑定相关参数
            if(StringUtils.equalsIgnoreCase(c.getConsumeType().name(), ConsumeTypeEnum.PUSH.name())){
                    pushConsumerBinding.binding(c,c.getConfig());
            }else if(StringUtils.equalsIgnoreCase(c.getConsumeType().name(), ConsumeTypeEnum.PULL.name())){
                    pullConsumerBinding.binding(c,c.getConfig());
            }

            c.doStart();
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
    @Override
    public  void  setMQContextEnvConfig(MQContextEnvConfig contextEnvConfig){
        this.contextEnvConfig = contextEnvConfig;
    }

    @Override
    public void destroy() {
        stopProducer();
        stopConsumer();
    }

}
