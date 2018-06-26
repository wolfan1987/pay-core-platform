package org.andrew.commons.wechat.model;

import java.io.Serializable;

/**
 * 微信jssdk对应实体。
 *
 * @author andrewliu
 */
public class WechatJssdkConfig implements Serializable {

    private String appId;

    private Long timeStamp;

    private String nonceStr;

    private String signature;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "WechatJSSDKConfig{" + "appId='" + appId + '\'' + ", timeStamp=" + timeStamp +
               ", nonceStr='" + nonceStr + '\'' + ", signature='" + signature + '\'' + '}';
    }
}
