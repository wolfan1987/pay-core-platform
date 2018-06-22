package org.andrew.commons.mqoper.entitys;



import org.andrew.commons.database.dao.Model;

import java.util.Date;

/**
 * mq消息对应数据库的实体。
 *
 * @author lijiguang 2017年9月15日
 */
public class MqMessage implements Model<Long> {

    private Long id;

    private String message;

    private String topic;

    private String keys;

    private String tags;

    private String status;

    private String msgId;

    private Date updateTime;

    private Date createTime;

    private Integer consumerNum;

    private String uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getConsumerNum() {
        return consumerNum;
    }

    public void setConsumerNum(Integer consumerNum) {
        this.consumerNum = consumerNum;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MqMessage{" + "id=" + id + ", message='" + message + '\'' + ", topic='" + topic +
               '\'' + ", keys='" + keys + '\'' + ", tags='" + tags + '\'' + ", status='" + status +
               '\'' + ", msgId='" + msgId + '\'' + ", updateTime=" + updateTime + ", createTime=" +
               createTime + ", consumerNum=" + consumerNum + ", uuid='" + uuid + '\'' + '}';
    }

    @Override
    public Long getKey() {
        return this.id;
    }
}
