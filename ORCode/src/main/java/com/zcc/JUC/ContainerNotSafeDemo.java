package com.zcc.JUC;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.thread
 * @author: zcc
 * @date: 2022/3/1 11:25
 * @version:
 * @Describe:集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        // 一个空的数组 size为10
        List<String> list = new CopyOnWriteArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.forEach(System.out::println);
//        Collections.synchronizedList(new ArrayList<>());
        /**
         * 1 故障现象
         * java.util.ConcurrentModificationException
         * 多线程访问的常见异常 并发修改异常
         *
         * 2 导致原因
         *  并发争抢修改导致
         *  A正在写，B过来抢夺，导致数据不一致异常。并发修改异常
         *
         * 3 解决方案
         *  3.1 使用Vector 不推荐 性能差
         *  3.2 使用List<String> list = Collections.synchronizedList(new ArrayList<>()); 不推荐 性能差
         *  3.3 使用new CopyOnWriteArrayList<>()
         *
         * 4 优化建议
         *
         */
//       java.util.ConcurrentModificationException
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        /**
         * HashSet
         *
         * 1.Collections.synchronizedSet(new HashSet<>());
         * 2.new CopyOnWriteArraySet<>();
         */
//        Set<String> strings = new HashSet<>();
//        Set<String> strings = new TreeSet<>();
//        Set<String> strings = Collections.synchronizedSet(new HashSet<>());
//        Set<String> strings = new LinkedHashSet<>();
        Set<String> strings = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                strings.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(strings);
            }, String.valueOf(i)).start();
        }

        /**
         * HashMap
         *
         * 1.new HashMap<>();
         * 2.Collections.synchronizedMap(new HashMap<>());
         * 3.new ConcurrentHashMap<>();
         *
         */
//        Map<String, String> stringStringHashMap = new HashMap<>();
//        Map<String, String> stringStringHashMap = new Hashtable<>();
//        Map<String, String> stringStringHashMap = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> stringStringHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                stringStringHashMap.put(UUID.randomUUID().toString().substring(0, 8), "1");
                System.out.println(stringStringHashMap);
            }, String.valueOf(i)).start();
        }
    }
}
