package com.zcc.iRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: com.zcc.springcloud
 * @ClassName: com.zcc.iRule
 * @author: zcc
 * @date: 2022/2/24 14:07
 * @version:
 * @Describe:
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();// 随机
    }

}
