package com.zcc.mvc.controller;

import bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/16 17:27
 */
//@RequestMapping("/hello")
@Controller
public class TestController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/requestMapping","/test","/213"}, method = RequestMethod.POST)
    public String success(){
        return "success";
    }

    @RequestMapping(value = "/testParams",params = {"username"})
    public String testParams(){
        return "success";
    }
    @RequestMapping(value = "/testParams",headers = {"username"})
    public String testHeader(){
        return "success";
    }
    @RequestMapping(value = "/testPath/{id}")
    public String testPath(@PathVariable(value = "id")Integer id){
        System.out.println("id="+id);
        return "success";
    }
///////////-----------------------------------------------------------------------------------------------------


    @RequestMapping(value = "/param")
    public String param(HttpServletRequest req, HttpServletResponse res){
//        req.getParameter()
        return "test_param";
    }


    @RequestMapping(value = "/testBean")
    public String testBean(User user){
        System.out.println(user);
        return "success";
    }
//
//    @RequestMapping("/testServletAPI")
//    public String testServletAPI(HttpServletRequest request){
//     ,servletAPI");
//        return "success";
//    }
}
