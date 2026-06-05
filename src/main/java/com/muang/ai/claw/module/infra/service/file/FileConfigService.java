package com.muang.ai.claw.module.infra.service.file;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.util.json.JsonUtils;
import com.muang.ai.claw.util.validation.ValidationUtils;
import com.muang.ai.claw.module.infra.controller.admin.file.vo.config.FileConfigPageForm;
import com.muang.ai.claw.module.infra.controller.admin.file.vo.config.FileConfigSaveForm;
import com.muang.ai.claw.module.infra.convert.file.FileConfigConvert;
import com.muang.ai.claw.module.infra.dal.dataobject.file.FileConfigDO;
import com.muang.ai.claw.module.infra.dal.mysql.file.FileConfigMapper;
import com.muang.ai.claw.config.file.core.client.FileClient;
import com.muang.ai.claw.config.file.core.client.FileClientConfig;
import com.muang.ai.claw.config.file.core.client.FileClientFactory;
import com.muang.ai.claw.config.file.core.enums.FileStorageEnum;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.util.cache.CacheUtils.buildAsyncReloadingCache;
import static com.muang.ai.claw.module.infra.constant.ErrorCodeConstants.FILE_CONFIG_DELETE_FAIL_MASTER;
import static com.muang.ai.claw.module.infra.constant.ErrorCodeConstants.FILE_CONFIG_NOT_EXISTS;

/**
 * 文件配置 Service 实现类
 *
 */
@Service
@Validated
@Slf4j
public class FileConfigService {

    private static final Long CACHE_MASTER_ID = 0L;

    /**
     * {@link FileClient} 缓存，通过它异步刷新 fileClientFactory
     */
    @Getter
    private final LoadingCache<Long, FileClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, FileClient>() {

                public FileClient load(Long id) {
                    FileConfigDO config = Objects.equals(CACHE_MASTER_ID, id) ?
                            fileConfigMapper.selectByMaster() : fileConfigMapper.selectById(id);
                    if (config != null) {
                        fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
                    }
                    return fileClientFactory.getFileClient(null == config ? id : config.getId());
                }

            });

    @Resource
    private FileClientFactory fileClientFactory;

    @Resource
    private FileConfigMapper fileConfigMapper;

    @Resource
    private Validator validator;

    public Long createFileConfig(FileConfigSaveForm createReqVO) {
        FileConfigDO fileConfig = FileConfigConvert.INSTANCE.convert(createReqVO)
                .setConfig(parseClientConfig(createReqVO.getStorage(), createReqVO.getConfig()))
                .setMaster(false); // 默认非 master
        fileConfigMapper.insert(fileConfig);
        return fileConfig.getId();
    }

    public void updateFileConfig(FileConfigSaveForm updateReqVO) {
        // 校验存在
        FileConfigDO config = validateFileConfigExists(updateReqVO.getId());
        // 更新
        FileConfigDO updateObj = FileConfigConvert.INSTANCE.convert(updateReqVO)
                .setConfig(parseClientConfig(config.getStorage(), updateReqVO.getConfig()));
        fileConfigMapper.updateById(updateObj);

        // 清空缓存
        clearCache(config.getId(), config.getMaster());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateFileConfigMaster(Long id) {
        // 校验存在
        validateFileConfigExists(id);
        // 更新其它为非 master
        fileConfigMapper.updateBatch(new FileConfigDO().setMaster(false));
        // 更新
        fileConfigMapper.updateById(new FileConfigDO().setId(id).setMaster(true));

        // 清空缓存
        clearCache(null, true);
    }

    private FileClientConfig parseClientConfig(Integer storage, Map<String, Object> config) {
        // 获取配置类
        Class<? extends FileClientConfig> configClass = FileStorageEnum.getByStorage(storage)
                .getConfigClass();
        FileClientConfig clientConfig = JsonUtils.parseObject2(JsonUtils.toJsonString(config), configClass);
        // 参数校验
        ValidationUtils.validate(validator, clientConfig);
        // 设置参数
        return clientConfig;
    }

    public void deleteFileConfig(Long id) {
        // 校验存在
        FileConfigDO config = validateFileConfigExists(id);
        if (Boolean.TRUE.equals(config.getMaster())) {
            throw exception(FILE_CONFIG_DELETE_FAIL_MASTER);
        }
        // 删除
        fileConfigMapper.deleteById(id);

        // 清空缓存
        clearCache(id, config.getMaster());
    }

    public void deleteFileConfigList(List<Long> ids) {
        // 校验是否有主配置
        List<FileConfigDO> configs = fileConfigMapper.selectByIds(ids);
        for (FileConfigDO config : configs) {
            if (Boolean.TRUE.equals(config.getMaster())) {
                throw exception(FILE_CONFIG_DELETE_FAIL_MASTER);
            }
        }

        // 批量删除
        fileConfigMapper.deleteByIds(ids);

        // 清空缓存
        ids.forEach(id -> clearCache(id, false));
    }

    /**
     * 清空指定文件配置
     *
     * @param id     配置编号
     * @param master 是否主配置
     */
    private void clearCache(Long id, Boolean master) {
        if (id != null) {
            clientCache.invalidate(id);
        }
        if (Boolean.TRUE.equals(master)) {
            clientCache.invalidate(CACHE_MASTER_ID);
        }
    }

    private FileConfigDO validateFileConfigExists(Long id) {
        FileConfigDO config = fileConfigMapper.selectById(id);
        if (config == null) {
            throw exception(FILE_CONFIG_NOT_EXISTS);
        }
        return config;
    }

    public FileConfigDO getFileConfig(Long id) {
        return fileConfigMapper.selectById(id);
    }

    public PageResult<FileConfigDO> getFileConfigPage(FileConfigPageForm pageReqVO) {
        return fileConfigMapper.selectPage(pageReqVO);
    }

    public String testFileConfig(Long id) throws Exception {
        // 校验存在
        validateFileConfigExists(id);
        // 上传文件
        byte[] content = ResourceUtil.readBytes("file/erweima.jpg");
        return getFileClient(id).upload(content, "public" + StrUtil.SLASH + IdUtil.fastSimpleUUID() + ".jpg", "image/jpeg");
    }

    public FileClient getFileClient(Long id) {
        return clientCache.getUnchecked(id);
    }

    public FileClient getMasterFileClient() {
        return clientCache.getUnchecked(CACHE_MASTER_ID);
    }

}
