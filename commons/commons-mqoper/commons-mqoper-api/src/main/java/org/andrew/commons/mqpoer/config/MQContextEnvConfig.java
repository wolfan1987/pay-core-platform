package org.andrew.commons.mqpoer.config;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  定义消息处理上下文相关环境参数
 * @Date: Created in 2018/7/9 16:26
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class MQContextEnvConfig {
    /**
     * 日志记录器类型，如果是在web环境下，则不需要指定日志记录器类型
     * 支持: log4j2和logback
     */
    private String  loggerName = null;
    /**
     * mq服务地址
     */
    private  String  mqNameServers = null;
    /**
     * 初始时是否测试mq服务是否可用
     */
    private  boolean  isTestServer = true;


    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getMqNameServers() {
        return mqNameServers;
    }

    public void setMqNameServers(String mqNameServers) {
        this.mqNameServers = mqNameServers;
    }

    public boolean isTestServer() {
        return isTestServer;
    }

    public void setTestServer(boolean testServer) {
        isTestServer = testServer;
    }
}
