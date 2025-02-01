package com.shihonghu.shi_blogs.service;

import com.shihonghu.shi_blogs.common.vo.BlogInfo;
import com.shihonghu.shi_blogs.entity.Blog;
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
public interface BlogService extends IService<Blog> {
    List<BlogInfo> getBlogListByCategoryName(String categoryName);
}
