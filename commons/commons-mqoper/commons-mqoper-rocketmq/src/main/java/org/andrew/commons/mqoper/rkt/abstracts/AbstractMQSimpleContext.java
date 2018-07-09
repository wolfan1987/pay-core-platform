package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.mqpoer.api.MQSimpleContext;
import org.andrew.commons.mqpoer.api.TaskScheduler;
import org.andrew.commons.mqpoer.entitys.ConsumeConfig;
import org.andrew.commons.mqpoer.entitys.ProduceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  抽象公用消息处理上下文实现
 * @Date: Created in 2018/7/7 12:01
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public  abstract class AbstractMQSimpleContext  implements MQSimpleContext{

    private static final Logger logger = LoggerFactory.getLogger(AbstractMQSimpleContext.class);
    /**
     * 默认topc生产相关参数配置
     */
    protected ProduceConfig  defaultProduceConfig = new ProduceConfig();
    /**
     * 默认消费topic相关参数设置
     */
    protected ConsumeConfig defualtConsumeConfig = new  ConsumeConfig();
    /**
     * 默认公用生产用调度器
     */
    protected TaskScheduler produceScheduler;

    /**
     * 默认公用消费用调度器
     */
    protected TaskScheduler consumeScheduler;

    /**
     * 命名服务器名
     */
    protected  String  namesrvs;
    /**
     * 当前处理器上下文件名称
     */
    protected   String   contextName;


    public TaskScheduler getProduceScheduler() {
        return produceScheduler;
    }

    public void setProduceScheduler(TaskScheduler produceScheduler) {
        this.produceScheduler = produceScheduler;
    }

    public TaskScheduler getConsumeScheduler() {
        return consumeScheduler;
    }

    public void setConsumeScheduler(TaskScheduler consumeScheduler) {
        this.consumeScheduler = consumeScheduler;
    }

    public String getNamesrvs() {
        return namesrvs;
    }

    public void setNamesrvs(String namesrvs) {
        this.namesrvs = namesrvs;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }
}
