package org.andrew.commons.database.dao;

import java.io.Serializable;

/**
 * 基础实体bean接口（序列化）。
 *
 * @author andrewliu 2017年4月18日下午5:17:43
 * @version 1.0.0
 */
public abstract interface Model<PKT extends Serializable> extends Serializable {
    public abstract PKT getKey();
}