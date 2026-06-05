package com.muang.ai.claw.module.system.dal.mysql.oauth2;

import com.muang.ai.claw.framework.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.module.system.dal.dataobject.oauth2.OAuth2CodeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2CodeMapper extends BaseMapperX<OAuth2CodeDO> {

    default OAuth2CodeDO selectByCode(String code) {
        return selectOne(OAuth2CodeDO::getCode, code);
    }

}
