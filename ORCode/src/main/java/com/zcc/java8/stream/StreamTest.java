package com.zcc.java8.stream;

import com.zcc.entity.User;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.java8
 * @author: zcc
 * @date: 2022/3/31 9:39
 * @version:
 * @Describe: java8 流操作测试
 */

public class StreamTest {

    /*
    * Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API 来并行执行操作。简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

        特点：

        1. 不是数据结构，不会保存数据。

        2. 不会修改原来的数据源，它会将操作后的数据保存到另外一个对象中。（保留意见：毕竟peek方法可以修改流中元素）

        3. 惰性求值，流在中间处理过程中，只是对操作进行了记录，并不会立即执行，需要等到执行终止操作的时候才会进行实际的计算。

    * */
    @Test
    public void create() throws FileNotFoundException {
        // Collection的 stream() 和 parallelStream() 将集合转成流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();
        // Arrays 的 stream() 将数组转为流
        int[] array = new int[2];
        Object[] objArray = new Object[23];
        IntStream stream1 = Arrays.stream(array);
        Stream<Object> stream2 = Arrays.stream(objArray);
        // Stream中的静态方法 of()、iterate() 和 generate()
        Stream<String> s = Stream.of("!", "4", "s");
        System.out.println(s);
        Stream<StringBuilder> a = Stream.iterate(new StringBuilder("a"), to -> to.append((char) (to.toString().charAt(to.length() - 1) + 1))).limit(26);
//        a.forEach(System.out::println);
//        ArrayList<User> list1 = new ArrayList<>();
//        for (int i = 0; i < 100000000; i++) {
//            list1.add(new User(i,"s"));
//        }
        List<User> limit = Stream.generate(User::new).limit(10).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis());
        List<User> collect = limit.stream().filter(item -> item.getId() % 2 == 0).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis());
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = limit.get(i);
            if (user.getId() % 2 == 0) {
                users.add(user);
            }
        }
        System.out.println(System.currentTimeMillis());

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\notes\\Reflect.md"));
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(System.out::println);


        Pattern pattern = Pattern.compile(",");
        Stream<String> s1 = pattern.splitAsStream("a,b,c,d");
        s1.forEach(System.out::println);

    }

    @Test
    public void testMiddleOperator() {
        class Student {
            private String name;
            private int age;

            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public String toString() {
                return "Student{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
        //1 筛选与切片
        /**
         *  filter：过滤流中的某些元素
         *  limit(n)：获取n个元素
         *  skip(n)：跳过n元素，配合limit(n)可实现分页
         *  distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
         */
        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14)
                .filter(e -> e > 5)
                .distinct()
                .skip(2)
                .limit(2);
//        stream.forEach(System.out::println);
        //2 映射
        /**
         *   map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
         *   flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
         */
        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
//        s1.forEach(System.out::println); // abc  123

        Map<? extends Class<?>, ? extends List<? extends Serializable>> collect = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<? extends Serializable> stream1 = Arrays.stream(split).map(e -> {
                if (e.toCharArray()[0] > '0' && e.toCharArray()[0] < '9') {
                    return Integer.parseInt(e);
                }
                if (e.toCharArray()[0] > 'a' && e.toCharArray()[0] < 'z') {
                    return e;
                }
                return e;
            });
            return stream1;
        }).collect(Collectors.groupingBy(Object::getClass));
        collect.forEach((k, v) -> {
//            System.out.println(k + "值" + v);
        }); // a b c 1 2 3

        // 3 排序
        /**
         * sorted()：自然排序，流中元素需实现Comparable接口
         * sorted(Comparator com)：定制排序，自定义Comparator排序器
         */
        List<String> c = Arrays.asList("ag", "ac", "ba");
        //String 类自身已实现Compareable接口
//        c.stream().sorted().forEach(System.out::println);// aa dd ff

        Student s = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);
        List<Student> studentList = Arrays.asList(s, s2, s3, s4);

        //自定义排序：先按姓名升序，姓名相同则按年龄升序
        studentList.stream().sorted(
                (o1, o2) -> {
                    if (o1.getName().equals(o2.getName())) {
                        return o1.getAge() - o2.getAge();
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
//        ).forEach(System.out::println);
        // 2.4 消费
        // peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。

        Student ac = new Student("aa", 10);
        Student ad = new Student("bb", 20);
        List<Student> lists = Arrays.asList(ac, ad);

        lists.stream()
                .peek(o -> o.setAge(100));
//                .forEach(System.out::println);
    }

    @Test
    public void stopOperation() {
        /**
         * 3.1 匹配、聚合操作
         *         allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
         *         noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
         *         anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
         *         findFirst：返回流中第一个元素
         *         findAny：返回流中的任意元素
         *         count：返回流中元素的总个数
         *         max：返回流中元素最大值
         *         min：返回流中元素最小值
         */

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
//        System.out.println(list1.stream().allMatch(a -> a > 5));
//        System.out.println(list1.stream().anyMatch(a -> a > 4));
//        System.out.println(list1.stream().anyMatch(a -> a > 5));
//        System.out.println(list1.stream().noneMatch(a -> a > 5));
//        System.out.println(list1.stream().findFirst().orElse(6));
//        System.out.println(list1.stream().findAny().get());
//        System.out.println(list1.stream().count());
//        System.out.println(list1.stream().max(Integer::compareTo).get());
//        System.out.println(list1.stream().min(Integer::compareTo).get());

        /**
         * 3.2 规约操作
         *         Optional<T> reduce(BinaryOperator<T> accumulator)：第一次执行时，accumulator函数的第一个参数为流中的第一个元素,第二个参数为流中元素的第二个元素；
         *         第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推。
         *         T reduce(T identity, BinaryOperator<T> accumulator)：流程跟上面一样，只是第一次执行时，accumulator函数的第一个参数为identity，而第二个参数为流中的第一个元素。
         *         <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner)：
         *         在串行流(stream)中，该方法跟第二个方法一样，即第三个参数combiner不会起作用。在并行流(parallelStream)中,我们知道流被fork join出多个线程进行执行，
         *         此时每个线程的执行流程就跟第二个方法reduce(identity,accumulator)一样，
         *         而第三个参数combiner函数，则是将每个线程的执行结果当成一个新的流，然后使用第一个方法reduce(accumulator)流程进行规约。
         *         例如：reduce(initParam,add,add)
         *         List<Integer> list = Arrays.asList(1,2,3)
         *         list.parallelStream().reduce(5,Integer::sum,Integer::sum) = 21
         *         分线程并行走 计算过程： a = 1+5 b = 2+5 c = 3+5
         *         result = a+b+c
         */
        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L);
        List<AtomicLong> longList = list.stream().map(AtomicLong::new).collect(Collectors.toList());
        Long v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        Long v1 = list.stream().reduce(10L, (x1, x2) -> x1 + x2);
        System.out.println(v1);  //310

        Long v2 = list.stream().reduce(0L,
                (x1, x2) -> x1 - x2,
                (x1, x2) -> x1 * x2);
        System.out.println(v2); // -300
        Long v3 = list.parallelStream().reduce(0L,
                (x1, x2) -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x1 - x2;
                },
                (x1, x2) -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x1 * x2;
                });
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        //activeCount()返回当前正在活动的线程的数量
        int total = Thread.activeCount();
        Long[] sum = {1L};
        list.forEach(item -> sum[0] *= item);
        long s = sum[0] * (-300);
        System.out.println(s);
        System.out.println(Stream.of(1, 2, 3).reduce(4, Integer::sum,Integer::sum));
        System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, Integer::sum,Integer::sum));
        int total1 = Thread.activeCount();

        /**
         * 收集操作
         *         collect：接收一个Collector实例，将流中元素收集成另外一个数据结构。
         *         Collector<T, A, R> 是一个接口，有以下5个抽象方法：
         *             Supplier<A> supplier()：创建一个结果容器A
         *             BiConsumer<A, T> accumulator()：消费型接口，第一个参数为容器A，第二个参数为流中元素T。
         *             BinaryOperator<A> combiner()：函数接口，该参数的作用跟上一个方法(reduce)中的combiner参数一样，将并行流中各                                                                 个子进程的运行结果(accumulator函数操作后的容器A)进行合并。
         *             Function<A, R> finisher()：函数式接口，参数为：容器A，返回类型为：collect方法最终想要的结果R。
         *             Set<Characteristics> characteristics()：返回一个不可变的Set集合，用来表明该Collector的特征。有以下三个特征：
         *                 CONCURRENT：表示此收集器支持并发。（官方文档还有其他描述，暂时没去探索，故不作过多翻译）
         *                 UNORDERED：表示该收集操作不会保留流中元素原有的顺序。
         *                 IDENTITY_FINISH：表示finisher参数只是标识而已，可忽略。
         */
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Map<Boolean, List<Integer>> collect = integerStream.collect(Collectors.partitioningBy(a -> a > 1));
        List<Integer> list2 = Stream.of(1, 2, 3).collect(Collectors.toList());
        Set<Integer> a = Stream.of(1, 2, 3).collect(Collectors.toSet());
        String collect1 = Stream.of(1, 2, 3).map(String::valueOf).collect(Collectors.joining(","));

        Double collect2 = Stream.of(1, 2, 3).collect(Collectors.averagingInt(Integer::intValue));
        Double collect3 = Stream.of(1.0, 2.0, 3.0).collect(Collectors.averagingDouble(Double::doubleValue));
        Double collect4 = Stream.of(1L, 2L, 3L).collect(Collectors.averagingLong(Long::longValue));
        int sum1 = Stream.of(1, 2, 3).collect(Collectors.summingInt(Integer::intValue));
        Double sum2 = Stream.of(1.0, 2.0, 3.0).collect(Collectors.summingDouble(Double::doubleValue));
        long sum3 = Stream.of(1L, 2L, 3L).collect(Collectors.summingLong(Long::longValue));
        LongSummaryStatistics collect5 = Stream.of(1L, 2L, 3L).collect(Collectors.summarizingLong(Long::longValue));
        IntSummaryStatistics collect6 = Stream.of(1, 2, 3).collect(Collectors.summarizingInt(Integer::intValue));
        DoubleSummaryStatistics collect7 = Stream.of(1.0, 2.0, 3.0).collect(Collectors.summarizingDouble(Double::doubleValue));
        Long collect8 = Stream.of(1L, 2L, 3L).collect(Collectors.counting());
        Map<String, Long> collect9 = Stream.of(11L, 2L, 3L,11L).collect(Collectors.toMap(Long::toHexString, Long::valueOf,(aw,b)->aw+b));
        Map<Integer, Map<Integer, List<Integer>>> collect10 = Stream.of(1, 2, 3).collect(Collectors.groupingBy(h -> h > 1 ? h : 0, Collectors.groupingBy(r -> r < 3 ? r : 10)));

        System.out.println(collect5);


    }


}
