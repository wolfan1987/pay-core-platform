package org.andrew.commons.wechat.vo;

import java.io.Serializable;

/**
 * 用户领取的卡信息。
 *
 * @author andrewliu
 */
public class GetCardVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String cardId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
