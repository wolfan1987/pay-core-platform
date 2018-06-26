package org.andrew.commons.wechat.service;

/**
 * 微信auth2认证处理。
 *
 * @author andrewliu
 */
public interface Auth2Service {

    /**
     * 获取微信认证地址。
     *
     * @param appId       公众号id
     * @param redirectUrl 回调地址
     * @param state       状态码
     * @return 返回地址。
     */
    String getAuth2Url(String appId, String redirectUrl, String state);
}
