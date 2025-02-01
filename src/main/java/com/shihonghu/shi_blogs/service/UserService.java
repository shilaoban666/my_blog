package com.shihonghu.shi_blogs.service;

import com.shihonghu.shi_blogs.entity.User;
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
public interface UserService extends IService<User> {

    /**
     * 查询所有用户部分信息
     * @return  所有用户的部分信息
     */
    List<User> getUserList();
}
