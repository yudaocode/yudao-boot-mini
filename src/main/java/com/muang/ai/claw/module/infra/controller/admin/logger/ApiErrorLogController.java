package com.muang.ai.claw.module.infra.controller.admin.logger;

import com.muang.ai.claw.config.apilog.core.annotation.ApiAccessLog;
import com.muang.ai.claw.common.core.CommonResult;
import com.muang.ai.claw.common.core.PageParam;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.infra.entity.logger.ApiErrorLogEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.config.excel.util.ExcelUtils;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageForm;
import com.muang.ai.claw.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import com.muang.ai.claw.module.infra.service.logger.ApiErrorLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.muang.ai.claw.config.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.muang.ai.claw.common.core.CommonResult.success;
import static com.muang.ai.claw.config.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - API 错误日志")
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class ApiErrorLogController {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @PutMapping("/update-status")
    @Operation(summary = "更新 API 错误日志的状态")
    @Parameters({
            @Parameter(name = "id", description = "编号", required = true, example = "1024"),
            @Parameter(name = "processStatus", description = "处理状态", required = true, example = "1")
    })
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:update-status')")
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id,
                                                          @RequestParam("processStatus") Integer processStatus) {
        apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得 API 错误日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<ApiErrorLogRespVO> getApiErrorLog(@RequestParam("id") Long id) {
        ApiErrorLogEntity apiErrorLog = apiErrorLogService.getApiErrorLog(id);
        return success(BeanUtils.toBean(apiErrorLog, ApiErrorLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得 API 错误日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<PageResult<ApiErrorLogRespVO>> getApiErrorLogPage(@Valid ApiErrorLogPageForm pageReqVO) {
        PageResult<ApiErrorLogEntity> pageResult = apiErrorLogService.getApiErrorLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ApiErrorLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出 API 错误日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportApiErrorLogExcel(@Valid ApiErrorLogPageForm exportReqVO,
                                       HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ApiErrorLogEntity> list = apiErrorLogService.getApiErrorLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "API 错误日志.xls", "数据", ApiErrorLogRespVO.class,
                BeanUtils.toBean(list, ApiErrorLogRespVO.class));
    }

}
