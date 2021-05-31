package com.pytap.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ecin520
 * @date 2020/11/2 16:59
 */
@Data
public class StockDTO implements Serializable {

    private static final long serialVersionUID = -655377463139206889L;

    private Long skuId;

    private Integer increaseStock;

    private Integer reduceStock;

}
