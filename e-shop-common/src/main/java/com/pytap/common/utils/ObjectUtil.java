package com.pytap.common.utils;

/**
 * @author Ecin520
 * @date 2020/9/14 16:15
 */
public class ObjectUtil {
    public static boolean isNull(Object obj) {
        return null == obj || obj.equals((Object)null);
    }

    public static boolean isNotNull(Object obj) {
        return null != obj && !obj.equals((Object)null);
    }
}
