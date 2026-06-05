package com.muang.ai.claw.module.system.dal.mysql.notify;

import com.muang.ai.claw.common.pojo.PageResult;
import com.muang.ai.claw.common.mybatis.core.mapper.BaseMapperX;
import com.muang.ai.claw.common.mybatis.core.query.LambdaQueryWrapperX;
import com.muang.ai.claw.module.system.controller.admin.notify.vo.template.NotifyTemplatePageReqVO;
import com.muang.ai.claw.module.system.dal.dataobject.notify.NotifyTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotifyTemplateMapper extends BaseMapperX<NotifyTemplateDO> {

    default NotifyTemplateDO selectByCode(String code) {
        return selectOne(NotifyTemplateDO::getCode, code);
    }

    default PageResult<NotifyTemplateDO> selectPage(NotifyTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NotifyTemplateDO>()
                .likeIfPresent(NotifyTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(NotifyTemplateDO::getName, reqVO.getName())
                .eqIfPresent(NotifyTemplateDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(NotifyTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NotifyTemplateDO::getId));
    }

    default List<NotifyTemplateDO> selectListByStatus(Integer status) {
        return selectList(NotifyTemplateDO::getStatus, status);
    }

}
