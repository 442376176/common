package com.zcc.test;

import com.zcc.Application;
import com.zcc.entity.User;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserDroolTest {
    @Autowired
    private KieBase kieBase;

    @Test
    public void rule(){
        //StatefulKnowledgeSession
        KieSession kieSession = kieBase.newKieSession();

        User userInfo = new User();
        userInfo.setName("superbing");
        userInfo.setPhone("13618607409");

        kieSession.insert(userInfo);
//        kieSession.setGlobal("log",log);
        int firedCount = kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("触发了" + firedCount + "条规则");
    }
}
