package org.andrew.commons.web.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * RAS公钥私钥创建。
 * Created by liudaan on 2017/9/21.
 */
public class CreateSecrteKey {

    public static final  String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY    = "RSAPublicKey";
    private static final String PRIVATE_KEY   = "RSAPrivateKey";

    /**
     * 获得公钥。
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        //获得map中的公钥对象 转为key对象
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        //byte[] publicKey = key.getEncoded();
        //编码返回字符串
        return encryptBase64(key.getEncoded()).replaceAll("\r\n", "");
    }

    /**
     * 获得私钥。
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        //获得map中的私钥对象 转为key对象
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        //byte[] privateKey = key.getEncoded();
        //编码返回字符串
        return encryptBase64(key.getEncoded()).replaceAll("\r\n", "");
    }

    /**
     * 解码返回byte。
     *
     * @param key 键值
     * @return byte数组
     * @throws Exception 异常
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * 编码返回字符串。
     *
     * @param key 键值
     * @return 字符串
     * @throws Exception 异常
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * map对象中存放公私钥。
     *
     * @return map集合
     * @throws Exception 异常
     */
    public static Map<String, Object> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    //    public static void main(String[] args) {
    //        Map<String, Object> keyMap;
    //        try {
    //            keyMap = initKey();
    //            String publicKey = getPublicKey(keyMap);
    //            System.out.println(publicKey);
    //            String privateKey = getPrivateKey(keyMap);
    //            System.out.println(privateKey);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
}
