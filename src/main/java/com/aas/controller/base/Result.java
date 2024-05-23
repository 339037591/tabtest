package com.aas.controller.base;

import lombok.Data;

@Data
public class Result {
    //是否成功
    private Boolean success;
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //数据
    private Object  data;

    public Result(Boolean success, Integer code, String msg, Object data) {
        this.success=success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object object) {
        return new Result(true,200,"成功",object);
    }
    public static Result success() {
        return new Result(true,200,"成功",null);
    }
    public static Result errro(Integer code,String msg ) {
        return new Result(false,code,msg,null);
    }
}
