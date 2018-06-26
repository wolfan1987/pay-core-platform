package org.andrew.commons.web.servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面form token注解。
 * 防止重复提交
 * Created by andrewliu on 2017/6/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {

    /**
     * 添加token。
     *
     * @return true:是 false：否
     */
    boolean save() default false;

    /**
     * 移除token。
     *
     * @return true:是 false:否
     */
    boolean remove() default false;
}
