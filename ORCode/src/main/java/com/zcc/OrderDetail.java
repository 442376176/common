package com.zcc;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

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
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(1);
        System.out.println(JSONObject.toJSONString(orderDetail));
    }
}
