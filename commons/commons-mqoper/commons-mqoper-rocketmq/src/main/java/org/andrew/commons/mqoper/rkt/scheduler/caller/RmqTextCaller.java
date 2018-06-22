package org.andrew.commons.mqoper.rkt.scheduler.caller;


import org.andrew.commons.mqoper.rkt.api.Producer;
import org.andrew.commons.mqoper.rkt.model.TextMsgVo;
import org.andrew.commons.mqoper.rkt.scheduler.JobCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文本消息调度重发。
 *
 * @author huangzebin
 */
public class RmqTextCaller extends JobCaller {

    private static final Logger logger = LoggerFactory.getLogger(RmqTextCaller.class);


    private Producer producer;

    private TextMsgVo message;

    public RmqTextCaller(Producer producer, TextMsgVo message) {
        this.producer = producer;
        this.message = message;
    }

    @Override
    public void call() throws Exception {
        boolean sendState = producer.doSend(message.getKeys(), message.getMessage());
        if (sendState) {
            //发送消息成功，把当前job从重发队列中移除，防止继续重复发送
            this.getJob().cancel();
        }
    }
}
