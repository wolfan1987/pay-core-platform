package org.andrew.commons.cache.impl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.andrew.commons.cache.api.Cache;
import org.andrew.commons.cache.api.SysCacheManager;

public abstract class AbstractCacheManager implements SysCacheManager {

    protected BiMap<String, Cache> cacheMap = HashBiMap.create();

    @Override
    public Cache createCache(String region) {
        Cache cache = newCache(region);
        cacheMap.put(region, cache);
        return cache;
    }

    protected abstract Cache newCache(String region);

    @Override
    public void clearCache(Cache cache) {
        cache.clear();
    }

    @Override
    public void clearCaches() {
        for (Cache cache : cacheMap.values()) {
            cache.clear();
        }
    }

    @Override
    public void removeCache(Cache cache) {
        //删除cache数据
        clearCache(cache);
        //从cacheManager中删除cache
        internalRemoveCache(cache);
    }

    @Override
    public void removeCaches() {
        for (Cache cache : cacheMap.values()) {
            removeCache(cache);
        }
    }

    protected abstract void internalRemoveCache(Cache cache);

}
