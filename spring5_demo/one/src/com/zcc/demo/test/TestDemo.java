package com.zcc.demo.test;

import com.zcc.demo.bean.*;
import com.zcc.demo.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;


/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/8 10:02
 */
public class TestDemo {

    @Test
    public void testAdd(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        User user = classPathXmlApplicationContext.getBean("user", User.class);

        user.add();
        HashMap<String, Object> map = new HashMap<>();

    }
    @Test
    public void test2(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        Book book = classPathXmlApplicationContext.getBean("book", Book.class);

        System.out.println(book.getName());
        System.out.println(book.getPrice());
    }
    @Test
    public void test3() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        Order order = classPathXmlApplicationContext.getBean("order", Order.class);

        System.out.println(order);
    }
    @Test
    public void test4() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        Book book1 = classPathXmlApplicationContext.getBean("book1", Book.class);


        System.out.println(book1.getName());
        System.out.println(book1.getPrice());
    }

    @Test
    public void test5() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        UserService service = classPathXmlApplicationContext.getBean("userService", UserService.class);
        service.add();
    }


    @Test
    public void test6() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean2.xml");

        Employee emp = classPathXmlApplicationContext.getBean("emp", Employee.class);
        System.out.println(emp.getDept().getName());
    }

    @Test
    public void test7() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean2.xml");

        Employee emp = classPathXmlApplicationContext.getBean("emp1", Employee.class);
        System.out.println(emp.getDept().getName());
    }
    @Test
    public void test8() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean2.xml");

        Employee emp = classPathXmlApplicationContext.getBean("emp2", Employee.class);
        System.out.println(emp.getDept().getName());
    }
    @Test
    public void test9() {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean3.xml");

        Student stu = classPathXmlApplicationContext.getBean("stu", Student.class);
        System.out.println(stu);
    }
}
