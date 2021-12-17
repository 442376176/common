package com.zcc.test;

import com.zcc.utils.idCardUtil.IDCardUtils;
import com.zcc.utils.PinYinUtil;
import lombok.Data;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/26 10:07
 */
@Data
public class TestDemo {
    //    @ExcelProperty(value = "客户名称", index = 1)
    // 分类名称
//    @ExcelProperty
    private String item;
    // 数量
//    @ExcelProperty
    private String count = "0";

    @Test
    public void testIdCardUtil() {
        System.out.println(IDCardUtils.getRegionNameByIdNo.apply("321200****0317"));

    }


    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\86151\\Desktop\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> collect = bufferedReader.lines().map(item -> PinYinUtil.getFirstLetter(item.split(",")[1].replace("\"", "")).toUpperCase() + "(" + item.trim().substring(0, item.trim().lastIndexOf(",")) + ")").collect(Collectors.toList());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\86151\\Desktop\\test1.txt"));
        collect.forEach(item->{
            try{
                bufferedWriter.write(item+",");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        bufferedWriter.close();
//        op.write(collect1.getBytes());
//        op.flush();
//        op.close();
    }
}
