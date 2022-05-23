package com.zcc.springcloud;

import com.zcc.iRule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 14:25
 */
@SpringBootApplication
@EnableEurekaClient
// 参数 name 指定规则的服务名  configuration 指定规则类
@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
