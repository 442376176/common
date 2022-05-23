package com.zcc.springcloud.controller;



import com.zcc.springcloud.entities.Payment;
import com.zcc.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    @Value("${url.payment.create}")
    private String PAYMENT_CREATE;
    @Value("${url.payment.get}")
    private String PAYMENT_GET;

    @PostMapping("create")
    private CommonResult getData(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_CREATE,payment,CommonResult.class);
    }
    @GetMapping("get")
    private CommonResult get(Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_GET + "?id=" + id, CommonResult.class);
        return forEntity.getBody();
    }

}
