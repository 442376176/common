//package com.zcc.controller;
//
//import com.zcc.entity.TestUser;
//import com.zcc.utils.sqlBuilder.Condition;
//import com.zcc.utils.sqlBuilder.SqlBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/test/")
//public class ORCodeController {
//
//    @Autowired
//    OrdersMapper mapper;
//
//    @Autowired
//    BookMapper bookMapper;
//
//    @Autowired
//    TestUserMapper userMapper;
//
//    @GetMapping("hello")
//    public List<TestUser> hello() throws Exception {
//
//        TestUser testUser = new TestUser();
//        testUser.setTEmail("test@gmail.com");
//        SqlBuilder sqlBuilder = new SqlBuilder();
//        List<Integer> list = new ArrayList<>();
//        for (int i = 80000; i < 80005; i++) {
//            list.add(i);
//        }
//        Condition condition = new Condition(TestUser.class);
//        condition.andEqualTo("tEmail", "test@gmail.com").andBetween("id", 800001, 800005).andLike("password", "@");
//        String build = sqlBuilder.select(false, TestUser.class).from(TestUser.class).where().byCondition(condition).orderBy("id", true).build();
//
//        List<TestUser> users = userMapper.selectTest("1", build);
//        return users;
//
//    }
////
////    Orders param = new Orders();
////        param.setRemark("test");
////        param.setSequence("12345601");
////    Book book = new Book();
////        book.setPublish("三联出版社");
////        System.out.println(build);
////    String build = sqlBuilder.builder().select(false).from(Orders.class).where(param).orderBy("id", true).build();
//////    String build = sqlBuilder.select(false, param.getClass()).from("orders").where(param).orderBy("id", true).build();
//////    String build = sqlBuilder.builder().select(true, Book.class).from(book.getClass()).where(book).orderBy("price", true).build();
//////    String build = sqlBuilder.select(false, TestUser.class).from(TestUser.class).where().like("tEmail", "e").and().in("id", list).orderBy("id", true).build();
////
////    List<Orders> orders = mapper.selectTest(build);
////    List<Book> books = bookMapper.selectTest(build);
////    List<Orders> list = new ArrayList<>();
////        for(
////    int i = 0;
////    i< 100000;i++)
////
////    {
////        Orders orders2 = new Orders();
////        orders2.setUserid("bee");
////        orders2.setName("Bee(ORM Framework)");
////        orders2.setTotal(new BigDecimal(91.99));
////        orders2.setRemark("");  //empty String test
////        list.add(orders2);
////    }
//
////    Date date = new Date();
////        mapper.insertList(list);
////    Date date1 = new Date();
////    int i = (int) (date1.getTime() - date.getTime());
////
////
////    Example example = new Example(Orders.class);
////        example.createCriteria().
////
////    andNotBetween("id",100001,100004);
////
////    Date date2 = new Date();
////         mapper.deleteByExample(example);
////    Date date3 = new Date();
////    int j = (int) (date3.getTime() - date2.getTime());
////        return"插入10w条记录时长为："+i+"删除10w条记录时长："+j;
//}
