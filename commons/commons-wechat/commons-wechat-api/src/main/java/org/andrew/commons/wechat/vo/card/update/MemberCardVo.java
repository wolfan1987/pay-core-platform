package org.andrew.commons.wechat.vo.card.update;

import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.model.card.BonusRule;
import org.andrew.commons.wechat.model.card.CustomCell;
import org.andrew.commons.wechat.model.card.CustomField;


import java.io.Serializable;

/**
 * 会员卡信息。
 *
 * @author andrewliu
 */
public class MemberCardVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 会员卡自定义卡面背景图。
     */
    @JSONField(name = "background_pic_url")
    private String backgroundPicUrl;

    /**
     * 是否支持积分，仅支持从false变为true，默认为false。
     * 需要审核。
     */
    @JSONField(name = "supply_bonus")
    private Boolean supplyBonus;

    /**
     * 积分清零规则。
     */
    @JSONField(name = "bonus_cleared")
    private String bonusCleared;

    @JSONField(name = "bonus_rules")
    private String bonusRules;

    /**
     * 积分信息类目跳转的url。
     */
    @JSONField(name = "bonus_url")
    private String bonusUrl;

    @JSONField(name = "balance_url")
    private String balanceUrl;

    /**
     * 基础信息。
     */
    @JSONField(name = "base_info")
    private BaseinfoUpdateVo baseinfoUpdateVo;

    /**
     * 是否支持储值，仅支持从false变为true，默认为false。
     * 需要审核。
     */
    @JSONField(name = "supply_balance")
    private Boolean supplyBalance;

    /**
     * 储值说明。
     */
    @JSONField(name = "balance_rules")
    private String balanceRules;

    /**
     * 特权说明。
     */
    @JSONField(name = "prerogative")
    private String prerogative;

    /**
     * 设置为true时，该卡将支持一键开卡详情见一键开卡。
     * 该选项与activate_url互斥。
     */
    @JSONField(name = "wx_activate")
    private Boolean wxActivate;

    /**
     * 是否开通自动激活，设置为true时用户领取会员卡自动设置为激活，
     * 详情见自动激活。
     */
    @JSONField(name = "auto_activate")
    private boolean autoActivate;

    /**
     * 激活链接。
     */
    @JSONField(name = "activate_url")
    private String activateUrl;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    @JSONField(name = "custom_field1")
    private CustomField customField1;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    @JSONField(name = "custom_field2")
    private CustomField customField2;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    @JSONField(name = "custom_field3")
    private CustomField customField3;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    @JSONField(name = "custom_cell1")
    private CustomCell customCell1;

    /**
     * 积分规则。
     */
    @JSONField(name = "bonus_rule")
    private BonusRule bonusRule;

    /**
     * 折扣，该会员卡享受的折扣优惠。
     * 需要审核。
     */
    @JSONField(name = "discount")
    private Integer discount;

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public Boolean isSupplyBonus() {
        return supplyBonus;
    }


    public void setSupplyBonus(Boolean supplyBonus) {
        this.supplyBonus = supplyBonus;
    }

    public String getBonusCleared() {
        return bonusCleared;
    }

    public void setBonusCleared(String bonusCleared) {
        this.bonusCleared = bonusCleared;
    }

    public String getBonusUrl() {
        return bonusUrl;
    }

    public void setBonusUrl(String bonusUrl) {
        this.bonusUrl = bonusUrl;
    }

    public String getBalanceUrl() {
        return balanceUrl;
    }

    public void setBalanceUrl(String balanceUrl) {
        this.balanceUrl = balanceUrl;
    }

    public BaseinfoUpdateVo getBaseinfoUpdateVo() {
        return baseinfoUpdateVo;
    }

    public void setBaseinfoUpdateVo(BaseinfoUpdateVo baseinfoUpdateVo) {
        this.baseinfoUpdateVo = baseinfoUpdateVo;
    }

    public Boolean isSupplyBalance() {
        return supplyBalance;
    }

    public void setSupplyBalance(Boolean supplyBalance) {
        this.supplyBalance = supplyBalance;
    }

    public String getBalanceRules() {
        return balanceRules;
    }

    public void setBalanceRules(String balanceRules) {
        this.balanceRules = balanceRules;
    }

    public String getPrerogative() {
        return prerogative;
    }

    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative;
    }

    public Boolean getWxActivate() {
        return wxActivate;
    }

    public void setWxActivate(Boolean wxActivate) {
        this.wxActivate = wxActivate;
    }

    public Boolean isAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(Boolean autoActivate) {
        this.autoActivate = autoActivate;
    }

    public String getActivateUrl() {
        return activateUrl;
    }

    public void setActivateUrl(String activateUrl) {
        this.activateUrl = activateUrl;
    }

    public CustomField getCustomField1() {
        return customField1;
    }

    public void setCustomField1(CustomField customField1) {
        this.customField1 = customField1;
    }

    public CustomField getCustomField2() {
        return customField2;
    }

    public void setCustomField2(CustomField customField2) {
        this.customField2 = customField2;
    }

    public CustomField getCustomField3() {
        return customField3;
    }

    public void setCustomField3(CustomField customField3) {
        this.customField3 = customField3;
    }

    public CustomCell getCustomCell1() {
        return customCell1;
    }

    public void setCustomCell1(CustomCell customCell1) {
        this.customCell1 = customCell1;
    }

    public BonusRule getBonusRule() {
        return bonusRule;
    }

    public void setBonusRule(BonusRule bonusRule) {
        this.bonusRule = bonusRule;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getSupplyBonus() {
        return supplyBonus;
    }


    public String getBonusRules() {
        return bonusRules;
    }

    public void setBonusRules(String bonusRules) {
        this.bonusRules = bonusRules;
    }

    public Boolean getSupplyBalance() {
        return supplyBalance;
    }


}
