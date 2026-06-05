package com.muang.ai.claw.util.collection;

import cn.hutool.core.collection.CollUtil;

import java.util.Set;

/**
 * Set 工具类
 *
 */
public class SetUtils {

    @SafeVarargs
    public static <T> Set<T> asSet(T... objs) {
        return CollUtil.newHashSet(objs);
    }

}
