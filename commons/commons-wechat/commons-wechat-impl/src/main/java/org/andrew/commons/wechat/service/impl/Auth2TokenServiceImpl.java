package org.andrew.commons.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.andrew.commons.wechat.constants.UrlConstant;
import org.andrew.commons.wechat.model.Auth2TokenInfo;
import org.andrew.commons.wechat.service.Auth2TokenService;
import org.andrew.commons.wechat.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

/**
 * 微信auth2认证token处理。
 *
 * @author andrewliu
 */
@Service
public class Auth2TokenServiceImpl implements Auth2TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Auth2TokenServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenService tokenService;

    private final String tokenKey = "wx_auth2_token:";

    private Auth2TokenInfo add(Auth2TokenInfo token) {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        String key = tokenKey.concat(token.getOpenId());
        opsForValue.set(key, token);
        return token;
    }

    @Override
    public Auth2TokenInfo flushToken(String appid, String reflushTOken) {
        RestTemplate rest = new RestTemplate();
        Map resultMap = rest.postForObject(UrlConstant.AUTH2_REFLUSH_TOKEN_URL, null, Map.class,
                                           appid, reflushTOken);
        String errorCode = resultMap.get("errcode").toString();
        if (errorCode != null && "0".equals(errorCode)) {
            LOGGER.info("刷新token出错", resultMap.get("errmsg").toString());
            return null;
        } else {
            Auth2TokenInfo auth2TokenInfo = new Auth2TokenInfo();
            auth2TokenInfo.setOpenId(resultMap.get("openid").toString());
            auth2TokenInfo.setToken(resultMap.get("access_token").toString());
            auth2TokenInfo.setExpire(Long.parseLong(resultMap.get("expires_in").toString()));
            auth2TokenInfo.setScope(resultMap.get("scope").toString());
            return auth2TokenInfo;
        }
    }

    @Override
    public boolean isValid(String appId, String openId) {
        RestTemplate rest = new RestTemplate();
        Map resultMap = rest.postForObject(
            UrlConstant.AUTH2_VALIDATE_URL, null, Map.class, appId, openId);
        Integer errcode = Integer.parseInt(resultMap.get("errcode").toString());
        if (errcode != 0) {
            LOGGER.info("auth2检测token失败", resultMap.get("errmsg").toString());
            return false;
        }
        return true;
    }

    /**
     * 从redis中获取auth2认证的token。
     *
     * @param openId openId
     * @return Auth2TokenInfo
     */
    public Auth2TokenInfo getTokenOnRedis(String openId) {
        ValueOperations<String, Auth2TokenInfo> opsForValue = redisTemplate.opsForValue();
        String key = tokenKey.concat(openId);
        return opsForValue.get(key);
    }

    @Override
    public Auth2TokenInfo requestToken(String appid, String secret, String code) {
        RestTemplate rest = new RestTemplate();
        String authUri = UrlConstant.AUTH2_TOKEN_URL;
        String jsonStr = rest.getForObject(authUri, String.class, appid, secret, code);
        Map resultMap = JSONObject.parseObject(jsonStr, Map.class);
        Auth2TokenInfo auth2TokenInfo;
        LOGGER.info("获取token返回结果：{}", JSONObject.toJSONString(resultMap));
        if (resultMap.get("access_token") != null) {
            auth2TokenInfo = new Auth2TokenInfo();
            String accessToken = resultMap.get("access_token").toString();
            String openid = resultMap.get("openid").toString();
            Object unionIdObj = resultMap.get("unionid");
            LOGGER.info("获取到的unionId:{}", unionIdObj);
            String unionId;
            if (unionIdObj == null) {
                String userInfoUri = UrlConstant.AUTH_GET_USER_INFO;
                String userInfoStr = rest.getForObject(
                    userInfoUri, String.class, accessToken, openid);
                LOGGER.info("获取到用户的信息：{}", userInfoStr);
                Map userInfo = JSONObject.parseObject(userInfoStr, Map.class);
                Object unIdObject = userInfo.get("unionid");
                LOGGER.info("获取到用户信息中的unionId:{}", unIdObject);
                unionId = unIdObject == null ? "" : unIdObject.toString();
            } else {
                unionId = unionIdObj.toString();
            }
            long expireIn = Long.parseLong(resultMap.get("expires_in").toString());
            auth2TokenInfo.setToken(accessToken);
            auth2TokenInfo.setRefreshToken(resultMap.get("refresh_token").toString());
            auth2TokenInfo.setOpenId(openid);
            auth2TokenInfo.setScope(resultMap.get("scope").toString());
            auth2TokenInfo.setUnionid(unionId);
            //设置为比微信提前200秒失效，失效前200秒对微信token进行更新
            auth2TokenInfo.setExpire(expireIn - 200);
            auth2TokenInfo.setCreateTime(new Date());
            this.add(auth2TokenInfo);
            return auth2TokenInfo;
        } else {
            LOGGER.info("获取微信token出错", resultMap.get("errmsg"));
            return null;
        }
    }
}
