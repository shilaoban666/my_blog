package com.shihonghu.shi_blogs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.common.vo.BlogInfo;
import com.shihonghu.shi_blogs.entity.Blog;
import com.shihonghu.shi_blogs.service.BlogService;
import com.shihonghu.shi_blogs.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  博客前端控制器
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@RestController
//@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    /**
     * 查询页面，并且分页分页
     * @param currentPage  当前页面
     * @return 当前页面信息
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1")Integer currentPage){
        //默认分页为5
        Page page = new Page(currentPage, 5);

        IPage pageData = blogService.page(page,
                new QueryWrapper<Blog>().orderByDesc("create_time"));//倒叙查询
        return Result.succ(pageData);
    }

    /**
     * 按类型进行博客的查找
     * @param currentPage
     * @param typeName
     * @return
     */
    @GetMapping("/blogsByType")
    public Result blogsByType(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam String typeName) {
//        if (redisService.hasHashKey(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage)) {
//            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage));
//        }

        List<BlogInfo> list = blogService.getBlogListByCategoryName(typeName);
        int pageSize = 10;
        Page page = new Page();
        int size = list.size();
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
        page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);

//        redisService.saveKVToHash(RedisKeyConfig.CATEGORY_BLOG_CACHE, typeName+currentPage, page);
        return Result.succ(page);
    }

    /**
     * 按创建时间查询所有博客
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/blogList")
    public Result blogList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) {

        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("create_time"));

        return Result.succ(pageData);
    }

    /**
     * 查询所有博客
     * @return
     */
    @GetMapping("/blog/all")
    public Result blogAll() {
        List<Blog> list = blogService.lambdaQuery().list();
        return Result.succ(list);
    }

    /**
     *
     * @param id 博客的ID
     * @return  查询到的博客信息
     */
    //动态路由
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable Long id){
//        通过ID来查这个博客
        Blog blog = blogService.getById(id);
        //判断是否为空 为空则断言异常
        Assert.notNull(blog,"该博客已被删除");
        if (blog.getStatus()!=1){
            return Result.fail("你没有权限查阅此博客");
        }
        return Result.succ(blog);
    }

    /**
     * 查询某个博客详细信息
     * @param id
     * @return
     */
    @GetMapping("/blog/detail/{id}")
    public Result getDetail(@PathVariable(name = "id") Long id) {


        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");

        return Result.succ(blog);

    }
    /**
     *
     * @param id 博客的ID
     * @return  博客的详细信息
     */
    //@PathVariable动态路由
//    @RequiresAuthentication  //需要认证之后才能操作
    @GetMapping("/blog/delete/{id}")
    public Result del(@PathVariable Long id){
        boolean b = blogService.removeById(id);
        //判断是否为空 为空则断言异常
        if(b==true){

            return Result.succ("文章删除成功");
        }else{
            return Result.fail("文章删除失败");
        }
    }

    /**
     * 查找我自己
     * @return
     */
    @GetMapping("/about")
    public Result aboutMe() {

        List<Blog> list = blogService.lambdaQuery().eq(Blog::getTitle, "关于我！！").list();
        return Result.succ(list.get(0));
    }

    /**
     * 新博客的编辑
     * @param blog  博客信息输入
     * @return    创建的博客
     */
    //@Validated校验
    //@RequestBody
    //添加删除  木有id则添加 有id则编辑

    @RequiresAuthentication  //需要认证之后才能操作
    @PostMapping("/blog/update")
    public Result edit(@Validated @RequestBody Blog blog){

        //一个空对象用于赋值
        Blog temp = null;
        System.out.println("temp="+temp);
        //如果有id则是编辑
        if(blog.getId() != null){//有博客，那就是编辑
            temp = blogService.getById(blog.getId());//将数据库的内容传递给temp
            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().
                    longValue()== ShiroUtil.getProfile().getId().longValue(),"没有编辑权限");

        } else {
            System.out.println("我没有文章");
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());//添加ID
            temp.setCreateTime(LocalDateTime.now());//添加
            temp.setStatus(0);
            System.out.println(temp);
        }
        temp.setUpdateTime(LocalDateTime.now());
        //将blog的值赋给temp 忽略 id userid created status 引用于hutool
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }


    /**
     *
     * @param queryString  所要搜索的博客信息
     * @return  搜索博客信息
     */
    @GetMapping("/search")
    public Result search(@RequestParam String queryString) {
        List<Blog> list = blogService.list(new QueryWrapper<Blog>().like("content", queryString).eq("status", 1).orderByDesc("create_time"));
        return Result.succ(list);
    }

    /**
     * 新建博客
     * @param blog
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blog/create")
    public Result create(@Validated @RequestBody Blog blog) {
        //System.out.println(blog.toString());
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreateTime(LocalDateTime.now());
        }
        temp.setUpdateTime(LocalDateTime.now());
        BeanUtil.copyProperties(blog, temp, "id", "userId", "createTime", "updateTime");
        boolean b = blogService.saveOrUpdate(temp);
//        redisService.deleteCacheByKey(RedisKeyConfig.BLOG_INFO_CACHE);
//        redisService.deleteCacheByKey(RedisKeyConfig.ARCHIVE_INFO_CACHE);
//        redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_BLOG_CACHE);
        return Result.succ(b);
    }


}
