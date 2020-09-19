package com.pytap.urp.model.dto;

import com.pytap.generator.entity.SysPermission;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/8 12:41
 */
@Data
public class PermissionGroupDTO {

    private Long id;

    private String name;

    private String description;

    private Date updateTime;

    private Date createTime;

    private List<SysPermission> permissions;

}
