package com.shihonghu.shi_blogs.mapper;

import com.shihonghu.shi_blogs.common.vo.BlogInfo;
import com.shihonghu.shi_blogs.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface BlogMapper extends BaseMapper<Blog> {
    @Select("select * from blog ")
    List<BlogInfo> getBlogByTypeName(String typeName);
}
