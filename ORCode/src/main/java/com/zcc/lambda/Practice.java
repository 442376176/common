package com.zcc.lambda;

import com.zcc.entity.Book;

import java.util.function.*;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/19 11:25
 */
public class Practice {


    private static BinaryOperator<Long> add = (x, y) -> x + y;
    private static Function<Long, Long> reduce = x -> x - 1;
    private static BiFunction<Long, Long, Long> longLongLongBiFunction = add.andThen(reduce);
    private static Consumer<Book> consumer1 = x -> x.setId(14);
    private static Consumer<Book> consumer2 = x -> x.setName("测试消费者");
    private static Consumer<Book> bookConsumer = consumer1.andThen(consumer2);
    // 断言
    private static Predicate<String> predicate = Predicate.isEqual("3");

//    public static void main(String[] args) {


        public static void main (String[]args){
//            String msgA = "Hello ";
//            String msgB = "World ";
//            System.out.println(
//                    getString(
//                            () -> msgA + msgB
//                    )
//            );
//            reduce.apply();
//            reduce.compose();
//            reduce.andThen();
//            List<Book> list = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                Book book = new Book();
//                book.setId(i);
//                list.add(book);
//            }
//            Map<Integer, Book> collect = list.stream().filter(item -> item.getId() < 5).collect(Collectors.toMap(Book::getId, Function.identity()));
//            System.out.println(collect);

//            System.out.println(predicate.test("4"));
//            Predicate<String> and = predicate.and(str -> str.equals("4"));
//            System.out.println(and.test("5"));
//            System.out.println(predicate.negate().test("4"));
//            System.out.println(predicate.or(str -> str.equals("4")).test("4"));
//            System.out.println(String.valueOf(new Date().getTime()));
//            ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "6");
            System.out.println("Ale_dEa7_Nn_qKu4EnEttDiz591jZJa7YttM7DThCOG7".length());
        }

        private static String getString(Supplier<String> stringSupplier) {
            return stringSupplier.get();
        }


//        Book book = new Book();
//        book.setId(9);
//        // 无返回
//        bookConsumer.accept(book);
//        System.out.println(book);
//        System.out.println(add.apply(5L, 9L));
//        System.out.println(longLongLongBiFunction.apply(5L, 17L));
//    }


}
