package org.andrew.commons.utils.http;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 短链接。
 * Created by andrewliu on 2017/7/21.
 */
public class ShortLink implements Serializable {

    @JSONField(name = "url_short")
    private String urlShort;

    @JSONField(name = "url_long")
    private String urlLong;

    @JSONField(name = "type")
    private String type;

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
