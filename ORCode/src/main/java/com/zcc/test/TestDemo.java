package com.zcc.test;

import com.zcc.entity.User;
import com.zcc.entity.User1;
import com.zcc.utils.PinYinUtil;
import com.zcc.utils.idCardUtil.IDCardUtils;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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


    private ExecutorService threadPoolExecutor = new ThreadPoolExecutor(3, 30, 0, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>());


    @Test
    public void d() {
        User user = new User();
        User user1 = user;
        user1.setPhone("123");
        System.out.println(user1);


    }

    @Test
    public void a() {
        threadPoolExecutor.execute(() -> testIdCardUtil());
        threadPoolExecutor.execute(() -> s());
        System.out.println(123);

    }

    @Test
    public void v() {
        System.out.println("调用a开始");
        a();
        System.out.println("结束");
    }

    @Test
    public void testIdCardUtil() {
        System.out.println(IDCardUtils.getRegionNameByIdNo.apply("321200****0317"));

    }

    @Test
    public void s() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("1", 2);
        System.out.println(map);

    }

    private <T, R> void getCopyList(List<T> listA, List<R> listB, Class<R> rClass) throws Exception {
        for (T t : listA) {
            R r = rClass.newInstance();
            BeanUtils.copyProperties(t, r);
            listB.add(r);
        }
//        return listB;
    }


    @Test
    public void c() throws Exception {
        List<User> list = new ArrayList<User>();
        User user = new User(1, "1");
        User user1 = new User(2, "2");
        list.add(user1);
        list.add(user);
        List<User1> list1 = new ArrayList<>();
        getCopyList(list, list1, User1.class);
        System.out.println(list1);
    }


    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\86151\\Desktop\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> collect = bufferedReader.lines().map(item -> PinYinUtil.getFirstLetter(item.split(",")[1].replace("\"", "")).toUpperCase() + "(" + item.trim().substring(0, item.trim().lastIndexOf(",")) + ")").collect(Collectors.toList());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\86151\\Desktop\\test1.txt"));
        collect.forEach(item -> {
            try {
                bufferedWriter.write(item + ",");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();
//        op.write(collect1.getBytes());
//        op.flush();
//        op.close();
    }
}
