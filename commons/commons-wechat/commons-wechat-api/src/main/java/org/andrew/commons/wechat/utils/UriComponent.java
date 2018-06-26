package org.andrew.commons.wechat.utils;



import org.andrew.commons.wechat.exception.WechatException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url转换相关工具。
 *
 * @author andrewliu
 */
public final class UriComponent {

    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    /**
     * url转换工具。
     *
     * @param url          地址
     * @param urlVariables 参数
     * @return 返回地址
     */
    public static String restUrl(String url, Object... urlVariables) throws Exception {
        if (urlVariables == null) {
            return url;
        }
        Iterator<Object> itor = Arrays.asList(urlVariables).iterator();
        Matcher matcher = NAMES_PATTERN.matcher(url);
        while (matcher.find()) {
            String source = matcher.group();
            if (itor.hasNext()) {
                Object obj = itor.next();
                url = url.replace(source, obj.toString());
            } else {
                throw new WechatException("参数位数不够");
            }
        }
        return url;
    }
}
