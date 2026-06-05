package com.muang.ai.claw.module.system.service.mail;

import com.muang.ai.claw.common.pojo.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.muang.ai.claw.module.system.controller.admin.mail.vo.account.MailAccountSaveReqVO;
import com.muang.ai.claw.module.system.dal.dataobject.mail.MailAccountDO;
import com.muang.ai.claw.module.system.dal.mysql.mail.MailAccountMapper;
import com.muang.ai.claw.module.system.dal.redis.RedisKeyConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.module.system.enums.ErrorCodeConstants.MAIL_ACCOUNT_NOT_EXISTS;
import static com.muang.ai.claw.module.system.enums.ErrorCodeConstants.MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS;

/**
 * 邮箱账号 Service 实现类
 *
 * @author wangjingyi
 * @since 2022-03-21
 */
@Service
@Validated
@Slf4j
public class MailAccountService {

    @Resource
    private MailAccountMapper mailAccountMapper;

    @Resource
    private MailTemplateService mailTemplateService;

    public Long createMailAccount(MailAccountSaveReqVO createReqVO) {
        MailAccountDO account = BeanUtils.toBean(createReqVO, MailAccountDO.class);
        mailAccountMapper.insert(account);
        return account.getId();
    }

    @CacheEvict(value = RedisKeyConstants.MAIL_ACCOUNT, key = "#updateReqVO.id")
    public void updateMailAccount(MailAccountSaveReqVO updateReqVO) {
        // 校验是否存在
        validateMailAccountExists(updateReqVO.getId());

        // 更新
        MailAccountDO updateObj = BeanUtils.toBean(updateReqVO, MailAccountDO.class);
        mailAccountMapper.updateById(updateObj);
    }

    @CacheEvict(value = RedisKeyConstants.MAIL_ACCOUNT, key = "#id")
    public void deleteMailAccount(Long id) {
        // 校验是否存在账号
        validateMailAccountExists(id);
        // 校验是否存在关联模版
        if (mailTemplateService.getMailTemplateCountByAccountId(id) > 0) {
            throw exception(MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS);
        }

        // 删除
        mailAccountMapper.deleteById(id);
    }

    @CacheEvict(value = RedisKeyConstants.MAIL_ACCOUNT,
            allEntries = true) // allEntries 清空所有缓存，因为 Spring Cache 不支持按照 ids 批量删除
    public void deleteMailAccountList(List<Long> ids) {
        // 1. 校验是否存在关联模版
        for (Long id : ids) {
            if (mailTemplateService.getMailTemplateCountByAccountId(id) > 0) {
                throw exception(MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS);
            }
        }

        // 2. 批量删除
        mailAccountMapper.deleteByIds(ids);
    }

    private void validateMailAccountExists(Long id) {
        if (mailAccountMapper.selectById(id) == null) {
            throw exception(MAIL_ACCOUNT_NOT_EXISTS);
        }
    }

    public MailAccountDO getMailAccount(Long id) {
        return mailAccountMapper.selectById(id);
    }

    @Cacheable(value = RedisKeyConstants.MAIL_ACCOUNT, key = "#id", unless = "#result == null")
    public MailAccountDO getMailAccountFromCache(Long id) {
        return getMailAccount(id);
    }

    public PageResult<MailAccountDO> getMailAccountPage(MailAccountPageReqVO pageReqVO) {
        return mailAccountMapper.selectPage(pageReqVO);
    }

    public List<MailAccountDO> getMailAccountList() {
        return mailAccountMapper.selectList();
    }

}
