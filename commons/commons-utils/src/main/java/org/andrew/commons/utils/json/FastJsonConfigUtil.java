package org.andrew.commons.utils.json;

import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FastJson自定义配置类。
 * Created by leaves chen[leaves615@gmail.com] on 2018/1/25.
 *
 * @author leaves chen[leaves615@gmail.com]
 */
public class FastJsonConfigUtil {
    private static Map<Integer, SerializeConfig> cacheMap = new ConcurrentHashMap<>();

    /**
     * 浮点格式化配置。
     * 1.自动舍弃.0
     * 2.四舍五入保留2位小数
     *
     * @return double配置对象
     */
    public static SerializeConfig floatFormatConfig() {
        SerializeConfig config = cacheMap.get(0);
        if (config == null) {
            synchronized (FastJsonConfigUtil.class) {
                if (!cacheMap.containsKey(0)) {
                    config = new SerializeConfig();
                    config.put(Double.class, new DoubleSerializer(new DecimalFormat("#.##")));
                    cacheMap.put(0, config);
                }
            }
        }
        return config;
    }
}
