package com.shihonghu.shi_blogs.service.impl;

import com.shihonghu.shi_blogs.common.vo.BlogInfo;
import com.shihonghu.shi_blogs.entity.Blog;
import com.shihonghu.shi_blogs.mapper.BlogMapper;
import com.shihonghu.shi_blogs.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Override
    public List<BlogInfo> getBlogListByCategoryName(String categoryName) {
        List<BlogInfo> blogs = blogMapper.getBlogByTypeName(categoryName);
        return blogs;
    }
}
