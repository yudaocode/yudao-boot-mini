package com.muang.ai.claw.module.system.mapper.oauth2;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.oauth2.vo.client.OAuth2ClientPageForm;
import com.muang.ai.claw.module.system.entity.oauth2.OAuth2ClientEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * OAuth2 客户端 Mapper
 *
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapperX<OAuth2ClientEntity> {

    default PageResult<OAuth2ClientEntity> selectPage(OAuth2ClientPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2ClientEntity>()
                .likeIfPresent(OAuth2ClientEntity::getName, reqVO.getName())
                .eqIfPresent(OAuth2ClientEntity::getStatus, reqVO.getStatus())
                .orderByDesc(OAuth2ClientEntity::getId));
    }

    default OAuth2ClientEntity selectByClientId(String clientId) {
        return selectOne(OAuth2ClientEntity::getClientId, clientId);
    }

}
