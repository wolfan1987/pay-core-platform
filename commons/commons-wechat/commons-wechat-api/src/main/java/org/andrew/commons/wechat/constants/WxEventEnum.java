package org.andrew.commons.wechat.constants;

/**
 * 微信事件枚举。
 *
 * @author andrewliu
 */
public enum WxEventEnum {

    CARD_PASS_CHECK("卡券审核通过"),
    CARD_NOT_PASS_CHECK("卡券未审核通过"),
    SCAN("扫码事件"),
    SUBSCRIBE("公众号关注事件"),
    UNSUBSCRIBE("公众号取消关注事件"),
    UPDATE_MEMBER_CARD("会员卡内容更新事件"),
    USER_CONSUME_CARD("卡券核销事件"),
    USER_DEL_CARD("用户卡券删除"),
    USER_ENTER_SESSION_FROM_CARD("从卡券进入公众号会话事件"),
    USER_GET_CARD("用户领取卡券事件"),
    USER_VIEW_CARD("查看会员卡事件"),
    USER_GIFTING_CARD("用户转赠卡券事件"), ;

    private String desc;

    WxEventEnum(String desc) {
        this.desc = desc;
    }

    public boolean equalsName(String name) {
        return this.name().equals(name);
    }

    public String getDesc() {
        return desc;
    }
}
