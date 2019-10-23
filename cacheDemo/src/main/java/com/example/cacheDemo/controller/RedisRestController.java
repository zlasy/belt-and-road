package com.example.cacheDemo.controller;

import com.example.cacheDemo.dto.User;
import com.example.cacheDemo.repository.DemoRepository;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisRestController {

    @Autowired
    RedissonClient redisson;

    @Autowired
    DemoRepository demoRepository;

    @GetMapping("/redis")
    public User testRedis(){
        RBucket bucket = redisson.getBucket("msgSeq:1014");
        System.out.println("read cache 1014");
        return demoRepository.selectUser((Integer)bucket.get());
    }
}
