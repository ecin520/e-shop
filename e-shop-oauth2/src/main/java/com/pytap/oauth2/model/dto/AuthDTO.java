package com.pytap.oauth2.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ecin520
 * @date 2020/8/22 17:00
 */
@Data
public class AuthDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private Date createTime;

}
