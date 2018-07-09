package org.andrew.commons.wechat.service.impl;


import org.andrew.commons.wechat.constants.UrlConstant;
import org.andrew.commons.wechat.service.Auth2Service;
import org.andrew.commons.wechat.utils.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信auth2认证。
 *
 * @author andrewliu
 */
@Service
public class Auth2ServiceImpl implements Auth2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Auth2ServiceImpl.class);

    /**
     * 获取微信auth2跳转的url。
     *
     * @param appid       appid
     * @param redirectUrl redirectUrl
     * @param state       state
     * @return 拼接完成后的auth2跳转路径。
     */
    public String getAuth2Url(String appid, String redirectUrl, String state) {
        String url = UrlConstant.AUTH2_URL;
        try {
            url = UriComponent.restUrl(url, appid, redirectUrl, state);
        } catch (Exception exception) {
            LOGGER.error("转换uri出错", exception);
            return null;
        }
        return url;
    }

}
