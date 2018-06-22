package org.andrew.commons.auditlog.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供把当前操作者存储到当前的容器中，方便审计日志模板读取数据。
 *
 * @author leaves chen on 2017/10/13
 */
public class ThreadOperatorContext {
    private static final Logger logger          = LoggerFactory.getLogger(
        ThreadOperatorContext.class);
    public static final  String OPERATOR_KEY    = "operator";
    public static final  String OPERATOR_ID_KEY = "operator_id";
    public static final  String IP_KEY          = "ip";

    private static final ThreadLocal<Map<String, Object>>
        resources = new InheritableThreadLocalMap();

    private static final class InheritableThreadLocalMap<T extends Map<String, Object>>
        extends InheritableThreadLocal<Map<String, Object>> {

        /**
         * This implementation was added to address a
         * <a href="http://jsecurity.markmail.org/search/?q=#query:+page:1+mid:xqi2yxurwmrpqrvj+state:results">
         * user-reported issue</a>。
         *
         * @param parentValue the parent value, a HashMap as defined in the
         *                    {@link #initialValue()} method.
         * @return the HashMap to be used by any parent-spawned child threads
         */
        @SuppressWarnings("unchecked")
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            if (parentValue != null) {
                return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap<String, Object>());
        }
    }

    /**
     * 放置操作员名称。
     *
     * @param operator 操作员
     */
    public static void setOperator(String operator) {
        put(OPERATOR_KEY, operator);
    }

    /**
     * 放置操作员编号。
     *
     * @param operatorId 操作员编号
     */
    public static void setOperatorId(String operatorId) {
        put(OPERATOR_ID_KEY, operatorId);
    }

    /**
     * 放置操作员IP。
     *
     * @param ip 放置操作员IP
     */
    public static void setIp(String ip) {
        put(IP_KEY, ip);
    }

    /**
     * 获取操作员。
     *
     * @return 操作员
     */
    public static String getOperator() {
        return (String) get(OPERATOR_KEY);
    }

    /**
     * 获取操作员id。
     *
     * @return 操作员id
     */
    public static String getOperatorId() {
        return (String) get(OPERATOR_ID_KEY);
    }

    /**
     * 获取ip。
     *
     * @return 获取ip
     */
    public static String getIp() {
        return (String) get(IP_KEY);
    }

    /**
     * 放置数据。
     *
     * @param key   关键字
     * @param value 值
     */
    public static void put(String key, String value) {
        ensureResourcesInitialized();
        resources.get().put(key, value);
    }

    /**
     * 取数据。
     *
     * @param key 关键字
     * @return 值
     */
    public static Object get(String key) {
        ensureResourcesInitialized();
        return resources.get().get(key);
    }

    /**
     * 返回除固定数据之外的其它参数。
     *
     * @return 集合
     */
    public static Map<String, Object> getParams() {
        Map<String, Object> params = resources.get();
        params.remove(OPERATOR_KEY);
        params.remove(OPERATOR_ID_KEY);
        params.remove(IP_KEY);
        return params;
    }
}
