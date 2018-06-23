package org.andrew.commons.cache.impl.ehcache;



import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.andrew.commons.cache.api.Cache;

import org.andrew.commons.cache.api.SysCacheManager;
import org.andrew.commons.cache.impl.exception.CacheException;

import java.util.*;


/**
 * 缓存处理。
 *
 * @author lijiguang
 */
public class EhCache implements Cache {
    private SysCacheManager  sysCacheManager;
    private static final String       GROUP_MAP = "group_map";
    private              CacheManager manager   = CacheManager.create(
        getClass().getResource("/META-INF/spring/ehcache.xml"));
    private net.sf.ehcache.Cache cache;

    /**
     * 空构造方法。
     */
    public EhCache() {}

    private String getKey(String group, String key) {
        return String.format("%s:%s", group, key);
    }

    public void init(String region) {
        cache = manager.getCache(region);
    }

    /**
     * 安全设置值。
     *
     * @param key    键值
     * @param object 值
     */
    public void putSafe(String key, Object object) {
        if (cache.get(key) != null) {
            throw new CacheException("key:" + key + " is exist");
        }
        Element element = new Element(key, object);
        cache.put(element);
    }

    /**
     * 设置值。
     *
     * @param key    键值
     * @param object 值
     */
    public void put(String key, Object object) {
        Element element = new Element(key, object);
        cache.put(element);
    }

    /**
     * 设置值。
     *
     * @param groupName 组名
     * @param key       键值
     * @param object    值
     */
    @SuppressWarnings("unchecked")
    public void put(String groupName, String key, Object object) {
        Element element = cache.get(GROUP_MAP);
        Map<String, Set<String>> groupMap = null;
        if (element == null) {
            groupMap = new HashMap<>();
        } else {
            groupMap = (Map<String, Set<String>>) element.getObjectValue();
        }
        Set<String> keysSet = groupMap.get(groupName);
        if (keysSet == null) {
            keysSet = new HashSet<>();
            groupMap.put(groupName, keysSet);
        }
        keysSet.add(key);
        put(getKey(groupName, key), object);
        put(GROUP_MAP, groupMap);
    }

    /**
     * 通过组获取集合。
     *
     * @param group 组名
     * @return 集合
     */
    @SuppressWarnings("unchecked")
    public Set<String> getGroupKeys(String group) {
        Element element = cache.get(GROUP_MAP);
        if (element == null) {
            return null;
        }
        Map<String, Set<String>> groupMap = (Map<String, Set<String>>) element.getObjectValue();
        return groupMap.get(group);
    }

    /**
     * 清楚组数据。
     *
     * @param group 组名
     */
    public void cleanGroup(String group) {
        Set<String> groupKeys = getGroupKeys(group);
        if (groupKeys != null) {
            for (String key : groupKeys) {
                remove(group, key);
            }
        }
    }

    public void clear() {
        cache.removeAll();
    }

    public String getStats() {
        return cache.getStatistics().toString();
    }

    public int freeMemoryElements(int numberToFree) {
        throw new CacheException("ehcache does not support this feature.");
    }

    public void destroy() {

        sysCacheManager.removeCache(this);
    }

    /**
     * 设置缓存管理器。
     *
     * @param manager 管理器
     */
    public void setCacheManager(SysCacheManager manager) {
        this.sysCacheManager = manager;
    }

    /**
     * 获取值。
     *
     * @param keys 键值
     * @return 对象集合。
     */
    public Object[] get(String[] keys) {
        List<Object> objs = new ArrayList<>();
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                objs.add(get(keys[i]));
            }
        }
        return objs.toArray();
    }

    public Object get(String groupName, String key) {
        return get(getKey(groupName, key));
    }

    /**
     * 获取值。
     *
     * @param group 组名
     * @param keys  键值
     * @return 值
     */
    public Object[] get(String group, String[] keys) {
        List<Object> objs = new ArrayList<>();
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                objs.add(get(group, keys[i]));
            }
        }
        return objs.toArray();
    }

    /**
     * 获取值。
     *
     * @param key 键值
     * @return 对象
     */
    public Object get(String key) {
        Element element = cache.get(key);
        Object value = element != null ? element.getObjectValue() : null;
        return value;
    }

    /**
     * 移除。
     *
     * @param keys 键值
     */
    public void remove(String[] keys) {
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                remove(keys[i]);
            }
        }
    }

    /**
     * 移除。
     *
     * @param group 组名
     * @param keys  键值
     */
    public void remove(String group, String[] keys) {
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                remove(group, keys[i]);
            }
        }
    }

    /**
     * 移除。
     *
     * @param key 键值
     */
    public void remove(String key) {
        cache.remove(key);
    }

    /**
     * 移除。
     *
     * @param group 组名
     * @param key   键值
     */
    public void remove(String group, String key) {
        Set<String> groupKeys = getGroupKeys(group);
        if (groupKeys != null) {
            cache.remove(getKey(group, key));
        }
    }

}
