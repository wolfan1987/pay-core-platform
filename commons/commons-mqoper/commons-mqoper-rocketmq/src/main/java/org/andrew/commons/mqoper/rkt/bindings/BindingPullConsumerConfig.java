package org.andrew.commons.mqoper.rkt.bindings;

import org.andrew.commons.exception.binding.BindingConfigException;
import org.andrew.commons.mqoper.config.BindingConfig;
import org.andrew.commons.mqoper.config.ConsumeConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:36
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class BindingPullConsumerConfig implements BindingConfig<DefaultMQPullConsumer,ConsumeConfig>{
    @Override
    public void binding(DefaultMQPullConsumer dest, ConsumeConfig src) throws BindingConfigException {
        dest.setNamesrvAddr(src.getNameSrv());
    }

    @Override
    public void reBinding(DefaultMQPullConsumer dest,ConsumeConfig src) throws BindingConfigException {

    }

    @Override
    public void clearBinding() throws BindingConfigException {

    }
}
