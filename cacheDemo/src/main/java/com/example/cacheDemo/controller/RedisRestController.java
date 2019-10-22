package com.example.cacheDemo.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisRestController {

    @Autowired
    RedissonClient redisson;

    @GetMapping("/redis")
    public String testRedis(){
        RBucket bucket = redisson.getBucket("msgSeq:1014");
        return bucket.get().toString();
    }
}
