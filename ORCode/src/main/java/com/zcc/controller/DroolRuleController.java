//package com.zcc.controller;
//
///**
// * @author zcc
// * @version 1.0
// * @date 2021/12/17 10:07
// */
//
//import com.zcc.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.kie.api.KieBase;
//import org.kie.api.runtime.KieSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Slf4j
//@Controller
//public class DroolRuleController {
//
//    @Autowired
//    private KieBase kieBase;
//
//    //http://localhost:8080/rule
//    @RequestMapping("/rule")
//    @ResponseBody
//    public String rule(){
//        //StatefulKnowledgeSession
//        KieSession kieSession = kieBase.newKieSession();
//
//        User userInfo = new User();
//        userInfo.setName("superbing");
//        userInfo.setPhone("13618607409");
//
//        kieSession.insert(userInfo);
//        kieSession.setGlobal("log",log);
//        int firedCount = kieSession.fireAllRules();
//        kieSession.dispose();
//        System.out.println("触发了" + firedCount + "条规则");
//        return "触发了" + firedCount + "条规则";
//    }
//}
