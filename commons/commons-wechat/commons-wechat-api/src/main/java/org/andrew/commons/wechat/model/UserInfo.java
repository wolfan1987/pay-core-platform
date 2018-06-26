package org.andrew.commons.wechat.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信用户详情。
 *
 * @author andrewliu
 */
public class UserInfo implements Serializable {

    /**
     * 用户的标识，对当前公众号唯一。
     */
    private String openid;

    /**
     * 用户的昵称。
     */
    private String nickName;

    /**
     * 用户的性别。
     * 值为1时是男性，
     * 值为2时是女性，
     * 值为0时是未知
     */
    private Integer sex;

    /**
     * 用户所在城市。
     */
    private String city;

    /**
     * 用户所在国家。
     */
    private String country;

    /**
     * 用户所在省份。
     */
    private String province;

    /**
     * 用户的语言，简体中文为zh_CN。
     */
    private String language;

    /**
     * 用户头像，最后一个数值代表正方形头像大小
     * （有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。
     * 若用户更换头像，
     * 原有头像URL将失效。
     */
    private String headimgurl;

    /**
     * 用户关注时间，为时间戳。
     * 如果用户曾多次关注，则取最后关注时间
     */
    @JSONField(name = "subscribe_time")
    private Long subscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 详见：获取用户个人信息（UnionID机制）
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者。
     * 可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID。
     */
    private Integer groupid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "openid='" + openid + '\'' + ", nickName='" + nickName + '\'' +
               ", sex=" + sex + ", city='" + city + '\'' + ", country='" + country + '\'' +
               ", province='" + province + '\'' + ", language='" + language + '\'' +
               ", headimgurl='" + headimgurl + '\'' + ", subscribeTime=" + subscribeTime +
               ", unionid='" + unionid + '\'' + ", remark='" + remark + '\'' + ", groupid=" +
               groupid + '}';
    }
}
