package com.pytap.common.annotation;

import java.lang.annotation.*;

/**
 * @author Ecin520
 * @date 2020/10/12 18:03
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermission {
    String value() default "";
}
