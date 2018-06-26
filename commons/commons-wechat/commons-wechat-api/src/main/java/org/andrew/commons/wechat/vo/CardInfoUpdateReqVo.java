package org.andrew.commons.wechat.vo;



import org.andrew.commons.wechat.model.card.BonusRule;
import org.andrew.commons.wechat.model.card.CustomCell;
import org.andrew.commons.wechat.model.card.CustomField;

import java.io.Serializable;

/**
 * 更新会员卡字段。
 *
 * @author andrewliu
 */
public class CardInfoUpdateReqVo extends CardBaseInfoUpdateReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卡面。
     */
    private String backgroundPicUrl;

    /**
     * 是否支持积分，仅支持从false变为true，默认为false。
     * 需要审核。
     */
    private Boolean supplyBonus;

    /**
     * 积分清零规则
     * 如：每年年底12月30号积分清0。
     */
    private String bonusCleared;
    /**
     * 积分规则。
     * 如：每消费一元获取1点积分
     */
    private String bonusRules;

    /**
     * xxxx.com
     * 设置跳转外链查看积分详情。
     * 仅适用于积分无法通过激活接口同步的情况下使用该字段
     */
    private String bonusUrl;

    /**
     * 设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。
     * xxxx.com
     */
    private String  balanceUrl;
    /**
     * 是否支持储值，填写true或false。
     * 如填写true，储值相关字段均为必填
     */
    private Boolean supplyBalance;

    /**
     * 储值说明。
     * xxx.com
     */
    private String balanceRules;

    /**
     * 会员卡特权说明,
     * 如:持白金会员卡到店消费，可享8折优惠。
     */
    private String prerogative;

    /**
     * 设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。
     * 填入该字段后仍需调用接口设置开卡项方可生效
     */
    private Boolean wxActivate;

    private Boolean autoActivate;

    /**
     * 激活会员卡的url。
     * xxx.com
     */
    private String activateUrl;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    private CustomField customField1;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    private CustomField customField2;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    private CustomField customField3;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    private CustomCell customCell;

    /**
     * 积分规则。用于微信买单功能。
     */
    private BonusRule bonusRule;

    /**
     * 折扣，该会员卡享受的折扣优惠,填10就是九折。
     */
    private Integer discount;

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public Boolean getSupplyBonus() {
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

    public String getBonusRules() {
        return bonusRules;
    }

    public void setBonusRules(String bonusRules) {
        this.bonusRules = bonusRules;
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

    public Boolean getSupplyBalance() {
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

    public CustomCell getCustomCell() {
        return customCell;
    }

    public void setCustomCell(CustomCell customCell) {
        this.customCell = customCell;
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

    public Boolean getAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(Boolean autoActivate) {
        this.autoActivate = autoActivate;
    }
}
