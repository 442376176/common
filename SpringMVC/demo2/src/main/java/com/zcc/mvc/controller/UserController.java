package com.zcc.mvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/18 10:17
 */
public class UserController {
    /**
     * 使用restful模拟用户的增删改查
     * /user    GET     查询所有用户信息
     * /user/1  GET     根据用户id查询用户信息
     * /user    POST    添加用户信息
     * /user/1  DELETE  根据用户id删除用户信息
     * /user    PUT     修改用户信息
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有用户信息");
        return "success";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserById(@PathVariable(value = "id") String id){
        System.out.println("根据id查询用户信息");
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String addUser(String username,String password){
        System.out.println("新增用户信息:名称"+username+"密码："+password);
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String editUser(String username,String password){
        System.out.println("修改用户信息:名称"+username+"密码："+password);
        return "success";
    }
}
