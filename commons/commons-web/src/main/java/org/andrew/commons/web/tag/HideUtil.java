package org.andrew.commons.web.tag;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 部分字符串*代替-->隐藏效果。
 * Created by andrewliu on 2017/6/27.
 */
public class HideUtil {
    private static final Logger log = LoggerFactory.getLogger(HideUtil.class);

    private static final String hide = "*";

    /**
     * 部分字符串用*代替。
     *
     * @param pre  前置
     * @param suf  后置
     * @param text 内容
     * @return 加密结果
     */
    public static String getHideString(int pre, int suf, String text) {
        return getHideString(pre, suf, text, hide);
    }

    /**
     * 部分字符串用*代替。
     *
     * @param pre      前置
     * @param suf      后置
     * @param text     内容
     * @param hideText 加密替换符合
     * @return 加密结果
     */
    public static String getHideString(int pre, int suf, String text, String hideText) {
        if (hideText == null || "".equals(hideText)) {
            hideText = hide;
        }
        log.debug("需要加密的值：{}", text);
        log.info("加密规则pre:{},suf:{},hideText:" + hideText + "", pre, suf);
        String reText;
        if (StringUtils.isBlank(text) || text.length() <= (pre + suf)) {
            reText = text;
        } else {
            reText = text.substring(0, pre) +
                     text.substring(pre, text.length() - suf).replaceAll("[^\\s]", hideText) +
                     text.substring(text.length() - suf, text.length());
        }
        log.info("加密结果：{}", reText);
        return reText;
    }
}
