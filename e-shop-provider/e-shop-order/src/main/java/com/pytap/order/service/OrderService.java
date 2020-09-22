package com.pytap.order.service;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsOrder;

/**
 * @author Ecin520
 * @date 2020/9/22 16:45
 */
public interface OrderService {
    /**
     * 插入订单
     * @param order 订单
     * @return Integer
     * */
    Integer insertOrder(EsOrder order);

    /**
     * 主键删除订单
     * @param id 订单主键
     * @return Integer
     * */
    Integer deleteOrderById(Long id);

    /**
     * 更新订单
     * @param order 订单
     * @return Integer
     * */
    Integer updateOrder(EsOrder order);

    /**
     * 获取订单
     * @param queryParam 查询参数
     * @return EsOrder
     * */
    EsOrder getOrder(EsOrder queryParam);

    /**
     * 列取订单
     * @param queryParam 查询参数
     * @return Page<EsOrder>
     * */
    Pager<EsOrder> listOrders(QueryParam<EsOrder> queryParam);
}