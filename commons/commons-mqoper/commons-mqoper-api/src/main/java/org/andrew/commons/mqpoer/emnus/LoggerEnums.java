package org.andrew.commons.mqpoer.emnus;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description: 日志记录器类型枚举
 * @Date: Created in 2018/7/9 16:17
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public enum LoggerEnums {
    ;
    //日志记录器类型(log4j2,logback
    private  String  loggerName;
    //日志记录器配置文件路径（classPath,META-INF)
    private   String pathType;
    //日志记录器配置文件名称
    private   String   configFileName;

    LoggerEnums(String loggerName, String pathType, String configFileName) {
        this.loggerName = loggerName;
        this.pathType = pathType;
        this.configFileName = configFileName;
    }

    public String getLoggerName() {
        return loggerName;
    }
    public String getPathType() {
        return pathType;
    }
    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }
}
