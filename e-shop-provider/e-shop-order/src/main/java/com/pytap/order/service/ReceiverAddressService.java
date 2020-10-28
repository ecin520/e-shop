package com.pytap.order.service;

import com.pytap.generator.entity.EsReceiverAddress;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/22 16:25
 */
public interface ReceiverAddressService {
    /**
     * 插入收货地址
     * @param receiverAddress 收货地址
     * @return Integer
     * */
    Integer insertReceiverAddress(EsReceiverAddress receiverAddress);

    /**
     * 主键删除收货地址
     * @param id 收货地址主键
     * @return Integer
     * */
    Integer deleteReceiverAddressById(Long id);

    /**
     * 更新收货地址
     * @param receiverAddress 收货地址
     * @return Integer
     * */
    Integer updateReceiverAddress(EsReceiverAddress receiverAddress);

    /**
     * 主键获取收货地址
     * @param id 主键
     * @return EsReceiverAddress
     * */
    EsReceiverAddress getReceiverAddressById(Long id);

    /**
     * 参数获取收货地址列表
     * @param queryParam 参数
     * @return List<EsReceiverAddress>
     * */
    List<EsReceiverAddress> listReceiverAddresses(EsReceiverAddress queryParam);

}
