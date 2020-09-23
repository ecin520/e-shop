package com.pytap.order.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsOrderReturnReason;

/**
 * @author Ecin520
 * @date 2020/9/23 17:31
 */
public interface OrderReturnReasonService {
    /**
     * 插入退货原因
     * @param orderReturnReason 退货原因
     * @return Integer
     * */
    Integer insertOrderReturnReason(EsOrderReturnReason orderReturnReason);

    /**
     * 主键删除退货原因
     * @param id 退货原因主键
     * @return Integer
     * */
    Integer deleteOrderReturnReasonById(Long id);

    /**
     * 更新退货原因
     * @param orderReturnReason 退货原因
     * @return Integer
     * */
    Integer updateOrderReturnReason(EsOrderReturnReason orderReturnReason);

    /**
     * 获取退货原因
     * @param queryParam 查询参数
     * @return EsOrderReturnReason
     * */
    EsOrderReturnReason getOrderReturnReason(EsOrderReturnReason queryParam);

    /**
     * 列取退货原因
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Page<EsOrderReturnReason>
     * */
    Pager<EsOrderReturnReason> listOrderReturnReasons(Integer pageNum, Integer pageSize);
}
