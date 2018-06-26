package org.andrew.commons.wechat.constants;

/**
 * 记录微信相关的url。
 *
 * @author andrewliu
 */
public interface UrlConstant {


    /**
     * 微信认证url。
     * 采用scope=snsapi_base
     * 不弹出授权界面的方式处理
     * 注：redirectURL参数必须用encode处理
     */
    String AUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={appId}&redirect_uri={redirectURL}&response_type=code&scope=snsapi_userinfo&state={state}#wechat_redirect";

    /**
     * 这里通过code换取的是一个特殊的网页授权access_token,
     * 与基础支持中的access_token（该access_token用于调用其他接口）不同。
     * 公众号可通过下述接口来获取网页授权access_token。
     * 如果网页授权的作用域为snsapi_base，
     * 则本步骤中获取到网页授权access_token的同时，
     * 也获取到了openid，snsapi_base式的网页授权流程即到此为止。
     */
    String AUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code";

    /**
     * 验证auth2的token是否有效。
     */
    String AUTH2_VALIDATE_URL = "https://api.weixin.qq.com/sns/auth?access_token={token}&openid={openId}";

    /**
     * 网页授权获取用户信息。
     */
    String AUTH_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token={token}&openid={openId}&lang=zh_CN";

    /**
     * 刷新auth2的token。
     */
    String AUTH2_REFLUSH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={appId}&grant_type=refresh_token&refresh_token={refreshToken}";

    /**
     * 获取token的url。
     */
    String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";

    /**
     * 获取用户信息的url。
     */
    String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={token}&openid={openid}&lang=zh_CN";

    /**
     * 获取js接口所需要的微信临时票据。
     */
    String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={token}&type=jsapi";

    /**
     * 微信卡券票据。
     */
    String WX_CARD_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={token}&type=wx_card";
}
