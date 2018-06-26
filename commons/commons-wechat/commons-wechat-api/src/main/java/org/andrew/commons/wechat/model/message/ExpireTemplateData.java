package org.andrew.commons.wechat.model.message;

import java.io.Serializable;

/**
 * Created by zxy on 2018/5/8.。
 */
public class ExpireTemplateData implements Serializable {

    private TemplateParam first;
    private TemplateParam name;
    private TemplateParam expDate;
    private TemplateParam remark;

    public TemplateParam getFirst() {
        return first;
    }

    public void setFirst(TemplateParam first) {
        this.first = first;
    }

    public TemplateParam getName() {
        return name;
    }

    public void setName(TemplateParam name) {
        this.name = name;
    }

    public TemplateParam getExpDate() {
        return expDate;
    }

    public void setExpDate(TemplateParam expDate) {
        this.expDate = expDate;
    }

    public TemplateParam getRemark() {
        return remark;
    }

    public void setRemark(TemplateParam remark) {
        this.remark = remark;
    }
}
