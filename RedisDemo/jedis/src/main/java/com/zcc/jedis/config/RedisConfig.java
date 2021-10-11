package com.zcc.jedis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

/**
 * @Author zcc
 * @Date 2021/8/6 15:27
 * @Describe Redis通用配置类
 */
@Configuration
// 开启缓存
@EnableCaching
public class RedisConfig {

    //不指定id的话bean 的id就是方法名
    //返回结果就是spring中管理的bean
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        //ObjectMapper 指定在转成json的时候的一些转换规则
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //把自定义objectMapper设置到jackson2JsonRedisSerializer中（可以不设置，使用默认规则）
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //RedisTemplate默认的序列化方式使用的是JDK的序列化
        //设置了key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //设置了value的序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        // 解决查询缓存转换异常的问题
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.activateDefaultTyping(, ObjectMapper.DefaultTyping.NON_FINAL);
//    }







}

