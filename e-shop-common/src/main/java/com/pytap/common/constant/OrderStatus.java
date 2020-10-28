package com.pytap.common.constant;

/**
 * @author Ecin520
 * @date 2020/10/28 15:06
 */
public class OrderStatus {

    /**
     * 待付款
     * */
    public static final Integer WAITING_FOR_PAYMENT = 0;

    /**
     * 待发货
     * */
    public static final Integer WAITING_FOR_DELIVERY = 1;

    /**
     * 已发货
     * */
    public static final Integer ALREADY_FOR_DELIVERY = 2;

    /**
     * 已完成
     * */
    public static final Integer FINISHED = 3;

    /**
     * 已关闭
     * */
    public static final Integer CLOSED = 4;

    /**
     * 无效订单
     * */
    public static final Integer INVALID = 5;

}
