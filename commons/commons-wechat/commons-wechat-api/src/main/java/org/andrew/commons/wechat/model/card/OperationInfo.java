package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * 卡券可选信息。
 *
 * @author andrewliu
 */
public class OperationInfo implements Serializable {

    /**
     * 是否自定义Code码。填写true或false，默认为false。
     */
    @JSONField(name = "use_custom_code")
    private Boolean useCustomCode;

    /**
     * 是否指定用户领取，填写true或false。默认为false。
     */
    @JSONField(name = "bind_openid")
    private Boolean bindOpenid;

    /**
     * 客服电话。
     */
    @JSONField(name = "service_phone")
    private String servicePhone;

    /**
     * 门店位置poiid。调用POI门店管理接口获取门店位置poiid。具备线下门店的商户为必填。
     */
    @JSONField(name = "location_id_list")
    private List<String> locationIdList;

    /**
     * 会员卡是否支持全部门店，填写后商户门店更新时会自动同步至卡券。
     */
    @JSONField(name = "use_all_locations")
    private Boolean useAllLocations;

    /**
     * 第三方来源名，例如同程旅游、大众点评。
     */
    private String source;

    /**
     * 自定义跳转外链的入口名字。
     */
    @JSONField(name = "custom_url_name")
    private String customUrlName;

    /**
     * 自定义跳转的URL。
     * "xxxx.com"
     */
    @JSONField(name = "custom_url")
    private String customUrl;

    /**
     * 显示在入口右侧的提示语。
     * 如：更多惊喜
     */
    @JSONField(name = "custom_url_sub_title")
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称,
     * 如：产品介绍。
     */
    @JSONField(name = "promotion_url_name")
    private String promotionUrlName;

    @JSONField(name = "center_title")
    private String centerTitle;

    @JSONField(name = "center_url")
    private String centerUrl;

    @JSONField(name = "center_sub_title")
    private String centerSubTitle;

    /**
     * XXXX.com
     * 入口跳转外链的地址链接。
     */
    @JSONField(name = "promotion_url")
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。
     * 如：卖场大优惠。
     */
    @JSONField(name = "promotion_url_sub_title")
    private String promotionUrlSubTitle;

    /**
     * 每人可领券的数量限制,不填写默认为50。
     * 因为系统购买之后，只能给购买人领取，所以限制默认为1
     */
    @JSONField(name = "get_limit")
    private Integer getLimit = 1;

    /**
     * 卡券领取页面是否可分享。
     */
    @JSONField(name = "can_share")
    private Boolean canShare = false;

    /**
     * 卡券是否可转赠。
     */
    @JSONField(name = "can_give_friend")
    private Boolean canGiveFriend = false;

    /**
     * 填写true为用户点击进入会员卡时推送事件，默认为false。
     */
    @JSONField(name = "need_push_on_view")
    private Boolean needPushOnView;

    public Boolean getUseCustomCode() {
        return useCustomCode;
    }

    public void setUseCustomCode(Boolean useCustomCode) {
        this.useCustomCode = useCustomCode;
    }

    public Boolean getBindOpenid() {
        return bindOpenid;
    }

    public void setBindOpenid(Boolean bindOpenid) {
        this.bindOpenid = bindOpenid;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public List<String> getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(List<String> locationIdList) {
        this.locationIdList = locationIdList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public Boolean isNeedPushOnView() {
        return needPushOnView;
    }

    public void setNeedPushOnView(Boolean needPushOnView) {
        this.needPushOnView = needPushOnView;
    }

    public Boolean isUseCustomCode() {
        return useCustomCode;
    }

    public Boolean isBindOpenid() {
        return bindOpenid;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
    }

    public String getCenterUrl() {
        return centerUrl;
    }

    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
    }

    public String getCenterSubTitle() {
        return centerSubTitle;
    }

    public void setCenterSubTitle(String centerSubTitle) {
        this.centerSubTitle = centerSubTitle;
    }

    public Boolean isUseAllLocations() {
        return useAllLocations;
    }

    public void setUseAllLocations(Boolean useAllLocations) {
        this.useAllLocations = useAllLocations;
    }
}
