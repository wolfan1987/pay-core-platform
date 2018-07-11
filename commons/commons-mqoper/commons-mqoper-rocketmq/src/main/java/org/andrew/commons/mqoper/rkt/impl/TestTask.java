package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.mqoper.rkt.abstracts.AbstractTask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  测试用Task
 * @Date: Created in 2018/7/11 11:27
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestTask extends AbstractTask {

    @Override
    public void execute() {

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(
                getNextExecTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        return Long.compare(
                this.getDelay(TimeUnit.MILLISECONDS), delayed.getDelay(TimeUnit.MILLISECONDS));
    }
}
