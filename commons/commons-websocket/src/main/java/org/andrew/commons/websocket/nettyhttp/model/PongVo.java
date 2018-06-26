package org.andrew.commons.websocket.nettyhttp.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * websocket心跳命令对象。
 *
 * @author Created by andrewliu on 2017/10/9.
 */
public class PongVo implements Serializable {

    @JSONField(name = "type")
    private Integer type = 99;//心跳回复类型99

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PongVo{" + "type=" + type + '}';
    }
}
