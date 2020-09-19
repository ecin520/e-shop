package com.pytap.common.annotation;

import java.lang.annotation.*;

/**
 * @author Ecin520
 * @date 2020/3/30 14:20
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}