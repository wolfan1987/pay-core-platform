package org.andrew.commons.mqoper.rkt.bindings;

import org.andrew.commons.mqoper.config.BindingConfig;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/12 11:37
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class BindingConfigFactory {
    private  static final  BindingConfig   producerBinding =  new BindingProducerConfig();
    private  static final  BindingConfig   pushConsumerBinding = new BindingPushConsumerConfig();
    private  static final  BindingConfig   pullConsumerBinding = new BindingPullConsumerConfig();

    private  static final  BindingConfigFactory   instance =  new  BindingConfigFactory();

    private   BindingConfigFactory(){

    }

    public  static final  BindingConfigFactory  getInstance(){
        return  instance;
    }

    public synchronized    final BindingConfig  getProducerBinding(){
        return  producerBinding;
    }

    public synchronized    final BindingConfig  getPushConsumerBinding(){
        return  pushConsumerBinding;
    }

    public synchronized    final BindingConfig  getPullConsumerBinding(){
        return  pullConsumerBinding;
    }
}
