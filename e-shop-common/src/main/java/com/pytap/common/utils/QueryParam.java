package com.pytap.common.utils;

import lombok.Data;

/**
 * 封装请求
 * @author Ecin520
 * @date 2020/9/14 15:11
 */
@Data
public class QueryParam<T> {

    private Integer pageNum;

    private Integer pageSize;

    private T queryParam;

}
