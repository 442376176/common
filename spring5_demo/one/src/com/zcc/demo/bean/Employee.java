package com.zcc.demo.bean;

import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/9 14:44
 */
public class Employee {
    private String no;
    private String name;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
