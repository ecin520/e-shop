package com.pytap.order.service;

import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsOrder;
import com.pytap.order.model.dto.OrderParamDTO;
import com.pytap.order.model.dto.OrderQueryDTO;
import com.pytap.order.model.vo.OrderVO;

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
     * 传入订单传输对象进行新增订单操作
     * @param orderParamDTO 订单传输对象
     * @return Integer
     * */
    Integer insertOrderByParam(OrderParamDTO orderParamDTO) throws GeneralException;

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
     * 列取订单
     * @param queryParam 查询参数
     * @return Page<EsOrder>
     * */
    Pager<EsOrder> listOrders(QueryParam<OrderQueryDTO> queryParam);

    /**
     * 获取用户订单列表视图
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @param queryParam 查询参数
     * @return  Pager<OrderVO>
     * */
    Pager<OrderVO> listUserOrders(Integer pageNum, Integer pageSize, EsOrder queryParam);

    /**
     * 获取订单视图
     * @param queryParam 查询参数
     * @return OrderVO
     * */
    OrderVO getOrderVO(EsOrder queryParam);

    /**
     * 获取订单
     * @param queryParam 查询参数
     * @return EsOrder
     * */
    EsOrder getOrder(EsOrder queryParam);

    /**
     * 取消订单
     * @param orderParamDTO 订单参数
     * @return Integer
     * */
    Integer cancelOrder(OrderParamDTO orderParamDTO) throws GeneralException;
}
