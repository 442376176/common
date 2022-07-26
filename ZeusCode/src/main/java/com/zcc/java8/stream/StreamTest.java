package com.zcc.java8.stream;

import com.zcc.entity.User;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BinaryOperator;
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
        System.out.println(Stream.of(1, 2, 3).reduce(4, Integer::sum, Integer::sum));
        System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, Integer::sum, Integer::sum));
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
        Map<String, Long> collect9 = Stream.of(11L, 2L, 3L, 11L).collect(Collectors.toMap(Long::toHexString, Long::valueOf, (aw, b) -> aw + b));
        Map<Integer, Map<Integer, List<Integer>>> collect10 = Stream.of(1, 2, 3).collect(Collectors.groupingBy(h -> h > 1 ? h : 0, Collectors.groupingBy(r -> r < 3 ? r : 10)));

        System.out.println(collect5);

    }

    // 测试收集器操作
    @Test
    public void collectorTest() {
        List<Student> menu = Arrays.asList(
                new Student("刘一", 721, true, Student.GradeType.THREE),
                new Student("陈二", 637, true, Student.GradeType.THREE),
                new Student("张三", 666, true, Student.GradeType.THREE),
                new Student("李四", 531, true, Student.GradeType.TWO),
                new Student("王五", 483, false, Student.GradeType.THREE),
                new Student("赵六", 367, true, Student.GradeType.THREE),
                new Student("孙七", 499, false, Student.GradeType.ONE));
        /**
         * 1.averagingDouble
         * averagingDouble方法返回一个Collector收集器，它生成应用于输入元素的double值函数的算术平均值。如果没有元素，则结果为0。
         * 返回的平均值可能会因记录值的顺序而变化，这是由于除了不同大小的值之外，还存在累积舍入误差。
         * 通过增加绝对量排序的值(即总量，样本越大，结果越准确)往往会产生更准确的结果。如果任何记录的值是NaN或者总和在任何点NaN，那么平均值将是NaN。
         * 注意： double格式可以表示-253到253范围内的所有连续整数。如果管道有超过253的值，则平均计算中的除数将在253处饱和，从而导致额外的数值误差。
         */

        /*序号	修饰符和类型	方法和描述
        1	static <T> Collector<T,?,Double>	averagingDouble(ToDoubleFunction<? super T> mapper)Returns a Collector that produces the arithmetic mean of a double-valued function applied to the input elements.
        2	static <T> Collector<T,?,Double>	averagingInt(ToIntFunction<? super T> mapper)Returns a Collector that produces the arithmetic mean of an integer-valued function applied to the input elements.
        3	static <T> Collector<T,?,Double>	averagingLong(ToLongFunction<? super T> mapper)Returns a Collector that produces the arithmetic mean of a long-valued function applied to the input elements.
        */

        Double averagingDouble = menu.stream().collect(Collectors.averagingDouble(Student::getTotalScore));
        Optional.ofNullable(averagingDouble).ifPresent(System.out::println);

        // 557.7142857142857
        /**
         * 2.collectingAndThen
         * collectingAndThen方法调整Collector收集器以执行其它的结束转换。例如，可以调整toList()收集器，以始终生成一个不可变的列表：
         */
        /*
         序号	修饰符和类型	方法和描述
           4	static <T,A,R,RR> Collector<T,A,RR>	collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)Adapts a Collector to perform an additional finishing transformation.
        */
        // 可以调整toList()收集器，以始终生成一个不可变的列表：
        List<Student> studentList = menu.stream().collect(
                Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(studentList);

        //以指定字符串The Average totalScore is->输出所有学生的平均总成绩
        // The Average totalScore is->557.7142857142857
        Optional.ofNullable(menu.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.averagingInt(Student::getTotalScore), a -> "The Average totalScore is->" + a)
        )).ifPresent(System.out::println);
        List<String> zcc = menu.stream().collect(
                Collectors.collectingAndThen(Collectors.mapping(Student::getName, Collectors.toList()), a -> {
                    List<String> strings = new ArrayList<>();
                    a.forEach(item -> {
                        StringBuilder builder = new StringBuilder(item);
                        builder.append("zcc");
                        strings.add(builder.toString());
                    });
                    return strings;
                }));
        System.out.println(zcc);

        /**
         * 3.counting
         * counting方法返回一个Collector收集器接受T类型的元素，用于计算输入元素的数量。如果没有元素，则结果为0。
         */
        /*
        static <T> Collector<T,?,Long>
        counting()
        Returns a Collector accepting elements of type T that counts the number of input elements.
        */
        // 统计所有学生人数
        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);


        /**
         * 4.groupingBy
         * 序号	修饰符和类型	方法和描述
         * 6	static <T,K> Collector<T,?,Map<K,List<T>>>	groupingBy(Function<? super T,? extends K> classifier)Returns a Collector implementing a “group by” operation on input elements of type T, grouping elements according to a classification function, and returning the results in a Map.
         * 7	static <T,K,A,D> Collector<T,?,Map<K,D>>	groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)Returns a Collector implementing a cascaded “group by” operation on input elements of type T, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
         * 8	static <T,K,D,A,M extends Map<K,D>>Collector<T,?,M>	groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)Returns a Collector implementing a cascaded “group by” operation on input elements of type T, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
         *   M 代表Map类型
         */

        /**
         * 4.1 groupingBy
         * groupingBy(Function)方法返回一个Collector收集器对T类型的输入元素执行"group by"操作，
         * 根据分类函数对元素进行分组，并将结果返回到Map。
         * 分类函数将元素映射到某些键类型K。收集器生成一个Map<K, List<T>>，其键是将分类函数应用于输入元素得到的值，其对应值为List，其中包含映射到分类函数下关联键的输入元素。
         * 无法保证返回的Map或List对象的类型，可变性，可序列化或线程安全性。
         *
         * 注意： 返回的Collector收集器不是并发的。对于并行流管道，combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
         * 如果不需要保留元素出现在生成的Map收集器中的顺序，则使用groupingByConcurrent(Function)可以提供更好的并行性能。
         *
         */
        // 统计各个年级的学生信息
        // {TWO=[Student{name='李四', totalScore=531, local=true, gradeType=TWO}],
        //THREE=[Student{name='刘一', totalScore=721, local=true, gradeType=THREE},
        //Student{name='陈二', totalScore=637, local=true, gradeType=THREE},
        //Student{name='张三', totalScore=666, local=true, gradeType=THREE},
        //Student{name='王五', totalScore=483, local=false, gradeType=THREE},
        //Student{name='赵六', totalScore=367, local=true, gradeType=THREE}],
        //ONE=[Student{name='孙七', totalScore=499, local=false, gradeType=ONE}]}

        Map<Student.GradeType, List<Student>> collect = menu.stream()
                .collect(Collectors.groupingBy(Student::getGradeType));

        Optional.ofNullable(collect).ifPresent(System.out::println);
        /**
         * 4.2groupingBy(Function, Collector)
         * groupingBy(Function, Collector)方法返回一个Collector收集器，对T类型的输入元素执行级联"group by"操作，
         * 根据分类函数对元素进行分组，然后使用指定的下游Collector收集器对与给定键关联的值执行缩减操作。
         *
         * 分类函数将元素映射到某些键类型K。下游收集器对T类型的元素进行操作，并生成D类型的结果。产生收集器生成Map<K, D>。
         * 返回的Map的类型，可变性，可序列化或线程安全性无法保证。
         *
         * 注意： 返回的Collector收集器不是并发的。对于并行流管道，combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
         * 如果不需要保留向下游收集器提供元素的顺序，则使用groupingByConcurrent(Function, Collector)可以提供更好的并行性能。
         */

        // 统计各年级学生人数
        Optional.of(menu.stream()
                .collect(Collectors.groupingBy(Student::getGradeType, Collectors.counting())))
                .ifPresent(System.out::println);
        /**
         * 4.3groupingBy(Function, Supplier, Collector)
         *groupingBy(Function, Supplier, Collector)方法返回一个Collector收集器，
         * 对T类型的输入元素执行级联"group by"操作，根据分类函数对元素进行分组，
         * 然后使用指定的下游Collector收集器对与给定键关联的值执行缩减操作。收集器生成的Map是使用提供的工厂函数创建的。
         * 分类函数将元素映射到某些键类型K。下游收集器对T类型的元素进行操作，并生成D类型的结果。产生收集器生成Map<K, D>。
         *
         * 注意： 返回的Collector收集器不是并发的。对于并行流管道，
         * combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
         * 如果不需要保留向下游收集器提供元素的顺序，则使用groupingByConcurrent(Function, Supplier, Collector)可以提供更好的并行性能。
         * */
        // 统计各个年级的平均成绩，并有序输出
        // class java.util.TreeMap
        // {ONE=499.0, TWO=531.0, THREE=574.8}
        Map<Student.GradeType, Double> map = menu.stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeType,
                        TreeMap::new,
                        Collectors.averagingInt(Student::getTotalScore)));

        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);

    /**
     * 6.joining
     * 序号	修饰符和类型	方法和描述
     * 12	static Collector<CharSequence,?,String>	joining()Returns a Collector that concatenates the input elements into a String, in encounter order.
     * 13	static Collector<CharSequence,?,String>	joining(CharSequence delimiter)Returns a Collector that concatenates the input elements, separated by the specified delimiter, in encounter order.
     * 14	static Collector<CharSequence,?,String>	joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)Returns a Collector that concatenates the input elements, separated by the specified delimiter, with the specified prefix and suffix, in encounter order.
     */
        /**
         * 6.1joining()
         * joining()方法返回一个Collector收集器，它按遇见顺序将输入元素连接成String。
         */
        // 将所有学生的姓名连接成字符串
        System.out.println(menu.stream().map(Student::getName).collect(Collectors.joining()));
        /**
         * 6.2joining(delimiter)
         * joining(delimiter)方法返回一个Collector收集器，它以遇见顺序连接由指定分隔符分隔的输入元素。
         */
        // 将所有学生的姓名用，连接成字符串
        System.out.println(menu.stream().map(Student::getName).collect(Collectors.joining(",")));
        /**
         * 6.3joining(delimiter, prefix, suffix)
         * joining(delimiter, prefix, suffix)方法返回一个Collector收集器，它以遇见顺序将由指定分隔符分隔的输入元素与指定的前缀和后缀连接起来。
         */
        System.out.println(menu.stream().map(Student::getName).collect(Collectors.joining(",","{","}")));

     /**
      * 7.mapping
      *序号	修饰符和类型	方法和描述
      * 15	static <T,U,A,R> Collector<T,?,R>	mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream)Adapts a Collector accepting elements of type U to one accepting elements of type T by applying a mapping function to each input element before accumulation.
      * mapping方法通过在累积之前将映射函数应用于每个输入元素，将Collector收集器接受U类型的元素调整为一个接受T类型的元素。
      */
        // 将所有学生的姓名以","分隔连接成字符串
        System.out.println(menu.stream().collect(Collectors.mapping(Student::getName,Collectors.joining(","))));
        // 收集所有学生分数
        System.out.println(menu.stream().collect(Collectors.mapping(Student::getTotalScore,Collectors.toList())));

        /**
         * 8.maxBy 9.minBy
         * 序号	修饰符和类型	方法和描述
         * 16	static <T> Collector<T,?,Optional<T>>	maxBy(Comparator<? super T> comparator)Returns a Collector that produces the maximal element according to a given Comparator, described as an Optional<T>.
         *      maxBy方法返回一个Collector收集器，它根据给定的Comparator比较器生成最大元素，描述为Optional<T>。
         * */
        // 列出所有学生中成绩最高的学生信息
        menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)))
                .ifPresent(System.out::println);
        // 列出所有学生中成绩最低的学生信息
        menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getTotalScore)))
                .ifPresent(System.out::println);
        /**
         * 10.partitioningBy
         *序号	修饰符和类型	方法和描述
         * 18	static <T> Collector<T,?,Map<Boolean,List<T>>>	partitioningBy(Predicate<? super T> predicate)Returns a Collector which partitions the input elements according to a Predicate, and organizes them into a Map<Boolean, List<T>>.
         * 19	static <T,D,A> Collector<T,?,Map<Boolean,D>>	partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream)Returns a Collector which partitions the input elements according to a Predicate, reduces the values in each partition according to another Collector, and organizes them into aMap<Boolean, D> whose values are the result of the downstream reduction.
         * */

            /**
             * 10.1 partitioningBy(Predicate)
             * partitioningBy(Predicate)方法返回一个Collector收集器，它根据Predicate对输入元素进行分区，
             * 并将它们组织成Map<Boolean, List<T>>。
             * */
            Map<Boolean, List<Student>> booleanListMap = menu.stream()
                    .collect(Collectors.partitioningBy(Student::isLocal));
            Optional.of(booleanListMap).ifPresent(System.out::println);
            /**
             * 10.2 partitioningBy(Predicate, Collector)
             *  partitioningBy(Predicate, Collector)方法返回一个Collector收集器，它根据Predicate对输入元素进行分区，
             *  根据另一个Collector收集器减少每个分区中的值，并将它们组织成Map<Boolean, D>，其值是下游减少的结果。
             *  返回的Map的类型，可变性，可序列化或线程安全性无法保证。
             * */
            Map<Boolean, Double> doubleMap = menu.stream()
                    .collect(Collectors.partitioningBy(
                            Student::isLocal,
                            Collectors.averagingInt(Student::getTotalScore)));
            Optional.of(doubleMap).ifPresent(System.out::println);
/**
 * 11.reducing
 *0	static <T> Collector<T,?,Optional<T>>	reducing(BinaryOperator<T> op)Returns a Collector which performs a reduction of its input elements under a specified BinaryOperator.
 * 21	static <T> Collector<T,?,T>	reducing(T identity, BinaryOperator<T> op)Returns a Collector which performs a reduction of its input elements under a specified BinaryOperator using the provided identity.
 * 22	static <T,U> Collector<T,?,U>	reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)Returns a Collector which performs a reduction of its input elements under a specified mapping function and BinaryOperator.
 *
 * */
    /**
     * 11.1 reducing(BinaryOperator)
     * 返回一个Collector收集器 它在指定的BinaryOperator下执行器输入元素的缩减。结果被描述为Optional<T>
     *  reducing()相关收集器在groupingBy/partitioningBy下游的多集缩减中使用非常有用。要对流执行简单缩减，请使用Stream#reduce（BinaryOperator）
     * */
    // 列出所有学生中成绩最高的学生信息
        long a = System.currentTimeMillis();

        menu.stream().collect(Collectors
                .reducing(BinaryOperator
                        .maxBy(Comparator
                                .comparingInt(Student::getTotalScore))))
                .ifPresent(System.out::println);

        long b = System.currentTimeMillis();

        menu.stream().reduce(BinaryOperator
                .maxBy(Comparator
                        .comparingInt(Student::getTotalScore)))
                .ifPresent(System.out::println);
        long c = System.currentTimeMillis();
        System.out.println(b-a);
        System.out.println(c-b);
      /**
       * 11.2reducing(Object, BinaryOperator)
       * 返回一个Collector收集器，它使用提供的标识在指定的BinaryOperator下执行其输入元素的缩减。
       * reducing()相关收集器在groupingBy或partitioningBy下游的多级缩减中使用时非常有用。要对流执行简单缩减，请使用Stream#reduce(Object, BinaryOperator)。
       * */
        long l = System.currentTimeMillis();
        Integer result = menu.stream()
                .map(Student::getTotalScore)
                .collect(Collectors.reducing(1, (d1, d2) -> d1 + d2));
        long q = System.currentTimeMillis();
        System.out.println(result);
        Integer result1 = menu.stream()
                .map(Student::getTotalScore).reduce(0, (d1, d2) -> d1 + d2);
        System.out.println(result1);
     /**
      * 11.3reducing(Object, Function, BinaryOperator)
      * 返回一个Collector收集器，它在指定的映射函数和BinaryOperator下执行其输入元素的缩减。这是对reducing(Object, BinaryOperator)的概括，它允许在缩减之前转换元素。
      *
      * reducing()相关收集器在groupingBy或partitioningBy下游的多级缩减中使用时非常有用。要对流执行简单缩减，请使用Stream#map(Function)和Stream#reduce(Object, BinaryOperator)。
      * */
        long w = System.currentTimeMillis();
        Integer result2 = menu.stream()
                .collect(Collectors.reducing(0, Student::getTotalScore, (d1, d2) -> d1 + d2));
        long e = System.currentTimeMillis();
        System.out.println(q-l);
        System.out.println(e-w);
        System.out.println(result2);
      /**
       *
       *12.summarizingDouble
       * 23	static <T> Collector<T,?,DoubleSummaryStatistics>	summarizingDouble(ToDoubleFunction<? super T> mapper)Returns a Collector which applies an double-producing mapping function to each input element, and returns summary statistics for the resulting values.
       * 24	static <T> Collector<T,?,IntSummaryStatistics>	summarizingInt(ToIntFunction<? super T> mapper)Returns a Collector which applies an int-producing mapping function to each input element, and returns summary statistics for the resulting values.
       * 25	static <T> Collector<T,?,LongSummaryStatistics>	summarizingLong(ToLongFunction<? super T> mapper)Returns a Collector which applies an long-producing mapping function to each input element, and returns summary statistics for the resulting values.
       * summarizingDouble方法返回一个Collector收集器，它将double生成映射函数应用于每个输入元素，并返回结果值的摘要统计信息。
       *
       * */
      // 统计所有学生的摘要信息（总人数，总成绩，最高成绩，最低成绩和平均成绩）
        DoubleSummaryStatistics res = menu.stream()
                .collect(Collectors.summarizingDouble(Student::getTotalScore));
        Optional.of(res).ifPresent(System.out::println);
     /**
      * 13.summingDouble
      * 26	static <T> Collector<T,?,Double>	summingDouble(ToDoubleFunction<? super T> mapper)Returns a Collector that produces the sum of a double-valued function applied to the input elements.
      * 27	static <T> Collector<T,?,Integer>	summingInt(ToIntFunction<? super T> mapper)Returns a Collector that produces the sum of a integer-valued function applied to the input elements.
      * 28	static <T> Collector<T,?,Long>	summingLong(ToLongFunction<? super T> mapper)Returns a Collector that produces the sum of a long-valued function applied to the input elements.
      *返回一个Collector收集器，它生成应用于输入元素的double值函数的总和。如果没有元素，则结果为0。
      * 返回的总和可能会因记录值的顺序而变化，这是由于除了不同大小的值之外，还存在累积舍入误差。
      * 通过增加绝对量排序的值(即总量，样本越大，结果越准确)往往会产生更准确的结果。
      * 如果任何记录的值是NaN或者总和在任何点NaN，那么总和将是NaN。
      * */
        Optional.of(menu.stream().collect(Collectors.summingDouble(Student::getTotalScore)))
                .ifPresent(System.out::println);
   /**
    * 14.toCollection
    * 返回一个Collector收集器，它按遇见顺序将输入元素累积到一个新的Collection收集器中。Collection收集器由提供的工厂创建。
    * */
   Optional.of(menu.stream().filter(d -> d.getTotalScore() > 600)
           .collect(Collectors.toCollection(LinkedList::new)))
           .ifPresent(v -> {
               System.out.println(v.getClass());
               System.out.println(v);
           });
/**
 * 15.toConcurrentMap
 * 30	static <T,K,U> Collector<T,?,ConcurrentMap<K,U>>	toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are the result of applying the provided mapping functions to the input elements.
 * 31	static <T,K,U> Collector<T,?,ConcurrentMap<K,U>>	toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction)Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are the result of applying the provided mapping functions to the input elements.
 * 32	static <T,K,U,M extends ConcurrentMap<K,U>>Collector<T,?,M>	toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are the result of applying the provided mapping functions to the input elements.
 *
 * */
    /**
     * 15.1toConcurrentMap(Function, Function)
     * 返回一个并发的Collector收集器，它将元素累积到ConcurrentMap中，其键和值是将提供的映射函数应用于输入元素的结果。
     *
     * 如果映射的键包含重复项(根据Object#equals(Object))，则在执行收集操作时会抛出IllegalStateException。如果映射的键可能有重复，请使用toConcurrentMap(Function, Function, BinaryOperator)。
     *
     * 注意： 键或值作为输入元素是常见的。在这种情况下，实用方法java.util.function.Function#identity()可能会有所帮助。
     *
     * 这是一个Collector.Characteristics#CONCURRENT并发和Collector.Characteristics#UNORDERED无序收集器。
     *
     * */
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Student::getName,Student::getTotalScore)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        /**
         * 15.2toConcurrentMap(Function, Function, BinaryOperator)
         * 返回一个并发的Collector收集器，它将元素累积到ConcurrentMap中，其键和值是将提供的映射函数应用于输入元素的结果。
         * */
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Student::getGradeType, Student::getName,(z,x)->z+","+x)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

        /**
         * 15.3toConcurrentMap(Function, Function, BinaryOperator, Supplier)
         * 返回一个并发的Collector收集器，它将元素累积到ConcurrentMap中，其键和值是将提供的映射函数应用于输入元素的结果。
         *
         * 如果映射的键包含重复项(根据Object#equals(Object))，则将值映射函数应用于每个相等的元素，并使用提供的合并函数合并结果。ConcurrentMap由提供的供应商函数创建。
         *
         * 这是一个Collector.Characteristics#CONCURRENT并发和Collector.Characteristics#UNORDERED无序收集器。
         * */
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(
                        Student::getGradeType,
                        v -> 1L,
                        (n, v) -> n + v,
                        ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    /**
     * 16.toList
     * 33	static <T> Collector<T,?,List<T>>	toList()Returns a Collector that accumulates the input elements into a new List.
     * 返回一个Collector收集器，它将输入元素累积到一个新的List中。返回的List的类型，可变性，可序列化或线程安全性无法保证;如果需要更多地控制返回的List，请使用toCollection(Supplier)。
     *
     * 示例：查出本地学生的信息并放入ArrayList中
     * */

        Optional.of(menu.stream().filter(Student::isLocal).collect(Collectors.toList()))
                .ifPresent(r -> {
                    System.out.println(r.getClass());
                    System.out.println(r);
                });
      /**
       * 17.toMap
       * 34	static <T,K,U> Collector<T,?,Map<K,U>>	toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
       * 35	static <T,K,U> Collector<T,?,Map<K,U>>	toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction)Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
       * 36	static <T,K,U,M extends Map<K,U>>Collector<T,?,M>	toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
       * */
       /**
        * 17.1toMap(Function, Function)
        * 返回一个Collector收集器，它将元素累积到Map中，其键和值是将提供的映射函数应用于输入元素的结果。
        *
        * 如果映射的键包含重复项(根据Object#equals(Object))，则在执行收集操作时会抛出IllegalStateException。如果映射的键可能有重复，请使用toMap(Function, Function, BinaryOperator)。
        *
        * 注意： 键或值作为输入元素是常见的。在这种情况下，实用方法java.util.function.Function#identity()可能会有所帮助。
        *
        * 返回的Collector收集器不是并发的。对于并行流管道，combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
        *
        * 如果不需要将结果以遇见的顺序插入Map，则使用toConcurrentMap(Function, Function)可以提供更好的并行性能。
        *
        * 17.2toMap(Function, Function, BinaryOperator)
        * 返回一个并发的Collector收集器，它将元素累积到Map中，其键和值是将提供的映射函数应用于输入元素的结果。
        *
        * 如果映射的键包含重复项(根据Object#equals(Object))，则将值映射函数应用于每个相等的元素，并使用提供的合并函数合并结果。
        *
        * 注意： 有多种方法可以处理映射到同一个键的多个元素之间的冲突。toMap的其它形式只是使用无条件抛出的合并函数，但你可以轻松编写更灵活的合并策略。例如，如果你有一个Person流，并且你希望生成一个“电话簿”映射名称到地址，但可能有两个人具有相同的名称，你可以按照以下方式进行优雅的处理这些冲突，并生成一个Map将名称映射到连接的地址列表中：
        *
        * Map<String, String> phoneBook
        *  people.stream().collect(toConcurrentMap(Person::getName,
        *                                          Person::getAddress,
        *                                          (s, a) -> s + ", " + a));
        * 1
        * 2
        * 3
        * 4
        * 返回的Collector收集器不是并发的。对于并行流管道，combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
        *
        * 如果不需要将结果以遇见的顺序插入Map，则使用toConcurrentMap(Function, Function, BinaryOperator)可以提供更好的并行性能。
        *
        * 17.3toMap(Function, Function, BinaryOperator, Supplier)
        * 返回一个Collector收集器，它将元素累积到Map中，其键和值是将提供的映射函数应用于输入元素的结果。
        *
        * 如果映射的键包含重复项(根据Object#equals(Object))，则将值映射函数应用于每个相等的元素，并使用提供的合并函数合并结果。Map由提供的供应商函数创建。
        *
        * 注意： 返回的Collector收集器不是并发的。对于并行流管道，combiner函数通过将键从一个映射合并到另一个映射来操作，这可能是一个昂贵的操作。
        *
        * 如果不需要将结果以遇见的顺序插入Map，则使用toConcurrentMap(Function, Function, BinaryOperator, Supplier)可以提供更好的并行性能。
        * */
    /**
     * 18.toSet
     * 37	static <T> Collector<T,?,Set<T>>	toSet()Returns a Collector that accumulates the input elements into a new Set.
     * 返回一个Collector收集器，它将输入元素累积到一个新的Set中。
     * 返回的Set的类型，可变性，可序列化或线程安全性无法保证;如果需要更多地控制返回的Set，请使用toCollection(Supplier)。
     * 这是一个Collector.Characteristics#UNORDERED无序收集器。
     * */
        Optional.of(menu.stream().filter(Student::isLocal).collect(Collectors.toSet()))
                .ifPresent(r -> {
                    System.out.println(r.getClass());
                    System.out.println(r);
                });
    }


    /**
     * 学生信息
     */

    static class Student {
        /**
         * 姓名
         */
        private String name;
        /**
         * 总分
         */
        private int totalScore;
        /**
         * 是否本地人
         */
        private boolean local;
        /**
         * 年级
         */
        private GradeType gradeType;

        /**
         * 年级类型
         */
        enum GradeType {ONE, TWO, THREE}

        Student(String name, int totalScore, boolean local, GradeType gradeType) {
            this.name = name;
            this.totalScore = totalScore;
            this.local = local;
            this.gradeType = gradeType;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", totalScore=" + totalScore +
                    ", local=" + local +
                    ", gradeType=" + gradeType +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(int totalScore) {
            this.totalScore = totalScore;
        }

        public boolean isLocal() {
            return local;
        }

        public void setLocal(boolean local) {
            this.local = local;
        }

        public GradeType getGradeType() {
            return gradeType;
        }

        public void setGradeType(GradeType gradeType) {
            this.gradeType = gradeType;
        }
    }
}


