package com.muang.ai.claw.module.system.constant.permission;

import com.muang.ai.claw.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 数据范围枚举类
 *
 * 用于实现数据级别的权限
 *
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum implements ArrayValuable<Integer> {

    ALL(1, "全部数据权限"),

    DEPT_CUSTOM(2, "指定部门数据权限"),
    DEPT_ONLY(3, "本部门数据权限"),
    DEPT_AND_CHILD(4, "本部门及以下数据权限"),

    SELF(5, "仅本人数据权限");

    /**
     * 范围
     */
    private final Integer scope;
    /**
     * 名字
     */
    private final String name;

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(DataScopeEnum::getScope).toArray(Integer[]::new);

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
