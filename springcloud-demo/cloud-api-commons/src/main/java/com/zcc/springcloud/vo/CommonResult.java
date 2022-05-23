package com.zcc.springcloud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static CommonResult success(Object data){
        return new CommonResult(200,"成功",data);
    }
}
