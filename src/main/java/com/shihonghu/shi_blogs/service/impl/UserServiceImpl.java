package com.shihonghu.shi_blogs.service.impl;

import com.shihonghu.shi_blogs.entity.User;
import com.shihonghu.shi_blogs.mapper.UserMapper;
import com.shihonghu.shi_blogs.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    public List<User> getUserList(){
        List<User> userInfos = userMapper.getUser();
        System.out.println(userMapper);
        System.out.println(userInfos);
        return  userInfos;
    }
}
