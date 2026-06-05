package com.muang.ai.claw.module.system.mapper.oauth2;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.config.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.config.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.config.tenant.core.aop.TenantIgnore;
import com.muang.ai.claw.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageForm;
import com.muang.ai.claw.module.system.entity.oauth2.OAuth2AccessTokenEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenEntity> {

    @TenantIgnore // 获取 token 的时候，需要忽略租户编号。原因是：一些场景下，可能不会传递 tenant-id 请求头，例如说文件上传、积木报表等等
    default OAuth2AccessTokenEntity selectByAccessToken(String accessToken) {
        return selectOne(OAuth2AccessTokenEntity::getAccessToken, accessToken);
    }

    default List<OAuth2AccessTokenEntity> selectListByRefreshToken(String refreshToken) {
        return selectList(OAuth2AccessTokenEntity::getRefreshToken, refreshToken);
    }

    default PageResult<OAuth2AccessTokenEntity> selectPage(OAuth2AccessTokenPageForm reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2AccessTokenEntity>()
                .eqIfPresent(OAuth2AccessTokenEntity::getUserId, reqVO.getUserId())
                .eqIfPresent(OAuth2AccessTokenEntity::getUserType, reqVO.getUserType())
                .likeIfPresent(OAuth2AccessTokenEntity::getClientId, reqVO.getClientId())
                .gt(OAuth2AccessTokenEntity::getExpiresTime, LocalDateTime.now())
                .orderByDesc(OAuth2AccessTokenEntity::getId));
    }

    default List<OAuth2AccessTokenEntity> selectListByUserIdAndUserType(Long userId, Integer userType) {
        return selectList(OAuth2AccessTokenEntity::getUserId, userId,
                OAuth2AccessTokenEntity::getUserType, userType);
    }

    /**
     * 物理删除指定过期时间之前的访问令牌
     *
     * @param expiresTime 最大时间
     * @param limit       删除条数，防止一次删除太多
     * @return 删除条数
     */
    @Delete("DELETE FROM system_oauth2_access_token WHERE expires_time < #{expiresTime} LIMIT #{limit}")
    Integer deleteByExpiresTimeLt(@Param("expiresTime") LocalDateTime expiresTime, @Param("limit") Integer limit);

}
