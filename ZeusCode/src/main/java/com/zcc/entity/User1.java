package com.zcc.entity;

import lombok.Data;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/22 15:18
 */
@Data
public class User1 {
    private Integer id;
    private String name;

    public void setId(Integer id) {
        this.id = 5;
    }
}
