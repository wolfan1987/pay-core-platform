package org.andrew.commons.cache.nosql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * redis锁实现。
 *
 * @author andrewliu
 */
public class RedisLock implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate redisTemplate;

    /**
     * lockKey。
     */
    private String lockKey;

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待。
     * 单位毫秒
     */
    private int expireMsecs = 60 * 1000;

    /**
     * 锁等待时间，防止线程饥饿。
     * 单位毫秒
     */
    private int timeoutMsecs = 10 * 1000;

    private volatile boolean locked = false;

    /**
     * 构造器。
     *
     * @param lockKey lockKey
     */
    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * 构造器。
     *
     * @param redisTemplate redisTemplate
     * @param lockKey       lockKey
     * @param timeoutMsecs  超时毫秒数
     */
    public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        this(redisTemplate, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 构造器。
     *
     * @param redisTemplate redisTemplate
     * @param lockKey       lockKey
     * @param timeoutMsecs  超时毫秒数
     * @param expireMsecs   锁等待时间
     */
    public RedisLock(
        RedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(redisTemplate, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     * 获取加锁key的名称。
     *
     * @return lockKey
     */
    public String getLockKey() {
        return lockKey;
    }

    private String get(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] data = connection.get(serializer.serialize(key));
                    connection.close();
                    if (data == null) {
                        return null;
                    }
                    return serializer.deserialize(data);
                }
            });
        } catch (Exception ex) {
            logger.error("get redis error, key : {}", key);
            throw new RuntimeException(ex);
        }
        return obj != null ? obj.toString() : null;
    }

    private boolean setNotExists(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(
                        serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception ex) {
            logger.error("setNX redis error, key : {}", key);
            throw new RuntimeException(ex);
        }
        return obj != null ? (Boolean) obj : false;
    }

    private String getSet(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(
                        serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (Exception ex) {
            logger.error("setNX redis error, key : {}", key);
            throw new RuntimeException(ex);
        }
        return obj != null ? (String) obj : null;
    }

    /**
     * 获得锁。
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁。
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     *
     * @return 获得锁返回true，超时返回false
     * @throws InterruptedException 线程中断的时候抛异常
     */
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            String expiresStr = String.valueOf(expires); //锁到期时间
            if (this.setNotExists(lockKey, expiresStr)) {
                locked = true;
                return true;
            }

            String currentValueStr = this.get(lockKey); //获取redis里lockKey的时间
            //判断锁是否超时
            if (currentValueStr != null &&
                Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //获取上一个锁到期时间，并设置现在的锁到期时间，
                String oldValueStr = this.getSet(lockKey, expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    locked = true;
                    return true;
                }
            }
            int delayTimout = (int) (Math.random() * 200 + 100);
            timeout -= delayTimout;
            /*
             随机延迟100~300毫秒，防止线程饥饿
             即,当同时到达多个进程,只会有一个进程获得锁,其他的都用同样的频率进行尝试,
             后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
             使用随机的等待时间可以一定程度上保证公平性
             */
            Thread.sleep(delayTimout);
        }
        return false;
    }


    /**
     * 释放锁。
     */
    public synchronized void unlock() {
        String currentValueStr = this.get(lockKey); //获取redis里lockKey的时间
        //只有锁没有超时，释放锁才有意义，否则无需释放
        if (currentValueStr != null &&
            Long.parseLong(currentValueStr) > System.currentTimeMillis()) {
            if (locked) {
                redisTemplate.delete(lockKey);
                locked = false;
            }
        }
    }

}