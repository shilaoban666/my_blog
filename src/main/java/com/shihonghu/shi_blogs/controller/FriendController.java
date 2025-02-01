package com.shihonghu.shi_blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.entity.Friend;
import com.shihonghu.shi_blogs.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

public class FriendController {
    @Autowired
    FriendService friendService;

    /**
     * 查询寻所有公开的友链
     * @return
     */
    @RequestMapping("/friend/all")
    public Result getFriendList(){
        List<Friend> list = friendService.lambdaQuery().eq(Friend::getIsPublished, 1).list();
        return Result.succ(list);
    }
    @RequestMapping("/friendList")
    public Result friendList(@RequestParam(defaultValue = "1") Integer currentPage
            ,@RequestParam(defaultValue = "10") Integer pageSize){
        Page page = new Page(currentPage, pageSize);
        IPage pageDate = friendService.page(page, new QueryWrapper<Friend>().orderByDesc("create_time"));

        return  Result.succ(pageDate);
    }

}
