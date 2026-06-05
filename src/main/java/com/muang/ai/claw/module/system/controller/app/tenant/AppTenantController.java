package com.muang.ai.claw.module.system.controller.app.tenant;

import com.muang.ai.claw.constant.CommonStatusEnum;
import com.muang.ai.claw.common.core.CommonResult;
import com.muang.ai.claw.module.system.entity.tenant.TenantEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.config.tenant.core.aop.TenantIgnore;
import com.muang.ai.claw.module.system.controller.app.tenant.vo.AppTenantRespVO;
import com.muang.ai.claw.module.system.service.tenant.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.muang.ai.claw.common.core.CommonResult.success;

@Tag(name = "用户 App - 租户")
@RestController
@RequestMapping("/system/tenant")
@Validated
public class AppTenantController {

    @Resource
    private TenantService tenantService;

    @GetMapping("/get-by-website")
    @PermitAll
    @TenantIgnore
    @Operation(summary = "使用域名，获得租户信息", description = "根据用户的域名，获得租户信息")
    @Parameter(name = "website", description = "域名", required = true, example = "www.iocoder.cn")
    public CommonResult<AppTenantRespVO> getTenantByWebsite(
            @RequestParam("website") @Pattern(regexp = "^[a-zA-Z0-9.-]+(:\\d{1,5})?$", message = "网站域名格式不正确") String website) {
        TenantEntity tenant = tenantService.getTenantByWebsite(website);
        if (tenant == null || CommonStatusEnum.isDisable(tenant.getStatus())) {
            return success(null);
        }
        return success(BeanUtils.toBean(tenant, AppTenantRespVO.class));
    }

}
