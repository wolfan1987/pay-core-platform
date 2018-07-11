package org.andrew.commons.mqoper.api;

import java.util.concurrent.Delayed;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  调度任务接口
 * @Date: Created in 2018/7/9 15:29
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Task  extends Delayed{

    void  execute();

}
