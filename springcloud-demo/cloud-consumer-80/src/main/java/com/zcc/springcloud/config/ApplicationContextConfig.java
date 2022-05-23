package com.zcc.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 14:32
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    // 通过该注解 赋予RestTemplate负载均衡的能力
    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
