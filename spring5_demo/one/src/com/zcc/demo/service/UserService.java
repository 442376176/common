package com.zcc.demo.service;

import com.zcc.demo.dao.UserDao;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/9 14:28
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        userDao.update();
        System.out.println("Add.....");
    }
}
