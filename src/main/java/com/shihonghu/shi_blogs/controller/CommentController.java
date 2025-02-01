package com.shihonghu.shi_blogs.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.common.vo.PageComment;
import com.shihonghu.shi_blogs.entity.Comment;
import com.shihonghu.shi_blogs.service.CommentService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
//    @Autowired     //用于邮件的启动和发送
//    MailService mailService;
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    /**
     * 分页查询所有评论
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/commentList")
    public Result getCommentListByPage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize ) {


        Page page = new Page(currentPage, pageSize);
        IPage pageData = commentService.page(page, new QueryWrapper<Comment>().orderByDesc("create_time"));

        //Assert.notNull(blog, "该博客已删除！");
        return Result.succ(pageData);

    }

    /**
     * 分页查询某个博客下面的评论
     * @param blogId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/comment/detail")
    public Result getCommentListByPageId(@RequestParam(defaultValue = "1") Long blogId, @RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize ) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = commentService.page(page, new QueryWrapper<Comment>().eq("blog_id",blogId).orderByDesc("create_time"));

        //Assert.notNull(blog, "该博客已删除！");
        return Result.succ(pageData);

    }
    /**
     * 获取某个博客下的所有评论
     */
    @GetMapping("/comment/{blogId}")
    public Result getCommentByBlogId(@PathVariable(name = "blogId") Long blogId) {

        //实体模型集合对象转换为VO对象集合
        List<PageComment> pageComments = commentService.getPageCommentListByDesc(blogId, (long) -1);

        for (PageComment pageComment : pageComments) {

            List<PageComment> reply = commentService.getPageCommentList(blogId, pageComment.getId());
            pageComment.setReplyComments(reply);
        }
        //Assert.notNull(blog, "该博客已删除！");
        return Result.succ(pageComments);
    }


}
