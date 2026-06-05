package com.muang.ai.claw.module.system.convert.user;

import com.muang.ai.claw.module.system.entity.dept.DeptEntity;
import com.muang.ai.claw.module.system.entity.dept.PostEntity;
import com.muang.ai.claw.module.system.entity.permission.RoleEntity;
import com.muang.ai.claw.module.system.entity.user.AdminUserEntity;
import com.muang.ai.claw.util.collection.CollectionUtils;
import com.muang.ai.claw.util.collection.MapUtils;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.dept.DeptSimpleRespVO;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.post.PostSimpleRespVO;
import com.muang.ai.claw.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import com.muang.ai.claw.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.muang.ai.claw.module.system.controller.admin.user.vo.user.UserRespVO;
import com.muang.ai.claw.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default List<UserRespVO> convertList(List<AdminUserEntity> list, Map<Long, DeptEntity> deptMap) {
        return CollectionUtils.convertList(list, user -> convert(user, deptMap.get(user.getDeptId())));
    }

    default UserRespVO convert(AdminUserEntity user, DeptEntity dept) {
        UserRespVO userVO = BeanUtils.toBean(user, UserRespVO.class);
        if (dept != null) {
            userVO.setDeptName(dept.getName());
        }
        return userVO;
    }

    default List<UserSimpleRespVO> convertSimpleList(List<AdminUserEntity> list, Map<Long, DeptEntity> deptMap) {
        return CollectionUtils.convertList(list, user -> {
            UserSimpleRespVO userVO = BeanUtils.toBean(user, UserSimpleRespVO.class);
            MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> userVO.setDeptName(dept.getName()));
            return userVO;
        });
    }

    default UserProfileRespVO convert(AdminUserEntity user, List<RoleEntity> userRoles,
                                      DeptEntity dept, List<PostEntity> posts) {
        UserProfileRespVO userVO = BeanUtils.toBean(user, UserProfileRespVO.class);
        userVO.setRoles(BeanUtils.toBean(userRoles, RoleSimpleRespVO.class));
        userVO.setDept(BeanUtils.toBean(dept, DeptSimpleRespVO.class));
        userVO.setPosts(BeanUtils.toBean(posts, PostSimpleRespVO.class));
        return userVO;
    }

}
