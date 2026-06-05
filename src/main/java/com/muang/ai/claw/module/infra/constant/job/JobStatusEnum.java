package com.muang.ai.claw.module.infra.constant.job;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.impl.jdbcjobstore.Constants;

import java.util.Collections;
import java.util.Set;

/**
 * 任务状态的枚举
 *
 */
@Getter
@AllArgsConstructor
public enum JobStatusEnum {

    /**
     * 初始化中
     */
    INIT(0, "初始化中", Collections.emptySet()),
    /**
     * 开启
     */
    NORMAL(1, "正常", Sets.newHashSet(Constants.STATE_WAITING, Constants.STATE_ACQUIRED, Constants.STATE_BLOCKED)),
    /**
     * 暂停
     */
    STOP(2, "暂停", Sets.newHashSet(Constants.STATE_PAUSED, Constants.STATE_PAUSED_BLOCKED));

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名字
     */
    private final String name;
    /**
     * 对应的 Quartz 触发器的状态集合
     */
    private final Set<String> quartzStates;

}
