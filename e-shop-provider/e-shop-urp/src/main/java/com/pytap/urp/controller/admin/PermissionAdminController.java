package com.pytap.urp.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysPermission;
import com.pytap.generator.entity.SysPermissionGroup;
import com.pytap.urp.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/7 16:24
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionAdminController {

    @Resource
    private PermissionService permissionService;

    @Log(value = "获取权限组总数量")
    @RequestMapping(value = "/group/count", method = RequestMethod.GET)
    public ResultEntity<Integer> countPermissionGroup() {
        return ResultEntity.success(permissionService.countPermissionGroup());
    }

    @Log(value = "获取所有权限组")
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ResultEntity<Pager<SysPermissionGroup>> listPermissionGroups(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResultEntity.success(permissionService.listPermissionGroups(pageNum, pageSize));
    }

    @Log(value = "删除权限组")
    @RequestMapping(value = "/group/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deletePermissionGroup(Long id) {
        return permissionService.deletePermissionGroupById(id) != 1 ? ResultEntity.fail("删除失败") : ResultEntity.success("删除成功");
    }

    @Log(value = "更新权限组")
    @RequestMapping(value = "/group/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updatePermissionGroup(@RequestBody SysPermissionGroup permissionGroup) {
        return permissionService.updatePermissionGroup(permissionGroup) != 1 ? ResultEntity.fail("更新失败") : ResultEntity.success("更新成功");
    }

    @Log(value = "添加权限组")
    @RequestMapping(value = "/group/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertPermissionGroup(@RequestBody SysPermissionGroup permissionGroup) {
        return permissionService.insertPermissionGroup(permissionGroup) != 1 ? ResultEntity.fail("添加失败") : ResultEntity.success("添加成功");
    }

    @Log(value = "添加权限")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertPermission(@RequestBody SysPermission permission) {
        return permissionService.insertPermission(permission) != 1 ? ResultEntity.fail("添加失败") : ResultEntity.success("添加成功");
    }

    @Log(value = "获取权限总数量")
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public ResultEntity<Integer> countPermission() {
        return ResultEntity.success(permissionService.countPermission());
    }

    @Log(value = "获取所有权限")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity<Pager<SysPermission>> listPermissions(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResultEntity.success(permissionService.listPermissions(pageNum, pageSize));
    }

    @Log(value = "更新权限")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updatePermissionGroup(@RequestBody SysPermission permission) {
        return permissionService.updatePermissionById(permission) != 1 ? ResultEntity.fail("更新失败") : ResultEntity.success("更新成功");
    }

    @Log(value = "删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deletePermissions(Long id) {
        return permissionService.deletePermissionById(id) != 1 ? ResultEntity.fail("删除失败") : ResultEntity.success("删除成功");
    }

    @Log(value = "批量删除权限")
    @RequestMapping(value = "/list/delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deletePermissions(@RequestBody List<Long> permissionIds) {
        return permissionService.deletePermissions(permissionIds) != 1 ? ResultEntity.fail("删除失败") : ResultEntity.success("删除成功");
    }


    @Log(value = "通过权限组id获取权限")
    @RequestMapping(value = "/list/group/{id}", method = RequestMethod.GET)
    public ResultEntity<Object> listPermissionsByGroupId(@PathVariable Long id) {
        return ResultEntity.success(permissionService.listPermissionsByGroupId(id));
    }


}
