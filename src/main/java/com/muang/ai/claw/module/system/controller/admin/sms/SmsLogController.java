package com.muang.ai.claw.module.system.controller.admin.sms;

import com.muang.ai.claw.common.apilog.core.annotation.ApiAccessLog;
import com.muang.ai.claw.common.pojo.CommonResult;
import com.muang.ai.claw.common.pojo.PageParam;
import com.muang.ai.claw.common.pojo.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.common.excel.core.util.ExcelUtils;
import com.muang.ai.claw.module.system.controller.admin.sms.vo.log.SmsLogPageReqVO;
import com.muang.ai.claw.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import com.muang.ai.claw.module.system.dal.dataobject.sms.SmsLogDO;
import com.muang.ai.claw.module.system.service.sms.SmsLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.muang.ai.claw.common.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.muang.ai.claw.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 短信日志")
@RestController
@RequestMapping("/system/sms-log")
@Validated
public class SmsLogController {

    @Resource
    private SmsLogService smsLogService;

    @GetMapping("/page")
    @Operation(summary = "获得短信日志分页")
    @PreAuthorize("@ss.hasPermission('system:sms-log:query')")
    public CommonResult<PageResult<SmsLogRespVO>> getSmsLogPage(@Valid SmsLogPageReqVO pageReqVO) {
        PageResult<SmsLogDO> pageResult = smsLogService.getSmsLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SmsLogRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得短信日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:sms-log:query')")
    public CommonResult<SmsLogRespVO> getSmsLog(@RequestParam("id") Long id) {
        SmsLogDO smsLog = smsLogService.getSmsLog(id);
        return success(BeanUtils.toBean(smsLog, SmsLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出短信日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:sms-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSmsLogExcel(@Valid SmsLogPageReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SmsLogDO> list = smsLogService.getSmsLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "短信日志.xls", "数据", SmsLogRespVO.class,
                BeanUtils.toBean(list, SmsLogRespVO.class));
    }

}
