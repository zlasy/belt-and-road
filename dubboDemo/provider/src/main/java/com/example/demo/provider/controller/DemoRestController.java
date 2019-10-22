package com.example.demo.provider.controller;

import com.dangdang.kefu.kim.session.dto.vo.SessionInfo;
import com.example.demo.api.DemoService;
import com.example.demo.provider.result.ApiResult;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @Autowired
    DemoService demoService;

    @Autowired
    RedissonClient redisson;

    @GetMapping("/testc/{str}")
    public ApiResult getDemoTest(@PathVariable String str){
        return ApiResult.builder().code("0").success(true).message("success")
                .data(demoService.test(str)).build();
    }

    @GetMapping("/redis")
    public SessionInfo testRedis(){
        RBucket bucket = redisson.getBucket("redisSessionInfo:1:12186");
        return (SessionInfo) bucket.get();
    }
}
