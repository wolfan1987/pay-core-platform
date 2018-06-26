package org.andrew.commons.wechat.vo.member.update;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 会员卡更新接收实体。
 *
 * @author andrewliu
 */
public class UserInfoUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卡券Code码。
     **/
    @NotBlank(message = "code cant be null")
    private String code;

    /**
     * 卡券ID。
     **/
    @NotBlank(message = "cardId cant be null")
    @JSONField(name = "card_id")
    private String cardId;

    /**
     * 支持商家激活时针对单个会员卡分配自定义的会员卡背景。
     */
    @JSONField(name = "background_pic_url  ")
    private String backgroundPicUrl;

    /**
     * 需要设置的积分全量值，传入的数值会直接显示，如果同时传入add_bonus和bonus,则前者无效。
     **/
    private Integer bonus;

    /**
     * 需要变更的积分，扣除积分用“-“表示。
     **/
    @JSONField(name = "add_bonus")
    private Integer addBounds;

    /**
     * 商家自定义积分消耗记录，不超过14个汉字。
     **/
    @JSONField(name = "record_bonus")
    private String recordBonus;

    /**
     * 需要设置的余额全量值，传入的数值会直接显示，如果同时传入add_balance和balance,则前者无效。
     **/
    private Long balance;

    /**
     * 需要变更的余额，扣除金额用“-”表示。单位为分。
     **/
    @JSONField(name = "add_balance")
    private Long addBalance;

    /**
     * 商家自定义金额消耗记录，不超过14个汉字。
     **/
    @JSONField(name = "record_balance")
    private String recordBalance;

    /**
     * 创建时字段custom_field1定义类型的最新数值，限制为4个汉字，12字节。
     **/
    @JSONField(name = "custom_field_value1")
    private String customFieldValue1;

    /**
     * 创建时字段custom_field2定义类型的最新数值，限制为4个汉字，12字节。
     **/
    @JSONField(name = "custom_field_value2")
    private String customFieldValue2;

    /**
     * 创建时字段custom_field2定义类型的最新数值，限制为4个汉字，12字节。
     **/
    @JSONField(name = "custom_field_value3")
    private String customFieldValue3;


    @JSONField(name = "notify_optional")
    private NotifyOptionalVo notifyOptionalVo;

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

    public Integer getAddBounds() {
        return addBounds;
    }

    public void setAddBounds(Integer addBounds) {
        this.addBounds = addBounds;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getRecordBonus() {
        return recordBonus;
    }

    public void setRecordBonus(String recordBonus) {
        this.recordBonus = recordBonus;
    }

    public Long getAddBalance() {
        return addBalance;
    }

    public void setAddBalance(Long addBalance) {
        this.addBalance = addBalance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getRecordBalance() {
        return recordBalance;
    }

    public void setRecordBalance(String recordBalance) {
        this.recordBalance = recordBalance;
    }

    public String getCustomFieldValue1() {
        return customFieldValue1;
    }

    public void setCustomFieldValue1(String customFieldValue1) {
        this.customFieldValue1 = customFieldValue1;
    }

    public String getCustomFieldValue2() {
        return customFieldValue2;
    }

    public void setCustomFieldValue2(String customFieldValue2) {
        this.customFieldValue2 = customFieldValue2;
    }

    public String getCustomFieldValue3() {
        return customFieldValue3;
    }

    public void setCustomFieldValue3(String customFieldValue3) {
        this.customFieldValue3 = customFieldValue3;
    }

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public NotifyOptionalVo getNotifyOptionalVo() {
        return notifyOptionalVo;
    }

    public void setNotifyOptionalVo(NotifyOptionalVo notifyOptionalVo) {
        this.notifyOptionalVo = notifyOptionalVo;
    }
}
