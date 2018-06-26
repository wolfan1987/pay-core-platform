package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.constants.DateInfoEnum;


import java.io.Serializable;

/**
 * 使用日期，有效期的信息。
 *
 * @author andrewliu
 */
public class DateInfo implements Serializable {

    public DateInfo() {
    }

    /**
     * 构造。
     *
     * @param builder Builder
     */
    public DateInfo(Builder builder) {
        this.type = builder.type;
        this.beginTimestamp = builder.beginTimestamp;
        this.endTimestamp = builder.endTimestamp;
        this.fixedTerm = builder.fixedTerm;
        this.fixedBeginTerm = builder.fixedBeginTerm;
    }

    /**
     * 使用时间的类型。
     */
    private DateInfoEnum type;

    /**
     * type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。
     * 从1970年1月1日00:00:00至起用时间的秒数，
     * 最终需转换为字符串形态传入。（东八区时间，单位为秒）
     */
    @JSONField(name = "begin_timestamp")
    private Long beginTimestamp;

    /**
     * type为DATE_TYPE_FIX_TIME_RANGE时，表示卡券统一的结束时间，
     * 建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
     */
    @JSONField(name = "end_timestamp")
    private Long endTimestamp;

    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。
     */
    @JSONField(name = "fixed_term")
    private Integer fixedTerm;

    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效。（单位为天）
     */
    @JSONField(name = "fixed_begin_term")
    private Integer fixedBeginTerm;

    public DateInfoEnum getType() {
        return type;
    }

    public void setType(DateInfoEnum type) {
        this.type = type;
    }

    public Long getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(Long beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Integer getFixedTerm() {
        return fixedTerm;
    }

    public void setFixedTerm(Integer fixedTerm) {
        this.fixedTerm = fixedTerm;
    }

    public Integer getFixedBeginTerm() {
        return fixedBeginTerm;
    }

    public void setFixedBeginTerm(Integer fixedBeginTerm) {
        this.fixedBeginTerm = fixedBeginTerm;
    }

    @Override
    public String toString() {
        return "DateInfo{" + "type=" + type + ", beginTimestamp=" + beginTimestamp +
               ", endTimestamp=" + endTimestamp + ", fixedTerm=" + fixedTerm + ", fixedBeginTerm=" +
               fixedBeginTerm + '}';
    }

    public static class Builder {

        private DateInfoEnum type;
        private Long         beginTimestamp;
        private Long         endTimestamp;
        private Integer      fixedTerm;
        private Integer      fixedBeginTerm;

        public Builder type(DateInfoEnum type) {
            this.type = type;
            return this;
        }

        public Builder beginTimestamp(Long beginTimestamp) {
            this.beginTimestamp = beginTimestamp;
            return this;
        }

        public Builder endTimestamp(Long endTimestamp) {
            this.endTimestamp = endTimestamp;
            return this;
        }

        public Builder fixedTerm(int fixedTerm) {
            this.fixedTerm = fixedTerm;
            return this;
        }

        public Builder fixedBeginTerm(int fixedBeginTerm) {
            this.fixedBeginTerm = fixedBeginTerm;
            return this;
        }

        public DateInfo build() {
            return new DateInfo(this);
        }
    }
}
