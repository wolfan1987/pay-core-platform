package org.andrew.commons.mqoper.rkt.bindings;

import org.andrew.commons.exception.binding.BindingConfigException;
import org.andrew.commons.mqoper.config.BindingConfig;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:30
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class BindingProducerConfig  implements BindingConfig<DefaultMQProducer,ProduceConfig> {
    @Override
    public void binding(DefaultMQProducer dest, ProduceConfig src) throws BindingConfigException {
        dest.setNamesrvAddr(src.getNameSrv());
    }

    @Override
    public void reBinding(DefaultMQProducer dest,ProduceConfig src) throws BindingConfigException {

    }

    @Override
    public void clearBinding() throws BindingConfigException {

    }
}
