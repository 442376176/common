package com.zcc.controller;

import com.zcc.utils.DownloadUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
    @GetMapping("/test")
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception {
        File file = new File("C:\\Users\\86151\\Desktop\\软件设计师教程 第5版@www.java1234.com.pdf");
        InputStream inputStream = new FileInputStream(file);
        DownloadUtil.downloadFile(request, response, inputStream, "测试.pdf");
    }

    public void setData(HttpServletRequest request,HttpServletResponse response,File file) throws Exception{
        String agent = (String) request.getHeader("USER-AGENT");
        String name = file.getName();
        if (agent != null && agent.indexOf("MSIE") == -1) {// FF
            String enableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(name.getBytes("UTF-8")))) + "?=";
            response.setHeader("Content-Disposition", "inline; filename=" + enableFileName);
        } else { // IE
            String enableFileName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "inline; filename=" + enableFileName);
        }
        // 未知文件类型
        response.setContentType("application/octet-stream");

        // 读出文件到response
        // 这里是先需要把要把文件内容先读到缓冲区
        // 再把缓冲区的内容写到response的输出流供用户下载
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] b = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(b);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);
        // 人走带门
        bufferedInputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
