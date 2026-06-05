package com.muang.ai.claw.module.system.api.dept;

import com.muang.ai.claw.module.system.entity.dept.PostEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.api.dept.dto.PostRespDTO;
import com.muang.ai.claw.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.muang.ai.claw.util.collection.CollectionUtils;
import java.util.Map;

/**
 * 岗位 API 实现类
 *
 */
@Service
public class PostApi {

    @Resource
    private PostService postService;

    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

    public List<PostRespDTO> getPostList(Collection<Long> ids) {
        List<PostEntity> list = postService.getPostList(ids);
        return BeanUtils.toBean(list, PostRespDTO.class);
    }

    public Map<Long, PostRespDTO> getPostMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return MapUtil.empty();
        }

        List<PostRespDTO> list = getPostList(ids);
        return CollectionUtils.convertMap(list, PostRespDTO::getId);
    }

}
