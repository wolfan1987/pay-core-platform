package org.andrew.commons.mqoper.rkt.abstracts;

import org.andrew.commons.mqoper.api.MQSimpleContext;
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
     * 命名服务器名
     */
    protected  String  namesrvs;
    /**
     * 当前处理器上下文件名称
     */
    protected   String   contextName;

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
