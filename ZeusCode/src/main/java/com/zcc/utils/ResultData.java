package com.zcc.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName AjaxResult
 * @Description TODO
 * @Author yangjianfei
 * @Version
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code ;

    private String msg;

    private T data;
}