package com.muang.ai.claw.config.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ObjectUtil;
import com.muang.ai.claw.module.system.constant.common.SexEnum;
import com.mzt.logapi.service.IParseFunction;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 行业的 {@link IParseFunction} 实现类
 *
 * @author HUIHUI
 */
@Component
@Slf4j
public class SexParseFunction implements IParseFunction {

    public static final String NAME = "getSex";

    @Override
    public boolean executeBefore() {
        return true; // 先转换值后对比
    }

    @Override
    public String functionName() {
        return NAME;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        Integer sex = Integer.valueOf(value.toString());
        return Arrays.stream(SexEnum.values())
                .filter(item -> ObjectUtil.equal(item.getSex(), sex))
                .findFirst().map(SexEnum::getName).orElse("");
    }

}
