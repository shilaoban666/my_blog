package com.shihonghu.shi_blogs.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * 给前端传入统一的JSON对象
 */



@Data//lambok可以使用get 和set方法
public class Result  implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result succ( Object data){

        return succ(200,"操作成功",data);
    }
    //成功
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    //失败
    public static Result fail(String msg){

        return fail(400,msg,null);
    }
    //失败
    public static Result fail(String msg,Object data){

        return fail(400,msg,data);
    }
    //失败
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
