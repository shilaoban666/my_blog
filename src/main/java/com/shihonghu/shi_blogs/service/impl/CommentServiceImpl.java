package com.shihonghu.shi_blogs.service.impl;

import com.shihonghu.shi_blogs.common.vo.PageComment;
import com.shihonghu.shi_blogs.entity.Comment;
import com.shihonghu.shi_blogs.mapper.CommentMapper;
import com.shihonghu.shi_blogs.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    /**
     * 通过博客id和父评论id查找所有子评论 并按照时间倒序排序
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Override
    public List<PageComment> getPageCommentListByDesc(Long blogId, Long parentCommentId) {
        return commentMapper.getPageCommentListByPageAndParentCommentIdByDesc(blogId, parentCommentId);
    }

    /**
     * 通过博客id和父评论id查找所有子评论
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Override
    public List<PageComment> getPageCommentList(Long blogId, Long parentCommentId) {
        return commentMapper.getPageCommentListByPageAndParentCommentId(blogId, parentCommentId);
    }

}
