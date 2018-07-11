package org.andrew.commons.mqoper.config;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 14:18
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Config {

    void  setNameSrv(String  nameSrv);

    String  getNameSrv();

     void  setConfigName(String  configName);

     String  getConfigName();

    /**
     * 验证必要配置是否正确
     * @return
     */
     boolean  validateConfig();

    /**
     * 验证进行pull消费时相关配置条件
     * @return
     */
      boolean   validatePullConfig();

    /**
     * 验证进行push消费时相关配置条件
     * @return
     */
      boolean  validatePushConfig();


}
