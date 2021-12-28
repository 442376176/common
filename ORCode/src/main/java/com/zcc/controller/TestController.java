package com.zcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zcc
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("/redis")
    public String testRedis(){
        redisTemplate.opsForValue().set("test","ok");
        return redisTemplate.opsForValue().get("test").toString();
    }

    @GetMapping("/123")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        List<TestDemo> list = new ArrayList<>();
//        for (int i = 0; i <10 ; i++) {
//            TestDemo demo = new TestDemo("1","1","1","1","1","1","1","1");
//            list.add(demo);
//        }
//        String s = JSONObject.toJSONString(list);
//        String s1 = HttpUtil.sendPost("http://127.0.0.1:8888/api/tenant/tenant/v1/write/tenantExcel", s, false);
//        System.out.println(JSON.parseObject(s1));
//        try{
//            ServletOutputStream outputStream = response.getOutputStream();
//        } catch (IOException e){
//            e.printStackTrace();
////        }
//        class ExportTest extends POIExcelExportUtils<User>{
//        }
//        List<String> column = new LinkedList<>();
//        column.add("id");
//        column.add("姓名");
//        List<User> userList = new ArrayList<>();
//        for (int i = 0; i <4 ; i++) {
//            User user = new User();
//            user.setId(i);
//            user.setName(i+"cs");
//            user.setSex(i%2==0?"男":"女");
//            userList.add(user);
//        }
//        ExportTest exportTest = new ExportTest();
//        exportTest.exportData(request,column,userList,"用户列表","用户表",response);
    }
}
