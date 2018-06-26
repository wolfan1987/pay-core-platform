package org.andrew.commons.wechat.model.card;

import java.io.Serializable;

/**
 * 自定义会员信息类目，会员卡激活后显示。
 *
 * @author andrewliu
 */
public class CustomCell implements Serializable {

    /**
     * 入口名称。
     */
    private String name;

    /**
     * 入口右侧提示语，6个汉字内。
     * 如：激活后显示
     */
    private String tips;

    /**
     * 入口跳转链接。
     */
    private String url;

    public CustomCell() {}

    /**
     * 构造函数。
     */
    public CustomCell(String name, String tips, String url) {
        this.name = name;
        this.tips = tips;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CustomCell{" + "name='" + name + '\'' + ", tips='" + tips + '\'' + ", url='" + url +
               '\'' + '}';
    }
}
