package com.shihonghu.shi_blogs.controller;


import com.shihonghu.shi_blogs.common.lang.Result;
import com.shihonghu.shi_blogs.common.vo.VisitorNum;
import com.shihonghu.shi_blogs.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@RestController
public class VisitorController {
    @Autowired
    VisitorService visitorService;
    /**
     * 获取总uv和pv
     */
    @GetMapping("/visitornum")
    public Result getPvAndUv() {
        int uv = visitorService.list().size();
        System.out.println(uv);
        int pv = visitorService.getPv();
        System.out.println(pv);
        VisitorNum visitorNum = new VisitorNum(uv,pv);

        return Result.succ(visitorNum);
    }



}
