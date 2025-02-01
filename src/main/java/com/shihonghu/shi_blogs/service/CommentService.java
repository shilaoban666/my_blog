package com.shihonghu.shi_blogs.service;

import com.shihonghu.shi_blogs.common.vo.PageComment;
import com.shihonghu.shi_blogs.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
public interface CommentService extends IService<Comment> {

    /**
     * 通过博客id和父评论id查找所有子评论 并按照时间倒序排序
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    public List<PageComment> getPageCommentListByDesc(Long blogId, Long parentCommentId);

    /**
     * 通过博客id和父评论id查找所有子评论
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    public List<PageComment> getPageCommentList(Long blogId, Long parentCommentId);
}
