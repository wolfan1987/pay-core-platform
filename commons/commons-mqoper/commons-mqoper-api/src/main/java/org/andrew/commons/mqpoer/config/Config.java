package org.andrew.commons.mqpoer.config;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 14:18
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Config {

    public  void  setConfigName(String  configName);

    public  String  getConfigName();

    /**
     * 验证必要配置是否正确
     * @return
     */
    public  boolean  validateConfig();

    /**
     * 验证进行pull消费时相关配置条件
     * @return
     */
    public   boolean   validatePullConfig();

    /**
     * 验证进行push消费时相关配置条件
     * @return
     */
    public   boolean  validatePushConfig();


}
