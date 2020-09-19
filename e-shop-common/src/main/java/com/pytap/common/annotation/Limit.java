package com.pytap.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 限制用户访问频率
 * @author Ecin520
 * @date 2020/8/19 10:32
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Limit {
    long seconds();
    long maxCount();
}
