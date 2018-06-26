package org.andrew.commons.wechat.service;


import org.andrew.commons.wechat.model.Auth2TokenInfo;

/**
 * 微信auth2 token service。
 *
 * @author andrewliu
 */
public interface Auth2TokenService {


    /**
     * 刷新auth2的Token。
     *
     * @param appId        appId
     * @param reflushToken reflushToken
     * @return Auth2TokenInfo
     */
    Auth2TokenInfo flushToken(String appId, String reflushToken);

    /**
     * 判断assessToken是否有效。
     *
     * @param appId  appId
     * @param openId openId
     * @return token是否有效 true有效，false无效
     */
    boolean isValid(String appId, String openId);

    /**
     * 请求微信服务端获取auth2的token。
     *
     * @param appId  appId
     * @param secret secret
     * @param code   code
     * @return Auth2TokenInfo
     */
    Auth2TokenInfo requestToken(String appId, String secret, String code);
}
