package org.andrew.commons.wechat.service;


import org.andrew.commons.wechat.model.WechatToken;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 微信token相关操作接口。
 *
 * @author andrewliu
 */
public interface TokenService {

    /**
     * 生成token。
     *
     * @param token 从微信获取到accessToken后组装的token对象
     * @return token对象
     */
    WechatToken add(RedisTemplate redisTemplate, WechatToken token);

    /**
     * 获取token,同时根据token生成ticket临时票据。
     *
     * @return token对象
     */
    WechatToken get(RedisTemplate redisTemplate);

    /**
     * 获取token字符串。
     *
     * @return token加密串
     */
    String getTokenStr(RedisTemplate redisTemplate);

    /**
     * 获取微信临时票据。
     *
     * @param token       accessToken
     * @param resetTicket 是否重置ticket，如果是true则获取新的ticket覆盖旧的ticket
     * @return 微信ticket
     */
    String getJsApiTicket(RedisTemplate redisTemplate, String token, boolean resetTicket);

    /**
     * 获取微信临时票据。
     * 如果redis中存在，则不获取新的票据
     *
     * @param token token令牌
     * @return ticket
     */
    String getJsApiTicket(RedisTemplate redisTemplate, String token);

    /**
     * 获取微信卡券临时票据。
     *
     * @param resetTicket 是否刷新票据
     * @return 卡券ticket
     */
    String getCardTicket(RedisTemplate redisTemplate, String token, boolean resetTicket);

    /**
     * 获取微信卡券临时票据。
     *
     * @param token accessToken
     * @return 卡券ticket字符串
     */
    String getCardTicketStr(RedisTemplate redisTemplate, String token);

    /**
     * 从微信服务器获取token，并且覆盖redis中token。
     * 一般用作为定时器
     */
    WechatToken getAndSetTokenRequest(
            RedisTemplate redisTemplate, String appid, String secrect, boolean isReflushToken);
}
