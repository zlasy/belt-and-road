package com.example.cacheDemo;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedLock {

    @Autowired
    RedissonClient redisson;

    public void lock(String key){
        RLock lock = redisson.getLock(key);
        lock.lock(1, TimeUnit.SECONDS);
    }
}
