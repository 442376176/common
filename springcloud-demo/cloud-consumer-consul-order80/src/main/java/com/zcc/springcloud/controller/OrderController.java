package com.zcc.springcloud.controller;


import com.zcc.springcloud.entities.Payment;
import com.zcc.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:50
 */
@Slf4j
@RestController
@RequestMapping("/api/consumer/test/")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
//    @Value("${url.payment.create}")
//    private String PAYMENT_CREATE;

    @GetMapping("getZk")
    private String getData() {
        try {
            Assert.state(false, "ceshi");
        } catch (IllegalStateException error) {
            return "失败";
        }finally {
            return restTemplate.getForObject("http://cloud-payment-service/api/payment/test/getCs", String.class);
        }
    }

}
