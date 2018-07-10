package org.andrew.commons.mqpoer.entitys;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  基本消息格式
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ProduceMessage {

    //消息组名
    protected  String  groupName;
    //消息实例名称
    protected  String  instanceName;
    //基本消息ID
    protected  String  msgId;
    //消息主题
    protected  String  topic;
    //消息内容
    protected  String  content;
    //消息发送时间
    protected  String  sendTime;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
