package org.andrew.commons.wechat.eventmsg.pojo;

/**
 * 接收微信消息的公共字段。
 */
public class WxRecvMsg extends WxMsg {

    /**
     * 消息id，64位整型。
     */
    private String msgId;

    public WxRecvMsg(
        String toUser, String fromUser, String createDt, String msgType, String msgId) {
        super(toUser, fromUser, createDt, msgType);
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "WxRecvMsg{" + "msgId='" + msgId + '\'' + "} " + super.toString();
    }
}
