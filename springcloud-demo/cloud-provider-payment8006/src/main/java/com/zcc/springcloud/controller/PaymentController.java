package com.zcc.springcloud.controller;



import com.zcc.springcloud.entities.Payment;
import com.zcc.springcloud.service.PaymentService;
import com.zcc.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:50
 */
@Slf4j
@RestController
@RequestMapping("/api/payment/test/")
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;




    @GetMapping("getCs")
    public String paymentCs(){
        return "springcloud with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

    @GetMapping("zk")
    public String paymentZk(){
        return "springcloud with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

    @GetMapping("getById")
    public CommonResult getById(Long id) {
        Payment byId = paymentService.getById(id);
        log.info("查询结果:"+byId+"。查询端口为"+serverPort);
        return CommonResult.success(byId);
    }

    @GetMapping("getAll")
    public CommonResult getAll() {
        List<Payment> all = paymentService.getAll();
        log.info("查询结果:"+all+"。查询端口为"+serverPort);
        return CommonResult.success(all);
    }

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int i  = paymentService.insert(payment);
        log.info("插入结果: 插入条数为"+i+"。插入端口为"+serverPort);
        return CommonResult.success(i);
    }
}
