package com.muang.ai.claw.module.system.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.muang.ai.claw.config.datapermission.core.annotation.DataPermission;
import com.muang.ai.claw.constant.CommonStatusEnum;
import com.muang.ai.claw.constant.UserTypeEnum;
import com.muang.ai.claw.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.muang.ai.claw.module.system.constant.logger.LoginLogTypeEnum;
import com.muang.ai.claw.module.system.constant.logger.LoginResultEnum;
import com.muang.ai.claw.module.system.constant.oauth2.OAuth2ClientConstants;
import com.muang.ai.claw.module.system.controller.admin.auth.vo.AuthLoginForm;
import com.muang.ai.claw.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import com.muang.ai.claw.module.system.controller.admin.auth.vo.AuthRegisterForm;
import com.muang.ai.claw.module.system.entity.oauth2.OAuth2AccessTokenEntity;
import com.muang.ai.claw.module.system.entity.user.AdminUserEntity;
import com.muang.ai.claw.module.system.service.logger.LoginLogService;
import com.muang.ai.claw.module.system.service.member.MemberService;
import com.muang.ai.claw.module.system.service.oauth2.OAuth2TokenService;
import com.muang.ai.claw.module.system.service.user.AdminUserService;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.util.servlet.ServletUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.module.system.constant.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static com.muang.ai.claw.module.system.constant.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;

/**
 * Auth Service 实现类
 *
 */
@Service
@Slf4j
public class AdminAuthService {

    @Resource
    private AdminUserService userService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private MemberService memberService;
    @Resource
    private Validator validator;

    public AdminUserEntity authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            createLoginLog(null, username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (CommonStatusEnum.isDisable(user.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @DataPermission(enable = false)
    public AuthLoginRespVO login(AuthLoginForm reqVO) {

        // 使用账号密码，进行登录
        AdminUserEntity user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId("");
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenEntity accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        return BeanUtils.toBean(accessTokenDO, AuthLoginRespVO.class);
    }

    public AuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenEntity accessTokenDO = oauth2TokenService.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return BeanUtils.toBean(accessTokenDO, AuthLoginRespVO.class);
    }

    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenEntity accessTokenDO = oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessTokenDO.getUserId(), accessTokenDO.getUserType(), logType);
    }

    private void createLogoutLog(Long userId, Integer userType, Integer logType) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId("");
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equal(getUserType().getValue(), userType)) {
            reqDTO.setUsername(getUsername(userId));
        } else {
            reqDTO.setUsername(memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogService.createLoginLog(reqDTO);
    }

    private String getUsername(Long userId) {
        if (userId == null) {
            return null;
        }
        AdminUserEntity user = userService.getUser(userId);
        return user != null ? user.getUsername() : null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    public AuthLoginRespVO register(AuthRegisterForm registerReqVO) {
        // 1. 校验验证码

        // 2. 校验用户名是否已存在
        Long userId = userService.registerUser(registerReqVO);

        // 3. 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(userId, registerReqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }



}
