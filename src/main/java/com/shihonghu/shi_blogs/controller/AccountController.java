package com.shihonghu.shi_blogs.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihonghu.shi_blogs.common.dto.LoginDto;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.entity.User;
import com.shihonghu.shi_blogs.service.UserService;
import com.shihonghu.shi_blogs.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 用于控制注册登录登出
 */
@RestController
public class AccountController {

    @Autowired
    public UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, //输入的密码账号
                        HttpServletResponse response){//获取的状态
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //判断是否为空
        Assert.notNull(user,"用户并不存在");
        //判断账号是否错误，这里用的是MD5
        if(!(SecureUtil.md5(user.getPassword()).equals(SecureUtil.md5(loginDto.getPassword())))){
            System.out.println(user.getPassword());
            //密码不同则抛出异常
            return Result.fail("密码不正确");
        }
        if (user.getStatus()==0){
            return Result.fail("你的账号已经被注销");
        }
        //获取JWT
        String jwt = jwtUtils.generateToken(user.getId());

        //将token 放在我们的header里面
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.succ(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .put("role", user.getRole()).map()

        );
    }

    //认证权限登录之后才可以操作
    @RequiresAuthentication
    @RequestMapping("/logout")
    public Result logout() {
        //退出登录
        SecurityUtils.getSubject().logout();
        return Result.succ("注销成功");}

}
