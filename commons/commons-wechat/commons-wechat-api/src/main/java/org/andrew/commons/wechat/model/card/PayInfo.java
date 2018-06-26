package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 支付机构体。
 *
 * @author andrewliu
 */
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "swipe_card")
    private SwipCard swipCard;

    public SwipCard getSwipCard() {
        return swipCard;
    }

    public void setSwipCard(SwipCard swipCard) {
        this.swipCard = swipCard;
    }
}
