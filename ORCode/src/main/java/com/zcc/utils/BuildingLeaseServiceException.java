package com.zcc.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName BuildingLeaseServiceException
 * @Description 通用楼宇租赁业务异常
 * @Author yjf
 * @Version 1.0
 **/
public class BuildingLeaseServiceException extends Exception{
    protected String code;

    protected String message;


    public BuildingLeaseServiceException() {

    }

    public BuildingLeaseServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BuildingLeaseServiceException(String msg, Throwable cause, Object... objects) {
        this();
        String format = StringUtils.replace(msg, "{}", "%s");
        this.message= String.format(format, objects);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}