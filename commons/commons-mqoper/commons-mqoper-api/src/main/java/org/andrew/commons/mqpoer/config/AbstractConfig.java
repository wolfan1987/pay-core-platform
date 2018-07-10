package org.andrew.commons.mqpoer.config;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  mq相关环境配置参数抽象基类
 * @Date: Created in 2018/7/10 15:31
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public  abstract  class AbstractConfig implements  Config {
    /**
     * 当前配置名称（ip+port+_topicname_toString();
     */
    protected  String configName;
    /**
     * 生产或消费组名称
     */
    protected  String  groupName;
    /**
     * 是否按tags进行路由计算
     */
    protected  boolean  isSelectorQueue = false;
    /**
     * 是否生产消费时支持sql查询参数
     */
    private  boolean  isSupportSqlQuery = false;

    @Override
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    @Override
    public String getConfigName() {
        return this.configName;
    }

    @Override
    public boolean validateConfig() {
        return validatePushConfig() || validatePullConfig();
    }

    @Override
    public boolean validatePullConfig() {
        return false;
    }

    @Override
    public boolean validatePushConfig() {
        return false;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isSelectorQueue() {
        return isSelectorQueue;
    }

    public void setSelectorQueue(boolean selectorQueue) {
        isSelectorQueue = selectorQueue;
    }

    public boolean isSupportSqlQuery() {
        return isSupportSqlQuery;
    }

    public void setSupportSqlQuery(boolean supportSqlQuery) {
        isSupportSqlQuery = supportSqlQuery;
    }
}
