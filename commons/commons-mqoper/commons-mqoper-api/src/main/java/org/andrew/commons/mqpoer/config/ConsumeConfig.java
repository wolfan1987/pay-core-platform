package org.andrew.commons.mqpoer.config;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消费消息相关参数配置
 * @Date: Created in 2018/7/9 17:06
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class ConsumeConfig extends  AbstractConfig {

    /**
     * 消费时，是客户端pull模式还是客户端监听，服务端push模式
     * pull=false;push=true
     */
    private boolean  isPullOrPush=true;
    /**
     * 在客户端拉模式时，要指定brokerName
     */
    private  String   borkerName = null;
    /**
     * 拉取消费时，是否要进行定时拉取
     */
    private  boolean  isSchedulePull = false;
    /**
     * 拉取消费时指定队列ID
     */
    private  int  pullQueueId = 0;
    /**
     * 拉取消费时，一次消拉取多少条
     */
    private  int  pullSize = 1;
    /**
     * 消费订阅的主题
     */
    private  String  subTopic = "";
    /**
     * 消费订阅主题下的分类标签，默认为所有标签，用"*"表示
     */
    private  String   subTopicTag = "*";
    /**
     * 消费时从队列的哪个位置开始消费
     * 0=CONSUME_FROM_FIRST_OFFSET,1=CONSUME_FROM_MAX_OFFSET,2=CONSUME_FROM_TIMESTAMP
     */
    private   int  fromWhere = 0;
    /**
     * 是否需要自动提交已消费消息
     */
    private  boolean  isAutoCommit = false;
    /**
     * 是否在消费时用MessageFilter进行消息过滤
     * false = 不过滤，true=需要过滤，并要提供MessageFilter的具体实现
     */
    private  boolean   isFilterTags = false;
    /**
     * 过滤时实现的的java源文件
     */
    private  String   filterJavaFile = "";
    /**
     * 过滤时实现的java源文件的class文件（含包名)
     */
    private  String   filterClassFile = "";
    /**
     * 消息模式：0=集群模式，1=广播模式
     */
    private  int  messageModel = 0;

    @Override
    public boolean validateConfig() {
        return super.validateConfig();
    }

    public boolean isPullOrPush() {
        return isPullOrPush;
    }

    public void setPullOrPush(boolean pullOrPush) {
        isPullOrPush = pullOrPush;
    }

    public String getBorkerName() {
        return borkerName;
    }

    public void setBorkerName(String borkerName) {
        this.borkerName = borkerName;
    }

    public boolean isSchedulePull() {
        return isSchedulePull;
    }

    public void setSchedulePull(boolean schedulePull) {
        isSchedulePull = schedulePull;
    }

    public int getPullQueueId() {
        return pullQueueId;
    }

    public void setPullQueueId(int pullQueueId) {
        this.pullQueueId = pullQueueId;
    }

    public int getPullSize() {
        return pullSize;
    }

    public void setPullSize(int pullSize) {
        this.pullSize = pullSize;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getSubTopicTag() {
        return subTopicTag;
    }

    public void setSubTopicTag(String subTopicTag) {
        this.subTopicTag = subTopicTag;
    }

    public int getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(int fromWhere) {
        this.fromWhere = fromWhere;
    }

    public boolean isAutoCommit() {
        return isAutoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        isAutoCommit = autoCommit;
    }

    public boolean isFilterTags() {
        return isFilterTags;
    }

    public void setFilterTags(boolean filterTags) {
        isFilterTags = filterTags;
    }

    public String getFilterJavaFile() {
        return filterJavaFile;
    }

    public void setFilterJavaFile(String filterJavaFile) {
        this.filterJavaFile = filterJavaFile;
    }

    public String getFilterClassFile() {
        return filterClassFile;
    }

    public void setFilterClassFile(String filterClassFile) {
        this.filterClassFile = filterClassFile;
    }

    public int getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(int messageModel) {
        this.messageModel = messageModel;
    }
}
