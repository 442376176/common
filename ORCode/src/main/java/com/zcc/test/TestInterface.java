package com.zcc.test;


import com.alibaba.fastjson.JSON;
import com.zcc.entity.Orders;
import com.zcc.utils.ProxyUtil;
import org.junit.Test;
import org.teasoft.bee.osql.Condition;
import org.teasoft.bee.osql.Suid;
import org.teasoft.bee.osql.SuidRich;
import org.teasoft.honey.osql.core.BeeFactoryHelper;
import org.teasoft.honey.osql.core.ConditionImpl;
import org.teasoft.honey.osql.core.ObjSQLRich;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestInterface {


    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    @Test
    public void select() {
        Suid suid = BeeFactoryHelper.getSuid();

        //需要先生成相应的Javabean
        Orders orders1 = new Orders();
        orders1.setId(100002L);
        orders1.setName("Bee(ORM Framework)");

        //1:select查询实例
        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动作为过滤条件
        List<Orders> list1 = suid.select(orders1);  //select
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).toString());
        }
    }

    @Test
    public void insert() {
        Suid suid = BeeFactoryHelper.getSuid();
        SuidRich suidRich = new ObjSQLRich();


//        //3:insert 插入实例
//        int insertNum = suid.insert(orders2); //insert
//        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动插入数据库中.
//        //方便结合DB插值,如id自动增长,由DB插入;createtime由DB默认插入
//        System.out.println("insert record:" + insertNum);
        //3:insert 插入实例

        List<Orders> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Orders orders2 = new Orders();
            orders2.setUserid("bee");
            orders2.setName("Bee(ORM Framework)");
            orders2.setTotal(new BigDecimal(91.99));
            orders2.setRemark("");  //empty String test
            list.add(orders2);
        }
        Date date = new Date();
        suidRich.insert(list);
        Date date1 = new Date();
        int i = (int) (date1.getTime() - date.getTime());
        System.out.println("插入10000条记录时长为：" + i);

        //4:delete 删除实例
        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动作为过滤条件
        //int deleteNum=suid.delete(orders2);   //delete
        //System.out.println("delete record:"+deleteNum);
    }

    @Test
    public void update() {
        Suid suid = BeeFactoryHelper.getSuid();
        Orders orders1 = new Orders();
        orders1.setId(100001L);
        //2:update更新实例
        orders1.setName("Bee--ORM Framework");
        //默认只更新需要更新的字段. 过滤条件默认只用id字段,其它需求可用SuidRich中的方法.
        int updateNum = suid.update(orders1);   //update
        System.out.println("update record:" + updateNum);
    }

    @Test
    public void delete() {
        Orders orders2 = new Orders();
        Suid suid = BeeFactoryHelper.getSuid();
        SuidRich suidRich = new ObjSQLRich();
        Condition condition = new ConditionImpl();
        condition.notBetween("id", "100001", "100004");
        Date date = new Date();
        suidRich.delete(orders2, condition);
        Date date1 = new Date();
        System.out.println(date1.getTime() - date.getTime());
//        orders2.setId(100005L);
        //4:delete 删除实例
        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动作为过滤条件
//        int deleteNum=suid.delete(orders2);   //delete
//        System.out.println("delete record:"+deleteNum);
    }
    @Test
    public void testSqlBuilder(){
//        String str = "getData";
////        int get = str.indexOf("get");
////        System.out.println(get);
//        String getData = StringUtils.substring("getData", 1);
//        System.out.println(StringUtils.substring(getData,2,3).toLowerCase()+StringUtils.substring(getData,3));
//        Map<String,String> map = new MyHashMap<>();
//        map.put("1","2");
//        map.put("3","sdsd");
//        System.out.println(map);
    }
    public static void main(String[] args) throws Exception {
        /**
         * bee小蜜蜂orm测试 *****************************************************************************
         */
//        Suid suid = BeeFactoryHelper.getSuid();
//
//        //需要先生成相应的Javabean
//        Orders orders1 = new Orders();
//        orders1.setId(100001L);
//        orders1.setName("Bee(ORM Framework)");
//
//        //1:select查询实例
//        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动作为过滤条件
//        List<Orders> list1 = suid.select(orders1);  //select
//        for (int i = 0; i < list1.size(); i++) {
//            System.out.println(list1.get(i).toString());
//        }
//
//        //2:update更新实例
//        orders1.setName("Bee--ORM Framework");
//        //默认只更新需要更新的字段. 过滤条件默认只用id字段,其它需求可用SuidRich中的方法.
//        int updateNum = suid.update(orders1);   //update
//        System.out.println("update record:" + updateNum);
//
//
//        Orders orders2 = new Orders();
//        orders2.setUserid("bee");
//        orders2.setName("Bee(ORM Framework)");
//        orders2.setTotal(new BigDecimal(91.99));
//        orders2.setRemark("");  //empty String test
//        int time = 0;
        //3:insert 插入实例
//        Date date = new Date();
//        for (int i = 0; i < 10000; i++) {
//            int insertNum = suid.insert(orders2); //insert
//        }
//        Date date1 = new Date();
//        int i = (int) (date.getTime()-date1.getTime());
//        System.out.println(i);
//        orders2.setId(Integer.toUnsignedLong(insertNum));
//        //默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动插入数据库中.
//        //方便结合DB插值,如id自动增长,由DB插入;createtime由DB默认插入
//        System.out.println("insert record:" + insertNum);

//        4:delete 删除实例
//        默认不处理null和空字符串.不用再写一堆的判断;其它有值的字段全部自动作为过滤条件
//        int deleteNum = suid.delete(orders2);   //delete
//        System.out.println("delete record:" + deleteNum);


        /**
         * 二维码测试 *****************************************************************************
         */
//                String text = "https://www.baidu.com/"; //这里是URL ，扫描之后就跳转到这个界面
//                String path = "src/main/resources/url.jpg"; //图片生成的位置
//                int width = 900;
//                int height = 900;
//                // 二维码图片格式
//                String format = "jpg";
//                // 设置编码，防止中文乱码
//                Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object> ();
//                ht.put (EncodeHintType.CHARACTER_SET, "UTF-8");
//                // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度,格式)
//                BitMatrix bitMatrix = new MultiFormatWriter().encode (text, BarcodeFormat.QR_CODE, width, height, ht);
//                // 生成二维码(定义二维码输出服务器路径)
//                File outputFile = new File(path);
//                if (!outputFile.exists ())
//                {
//                    //创建文件夹
//                    outputFile.mkdir ();
//                }
//                int b_width = bitMatrix.getWidth ();
//                int b_height = bitMatrix.getHeight ();
//                // 建立图像缓冲器
//                BufferedImage image = new BufferedImage (b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
//                for ( int x = 0; x < b_width; x++ )
//                {
//                    for ( int y = 0; y < b_height; y++ )
//                    {
//                        image.setRGB (x, y, bitMatrix.get (x, y) ? BLACK : WHITE);
//                    }
//                }
//                // 生成二维码
//                ImageIO.write (image, format, new File (path)); //二维码的名称 是 erweima.sgif

/**
 * 实体对象动态赋值测试 *****************************************************************************
 */
                Orders order = new Orders();
                order.setId(1L);
                order.setName("order1");
                List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderid(1);
                orderDetail.setOrderPrice("1USD");
                orderDetail.setOrderSku("Sku1");

                orderDetailList.add(orderDetail);

                OrderDetail orderDetail2 = new OrderDetail();
                orderDetail2.setOrderid(1);
                orderDetail2.setOrderPrice("2USD");
                orderDetail2.setOrderSku("Sku2");
                orderDetailList.add(orderDetail2);

                try {
                    HashMap addMap = new HashMap();
                    HashMap addValMap = new HashMap();
                    addMap.put("orderDetail", Class.forName("java.util.List"));
                    addValMap.put("orderDetail", orderDetailList);
                    Object obj2= new ProxyUtil().dynamicClass(order,addMap,addValMap);

                    System.out.println(JSON.toJSONString(obj2));
                    System.out.println(obj2.getClass().getName());
                    Field[] declaredFields = obj2.getClass().getDeclaredFields();
                    for (int i = 0; i < declaredFields.length; i++) {
                        System.out.println(declaredFields[i].getName());
                    }
                    Method[] declaredMethods = obj2.getClass().getDeclaredMethods();
                    for (int i = 0; i < declaredMethods.length; i++) {
                        System.out.println(declaredMethods[i].getName());
                    }
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }

    }


}
