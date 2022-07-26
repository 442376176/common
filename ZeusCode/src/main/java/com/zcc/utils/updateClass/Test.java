package com.zcc.utils.updateClass;

import com.zcc.entity.User;

public class Test {

    public static void main(String[] args) {
        User stu = new User();
        stu.setId(10);
        stu.setName("Tom");
//        stu.address = "Beijing";    // 新增加的 address 字段
//        stu.tel = 56;               // 新增加的 tel 字段
        System.out.println("Name:    " + stu.getName());
        System.out.println("Age:     " + stu.getId());
//        System.out.println("Address: " + stu.address);
//        System.out.println("Tel:     " + stu.tel);
    }
}