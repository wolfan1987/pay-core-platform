package org.andrew.commons.wechat.eventmsg;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * 转换接收到的事件消息。
 */
public class WxRecvEventMsgParser extends WxRecvMsgBaseParser {

    @Override
    protected WxRecvEventMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
        String event = getElementText(root, "Event");
        String latitude = getElementText(root, "Latitude");
        String longitude = getElementText(root, "Longitude");
        String precision = getElementText(root, "Precision");
        //二维码的ticket,用来换取微信二维码图片
        String ticket = getElementText(root, "Ticket");
        //模板消息发送状态
        String status = getElementText(root, "Status");
        //用户自定义卡券code
        String userCardCode = getElementText(root, "UserCardCode");
        //微信会员卡id
        String cardId = getElementText(root, "CardId");
        //审核不通过原因
        String refuseReason = getElementText(root, "RefuseReason");
        //接收卡券用户openId
        String friendUserName = getElementText(root, "FriendUserName");
        //是否转赠退回，0代表不是，1代表是。
        String isReturnBack = getElementText(root, "IsReturnBack");
        //是否是群转赠
        String isChatRoom = getElementText(root, "IsChatRoom");

        WxRecvEventMsg wxRecvEventMsg = new WxRecvEventMsg.Builder().toUser(
            msg.getToUser()).fromUser(msg.getFromUser()).createDt(msg.getCreateDt()).msgType(
            msg.getMsgType()).msgId(msg.getMsgId()).event(event).latitude(latitude).longitude(
            longitude).precision(precision).ticket(ticket).status(status).userCardCode(
            userCardCode).cardId(cardId).refuseReason(refuseReason).friendUserName(
            friendUserName).isReturnBack(isReturnBack).isChatRoom(isChatRoom).build();
        return wxRecvEventMsg;
    }

}
