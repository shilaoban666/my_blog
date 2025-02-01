package com.shihonghu.shi_blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.entity.Type;
import com.shihonghu.shi_blogs.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class TypeController {
    @Autowired
    TypeService typeService;

    /**
     * 查询所有分类
     */
    @GetMapping("/types")
    public Result blogs(){
        List<Type> list = typeService.list(new QueryWrapper<Type>());
        return Result.succ(list);
    }
    /**
     * 分页查询分类
     */
    @GetMapping("type/list")
    public Result typeList(@RequestParam(defaultValue = "1")Integer currentPage,
                           @RequestParam(defaultValue = "10")Integer pageSize){
        Page page = new Page(currentPage, pageSize);
        IPage pageData = typeService.page(page, new QueryWrapper<Type>());
        return Result.succ(pageData);

    }

    /**
     * 增加分类
     */
    @PostMapping("/type/create")
    public Result createType(@Validated @RequestBody Type type){
        if (type==null) {
            return Result.fail("类型不能为空");
        }else {
            typeService.saveOrUpdate(type);
        }
        return Result.succ(type);
    }

    /**
     * 修改分类
     * @param type
     * @return
     */
    @PostMapping("/type/update")
    public Result updateType(@Validated @RequestBody Type type){
        if (type==null) {
            return Result.fail("类型不能为空");
        }else {
            typeService.saveOrUpdate(type);
        }
        return Result.succ(type);
    }
    /**
     * 删除分类
     */
    @GetMapping("/type/delete/{id}")
    public Result delete(@PathVariable(name="id") Long id){
        if (typeService.removeById(id)){
            return Result.succ(typeService.getById(id));
        }else {
            return Result.fail("删除失败");
        }
    }

}
