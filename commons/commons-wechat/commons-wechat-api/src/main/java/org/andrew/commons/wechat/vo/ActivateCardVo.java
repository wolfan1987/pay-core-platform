package org.andrew.commons.wechat.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 激活会员卡所需要的相关字段。
 *
 * @author andrewliu
 */
public class ActivateCardVo implements Serializable {

    /**
     * 会员卡编号，由开发者填入，作为序列号显示在用户的卡包里。可与Code码保持等值。
     */
    @JSONField(name = "membership_number")
    private String membershipNumber;

    /**
     * 创建会员卡时获取的初始code。
     **/
    private String code;

    /**
     * 持初始积分，不填为0。
     **/
    @JSONField(name = "init_bonus")
    private String initBonus;

    /**
     * 初始余额，不填为0。
     **/
    @JSONField(name = "init_balance")
    private String initBalance;

    @JSONField(name = "card_id")
    private String cardId;

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInitBonus() {
        return initBonus;
    }

    public void setInitBonus(String initBonus) {
        this.initBonus = initBonus;
    }

    public String getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(String initBalance) {
        this.initBalance = initBalance;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "ActivateCardVo{" + "membershipNumber='" + membershipNumber + '\'' + ", code='" +
               code + '\'' + ", initBonus='" + initBonus + '\'' + ", initBalance='" + initBalance +
               '\'' + ", cardId='" + cardId + '\'' + '}';
    }
}
