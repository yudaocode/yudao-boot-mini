package com.muang.ai.claw.module.system.mapper.oauth2;

import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.module.system.entity.oauth2.OAuth2CodeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2CodeMapper extends BaseMapperX<OAuth2CodeEntity> {

    default OAuth2CodeEntity selectByCode(String code) {
        return selectOne(OAuth2CodeEntity::getCode, code);
    }

}
