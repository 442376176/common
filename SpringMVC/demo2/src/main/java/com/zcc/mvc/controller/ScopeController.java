package com.zcc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/17 14:36
 */
@Controller
public class ScopeController {

    @RequestMapping("/testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest request) {
        request.setAttribute("testRequestScope","hello,ServletApi");
        return "success";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("testRequestScope","hello , modelAndView");
        mav.setViewName("success");
        System.out.println(mav.getClass().getName());
        return mav;
    }


    @RequestMapping("/testModel")
    public String testModel(Model model) {
        model.addAttribute("testRequestScope","hello , model");
        System.out.println(model.getClass().getName());
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map) {
        map.put("testRequestScope","hello,map");
        System.out.println(map);
        return "success";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("testRequestScope","hello , modelMap");
        System.out.println(modelMap.getClass().getName());
        return "success";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        session.setAttribute("testSession","hello , session");
        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("testSession","hello , application");
        return "success";
    }
    @RequestMapping("/test_rest")
    public String testRESTFul() {
        return "test_restFul";
    }
}
