package org.andrew.commons.wechat.service.impl;


import org.andrew.commons.wechat.constants.UrlConstant;
import org.andrew.commons.wechat.exception.WechatException;
import org.andrew.commons.wechat.model.WechatToken;
import org.andrew.commons.wechat.service.TokenService;
import org.andrew.commons.wechat.utils.WechatTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 处理微信token相关的操作。
 *
 * @author andrewliu
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final String tokenKey = "wechatTokenKey";

    private final String cardTicketKey = "wechatCardTicketKey";

    private final String ticketKey = "wechatTicketKey";

    @Override
    public WechatToken get(RedisTemplate redisTemplate) {
        WechatToken token = null;
        try {
            ValueOperations<String, WechatToken> valueOps = redisTemplate.opsForValue();
            token = valueOps.get(tokenKey);
            if (token != null && token.isValid()) {
                return token;
            }
        } catch (Exception ex) {
            logger.error("从redis中获取微信accessToken出错,{}", ex);
        }
        return token;
    }

    @Override
    public String getTokenStr(RedisTemplate redisTemplate) {
        WechatToken token = this.get(redisTemplate);
        if (token != null) {
            return token.getToken();
        }
        return null;
    }

    @Override
    public String getCardTicket(RedisTemplate redisTemplate, String tokenStr, boolean resetTicket) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String ticket;
        if (!resetTicket) {
            ticket = valueOps.get(cardTicketKey);
            if (!StringUtils.isEmpty(ticket)) {
                return ticket;
            }
        }
        ticket = WechatTokenUtil.getCardTicket(tokenStr);
        if (StringUtils.isEmpty(ticket)) {
            throw new WechatException("获取临时票据ticket失败");
        }
        valueOps.set(cardTicketKey, ticket);
        return ticket;
    }

    public String getCardTicketStr(RedisTemplate redisTemplate, String token) {
        return getCardTicket(redisTemplate, token, false);
    }

    @Override
    public WechatToken getAndSetTokenRequest(
        RedisTemplate redisTemplate, String appid, String secrect, boolean isReflushToken) {
        if (logger.isDebugEnabled()) {
            logger.debug("获取微信token");
        }
        WechatToken token = null;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("获取微信token,取得redis锁");
            }
            //是否需要重新设置token到redis
            boolean flag = false;
            if (isReflushToken) {
                token = WechatTokenUtil.getTokenRequest(UrlConstant.TOKEN_URL, appid, secrect);
                flag = true;
            } else {
                token = this.get(redisTemplate);
                if (token == null || !token.isValid()) {
                    token = WechatTokenUtil.getTokenRequest(UrlConstant.TOKEN_URL, appid, secrect);
                    flag = true;
                }
            }
            if (flag) {
                if (token != null) {
                    this.add(redisTemplate, token);
                }
            }
        } catch (Exception ex) {
            logger.info("从微信服务器获取token失败", ex);
            throw new RuntimeException(ex);
        }
        return token;
    }

    @Override
    public String getJsApiTicket(RedisTemplate redisTemplate, String tokenStr, boolean resetTicket)
        throws WechatException {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String ticket;
        if (!resetTicket) {
            ticket = valueOps.get(ticketKey);
            if (!StringUtils.isEmpty(ticket)) {
                return ticket;
            }
        }
        ticket = WechatTokenUtil.getJsApiTicket(tokenStr);
        if (StringUtils.isEmpty(ticket)) {
            throw new WechatException("获取临时票据ticket出错");
        }
        valueOps.set(ticketKey, ticket);
        return ticket;
    }

    @Override
    public String getJsApiTicket(RedisTemplate redisTemplate, String token) {
        return getJsApiTicket(redisTemplate, token, false);
    }

    @Override
    public WechatToken add(RedisTemplate redisTemplate, WechatToken token) {
        ValueOperations<String, WechatToken> valueOps = redisTemplate.opsForValue();
        valueOps.set(tokenKey, token);
        //redis超时时间比微信超时时间多50秒。
        // 防止token未到期被redis主动删除
        long redisExpire = token.getExpire() + 50;
        redisTemplate.expire(token.getExpire().toString(), redisExpire, TimeUnit.SECONDS);
        //如果token变更，重新获取ticket到redis中
        getJsApiTicket(redisTemplate, token.getToken(), true);
        getCardTicket(redisTemplate, token.getToken(), true);
        return token;
    }
}
