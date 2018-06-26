package org.andrew.commons.wechat.utils;

import com.alibaba.fastjson.JSONObject;

import org.andrew.commons.wechat.constants.UrlConstant;
import org.andrew.commons.wechat.model.WechatToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信票据和token管理。
 *
 * @author andrewliu
 */
public final class WechatTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatTokenUtil.class);

    private WechatTokenUtil() {
    }

    /**
     * 获取微信token。
     *
     * @param tokenUrl 获取token的url
     * @param appid    微信的appid
     * @param secrect  微信secrect
     * @return 微信token对应的实体
     * @throws Exception 抛出异常
     */
    public static WechatToken getTokenRequest(String tokenUrl, String appid, String secrect) {
        WechatToken token;
        RestTemplate rest = new RestTemplate();
        Map<String, Object> map = rest.getForObject(tokenUrl, Map.class, appid, secrect);
        String result = JSONObject.toJSONString(map);
        LOGGER.info("获取token返回结果：{}", result);
        if (map.get("access_token") != null) {
            token = new WechatToken();
            String accessToken = map.get("access_token").toString();
            long expireIn = Long.parseLong(map.get("expires_in").toString());
            token.setToken(accessToken);
            //设置为比微信提前200秒失效，失效前200秒对微信token进行更新
            token.setExpire(expireIn - 200);
            token.setCreateTime(new Date());
            return token;
        } else {
            throw new RuntimeException(String.format("获取微信token出错:%s", result));
        }
    }

    /**
     * 获取用户基本信息。
     * subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * openid 用户的标识，对当前公众号唯一。
     * nickname 用户的昵称。
     * sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知。
     * city 用户所在城市。
     * country 用户所在国家。
     * province 用户所在省份。
     * language 用户的语言，简体中文为zh_CN。
     * headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * subscribe_time 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间。
     * unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注。
     * groupid 用户所在的分组ID（兼容旧的用户分组接口）。
     * tagid_list 用户被打上的标签ID列表。
     *
     * @param accessToken 访问的token
     * @param openid      openid
     * @return map 用户基本信息
     */
    public static Map<String, Object> getInfo(String accessToken, String openid) {
        Map<String, Object> map = new HashMap<>();
        RestTemplate rest = new RestTemplate();
        Map<String, Object> result = rest.getForObject(
            UrlConstant.GET_USER_INFO_URL, Map.class, accessToken, openid);
        if (result != null) {
            map = result;
        }
        return map;
    }

    /**
     * 远程获取微信临时票据ticket。
     *
     * @param token token
     * @return 返回结果
     */
    public static String getJsApiTicket(String token) {
        RestTemplate rest = new RestTemplate();
        //接收返回的json字符串
        String ticket = rest.postForObject(
            UrlConstant.GET_JSAPI_TICKET_URL, null, String.class, token);
        //正则表达式截取返回json中的ticket值
        Pattern pattern = Pattern.compile("\"ticket\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(ticket);
        if (matcher.find()) {
            ticket = matcher.group(1);
            return ticket;
        }
        return "";
    }

    /**
     * 获取微信卡券票据。
     *
     * @param token token
     * @return 返回票据
     */
    public static String getCardTicket(String token) {
        RestTemplate rest = new RestTemplate();
        String ticket = rest.postForObject(
            UrlConstant.WX_CARD_TICKET_URL, null, String.class, token);
        //正则表达式截取返回json中的ticket值
        Pattern pattern = Pattern.compile("\"ticket\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(ticket);
        if (matcher.find()) {
            ticket = matcher.group(1);
            return ticket;
        }
        return "";
    }
}
