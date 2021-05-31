package com.pytap.order.service;

import com.pytap.generator.entity.EsDelivery;
import com.pytap.order.model.dto.DeliveryDTO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/11/4 14:53
 */
public interface DeliveryService {
    /**
     * 插入物流
     * @param delivery 物流
     * @return Integer
     * */
    Integer insertDelivery(EsDelivery delivery);

    /**
     * 主键删除物流
     * @param id 物流地址主键
     * @return Integer
     * */
    Integer deleteDeliveryById(Long id);

    /**
     * 更新物流
     * @param delivery 物流
     * @return Integer
     * */
    Integer updateDelivery(EsDelivery delivery);

    /**
     * 参数获取物流
     * @param queryParam 查询参数
     * @return EsDelivery
     * */
    EsDelivery getDeliveryByParam(EsDelivery queryParam);

    /**
     * 参数获取物流列表
     * @param queryParam 参数
     * @return List<EsDelivery>
     * */
    List<EsDelivery> listDeliveries(EsDelivery queryParam);

    /**
     * 插入订单物流
     * @param deliveryDTO 物流信息，包括订单
     * @return Integer
     * */
    Integer insertOrderDelivery(DeliveryDTO deliveryDTO);

    /**
     * 确认收货
     * @param deliveryNumber 物流号
     * @param orderId 订单号
     * @return Integer
     * */
    Integer confirmReceipt(String deliveryNumber, Long orderId);
}
