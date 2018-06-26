package org.andrew.commons.web.prop;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 类型转换器。
 * @author andrewliu
 */
public class CastUtil {

    /**
     * 转为String型。
     * @param obj 待转换的值
     * @return 转换类型后的值
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为String型，提供默认值。
     * 如果传入需要转换在值为空，则输出默认值。
     * @param obj 待转换的值
     * @param defaultValue 默认值
     * @return 转换类型后的值
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double型。
     * @param obj 待转换的值。
     * @return 转换后的值。
     */
    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转为double型,提供默认值。
     * @param obj 待转换的值。
     * @param defaultValue 默认值。
     * @return 转换后的值。
     */
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException ex) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为long型。
     * @param obj 待转换的值。
     * @return 转换后的值。
     */
    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为long型，提供默认值。
     * @param obj 带转换的值。
     * @param defaultValue 默认值。
     * @return 转换后的值。
     */
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException ex) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为int型。
     * @param obj 带转换的值。
     * @return 转换后的值。
     */
    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为int型，提供默认值。
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException ex) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为boolean型。
     * @param obj 带转换的值。
     * @return 转换后的值。
     */
    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * 转为boolean型。
     * @param obj 带转换的值。
     * @param defaultValue 默认值、
     * @return 转换后的值。
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

    /**
     * 转为String[]型。
     * @param objArray 待转换的数组。
     * @return 转换后的值。
     */
    public static String[] castStringArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        String[] strArray = new String[objArray.length];
        if (ArrayUtils.isNotEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                strArray[i] = castString(objArray[i]);
            }
        }
        return strArray;
    }

    /**
     * 转为double[]型。
     * @param objArray 待转换的数组。
     * @return 转换后的数组。
     */
    public static double[] castDoubleArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        double[] doubleArray = new double[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                doubleArray[i] = castDouble(objArray[i]);
            }
        }
        return doubleArray;
    }

    /**
     * 转为long[]型。
     * @param objArray 带转换的数组。
     * @return 转换后的数组。
     */
    public static long[] castLongArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        long[] longArray = new long[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                longArray[i] = castLong(objArray[i]);
            }
        }
        return longArray;
    }

    /**
     * 转为int[]型。
     * @param objArray 带转换的数组。
     * @return 转换后的数组。
     */
    public static int[] castIntArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if (!ArrayUtils.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                intArray[i] = castInt(objArray[i]);
            }
        }
        return intArray;
    }
}
