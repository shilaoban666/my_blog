package com.shihonghu.shi_blogs.mapper;

import cn.hutool.system.UserInfo;
import com.shihonghu.shi_blogs.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user  order by create_time desc")
    public List<User> getUser();
}
