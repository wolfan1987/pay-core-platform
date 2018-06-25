package org.andrew.commons.utils.excel.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * Excel标题名称。
     *
     * @return 名称
     */
    public String name() default "";

    /**
     * Excel数据类型。
     *
     * @return 数据类型
     */
    public Class<?> clazz() default Object.class;

}
