package org.andrew.commons.mqoper.rkt.model;

import java.io.Serializable;

/**
 * mq 消息实体。
 * @author andrewliu
 */
public class MqMessageVo implements Serializable {

    private String topic;

    private String tags;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
