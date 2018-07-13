package org.andrew.commons.mqoper.entitys;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  生产消息到mq相关结果包装
 * @Date: Created in 2018/7/9 16:29
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface ProduceResult<T> {

    T   getProduceResult();

    void   setProduceResult(T t);


}
