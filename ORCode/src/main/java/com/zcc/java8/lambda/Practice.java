package com.zcc.java8.lambda;

import com.zcc.entity.Book;
import com.zcc.utils.JudgeNullUtil;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/19 11:25
 */
public class Practice {

    // 操作
    private static BinaryOperator<Long> add = (x, y) -> x + y;
    // 函数
    private static Function<Long, Long> reduce = x -> x - 1;
    // 出入参同类型函数
    private static UnaryOperator<Long> subtract = x -> x - 5;
    // 双函数
    private static BiFunction<Long, Long, Long> longLongLongBiFunction = add.andThen(reduce);
    // 消费
    private static Consumer<Book> consumer1 = x -> x.setId(14);
    private static Consumer<Book> consumer2 = x -> x.setName("测试消费者");
    private static Consumer<Book> bookConsumer = consumer1.andThen(consumer2);
    // 断言
    private static Predicate<String> predicate = Predicate.isEqual("3");
    // 供给
    private static Supplier<Practice> ce = Practice::new;

    //    public static void main(String[] args) {
    class S {
        public S() {
            System.out.println(18);
        }
    }

    @Test
    public void testSupplier() {
        // supplier也是是用来创建对象的，但是不同于传统的创建对象语法

        // 创建Supplier容器，声明为S类型，此时并不会调用对象的构造方法，即不会创建对象
        Supplier<S> supplier = S::new;
        // 调用get方法，此时会调用对象的构造方法，即获取真正对象
        S s = supplier.get();
        // 每次调用构造方法，即获取的对象不同
        S s1 = supplier.get();
        System.out.println(s.equals(s1) ? "相同" : "不同");
    }


    @Test
    public void test1() {
        UnaryOperator<Long> subtract = x -> x - 5;
        Function<Long, Long> compose = subtract.compose(reduce.compose(subtract));
        System.out.println(System.currentTimeMillis());
        Long apply = compose.apply(11L);
        System.out.println(System.currentTimeMillis());
        System.out.println(apply);
    }

    public static void main(String[] args) {
        String msgA = "Hello ";
        String msgB = "World ";
        System.out.println(
                getString(
                        () -> msgA + msgB
                )
        );
        reduce.apply(5L);
        Function<Long, Long> compose = reduce.compose(reduce);
        Function<Long, Long> longLongFunction = reduce.andThen(compose);
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(i);
            list.add(book);
        }
        Map<Integer, Book> collect = list.stream().filter(item -> item.getId() < 5).collect(Collectors.toMap(Book::getId, Function.identity()));
        System.out.println(collect);

        System.out.println(predicate.test("4"));
        Predicate<String> and = predicate.and(str -> str.equals("4"));
        System.out.println(and.test("5"));
        System.out.println(predicate.negate().test("4"));
        System.out.println(predicate.or(str -> str.equals("4")).test("4"));
        System.out.println(String.valueOf(new Date().getTime()));
        ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "6");
//            System.out.println("Ale_dEa7_Nn_qKu4EnEttDiz591jZJa7YttM7DThCOG7".length());
//        }
//


        Book book = new Book();
        book.setId(9);
        // 无返回
        bookConsumer.accept(book);
        System.out.println(book);
        System.out.println(add.apply(5L, 9L));
        System.out.println(longLongLongBiFunction.apply(5L, 17L));

    }

    private static String getString(Supplier<String> stringSupplier) {
        return stringSupplier.get();
    }

    @Test
    public void test() {
        List<String> s = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("2");
                add("2");
                add("1");
            }
        };
        String s1 = s.stream().filter(item -> item.equals("1")).findFirst().orElse(new String("3"));
    }

    @Test
    public void testUtil(){
        List<String> list =  new ArrayList<>();
        list.add("!");
        String s = "1";
        Map<String, Integer> map = new HashMap<String, Integer>();
        System.out.println(JudgeNullUtil.isNotNull(map));
        Object q = new Object();
        System.out.println(JudgeNullUtil.isNotNull(q));
    }

    @Test
    public void testThen(){
        UnaryOperator<Object> integerFunction = a->(Integer)a*1;
        UnaryOperator<Object> integerFunction1 = a->(Integer)a-3;
        Function<Object, Object> integerFunction2 = integerFunction.andThen(integerFunction1);
        Object apply = integerFunction2.apply(1);
        System.out.println(apply);

    }
}
