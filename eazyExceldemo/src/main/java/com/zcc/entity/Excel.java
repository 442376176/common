package com.zcc.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/20 17:22
 */

@Data
public class Excel {
    @ExcelProperty({"主标题", "字符串标题"})
    private String name;
    @ExcelProperty({"主标题", "日期标题"})
    private String data;

}

