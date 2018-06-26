package org.andrew.commons.wechat.model;

/**
 * auth2 token信息，网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同。
 *
 * @author andrewliu
 */
public class Auth2TokenInfo extends WechatToken {

    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID。
     */
    private String openId;
    /**
     * 用户授权的作用域，使用逗号（,）分隔。
     */
    private String scope;
    /**
     * 用户刷新access_token。
     */
    private String refreshToken;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "Auth2TokenInfo{" + "openId='" + openId + '\'' + ", scope='" + scope + '\'' +
               ", refreshToken='" + refreshToken + '\'' + ", unionid='" + unionid + '\'' + "} " +
               super.toString();
    }
}
