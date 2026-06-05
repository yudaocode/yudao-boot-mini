package com.muang.ai.claw.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import com.muang.ai.claw.framework.common.pojo.CommonResult;
import com.muang.ai.claw.framework.datapermission.core.annotation.DataPermission;
import com.muang.ai.claw.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.muang.ai.claw.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import com.muang.ai.claw.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.muang.ai.claw.module.system.convert.user.UserConvert;
import com.muang.ai.claw.module.system.dal.dataobject.dept.DeptDO;
import com.muang.ai.claw.module.system.dal.dataobject.dept.PostDO;
import com.muang.ai.claw.module.system.dal.dataobject.permission.RoleDO;
import com.muang.ai.claw.module.system.dal.dataobject.user.AdminUserDO;
import com.muang.ai.claw.module.system.service.dept.DeptService;
import com.muang.ai.claw.module.system.service.dept.PostService;
import com.muang.ai.claw.module.system.service.permission.PermissionService;
import com.muang.ai.claw.module.system.service.permission.RoleService;
import com.muang.ai.claw.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muang.ai.claw.framework.common.pojo.CommonResult.success;
import static com.muang.ai.claw.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    @DataPermission(enable = false) // 关闭数据权限，避免只查看自己时，查询不到部门。
    public CommonResult<UserProfileRespVO> getUserProfile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        // 获得用户角色
        List<RoleDO> userRoles = roleService.getRoleListFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        // 获得部门信息
        DeptDO dept = user.getDeptId() != null ? deptService.getDept(user.getDeptId()) : null;
        // 获得岗位信息
        List<PostDO> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postService.getPostList(user.getPostIds()) : null;
        return success(UserConvert.INSTANCE.convert(user, userRoles, dept, posts));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

}
