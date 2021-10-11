package com.zcc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/16 17:27
 */
//@RequestMapping("/hello")
@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
