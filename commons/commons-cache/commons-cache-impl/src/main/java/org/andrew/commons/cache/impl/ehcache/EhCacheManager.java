package org.andrew.commons.cache.impl.ehcache;

import net.sf.ehcache.CacheManager;
import org.andrew.commons.cache.api.Cache;
import org.andrew.commons.cache.impl.AbstractCacheManager;

public class EhCacheManager extends AbstractCacheManager {

    private CacheManager manager;

    private EhCacheManager() {
        this(CacheManager.getInstance());
    }

    public static EhCacheManager getInstance() {
        return new EhCacheManager();
    }

    public static EhCacheManager getInstance(CacheManager manager) {
        return new EhCacheManager(manager);
    }

    private EhCacheManager(CacheManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    protected Cache newCache(String region) {
        EhCache cache = new EhCache();
        cache.init(region);
        return cache;
    }

    @Override
    protected void internalRemoveCache(Cache cache) {
        manager.removeCache(cacheMap.inverse().get(cache));
    }

    @Override
    public void shutdown() {
        manager.shutdown();
    }
}
