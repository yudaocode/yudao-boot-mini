package com.muang.ai.claw.module.system.convert.oauth2;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.muang.ai.claw.common.core.KeyValue;
import com.muang.ai.claw.constant.UserTypeEnum;
import com.muang.ai.claw.module.system.entity.oauth2.*;
import com.muang.ai.claw.util.collection.CollectionUtils;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.config.security.core.util.SecurityFrameworkUtils;
import com.muang.ai.claw.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAccessTokenRespVO;
import com.muang.ai.claw.module.system.controller.admin.oauth2.vo.open.OAuth2OpenAuthorizeInfoRespVO;
import com.muang.ai.claw.module.system.controller.admin.oauth2.vo.open.OAuth2OpenCheckTokenRespVO;
import com.muang.ai.claw.module.system.util.oauth2.OAuth2Utils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface OAuth2OpenConvert {

    OAuth2OpenConvert INSTANCE = Mappers.getMapper(OAuth2OpenConvert.class);

    default OAuth2OpenAccessTokenRespVO convert(OAuth2AccessTokenEntity bean) {
        OAuth2OpenAccessTokenRespVO respVO = BeanUtils.toBean(bean, OAuth2OpenAccessTokenRespVO.class);
        respVO.setTokenType(SecurityFrameworkUtils.AUTHORIZATION_BEARER.toLowerCase());
        respVO.setExpiresIn(OAuth2Utils.getExpiresIn(bean.getExpiresTime()));
        respVO.setScope(OAuth2Utils.buildScopeStr(bean.getScopes()));
        return respVO;
    }

    default OAuth2OpenCheckTokenRespVO convert2(OAuth2AccessTokenEntity bean) {
        OAuth2OpenCheckTokenRespVO respVO = BeanUtils.toBean(bean, OAuth2OpenCheckTokenRespVO.class);
        respVO.setExp(LocalDateTimeUtil.toEpochMilli(bean.getExpiresTime()) / 1000L);
        respVO.setUserType(UserTypeEnum.ADMIN.getValue());
        return respVO;
    }

    default OAuth2OpenAuthorizeInfoRespVO convert(OAuth2ClientEntity client, List<OAuth2ApproveEntity> approves) {
        // 构建 scopes
        List<KeyValue<String, Boolean>> scopes = new ArrayList<>(client.getScopes().size());
        Map<String, OAuth2ApproveEntity> approveMap = CollectionUtils.convertMap(approves, OAuth2ApproveEntity::getScope);
        client.getScopes().forEach(scope -> {
            OAuth2ApproveEntity approve = approveMap.get(scope);
            scopes.add(new KeyValue<>(scope, approve != null ? approve.getApproved() : false));
        });
        // 拼接返回
        return new OAuth2OpenAuthorizeInfoRespVO(
                new OAuth2OpenAuthorizeInfoRespVO.Client(client.getName(), client.getLogo()), scopes);
    }

}
