package org.andrew.commons.web.util;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * 图片工具类。
 *
 * @author andrewliu
 */
public class ImageUtil {

    /**
     * base64解码。
     *
     * @param str base64字符串
     * @return byte[]
     */
    public static byte[] decode(String str) {
        Base64 decoder = new Base64();
        byte[] result = decoder.decodeBase64(str);
        return result;
    }

    /**
     * 生成图片名称。
     *
     * @return String 图片名称
     */
    public static String getFileName() {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + ".jpg";
        return fileName;
    }

    /**
     * 生成缩略图名称。
     *
     * @param fileName 图片名称
     * @return String 返回的名称
     */
    public static String toThumbnail(String fileName) {
        return fileName + "_thumbnail.jpg";
    }

}
