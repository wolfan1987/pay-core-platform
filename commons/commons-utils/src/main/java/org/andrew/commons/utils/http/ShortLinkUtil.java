package org.andrew.commons.utils.http;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

/**
 * 长链接转换为短链接工具类。
 * Created by andrewliu on 2017/7/21.
 */
public class ShortLinkUtil {
    private static final String apiUrl = "http://api.t.sina.com.cn/short_url/shorten.json";
    private static final String source = "3271760578";

    /**
     * 长连接转短连接。
     *
     * @param urlLong 长链接
     * @return 短链接
     * @throws Exception 转换失败异常
     */
    public static String getShortUrl(String urlLong) throws Exception {
        ShortLink shortLink = getShortLink(urlLong);
        if (shortLink != null) {
            return shortLink.getUrlShort();
        }
        return null;
    }

    /**
     * 长连接转短连接。
     *
     * @param urlLong 长链接
     * @return 短链接对象
     * @throws Exception 转换失败异常
     */
    public static ShortLink getShortLink(String urlLong) throws Exception {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("source", source);
        hashMap.put("url_long", urlLong);
        String back = HttpUtil.urlGet(apiUrl, hashMap);
        List<ShortLink> list = JSON.parseArray(back, ShortLink.class);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }
}
