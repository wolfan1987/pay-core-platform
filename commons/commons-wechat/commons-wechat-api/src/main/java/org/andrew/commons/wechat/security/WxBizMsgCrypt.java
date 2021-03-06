package org.andrew.commons.wechat.security;


import org.andrew.commons.wechat.exception.AesException;
import org.andrew.commons.wechat.utils.XmlParse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * 提供接收和推送给公众平台消息的加解密接口(UTF8编码的字符串)。
 *
 * @author andrewliu
 */
public class WxBizMsgCrypt {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxBizMsgCrypt.class);

    static Charset CHARSET = Charset.forName("utf-8");
    Base64 base64 = new Base64();
    byte[] aesKey;
    String token;
    String appId;
    /**
     * 时间戳。
     * 可以自己生成，也可以用URL参数的timestamp
     */
    String timestamp;
    /**
     * 随机串，可以自己生成，也可以用URL参数的nonce。
     */
    String nonce;
    /**
     * 加密类型。
     */
    String encryptType;

    /**
     * 构造方法。
     *
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    public WxBizMsgCrypt(String token, String encodingAesKey, String appId)
        throws RuntimeException {
        if (encodingAesKey.length() != 43) {
            throw new RuntimeException("SymmetricKey非法");
        }
        this.token = token;
        this.appId = appId;
        aesKey = Base64.decodeBase64(encodingAesKey + "=");
    }

    /**
     * 生成4个字节的网络字节序。
     *
     * @param sourceNumber sourceNumber
     * @return byte[]
     */
    byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }

    /**
     * 还原4个字节的网络字节序。
     *
     * @param orderBytes orderBytes
     * @return 返回字节码
     */
    int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * 随机生成16位字符串。
     *
     * @return 返回随机码
     */
    String getRandomStr() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 对明文进行加密。
     *
     * @param text 需要加密的明文
     * @return 加密后base64编码的字符串
     */
    String encrypt(String randomStr, String text) {
        ByteGroup byteCollector = new ByteGroup();
        byte[] randomStrBytes = randomStr.getBytes(CHARSET);
        byte[] textBytes = text.getBytes(CHARSET);
        byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
        byte[] appidBytes = appId.getBytes(CHARSET);

        // randomStr + networkBytesOrder + text + appid
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(textBytes);
        byteCollector.addBytes(appidBytes);

        //使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = Pkcs7Encoder.encode(byteCollector.size());
        byteCollector.addBytes(padBytes);

        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.toBytes();

        try {
            // 设置加密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            // 加密
            byte[] encrypted = cipher.doFinal(unencrypted);

            // 使用BASE64对加密后的字符串进行编码
            String base64Encrypted = base64.encodeToString(encrypted);

            return base64Encrypted;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException |
            NoSuchAlgorithmException | BadPaddingException |
            IllegalBlockSizeException | NoSuchPaddingException ex) {
            LOGGER.error("encrypt error.", ex);
            throw new RuntimeException("签名验证错误");
        }
    }

    /**
     * 对密文进行解密。
     *
     * @param text 需要解密的密文
     * @return 解密得到的明文
     * @throws AesException aes解密失败
     */
    String decrypt(String text) throws AesException {
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));

            LOGGER.info("----------------------------------------" + cipher);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            LOGGER.info(text);
            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(text);

            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException("decrypt error", ex);
        }

        String xmlContent;
        String fromAppid;
        try {
            // 去除补位字符
            byte[] bytes = Pkcs7Encoder.decode(original);

            // 分离16位随机字符串,网络字节序和AppId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

            int xmlLength = recoverNetworkBytesOrder(networkOrder);

            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            fromAppid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                                   CHARSET);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new AesException(AesException.IllegalBuffer);
        }

        // appid不相同的情况
        if (!fromAppid.equals(appId)) {
            throw new AesException(AesException.ValidateAppidError);
        }
        return xmlContent;

    }

    /**
     * 将公众平台回复用户的消息加密打包。
     * <ol>
     * <li>对要发送的消息进行AES-CBC加密</li>
     * <li>生成安全签名</li>
     * <li>将消息密文和安全签名打包成xml格式</li>
     * </ol>
     *
     * @param replyMsg  公众平台待回复用户的消息，xml格式的字符串
     * @param timeStamp 时间戳，可以自己生成，也可以用URL参数的timestamp
     * @param nonce     随机串，可以自己生成，也可以用URL参数的nonce
     * @return 加密后的可以直接回复用户的密文，包括msg_signature, timestamp, nonce, encrypt的xml格式的字符串
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public String encryptMsg(String replyMsg, String timeStamp, String nonce) throws Exception {
        // 加密
        String encrypt = encrypt(getRandomStr(), replyMsg);
        // 生成安全签名
        if ("".equals(timeStamp)) {
            timeStamp = Long.toString(System.currentTimeMillis());
        }
        String signature = Sha1.getSha1(token, timeStamp, nonce, encrypt);
        // 生成发送的xml
        String result = XmlParse.generate(encrypt, signature, timeStamp, nonce);
        return result;
    }

    /**
     * 检验消息的真实性，并且获取解密后的明文。
     * <ol>
     * <li>利用收到的密文生成安全签名，进行签名验证</li>
     * <li>若验证通过，则提取xml中的加密消息</li>
     * <li>对消息进行解密</li>
     * </ol>
     *
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @param postData     密文，对应POST请求的数据
     * @return 解密后的原文
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData)
        throws Exception {

        // 密钥，公众账号的app secret
        // 提取密文
        Object[] encrypt = XmlParse.extract(postData);
        // 验证安全签名
        String signature = Sha1.getSha1(token, timeStamp, nonce, encrypt[1].toString());
        // 和URL中的签名比较是否相等
        // System.out.println("第三方收到URL中的签名：" + msg_sign);
        // System.out.println("第三方校验签名：" + signature);
        if (!signature.equals(msgSignature)) {
            throw new Exception("签名验证错误");
        }
        LOGGER.info("密文内容：{}", encrypt.toString());
        // 解密
        String result = decrypt(encrypt[1].toString());
        return result;
    }

    /**
     * 验证URL。
     *
     * @param msgSignature 签名串，对应URL参数的msg_signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @param echoStr      随机串，对应URL参数的echostr
     * @return 解密之后的echostr
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public String verifyUrl(String msgSignature, String timeStamp, String nonce, String echoStr)
        throws Exception {
        String signature = Sha1.getSha1(token, timeStamp, nonce, echoStr);

        if (!signature.equals(msgSignature)) {
            throw new RuntimeException("签名验证错误");
        }

        String result = decrypt(echoStr);
        return result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

}
