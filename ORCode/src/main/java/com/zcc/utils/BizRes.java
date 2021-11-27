package com.zcc.utils;


import java.io.Serializable;

public class BizRes implements Serializable {
    private Integer status;
    private String msg;
    private Object data;

    public static BizRes build(Integer status, String msg, Object data) {
        return new BizRes(status, msg, data);
    }

    public static BizRes formException(BizException e) {
        return new BizRes(e.getStatus(), e.getMsg(), e.getData());
    }

    public static BizRes exception(Exception e) {
        return error500(e.getMessage());
    }

    public static BizRes success(Object data) {
        return new BizRes(200, "success", data);
    }

    public static BizRes success(String msg) {
        return new BizRes(200, msg, (Object)null);
    }

    public static BizRes error400(String msg) {
        return new BizRes(400, msg, "参数异常");
    }

    public static BizRes error400(String msg, String data) {
        return new BizRes(400, msg, data);
    }

    public static BizRes error500(String msg) {
        return new BizRes(500, msg, "服务器内部错误");
    }

    public static BizRes error500(String msg, String data) {
        return new BizRes(500, msg, data);
    }

    public static BizRes error600(String msg, String data) {
        return new BizRes(600, msg, data);
    }

    public static BizRes error600(String msg) {
        return new BizRes(600, msg, "业务逻辑错误");
    }

    public static BizRes success() {
        return new BizRes(200, "success", (Object)null);
    }

    public BizRes() {
    }

    public BizRes(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}