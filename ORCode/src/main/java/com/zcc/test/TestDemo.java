package com.zcc.test;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/26 10:07
 */
@Data
public class TestDemo {
    //    @ExcelProperty(value = "客户名称", index = 1)
    // 分类名称
//    @ExcelProperty
    private String item;
    // 数量
//    @ExcelProperty
    private String count = "0";
}
