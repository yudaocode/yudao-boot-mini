package com.muang.ai.claw.module.system.service.notice;

import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.controller.admin.notice.vo.NoticePageForm;
import com.muang.ai.claw.module.system.controller.admin.notice.vo.NoticeSaveForm;
import com.muang.ai.claw.module.system.dal.dataobject.notice.NoticeDO;
import com.muang.ai.claw.module.system.dal.mysql.notice.NoticeMapper;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.module.system.constant.ErrorCodeConstants.NOTICE_NOT_FOUND;

/**
 * 通知公告 Service 实现类
 *
 */
@Service
public class NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    public Long createNotice(NoticeSaveForm createReqVO) {
        NoticeDO notice = BeanUtils.toBean(createReqVO, NoticeDO.class);
        noticeMapper.insert(notice);
        return notice.getId();
    }

    public void updateNotice(NoticeSaveForm updateReqVO) {
        // 校验是否存在
        validateNoticeExists(updateReqVO.getId());
        // 更新通知公告
        NoticeDO updateObj = BeanUtils.toBean(updateReqVO, NoticeDO.class);
        noticeMapper.updateById(updateObj);
    }

    public void deleteNotice(Long id) {
        // 校验是否存在
        validateNoticeExists(id);
        // 删除通知公告
        noticeMapper.deleteById(id);
    }

    public void deleteNoticeList(List<Long> ids) {
        noticeMapper.deleteByIds(ids);
    }

    public PageResult<NoticeDO> getNoticePage(NoticePageForm reqVO) {
        return noticeMapper.selectPage(reqVO);
    }

    public NoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    @VisibleForTesting
    public void validateNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }

}
