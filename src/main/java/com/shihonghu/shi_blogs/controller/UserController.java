package com.shihonghu.shi_blogs.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.system.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihonghu.shi_blogs.common.dto.LoginDto;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.entity.User;
import com.shihonghu.shi_blogs.service.UserService;
import com.shihonghu.shi_blogs.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result createUser(@Validated @RequestBody User user){

        if (user==null){
            return Result.fail("用户不能为空");
        }else {
            user.setUsername(user.getUsername());//设置用户名
            user.setPassword(user.getPassword());//设置密码
            user.setNickname(user.getNickname());//设置昵称
            user.setAvatar(user.getAvatar());//设置头像地址
            user.setEmail(user.getEmail());//设置邮箱地址
            user.setCreateTime(LocalDateTime.now());//角色创建时间
            user.setUpdateTime(LocalDateTime.now());//角色修改时间
            userService.saveOrUpdate(user);
        }
        return Result.succ(user);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return删除的数据
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);//根据ID获取用户
        if(user.getRole().equals("role_root")){
            return Result.fail("禁止删除此用户");
        }
        if (userService.removeById(id)) {
            return Result.succ(user);
        } else {
            return Result.fail("删除失败");
        }

    }

    /**
     * 根据ID修改用户的状态
     * @param id
     * @return
     */
    @GetMapping("/pulish/{id}")
    public Result publish(@PathVariable(name = "id")Long id){
        User user = userService.getById(id);//根据ID获取用户
        if(user.getRole().equals("role_root")){
            return Result.fail("禁止禁用此用户");
        }
        if (user.getStatus()==0)
        {
            user.setStatus(1);
        }
        else {
            user.setStatus(0);
        }
        userService.saveOrUpdate(user);//修改
        return Result.succ(null);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result updateUser(@Validated @RequestBody User user){//根据提交的JSON数据修改用户
        if(user==null){
            return Result.fail("名称不能为空");
        }
        else{
            user.setUpdateTime(LocalDateTime.now());
            User subUser = userService.getById(user.getId());
            System.out.println(subUser);
            if(subUser.getRole().equals("role_root")){
                return Result.fail("禁止修改此用户");
            }
            //未修改密码
            if(user.getPassword().equals("")){
                user.setPassword(subUser.getPassword());
            }
            else{
                //这里可以添加功能，让数据为 ，存储在数据中的密码为md5加密后的
                user.setPassword(user.getPassword());
            }
            userService.saveOrUpdate(user);
        }
        return Result.succ(user);
    }

    /**
     * 查询用户信息并且扥也
     * @param currentPage 当前页数
     * @param pageSize 最大页数
     * @return  查询到的页面数据
     */
    @GetMapping("/list")
    public Result userList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<User> list = userService.getUserList();
//        System.out.println(userService);
//        System.out.println(list);
        int size = list.size();
        Page page = new Page(currentPage,pageSize);
        if (pageSize > size) {
            pageSize = size;
        }
        // 求出最大页数，防止currentPage越界
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        if (currentPage > maxPage) {
            currentPage = maxPage;
        }
        // 当前页第一条数据的下标
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;
        List pageList = new ArrayList();
        // 将当前页的数据放进pageList
        for (int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }
        page.setTotal(list.size()).setRecords(pageList);
        return Result.succ(page);
    }


}
