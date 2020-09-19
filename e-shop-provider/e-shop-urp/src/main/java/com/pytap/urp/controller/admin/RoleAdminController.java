package com.pytap.urp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysRole;
import com.pytap.urp.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/1 18:23
 */
@RequestMapping("/admin/role")
@RestController
public class RoleAdminController {

    @Resource
    private RoleService roleService;

    @Log(value = "获取角色总数量")
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public ResultEntity<Integer> countRole() {
        return ResultEntity.success(roleService.countRole());
    }

    @Log(value = "获取所有角色")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultEntity<Pager<SysRole>> listRoles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResultEntity.success(roleService.listRoles(pageNum, pageSize));
    }

    @Log(value = "插入角色")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertRole(@RequestBody SysRole role) {
        return 1 != roleService.insertRole(role) ? ResultEntity.fail("添加失敗") : ResultEntity.success("插入成功");
    }

    @Log(value = "更新角色")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateRole(@RequestBody SysRole role) {
        return 1 != roleService.updateRoleById(role) ? ResultEntity.fail("更新失敗") : ResultEntity.success("更新成功");
    }

    @Log(value = "批量刪除角色")
    @RequestMapping(value = "/delete/list", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteRoles(@RequestBody List<Long> roleIds) {
        return 1 != roleService.deleteRoles(roleIds) ? ResultEntity.fail("刪除失敗") : ResultEntity.success("刪除成功");
    }

    @Log(value = "角色id获取权限")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResultEntity<List<SysPermission>> listPermissionsByRoleId(Long roleId) {
        return ResultEntity.success(roleService.listPermissionsByRoleId(roleId));
    }

    @Log(value = "更新角色权限")
    @RequestMapping(value = "/permissions/update/{roleId}", method = RequestMethod.PUT)
    public ResultEntity<List<SysPermission>> updateRolePermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        return 1 != roleService.updateRolePermissions(roleId, permissionIds)? ResultEntity.fail("更新失败") : ResultEntity.success("更新成功");
    }


}
