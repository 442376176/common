package com.zcc.test;

import com.zcc.JUC.interviewQuestions.SleepHelper;
import com.zcc.entity.User;
import com.zcc.entity.User1;
import com.zcc.utils.CollectionUtils;
import com.zcc.utils.PinYinUtil;
import com.zcc.utils.idCardUtil.IDCardUtils;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
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


    private Function<String, String> isDouble = (s) -> {
        double i = Double.valueOf(s);
        int j = (int) i;
        if (i - j > 0) {
            return String.valueOf(i);
        }
        return String.valueOf(j);
    };

    @Test
    public void g() {
        System.out.println(isDouble.apply("1.000"));
    }

    @Test
    public void f() {
        long l = System.currentTimeMillis() / 1000;
        long s = 1640099415;
        System.out.println(l - s);
        long l1 = (l - s) / 60;
        long l2 = l1 / 60;
        long l3 = l2 / 24;
        System.out.println(l3);
    }

    @Test
    public void e() {

        List<Long> l1 = new ArrayList<>();
        List<Long> l2 = new ArrayList<>();
        l1.add(1L);
        l1.add(2L);
        l2.add(2L);
        CollectionUtils.getUnion(l1, l2);
        System.out.println(l1);

    }

    /**
     * 字符串转换unicode
     * @param string
     * @return
     */
    /**
     * 字符串转换unicode
     */
//中文转unicode编码
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }


    @Test
    public void h() throws Exception {
        String s = URLEncoder.encode("ss", "UTF-8");
        String s1 = gbEncoding(s);
        System.out.println(s1);
    }

    @Test
    public void testXmlUtil() {
//        try {
//            String chartType = XMLUtil.getChartType("chartType", "src\\main\\resources\\xml\\config.xml", 0);
//            System.out.println(chartType);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void d() {
        User user = new User();
        User user1 = user;
        user1.setPhone("123");
        user.setPhone("12");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user);
        users.stream().map(item -> {
            if (item.getPhone().equals("12")) {
                item.setPhone("123");
            }
            return item;
        }).collect(Collectors.toList());
        System.out.println(user);


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



//    @Test
//    public void testBuilderAnnotation() {
//        TestBuilder build = TestBuilder.builder()
//                .age("1")
//                .name("1")
//                .build();
//    }
//    @Data
//    @Builder
//    public class TestBuilder {
//        private String name;
//        private String age;
//    }

    private static volatile int i = 0;
    @Test
    public void test1(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();

    }

    @Test
    public void test2(){
//        String s = "2021-02-11";
//        String substring = s.substring(0, s.lastIndexOf("-"));
//        System.out.println(substring);
        int i = 1;

        long l = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            BitSet bitSet = new BitSet(Integer.MAX_VALUE - 16);
            
        }
        System.out.println(System.currentTimeMillis()-l);
        long l1 = System.currentTimeMillis();
        for (int j = 0; j < 1000; j++) {
            for (int k = 0; k < 1000000; k++) {
                i++;
            }
        }
        System.out.println(System.currentTimeMillis()-l1);
    }


    @Test
    public void test3(){
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        List<String> string1 = new ArrayList<>();
        string1.add("a");
        string1.add("d");
        System.out.println(strings.retainAll(string1));
        System.out.println(strings);
    }


    @Test
    public void test4(){
    String s = "123456";
        String[] split = s.split(",");
        System.out.println(split[0]);
    }

    @Test
    public void test5(){
        StopWatch watch = new StopWatch();
        watch.start();
        SleepHelper.sleepMilli(1000);
        watch.stop();
        System.out.println(watch);
        watch.start();
        SleepHelper.sleepMilli(1000);
        watch.stop();
        System.out.println(watch);
    }

    @Test
    public void test6(){
      List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(1);
        a.add(2);
        b.add(1);
//        b.add(2);
        b.add(3);
        System.out.println(a.removeAll(b));
    }
}



