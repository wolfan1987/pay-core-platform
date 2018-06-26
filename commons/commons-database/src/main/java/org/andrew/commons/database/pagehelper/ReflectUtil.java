package org.andrew.commons.database.pagehelper;

import java.lang.reflect.Field;

/**
 * 分页查询获取总条数参数设置。
 * Created by andrewliu on 2017/8/24.
 */
public class ReflectUtil {

    /**
     * 获取obj对象fieldName的Field。
     *
     * @param obj       对象
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass();
             superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取obj对象fieldName的属性值。
     *
     * @param obj       对象
     * @param fieldName 字段名
     * @return 对象
     * @throws SecurityException        异常
     * @throws NoSuchFieldException     异常
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException   异常
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
        throws SecurityException, NoSuchFieldException, IllegalArgumentException,
               IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 设置obj对象fieldName的属性值。
     *
     * @param obj       对象
     * @param fieldName 字段名
     * @param value     值
     * @throws SecurityException        异常
     * @throws NoSuchFieldException     异常
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException   异常
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value)
        throws SecurityException, NoSuchFieldException, IllegalArgumentException,
               IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        if (field != null) {
            if (field.isAccessible()) {
                field.set(obj, value);
            } else {
                field.setAccessible(true);
                field.set(obj, value);
                field.setAccessible(false);
            }
        } else {
            throw new NoSuchFieldException();
        }
    }

}
