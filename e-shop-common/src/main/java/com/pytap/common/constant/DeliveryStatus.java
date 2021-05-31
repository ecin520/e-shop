package com.pytap.common.constant;

/**
 * 物流状态
 * @author Ecin520
 * @date 2020/12/31 10:38
 */
public class DeliveryStatus {
    /**
     * 待揽收
     * */
    public static final Integer TO_BE_COLLECTED = 0;

    /**
     * 运输中
     * */
    public static final Integer IN_TRANSIT = 1;

    /**
     * 已送达
     * */
    public static final Integer ARRIVED = 2;

    /**
     * 出错
     * */
    public static final Integer ERROR = 3;
}
