package org.andrew.commons.wechat.vo.card.update;

import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.constants.CodeTypeEnum;
import org.andrew.commons.wechat.model.card.DateInfo;
import org.andrew.commons.wechat.model.card.PayInfo;


import java.io.Serializable;
import java.util.List;

/**
 * 更新基础信息。
 *
 * @author andrewliu
 */
public class BaseinfoUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 会员卡标题，字数上限为9个汉字。
     * 需要审核。
     */
    @JSONField(name = "title")
    private String title;

    /**
     * 卡券的商户logo，建议像素为300*300。
     */
    @JSONField(name = "logo_url")
    private String logoUrl;

    /**
     * 使用提醒，字数上限为16个汉字。
     */
    @JSONField(name = "notice")
    private String notice;

    /**
     * 使用说明。
     * 需要审核。
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 客服电话。
     */
    @JSONField(name = "service_phone")
    private String servicePhone;

    /**
     * 卡券颜色。
     */
    @JSONField(name = "color")
    private String color;

    /**
     * 支持更新适用门店列表。
     */
    @JSONField(name = "location_id_list")
    private List<String> localIdList;

    /**
     * 支持全部门店，填入后卡券门店跟随商户门店更新而更新。
     */
    @JSONField(name = "use_all_locations")
    private Boolean useAllLocations;

    /**
     * 支持全部门店，填入后卡券门店跟随商户门店更新而更新。
     */
    @JSONField(name = "center_title")
    private String centerTitle;

    /**
     * 会员卡中部的跳转按钮名称，建议用作使用用途。
     */
    @JSONField(name = "center_sub_title")
    private String centerSubTitle;

    /**
     * 会员卡中部按钮对应跳转的url。
     */
    @JSONField(name = "center_url")
    private String centerUrl;

    /**
     * 自定义跳转入口的名字。
     */
    @JSONField(name = "custom_url_name")
    private String customUrlName;

    /**
     * 自定义跳转的URL。
     */
    @JSONField(name = "custom_url")
    private String customUrl;

    /**
     * 显示在入口右侧的提示语。
     */
    @JSONField(name = "custom_url_sub_title")
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称。
     */
    @JSONField(name = "promotion_url_name")
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接。
     */
    @JSONField(name = "promotion_url")
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。
     */
    @JSONField(name = "promotion_url_sub_title")
    private String promotionUrlSubTitle;

    /**
     * Code码展示类型。
     */
    @JSONField(name = "code_type")
    private CodeTypeEnum codeType;

    /**
     * 支付功能结构体，swipe_card结构。
     */
    @JSONField(name = "pay_info")
    private PayInfo payInfo;

    /**
     * 每人可领券的数量限制。
     */
    @JSONField(name = "get_limit")
    private Integer getLimit;

    /**
     * 卡券原生领取页面是否可分享。
     */
    @JSONField(name = "can_share")
    private Boolean canShare;

    /**
     * 卡券是否可转赠。
     */
    @JSONField(name = "can_give_friend")
    private Boolean canGiveFriend;

    /**
     * 使用日期，有效期的信息，有效期时间修改仅支持有效区间的扩大。
     */
    @JSONField(name = "date_info")
    private DateInfo dateInfo;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getLocalIdList() {
        return localIdList;
    }

    public void setLocalIdList(List<String> localIdList) {
        this.localIdList = localIdList;
    }

    public Boolean isUseAllLocations() {
        return useAllLocations;
    }

    public void setUseAllLocations(Boolean useAllLocations) {
        this.useAllLocations = useAllLocations;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
    }

    public String getCenterSubTitle() {
        return centerSubTitle;
    }

    public void setCenterSubTitle(String centerSubTitle) {
        this.centerSubTitle = centerSubTitle;
    }

    public String getCenterUrl() {
        return centerUrl;
    }

    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
    }

    public String getCustomUrlName() {
        return customUrlName;
    }

    public void setCustomUrlName(String customUrlName) {
        this.customUrlName = customUrlName;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomUrlSubTitle() {
        return customUrlSubTitle;
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        this.customUrlSubTitle = customUrlSubTitle;
    }

    public String getPromotionUrlName() {
        return promotionUrlName;
    }

    public void setPromotionUrlName(String promotionUrlName) {
        this.promotionUrlName = promotionUrlName;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getPromotionUrlSubTitle() {
        return promotionUrlSubTitle;
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        this.promotionUrlSubTitle = promotionUrlSubTitle;
    }

    public CodeTypeEnum getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    public PayInfo getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfo payInfo) {
        this.payInfo = payInfo;
    }

    public Integer getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(Integer getLimit) {
        this.getLimit = getLimit;
    }

    public Boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public Boolean isCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }
}
