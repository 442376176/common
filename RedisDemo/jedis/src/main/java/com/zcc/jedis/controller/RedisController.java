package com.zcc.jedis.controller;

import com.zcc.jedis.dto.TestDto;
import com.zcc.jedis.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @Author zcc
 * @Date 2021/8/9 9:55
 * @Describe
 */
@RestController
@RequestMapping("/api/redis/v1/")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("test")
    public String testRedis(){
        redisTemplate.opsForValue().set("name","zcc");
        return (String)redisTemplate.opsForValue().get("name");
    }

    @PostMapping("secKill")
    public boolean secKill(@RequestBody TestDto dto)throws Exception{
        String userId = new Random().nextInt(5000)+"";
        return SecKillService.doSecKill(userId, dto.getGoodId());
    }

}
