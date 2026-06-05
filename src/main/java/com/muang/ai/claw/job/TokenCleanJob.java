package com.muang.ai.claw.job;

import com.muang.ai.claw.config.tenant.core.aop.TenantIgnore;
import com.muang.ai.claw.module.system.service.oauth2.OAuth2TokenService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 物理删除过期 N 天的令牌的定时任务
 *
 * @author preschooler
 */
@Component
@Slf4j
public class TokenCleanJob {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    /**
     * 清理过期（14）天的令牌
     */
    private static final Integer JOB_CLEAN_RETAIN_DAY = 14;

    /**
     * 每次删除间隔的条数，如果值太高可能会造成数据库的压力过大
     */
    private static final Integer DELETE_LIMIT = 100;

    /**
     * 每天凌晨 2 点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @TenantIgnore
    public void execute() {
        Integer refreshCount = oauth2TokenService.cleanRefreshToken(JOB_CLEAN_RETAIN_DAY, DELETE_LIMIT);
        log.info("[execute][定时执行清理刷新令牌数量 ({}) 个]", refreshCount);
        Integer accessCount = oauth2TokenService.cleanAccessToken(JOB_CLEAN_RETAIN_DAY, DELETE_LIMIT);
        log.info("[execute][定时执行清理访问令牌数量 ({}) 个]", accessCount);
    }

}
