package org.andrew.commons.mqoper.config;

import org.andrew.commons.exception.binding.BindingConfigException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  绑定mq相关配置信息
 * @Date: Created in 2018/7/12 11:22
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface BindingConfig<S ,T extends Config> {

    /**
     * 绑定mq相关配置
     * @param dest  被绑定对象
     * @param src   配置源
     * @throws BindingConfigException
     */
    void binding(S dest, T src) throws BindingConfigException;


    void  reBinding(S dest,T src) throws BindingConfigException;

    void  clearBinding() throws BindingConfigException;

}
