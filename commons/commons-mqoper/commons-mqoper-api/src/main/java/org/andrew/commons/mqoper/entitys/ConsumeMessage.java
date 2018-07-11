package org.andrew.commons.mqoper.entitys;

import java.io.Serializable;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/9 11:16
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ConsumeMessage  implements Serializable {
    /**
     * 主题。
     */
    private String topic;

    /**
     * 子主题，用来订阅时做过滤。
     */
    private String tags;

    /**
     * 消息内容。
     */
    private String body;

    /**
     * 消息id。
     */
    private String msgId;

    /**
     * 消息所在队列id。
     */
    private int queueId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    @Override
    public String toString() {
        return "ConsumerMessage{" + "topic='" + topic + '\'' + ", tags='" + tags + '\'' +
                ", body='" + body + '\'' + ", msgId='" + msgId + '\'' + ", queueId=" + queueId + '}';
    }

}
