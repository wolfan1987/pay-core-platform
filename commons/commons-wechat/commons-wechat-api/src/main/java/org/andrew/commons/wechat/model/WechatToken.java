package org.andrew.commons.wechat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信token实体。
 *
 * @author andrewliu
 */
public class WechatToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long   id;
    /**
     * 微信token。
     */
    private String token;
    /**
     * 时间/秒。
     */
    private Long   expire;
    /**
     * 创建时间。
     */
    private Date   createTime;

    /**
     * 超时时间。
     */
    private Date expireTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }


    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 验证是否在有效期。
     */
    public boolean isValid() {
        if (this.createTime == null) {
            return false;
        }
        long nowDateTime = new Date().getTime();
        long expireTme = this.createTime.getTime() + expire * 1000;
        return expireTme > nowDateTime;
    }

    @Override
    public String toString() {
        return "WechatToken{" + "id=" + id + ", token='" + token + '\'' + ", expire=" + expire +
               ", createTime=" + createTime + ", expireTime=" + expireTime + '}';
    }
}
