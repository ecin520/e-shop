package com.pytap.common.utils;

import java.math.BigInteger;
import java.util.UUID;

/**
 * UUID工具
 * @author Ecin520
 * @date 2020/8/11 21:02
 */
public class UUIDUtil {

    /**
     * 获取UUID
     * */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取去掉'-'的UUID
     * */
    public static String getUuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成uuid数字字符串
     * */
    public static String uuidNumberString() {
        UUID uuid = UUID.randomUUID();
        BigInteger a = BigInteger.valueOf(uuid.getMostSignificantBits());
        BigInteger b = BigInteger.valueOf(uuid.getLeastSignificantBits());
        return a.compareTo(b) < 0 ? b.multiply(b).add(a).toString() : a.multiply(a).add(a).add(b).toString();
    }

}
