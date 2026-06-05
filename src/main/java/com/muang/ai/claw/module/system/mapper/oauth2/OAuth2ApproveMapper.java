package com.muang.ai.claw.module.system.mapper.oauth2;

import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.entity.oauth2.OAuth2ApproveEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OAuth2ApproveMapper extends BaseMapperX<OAuth2ApproveEntity> {

    default int update(OAuth2ApproveEntity updateObj) {
        return update(updateObj, new LambdaQueryWrapperX<OAuth2ApproveEntity>()
                .eq(OAuth2ApproveEntity::getUserId, updateObj.getUserId())
                .eq(OAuth2ApproveEntity::getUserType, updateObj.getUserType())
                .eq(OAuth2ApproveEntity::getClientId, updateObj.getClientId())
                .eq(OAuth2ApproveEntity::getScope, updateObj.getScope()));
    }

    default List<OAuth2ApproveEntity> selectListByUserIdAndUserTypeAndClientId(Long userId, Integer userType, String clientId) {
        return selectList(new LambdaQueryWrapperX<OAuth2ApproveEntity>()
                .eq(OAuth2ApproveEntity::getUserId, userId)
                .eq(OAuth2ApproveEntity::getUserType, userType)
                .eq(OAuth2ApproveEntity::getClientId, clientId));
    }

}
