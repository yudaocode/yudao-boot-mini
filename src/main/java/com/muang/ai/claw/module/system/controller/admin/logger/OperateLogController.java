package com.muang.ai.claw.module.system.controller.admin.logger;

import com.muang.ai.claw.config.apilog.core.annotation.ApiAccessLog;
import com.muang.ai.claw.common.core.CommonResult;
import com.muang.ai.claw.common.core.PageParam;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.config.excel.util.ExcelUtils;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.operatelog.OperateLogPageForm;
import com.muang.ai.claw.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.muang.ai.claw.module.system.dal.dataobject.logger.OperateLogDO;
import com.muang.ai.claw.module.system.service.logger.OperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.muang.ai.claw.config.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.muang.ai.claw.common.core.CommonResult.success;

@Tag(name = "管理后台 - 操作日志")
@RestController
@RequestMapping("/system/operate-log")
@Validated
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;

    @GetMapping("/get")
    @Operation(summary = "查看操作日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public CommonResult<OperateLogRespVO> getOperateLog(@RequestParam("id") Long id) {
        OperateLogDO operateLog = operateLogService.getOperateLog(id);
        return success(BeanUtils.toBean(operateLog, OperateLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "查看操作日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:operate-log:query')")
    public CommonResult<PageResult<OperateLogRespVO>> pageOperateLog(@Valid OperateLogPageForm pageReqVO) {
        PageResult<OperateLogDO> pageResult = operateLogService.getOperateLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OperateLogRespVO.class));
    }

    @Operation(summary = "导出操作日志")
    @GetMapping("/export-excel")
    @PreAuthorize("@ss.hasPermission('system:operate-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogPageForm exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperateLogDO> list = operateLogService.getOperateLogPage(exportReqVO).getList();
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogRespVO.class,
                BeanUtils.toBean(list, OperateLogRespVO.class));
    }

}
