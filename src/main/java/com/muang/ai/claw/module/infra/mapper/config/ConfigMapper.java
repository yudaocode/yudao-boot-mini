package com.muang.ai.claw.module.infra.mapper.config;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.infra.controller.admin.config.vo.ConfigPageForm;
import com.muang.ai.claw.module.infra.entity.config.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper extends BaseMapperX<ConfigEntity> {

    default ConfigEntity selectByKey(String key) {
        return selectOne(ConfigEntity::getConfigKey, key);
    }

    default PageResult<ConfigEntity> selectPage(ConfigPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ConfigEntity>()
                .likeIfPresent(ConfigEntity::getName, reqVO.getName())
                .likeIfPresent(ConfigEntity::getConfigKey, reqVO.getKey())
                .eqIfPresent(ConfigEntity::getType, reqVO.getType())
                .betweenIfPresent(ConfigEntity::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ConfigEntity::getId));
    }

}
