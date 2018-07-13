package org.andrew.commons.mqoper.rkt.model;

import org.andrew.commons.mqoper.entitys.ProduceResult;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/13 15:40
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class MQProduceResult  implements ProduceResult<SendResult>{
    private  SendResult  sendResult = null;
    @Override
    public SendResult getProduceResult() {
        return sendResult;
    }

    @Override
    public void setProduceResult(SendResult sendResult) {
        this.sendResult = sendResult;
    }
}
