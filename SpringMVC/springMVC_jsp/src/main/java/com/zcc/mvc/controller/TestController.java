package com.zcc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/17 19:39
 */

@Controller
public class TestController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
