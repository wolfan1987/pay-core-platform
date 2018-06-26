package org.andrew.commons.wechat.eventmsg.pojo;

/**
 * 接收的文本消息实体。
 */
public class WxRecvTextMsg extends WxRecvMsg {

    /**
     * 文本消息内容。
     */
    private String content;

    /**
     * 构造函数。
     */
    public WxRecvTextMsg(WxRecvMsg msg, String content) {
        super(msg.getToUser(), msg.getFromUser(), msg.getCreateDt(), msg.getMsgType(),
              msg.getMsgId());
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WxRecvTextMsg{" + "content='" + content + '\'' + "} " + super.toString();
    }
}
