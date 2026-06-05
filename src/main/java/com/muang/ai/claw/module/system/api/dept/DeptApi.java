package com.muang.ai.claw.module.system.api.dept;

import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.api.dept.dto.DeptRespDTO;
import com.muang.ai.claw.module.system.dal.dataobject.dept.DeptDO;
import com.muang.ai.claw.module.system.service.dept.DeptService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;
import com.muang.ai.claw.util.collection.CollectionUtils;
import java.util.Map;

/**
 * 部门 API 实现类
 *
 */
@Service
public class DeptApi {

    @Resource
    private DeptService deptService;

    public DeptRespDTO getDept(Long id) {
        DeptDO dept = deptService.getDept(id);
        return BeanUtils.toBean(dept, DeptRespDTO.class);
    }

    public List<DeptRespDTO> getDeptList(Collection<Long> ids) {
        List<DeptDO> depts = deptService.getDeptList(ids);
        return BeanUtils.toBean(depts, DeptRespDTO.class);
    }

    public void validateDeptList(Collection<Long> ids) {
        deptService.validateDeptList(ids);
    }

    public List<DeptRespDTO> getChildDeptList(Long id) {
        List<DeptDO> childDeptList = deptService.getChildDeptList(id);
        return BeanUtils.toBean(childDeptList, DeptRespDTO.class);
    }

    public Map<Long, DeptRespDTO> getDeptMap(Collection<Long> ids) {
        List<DeptRespDTO> list = getDeptList(ids);
        return CollectionUtils.convertMap(list, DeptRespDTO::getId);
    }

}
