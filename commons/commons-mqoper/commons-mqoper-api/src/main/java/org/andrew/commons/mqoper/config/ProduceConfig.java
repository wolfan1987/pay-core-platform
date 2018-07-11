package org.andrew.commons.mqoper.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:   生产消息相关参数配置
 * @Date: Created in 2018/7/9 17:06
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ProduceConfig  extends AbstractConfig{
    /**
     * 生产的topicName
     */
    private  String  topic;
    /**
     * topic的分类标签
     */
    private  String  tags;
    /**
     * 查询返回最大条数
     */
    private  long  queryMaxNUm = 10;
    /**
     * 查询开始位置
     */
    private  long  queryBegin;
    /**
     * 查询结束位置
     */
    private  long  queryEnd;
    /**
     * 是否异步发送
     */
    private  boolean  isAsynProduce = false;
    /**
     * 进行躜由计算时参与的tags数组,默认为null，不路由
     */
    private  String[]   selectorTags =null;
    /**
     * 生产时默认字符编辑
     */
    private  String  defaultCharset = "UTF-8";
    /**
     * 实例名称
     */
    private   String  instanceName = "";
    /**
     * 当要生产支付sql查询参数时，用于传入sql参数
     */
    private Map<String,Object> sqlQueryPairMap = new HashMap<String,Object>();
    /**
     * 是否支持批量发送(默认为不支持）
     */
    private  boolean  isSupportBatchSend = false;
    /**
     * 是否要支持消息事务，默认为不用支持
     */
    private  boolean  isSupportTransaction = false;

    @Override
    public boolean validateConfig() {
       return super.validateConfig();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getQueryMaxNUm() {
        return queryMaxNUm;
    }

    public void setQueryMaxNUm(long queryMaxNUm) {
        this.queryMaxNUm = queryMaxNUm;
    }

    public long getQueryBegin() {
        return queryBegin;
    }

    public void setQueryBegin(long queryBegin) {
        this.queryBegin = queryBegin;
    }

    public long getQueryEnd() {
        return queryEnd;
    }

    public void setQueryEnd(long queryEnd) {
        this.queryEnd = queryEnd;
    }

    public boolean isAsynProduce() {
        return isAsynProduce;
    }

    public void setAsynProduce(boolean asynProduce) {
        isAsynProduce = asynProduce;
    }

    public String[] getSelectorTags() {
        return selectorTags;
    }

    public void setSelectorTags(String[] selectorTags) {
        this.selectorTags = selectorTags;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Map<String, Object> getSqlQueryPairMap() {
        return sqlQueryPairMap;
    }

    public void setSqlQueryPairMap(Map<String, Object> sqlQueryPairMap) {
        this.sqlQueryPairMap = sqlQueryPairMap;
    }

    public boolean isSupportBatchSend() {
        return isSupportBatchSend;
    }

    public void setSupportBatchSend(boolean supportBatchSend) {
        isSupportBatchSend = supportBatchSend;
    }

    public boolean isSupportTransaction() {
        return isSupportTransaction;
    }

    public void setSupportTransaction(boolean supportTransaction) {
        isSupportTransaction = supportTransaction;
    }
}
