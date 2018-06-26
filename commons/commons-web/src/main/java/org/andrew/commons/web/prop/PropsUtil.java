package org.andrew.commons.web.prop;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置文件读取。
 * @author andrewliu
 * @modify leaves chen
 */
public class PropsUtil {

  //  private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
    private static final Object LOCK = new Object();
    private static final Map<String, Properties> PROP_CACHE = new ConcurrentHashMap<>();

    /**
     * 加载属性文件。
     * @param propsPath properties文件路径。
     * @return Properties
     */
    public static Properties loadProps(String propsPath) {
        if (!PROP_CACHE.containsKey(propsPath)) {
            synchronized (LOCK) {
                if (!PROP_CACHE.containsKey(propsPath)) {
                    Properties props = new Properties();
                    InputStream is = null;
                    try {
                        if (null == propsPath || "".equals(propsPath.trim())) {
                            throw new IllegalArgumentException();
                        }
                        String suffix = ".properties";
                        if (propsPath.lastIndexOf(suffix) == -1) {
                            propsPath += suffix;
                        }
                        is = PropsUtil.class.getClassLoader().getResourceAsStream(propsPath);
                        if (is != null) {
                            props.load(new InputStreamReader(is, "utf-8"));
                        }
                    } catch (Exception ex) {
                     //   LOGGER.error("加载属性文件出错", ex);
                        throw new RuntimeException(ex);
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                        } catch (IOException ex) {
                        //    LOGGER.error("释放资源出错", ex);
                        }
                    }
                    PROP_CACHE.put(propsPath, props);
                }
            }
        }
        return PROP_CACHE.get(propsPath);
    }

    /**
     * 获取字符型属性。
     * @param props properties配置。
     * @param key key
     * @return string
     */
    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取字符型属性。
     * @param props properties配置。
     * @param key key
     * @param defalutValue 默认值。
     * @return string
     */
    public static String getString(Properties props, String key, String defalutValue) {
        String value = defalutValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性。
     * @param props properties配置。
     * @param key key
     * @return int
     */
    public static int getNumber(Properties props, String key) {
        int value = 0;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取数值型属性。
     * @param props properties配置。
     * @param key key
     * @param defaultValue 默认值。
     * @return int
     */
    public static int getNumber(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性。
     * @param props properties配置。
     * @param key  key
     * @return boolean
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性。
     * @param props properties配置。
     * @param key key
     * @param defalutValue 默认值
     * @return boolean
     */
    public static boolean getBoolean(Properties props, String key, boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
