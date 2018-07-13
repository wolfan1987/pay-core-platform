package org.andrew.commons.mqoper.rkt.bindings;

import org.andrew.commons.exception.binding.BindingConfigException;
import org.andrew.commons.mqoper.config.BindingConfig;
import org.andrew.commons.mqoper.config.ConsumeConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;


/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:30
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class BindingPushConsumerConfig implements BindingConfig<DefaultMQPushConsumer,ConsumeConfig> {

    @Override
    public void binding(DefaultMQPushConsumer dest, ConsumeConfig src) throws BindingConfigException {
        dest.setNamesrvAddr(src.getNameSrv());
    }

    @Override
    public void reBinding(DefaultMQPushConsumer dest,ConsumeConfig src) throws BindingConfigException {

    }

    @Override
    public void clearBinding() throws BindingConfigException {

    }
}
