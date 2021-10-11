package com.zcc.controller;

import com.alibaba.excel.EasyExcel;
import com.zcc.entity.Excel;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/20 17:17
 */
@RestController
public class TestController {


        public static void main(String[] args) {

            //实现excel写的操作
            //1 设置写入文件夹地址和excel文件名称
            String filename = "C:\\Users\\86151\\Desktop\\demo\\eazyExceldemo\\write.xlsx";
            // 2 调用easyexcel里面的方法实现写操作
            // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
            EasyExcel.write(filename, Excel.class).sheet("学生列表").doWrite(getData());

        }

        //创建方法返回list集合
        private static List<Excel> getData() {
            List<Excel> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Excel data = new Excel();
                data.setData(i+"");
                data.setName("lucy"+i);
                list.add(data);
            }
            return list;
        }

}
