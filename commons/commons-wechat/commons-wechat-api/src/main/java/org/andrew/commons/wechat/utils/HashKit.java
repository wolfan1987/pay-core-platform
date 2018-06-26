package org.andrew.commons.wechat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha1加密。
 *
 * @author andrewliu
 */
public final class HashKit {

    private static final char[] LETTERS = "0123456789ABCDEF".toCharArray();

    public static String md5(String value) throws NoSuchAlgorithmException {
        return hash(MessageDigest.getInstance("md5"), value);
    }

    //加密字符串sha1 
    public static String sha1(String value) throws NoSuchAlgorithmException {
        return hash(MessageDigest.getInstance("SHA1"), value);
    }

    private static String hash(MessageDigest digest, String src) {
        return toHexString(digest.digest(src.getBytes()));
    }

    private static String toHexString(byte[] bytes) {
        char[] values = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            values[index++] = LETTERS[((b & 0xF0) >>> 4)];
            values[index++] = LETTERS[b & 0xF];
        }
        return String.valueOf(values);
    }

}
