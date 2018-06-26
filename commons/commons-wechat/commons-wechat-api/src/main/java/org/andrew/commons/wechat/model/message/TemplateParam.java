package org.andrew.commons.wechat.model.message;

import java.io.Serializable;

/**
 * 模板消息对象。
 *
 * @author andrewliu
 */
public class TemplateParam implements Serializable {
    /**
     * 参数值。
     */
    private String value;

    /**
     * 颜色。
     */
    private String color;

    /**
     * 模板参数构造函数。
     */
    public TemplateParam(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

