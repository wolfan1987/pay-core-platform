package org.andrew.commons.mqoper.emnus;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description: 日志记录器类型枚举
 * @Date: Created in 2018/7/9 16:17
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public enum LoggerEnums {
    LOGGER4j2_CLASSPATH("LOG4J42","CLASSPATH","log4j2.xml"),
    LOGGER4J2_METAINF("LOG4J42","META-INF","log4j2.xml"),
    LOGBACK_CLASSPATH("LOGBACK","CLASSPATH","logback.xml"),
    LOGBACK_METAINF("LOGBACK","META-INF","logback.xml")
    ;
    //日志记录器类型(log4j2,logback
    private  String  loggerName;
    //日志记录器配置文件路径（classPath,META-INF)
    private   String pathType;
    //日志记录器配置文件名称
    private   String   configFileName;

  private   LoggerEnums(String loggerName, String pathType, String configFileName) {
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
