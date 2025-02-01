package com.shihonghu.shi_blogs.mapper;

import com.shihonghu.shi_blogs.common.vo.PageComment;
import com.shihonghu.shi_blogs.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据创建时间倒序 根据博客id和父评论id查询所有子评论
     */
    @Select("select id, nickname, content, website,avatar, create_time, is_admin_comment,parent_comment_nickname  from comment where blog_id=#{blogId} and parent_comment_id=#{parentCommentId} order by create_time desc")
    List<PageComment> getPageCommentListByPageAndParentCommentIdByDesc(@Param("blogId") long blogId, @Param("parentCommentId") long parentCommentId);

    /**
     * 根据博客id和父评论id查询所有子评论
     */
    @Select("select id, nickname, content, website,avatar, create_time, is_admin_comment,parent_comment_nickname  from comment where blog_id=#{blogId} and parent_comment_id=#{parentCommentId} order by create_time")
    List<PageComment> getPageCommentListByPageAndParentCommentId(@Param("blogId") long blogId, @Param("parentCommentId") long parentCommentId);

}
