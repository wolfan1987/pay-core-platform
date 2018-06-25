package org.andrew.commons.utils.string;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * MD5签名工具类
 * Created by longshuzhen on 2017/7/5。
 */
public class Md5Utils {
    /**
     * 根据传入参数进行MD5签名。
     *
     * @param signValueMap - 加密value值集合
     * @param signKeyNames - 加密key集和
     * @param key          - 加密key值
     * @return sign字符串
     */
    public static String sign(Map<String, String> signValueMap, String[] signKeyNames, String key) {
        String sign = "";
        if (signValueMap != null && signValueMap.size() > 0) {
            //先将加密key按字典排序
            Arrays.sort(signKeyNames);

            //组装签名串明文
            StringBuilder sb = new StringBuilder("");
            for (String name : signKeyNames) {
                if (!name.equals("sign") && !name.equals("key")) {
                    sb.append(name + "=" + signValueMap.get(name).toString().trim() + "&");
                }
            }
            //加上key
            sb.append("key=" + key);
            //返回加密串
            sign = md5Encode(sb.toString());
        }
        return sign;
    }

    /**
     * MD5加密。
     *
     * @param plainText 明文
     * @return 32位大写密文
     */
    public static String md5Encode(String plainText) {
        String remd5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("UTF-8"));
            byte[] bx = md.digest();

            int ii;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < bx.length; offset++) {
                ii = bx[offset];
                if (ii < 0) {
                    ii += 256;
                }
                if (ii < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(ii));
            }
            remd5 = buf.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return remd5.toUpperCase();
    }

}