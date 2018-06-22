package org.andrew.commons.mqoper.rkt.executes.producer;

import com.alibaba.fastjson.JSON;

/**
 * Created by leaves chen[leaves615@gmail.com] on 2017/10/17。
 *
 * @author leaves chen[leaves615@gmail.com]
 */
public class JsonMessageProducer extends TextMessageProducer {
    public JsonMessageProducer(int intervalMillis) {
        super(intervalMillis);
    }

    public JsonMessageProducer(String jobQueueName, int intervalMillis) {
        super(jobQueueName, intervalMillis);
    }

    public Boolean sendTextMsg(Object msg, String keys) {
        String jsonMessage = JSON.toJSONString(msg);
        return super.doSend(keys, jsonMessage);
    }
}
