package com.pytap.urp.service;

import com.pytap.generator.entity.EsCustomerService;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/25 11:04
 */
public interface CustomerServiceService {
    /**
     * 统计总数
     * @return Integer
     * */
    Integer countCustomerService();

    /**
     * 插入客服
     * @param customerService 客服实体
     * @return Integer
     * */
    Integer insertCustomerService(EsCustomerService customerService);

    /**
     * 删除客服
     * @param id 客服id
     * @return Integer
     * */
    Integer deleteCustomerServiceById(Long id);

    /**
     * 更新客服
     * @param customerService 客服实体
     * @return Integer
     * */
    Integer updateCustomerServiceById(EsCustomerService customerService);

    /**
     * 查询客服
     * @param queryParam 查询参数
     * @return CustomerService 客服实体
     * */
    EsCustomerService getCustomerService(EsCustomerService queryParam);

    /**
     * 获取所有客服
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return List<SysRole>
     * */
    List<EsCustomerService> listCustomerServices(Integer pageNum, Integer pageSize);
}
