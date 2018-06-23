package org.andrew.commons.cache.api;

import java.util.Set;

public interface Cache {

    /**
     * 缓存区域初始化。
     *
     * @param region region
     */
    void init(String region);

    Object get(String key);

    Object get(String group, String key);

    Object[] get(String[] keys);

    Object[] get(String group, String[] keys);

    Set<String> getGroupKeys(String group);

    void cleanGroup(String group);

    void put(String key, Object obj);

    void put(String groupName, String key, Object object);

    void putSafe(String key, Object object);

    void clear();

    void remove(String key);

    void remove(String group, String key);

    void remove(String[] keys);

    void remove(String group, String[] keys);

    String getStats();

    int freeMemoryElements(int numberToFree);

    void destroy();
}
