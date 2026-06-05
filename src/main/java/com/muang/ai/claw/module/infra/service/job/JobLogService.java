package com.muang.ai.claw.module.infra.service.job;

import com.muang.ai.claw.common.quartz.core.service.JobLogFrameworkService;

import com.muang.ai.claw.common.pojo.PageResult;
import com.muang.ai.claw.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import com.muang.ai.claw.module.infra.dal.dataobject.job.JobLogDO;
import com.muang.ai.claw.module.infra.dal.mysql.job.JobLogMapper;
import com.muang.ai.claw.module.infra.enums.job.JobLogStatusEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * Job 日志 Service 实现类
 *
 */
@Service
@Validated
@Slf4j
public class JobLogService implements JobLogFrameworkService {

    @Resource
    private JobLogMapper jobLogMapper;

    public Long createJobLog(Long jobId, LocalDateTime beginTime,
                             String jobHandlerName, String jobHandlerParam, Integer executeIndex) {
        JobLogDO log = JobLogDO.builder().jobId(jobId).handlerName(jobHandlerName)
                .handlerParam(jobHandlerParam).executeIndex(executeIndex)
                .beginTime(beginTime).status(JobLogStatusEnum.RUNNING.getStatus()).build();
        jobLogMapper.insert(log);
        return log.getId();
    }

    @Async
    public void updateJobLogResultAsync(Long logId, LocalDateTime endTime, Integer duration, boolean success, String result) {
        try {
            JobLogDO updateObj = JobLogDO.builder().id(logId).endTime(endTime).duration(duration)
                    .status(success ? JobLogStatusEnum.SUCCESS.getStatus() : JobLogStatusEnum.FAILURE.getStatus())
                    .result(result).build();
            jobLogMapper.updateById(updateObj);
        } catch (Exception ex) {
            log.error("[updateJobLogResultAsync][logId({}) endTime({}) duration({}) success({}) result({})]",
                    logId, endTime, duration, success, result);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public Integer cleanJobLog(Integer exceedDay, Integer deleteLimit) {
        int count = 0;
        LocalDateTime expireDate = LocalDateTime.now().minusDays(exceedDay);
        // 循环删除，直到没有满足条件的数据
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            int deleteCount = jobLogMapper.deleteByCreateTimeLt(expireDate, deleteLimit);
            count += deleteCount;
            // 达到删除预期条数，说明到底了
            if (deleteCount < deleteLimit) {
                break;
            }
        }
        return count;
    }

    public JobLogDO getJobLog(Long id) {
        return jobLogMapper.selectById(id);
    }

    public PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO) {
        return jobLogMapper.selectPage(pageReqVO);
    }

}
