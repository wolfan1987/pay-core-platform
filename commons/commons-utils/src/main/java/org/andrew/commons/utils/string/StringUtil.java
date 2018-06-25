package org.andrew.commons.utils.string;

/**
 * 字符串处理工具类。
 *
 * @author andrewliu on 2017年4月28日下午3:40:56
 * @version 1.0.0
 */
public class StringUtil {

    public static final String nullToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 判断是否为空。
     *
     * @param str 需要判断的字符串
     * @return boolean 返回是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断是否非空。
     *
     * @param str 需要判断的字符串
     * @return boolean 返回是否非空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
