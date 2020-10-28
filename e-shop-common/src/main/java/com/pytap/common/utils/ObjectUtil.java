package com.pytap.common.utils;

/**
 * @author Ecin520
 * @date 2020/9/14 16:15
 */
public class ObjectUtil {

    public static boolean isAllNull(Object ...objects) {
        boolean flag = false;
        for (Object object : objects) {
            if (null != object) {
                flag = true;
                break;
            }
        }
        return !flag;
    }

}
