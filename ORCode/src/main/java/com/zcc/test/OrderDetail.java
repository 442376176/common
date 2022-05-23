package com.zcc.test;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/9/9 19:40
 */
@Data
public class OrderDetail {
    private int orderid;
    private String orderPrice;
    private String orderSku;

    public static void main(String[] args) {
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderid(1);
//        System.out.println(JSONObject.toJSONString(orderDetail));
//        BigDecimal s = BigDecimal.ZERO;
//        BigDecimal a = new BigDecimal("7.1854949611116");
//        BigDecimal multiply = s.multiply(a).setScale(2, RoundingMode.HALF_UP);
//        System.out.println(multiply);
//        int i = 0;
//        for (;i<10;i++){
//            if (i==8)break;
//        }
//        System.out.println(i);
        Map<Long, Integer> rBills = new HashMap<>();
        rBills.put(1L,1);
        Integer s = rBills.get(2L);
        System.out.println(s);
    }
}
