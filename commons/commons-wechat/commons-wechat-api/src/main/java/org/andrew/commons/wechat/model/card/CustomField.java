package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.constants.NameTypeEnum;


/**
 * 自定义会员信息类目，会员卡激活后显示。
 *
 * @author andrewliu
 */
public class CustomField {

    @JSONField(name = "name_type")
    private NameTypeEnum nameType;

    @JSONField(name = "url")
    private String url;

    public CustomField() {}

    public CustomField(NameTypeEnum nameType, String url) {
        this.nameType = nameType;
        this.url = url;
    }

    public NameTypeEnum getNameType() {
        return nameType;
    }

    public void setNameType(NameTypeEnum nameType) {
        this.nameType = nameType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CustomField{" + "nameType='" + nameType + '\'' + ", url='" + url + '\'' + '}';
    }
}
