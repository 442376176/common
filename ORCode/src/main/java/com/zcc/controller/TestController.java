package com.zcc.controller;

import com.zcc.entity.User;
import com.zcc.utils.excelExport.POIExcelExportUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/26 10:25
 */
@RestController
@RequestMapping("/test")
public class TestController {

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
//        }
        class ExportTest extends POIExcelExportUtils<User>{
        }
        List<String> column = new LinkedList<>();
        column.add("id");
        column.add("姓名");
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            User user = new User();
            user.setId(i);
            user.setName(i+"cs");
            user.setSex(i%2==0?"男":"女");
            userList.add(user);
        }
        ExportTest exportTest = new ExportTest();
//        exportTest.exportData(request,column,userList,"用户列表","用户表",response);
    }
}
