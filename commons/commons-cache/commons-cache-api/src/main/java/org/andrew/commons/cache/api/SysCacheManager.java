package org.andrew.commons.cache.api;

public interface SysCacheManager {

    /**
     * 根据region创建缓存实例。
     *
     * @param region region
     * @return Cache 缓存实列
     */
    Cache createCache(String region);

    /**
     * 清除指定的缓存内容。
     *
     * @param cache cache
     */
    void clearCache(Cache cache);

    /**
     * 清除所有缓存内容。
     */
    void clearCaches();

    /**
     * 从管理器中移出缓存。
     *
     * @param cache cache
     */
    void removeCache(Cache cache);

    /**
     * 从管理器中移出所有的缓存。
     */
    void removeCaches();

    /**
     * 缓存关闭。
     */
    void shutdown();
}


