package org.andrew.commons.web.util;



import org.andrew.commons.web.prop.PropsUtil;

import java.util.Properties;

/**
 * 读取微信配置文件。
 *
 * @author andrewliu
 */
public interface WxProReader {

    Properties configProps = PropsUtil.loadProps("config/wx.properties");

    String APPID = PropsUtil.getString(configProps, "wechat.appid");

    String APPID_SECRECT = PropsUtil.getString(configProps, "wechat.appid.secrect");

    String ENCODING_AES_KEY = PropsUtil.getString(configProps, "wechat.encoding.aes.key");
}
