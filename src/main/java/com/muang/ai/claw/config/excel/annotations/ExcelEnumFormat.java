package com.muang.ai.claw.config.excel.annotations;

import java.lang.annotation.*;

/**
 * Excel 枚举格式化
 *
 * 配合 {@link com.muang.ai.claw.config.excel.convert.ExcelEnumConvert} 使用，
 * 实现将枚举的值，格式化成枚举的中文标签（基于枚举的 getName 方法）
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelEnumFormat {

    /**
     * @return 枚举类（需提供值的 getter 以及 getName 标签方法）
     */
    Class<? extends Enum<?>> value();

}
