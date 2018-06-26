package org.andrew.commons.wechat.vo;

import java.io.Serializable;

/**
 * 接收web端传递过来的配置信息。
 *
 * @author andrewliu
 */
public class EventHandlerVo implements Serializable {

    private String timestamp;

    private String nonce;

    private String appid;

    private String encodeAesKey;

    private String token;

    /**
     * 微信传递参数转换为xml。
     */
    private String fromXml;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getEncodeAesKey() {
        return encodeAesKey;
    }

    public void setEncodeAesKey(String encodeAesKey) {
        this.encodeAesKey = encodeAesKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFromXml() {
        return fromXml;
    }

    public void setFromXml(String fromXml) {
        this.fromXml = fromXml;
    }
}
