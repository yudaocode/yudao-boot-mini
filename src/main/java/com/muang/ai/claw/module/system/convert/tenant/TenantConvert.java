package com.muang.ai.claw.module.system.convert.tenant;

import com.muang.ai.claw.module.system.controller.admin.tenant.vo.tenant.TenantSaveForm;
import com.muang.ai.claw.module.system.controller.admin.user.vo.user.UserSaveForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 租户 Convert
 *
 */
@Mapper
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    default UserSaveForm convert02(TenantSaveForm bean) {
        UserSaveForm reqVO = new UserSaveForm();
        reqVO.setUsername(bean.getUsername());
        reqVO.setPassword(bean.getPassword());
        reqVO.setNickname(bean.getContactName()).setMobile(bean.getContactMobile());
        return reqVO;
    }

}
