package org.andrew.commons.ecard.common;

import org.andrew.commons.web.prop.PropsUtil;

import java.util.Properties;

/**
 * 静态文件版本。
 *
 * @author andrewliu
 */
public interface H5VersionConfig {

    Properties configProps = PropsUtil.loadProps("config/h5Version.properties");

    String VERSION = PropsUtil.getString(configProps, "h5.version");
}
