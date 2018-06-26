package org.andrew.commons.wechat.exception;

/**
 * 微信框架异常。
 *
 * @author andrewliu
 */
public class WechatException extends RuntimeException {

    public WechatException(String message) {
        super(message);
    }

    public WechatException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
