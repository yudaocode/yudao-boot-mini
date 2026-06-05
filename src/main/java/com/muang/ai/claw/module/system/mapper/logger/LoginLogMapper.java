package com.muang.ai.claw.module.system.mapper.logger;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.loginlog.LoginLogPageForm;
import com.muang.ai.claw.module.system.entity.logger.LoginLogEntity;
import com.muang.ai.claw.module.system.constant.logger.LoginResultEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapperX<LoginLogEntity> {

    default PageResult<LoginLogEntity> selectPage(LoginLogPageForm reqVO) {
        LambdaQueryWrapperX<LoginLogEntity> query = new LambdaQueryWrapperX<LoginLogEntity>()
                .likeIfPresent(LoginLogEntity::getUserIp, reqVO.getUserIp())
                .likeIfPresent(LoginLogEntity::getUsername, reqVO.getUsername())
                .betweenIfPresent(LoginLogEntity::getCreateTime, reqVO.getCreateTime());
        if (Boolean.TRUE.equals(reqVO.getStatus())) {
            query.eq(LoginLogEntity::getResult, LoginResultEnum.SUCCESS.getResult());
        } else if (Boolean.FALSE.equals(reqVO.getStatus())) {
            query.gt(LoginLogEntity::getResult, LoginResultEnum.SUCCESS.getResult());
        }
        query.orderByDesc(LoginLogEntity::getId); // 降序
        return selectPage(reqVO, query);
    }

}
