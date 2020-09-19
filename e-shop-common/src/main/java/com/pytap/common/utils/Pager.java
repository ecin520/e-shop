package com.pytap.common.utils;

import lombok.Data;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 12:00
 */
@Data
public class Pager<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer total;

    private List<T> content;

}
