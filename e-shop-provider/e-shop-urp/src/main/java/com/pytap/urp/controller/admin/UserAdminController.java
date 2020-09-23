package com.pytap.urp.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import com.pytap.urp.model.vo.UserVO;
import com.pytap.urp.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/8/15 23:26
 */
@RequestMapping("/admin/user")
@RestController
public class UserAdminController {

    @Resource
    private UserService userService;

    @Log("用户名获取用户")
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ResultEntity<UserVO> getUserByUsername(@PathVariable String username) {
        UserVO user = userService.getUserVOByUsername(username);
        return null != user ? ResultEntity.success(user) : ResultEntity.fail(400, "用户不存在");
    }

    @Log("主键获取用户视图对象")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ResultEntity<UserVO> getUserVOById(@PathVariable Long id) {
        UserVO sysUserVO = userService.getUserVOById(id);
        return null != sysUserVO ? ResultEntity.success(sysUserVO) : ResultEntity.fail(400, "用户不存在");
    }

    @Log("主键获取用户")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResultEntity<SysUser> getUserById(@PathVariable Long id) {
        SysUser user = userService.getUserById(id);
        return null != user ? ResultEntity.success(user) : ResultEntity.fail(400, "用户不存在");
    }

    @Log("添加用户")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertUser(@RequestBody SysUser user) throws GeneralException {
        if (null == user.getUsername() || "".equals(user.getUsername())) {
            throw new GeneralException("用户名不能为空");
        }
        if (null == user.getPassword() || "".equals(user.getPassword())) {
            throw new GeneralException("密码不能为空");
        }
        return 1 == userService.insertUser(user) ? ResultEntity.success("添加成功") : ResultEntity.fail(400, "添加失败");
    }

    @Log("更新用户信息")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateUser(@RequestBody SysUser user) {
        return 1 == userService.updateUser(user) ? ResultEntity.success("更新成功") : ResultEntity.fail(400, "更新失败");
    }

    @Log(value = "获取用户总数量")
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public ResultEntity<Integer> getUserById() {
        return ResultEntity.success(userService.countUser());
    }



    @Log(value = "分页获取用户")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultEntity<Pager<UserVO>> listAllUsers(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResultEntity.success(userService.listUsers(pageNum, pageSize));
    }

    @Log(value = "关键字搜索用户")
    @RequestMapping(value = "/list/keyword", method = RequestMethod.GET)
    public ResultEntity<Pager<UserVO>> listSearchUsers(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "keyword") String keyword) {
        return ResultEntity.success(userService.listSearchUsers(pageNum, pageSize, keyword));
    }

    @Log(value = "用户名获取角色")
    @RequestMapping(value = "/roles/{username}", method = RequestMethod.GET)
    public ResultEntity<List<SysRole>> listUserAllRoles(@PathVariable String username) {
        return ResultEntity.success(userService.listUserRoles(username));
    }

    @Log(value = "更新用户角色")
    @RequestMapping(value = "/roles/update/{userId}", method = RequestMethod.PUT)
    public ResultEntity<Object> updateUserRoles(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        return 0 > userService.updateUserRoles(userId, roleIds) ? ResultEntity.fail("更新失败") : ResultEntity.success("更新成功");
    }

    @Log(value = "删除用户")
    @RequestMapping(value = "/delete/list", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteUserRoles(@RequestBody List<Long> userIds) {
        return 1 != userService.deleteUsers(userIds) ? ResultEntity.fail("删除失败") : ResultEntity.success("删除成功");
    }

}
