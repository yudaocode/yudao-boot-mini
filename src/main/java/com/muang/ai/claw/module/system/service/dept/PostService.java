package com.muang.ai.claw.module.system.service.dept;

import cn.hutool.core.collection.CollUtil;
import com.muang.ai.claw.constant.CommonStatusEnum;
import com.muang.ai.claw.common.core.PageResult;
import com.muang.ai.claw.module.system.entity.dept.PostEntity;
import com.muang.ai.claw.util.object.BeanUtils;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.post.PostPageForm;
import com.muang.ai.claw.module.system.controller.admin.dept.vo.post.PostSaveForm;
import com.muang.ai.claw.module.system.mapper.dept.PostMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.muang.ai.claw.common.exception.util.ServiceExceptionUtil.exception;
import static com.muang.ai.claw.util.collection.CollectionUtils.convertMap;
import static com.muang.ai.claw.module.system.constant.ErrorCodeConstants.*;

/**
 * 岗位 Service 实现类
 *
 */
@Service
@Validated
public class PostService {

    @Resource
    private PostMapper postMapper;

    public Long createPost(PostSaveForm createReqVO) {
        // 校验正确性
        validatePostForCreateOrUpdate(null, createReqVO.getName(), createReqVO.getCode());

        // 插入岗位
        PostEntity post = BeanUtils.toBean(createReqVO, PostEntity.class);
        postMapper.insert(post);
        return post.getId();
    }

    public void updatePost(PostSaveForm updateReqVO) {
        // 校验正确性
        validatePostForCreateOrUpdate(updateReqVO.getId(), updateReqVO.getName(), updateReqVO.getCode());

        // 更新岗位
        PostEntity updateObj = BeanUtils.toBean(updateReqVO, PostEntity.class);
        postMapper.updateById(updateObj);
    }

    public void deletePost(Long id) {
        // 校验是否存在
        validatePostExists(id);
        // 删除岗位
        postMapper.deleteById(id);
    }

    public void deletePostList(List<Long> ids) {
        postMapper.deleteByIds(ids);
    }

    private void validatePostForCreateOrUpdate(Long id, String name, String code) {
        // 校验自己存在
        validatePostExists(id);
        // 校验岗位名的唯一性
        validatePostNameUnique(id, name);
        // 校验岗位编码的唯一性
        validatePostCodeUnique(id, code);
    }

    private void validatePostNameUnique(Long id, String name) {
        PostEntity post = postMapper.selectByName(name);
        if (post == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的岗位
        if (id == null) {
            throw exception(POST_NAME_DUPLICATE);
        }
        if (!post.getId().equals(id)) {
            throw exception(POST_NAME_DUPLICATE);
        }
    }

    private void validatePostCodeUnique(Long id, String code) {
        PostEntity post = postMapper.selectByCode(code);
        if (post == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的岗位
        if (id == null) {
            throw exception(POST_CODE_DUPLICATE);
        }
        if (!post.getId().equals(id)) {
            throw exception(POST_CODE_DUPLICATE);
        }
    }

    private void validatePostExists(Long id) {
        if (id == null) {
            return;
        }
        if (postMapper.selectById(id) == null) {
            throw exception(POST_NOT_FOUND);
        }
    }

    public List<PostEntity> getPostList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return postMapper.selectByIds(ids);
    }

    public List<PostEntity> getPostList(Collection<Long> ids, Collection<Integer> statuses) {
        return postMapper.selectList(ids, statuses);
    }

    public PageResult<PostEntity> getPostPage(PostPageForm reqVO) {
        return postMapper.selectPage(reqVO);
    }

    public PostEntity getPost(Long id) {
        return postMapper.selectById(id);
    }

    public void validatePostList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得岗位信息
        List<PostEntity> posts = postMapper.selectByIds(ids);
        Map<Long, PostEntity> postMap = convertMap(posts, PostEntity::getId);
        // 校验
        ids.forEach(id -> {
            PostEntity post = postMap.get(id);
            if (post == null) {
                throw exception(POST_NOT_FOUND);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(post.getStatus())) {
                throw exception(POST_NOT_ENABLE, post.getName());
            }
        });
    }
}
