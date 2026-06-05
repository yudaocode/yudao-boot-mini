package com.muang.ai.claw.module.infra.service.config;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.infra.controller.admin.config.vo.ConfigPageForm;
import com.muang.ai.claw.module.infra.controller.admin.config.vo.ConfigSaveForm;
import com.muang.ai.claw.module.infra.convert.config.ConfigConvert;
import com.muang.ai.claw.module.infra.dal.dataobject.config.ConfigDO;
import com.muang.ai.claw.module.infra.dal.mysql.config.ConfigMapper;
import com.muang.ai.claw.module.infra.constant.config.ConfigTypeEnum;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.module.infra.constant.ErrorCodeConstants.*;

/**
 * 参数配置 Service 实现类
 */
@Service
@Slf4j
@Validated
public class ConfigService {

    @Resource
    private ConfigMapper configMapper;

    public Long createConfig(ConfigSaveForm createReqVO) {
        // 校验参数配置 key 的唯一性
        validateConfigKeyUnique(null, createReqVO.getKey());

        // 插入参数配置
        ConfigDO config = ConfigConvert.INSTANCE.convert(createReqVO);
        config.setType(ConfigTypeEnum.CUSTOM.getType());
        configMapper.insert(config);
        return config.getId();
    }

    public void updateConfig(ConfigSaveForm updateReqVO) {
        // 校验自己存在
        validateConfigExists(updateReqVO.getId());
        // 校验参数配置 key 的唯一性
        validateConfigKeyUnique(updateReqVO.getId(), updateReqVO.getKey());

        // 更新参数配置
        ConfigDO updateObj = ConfigConvert.INSTANCE.convert(updateReqVO);
        configMapper.updateById(updateObj);
    }

    public void deleteConfig(Long id) {
        // 校验配置存在
        ConfigDO config = validateConfigExists(id);
        // 内置配置，不允许删除
        if (ConfigTypeEnum.SYSTEM.getType().equals(config.getType())) {
            throw exception(CONFIG_CAN_NOT_DELETE_SYSTEM_TYPE);
        }
        // 删除
        configMapper.deleteById(id);
    }

    public void deleteConfigList(List<Long> ids) {
        // 校验是否有内置配置
        List<ConfigDO> configs = configMapper.selectByIds(ids);
        configs.forEach(config -> {
            if (ConfigTypeEnum.SYSTEM.getType().equals(config.getType())) {
                throw exception(CONFIG_CAN_NOT_DELETE_SYSTEM_TYPE);
            }
        });

        // 批量删除
        configMapper.deleteByIds(ids);
    }

    public ConfigDO getConfig(Long id) {
        return configMapper.selectById(id);
    }

    public ConfigDO getConfigByKey(String key) {
        return configMapper.selectByKey(key);
    }

    public PageResult<ConfigDO> getConfigPage(ConfigPageForm pageReqVO) {
        return configMapper.selectPage(pageReqVO);
    }

    @VisibleForTesting
    public ConfigDO validateConfigExists(Long id) {
        if (id == null) {
            return null;
        }
        ConfigDO config = configMapper.selectById(id);
        if (config == null) {
            throw exception(CONFIG_NOT_EXISTS);
        }
        return config;
    }

    @VisibleForTesting
    public void validateConfigKeyUnique(Long id, String key) {
        ConfigDO config = configMapper.selectByKey(key);
        if (config == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的参数配置
        if (id == null) {
            throw exception(CONFIG_KEY_DUPLICATE);
        }
        if (!config.getId().equals(id)) {
            throw exception(CONFIG_KEY_DUPLICATE);
        }
    }

}
