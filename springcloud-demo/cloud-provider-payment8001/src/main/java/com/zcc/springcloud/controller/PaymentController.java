package com.zcc.springcloud.controller;


import com.zcc.springcloud.entities.Payment;
import com.zcc.springcloud.service.PaymentService;
import com.zcc.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:50
 */
@Slf4j
@RestController
@RequestMapping("/api/payment/test/")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("getById")
    public CommonResult getById(Long id) {
        Payment byId = paymentService.getById(id);
        log.info("查询结果:" + byId + "。查询端口为" + serverPort);
        return CommonResult.success(byId);
    }

    @GetMapping("getAll")
    public CommonResult getAll() {
        List<Payment> all = paymentService.getAll();
        log.info("查询结果:" + all + "。查询端口为" + serverPort);
        return CommonResult.success(all);
    }

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.insert(payment);
        log.info("插入结果: 插入条数为" + i + "。插入端口为：" + serverPort);
        return CommonResult.success(i);
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping("getDiscover")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(element->{
            log.info("*******************************element:"+element);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        instances.forEach(serviceInstance->{
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        });
        return CommonResult.success(discoveryClient);
    }
}
