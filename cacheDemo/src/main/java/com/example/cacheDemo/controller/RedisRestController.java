package com.example.cacheDemo.controller;

import com.example.cacheDemo.dto.User;
import com.example.cacheDemo.repository.DemoRepository;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    static Integer k = 0;
    @GetMapping("/lock")
    public Integer testLock() throws InterruptedException {
        k = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 0; i< 100; i++){
            executorService.submit(() -> inc(k));
        }
        Thread.sleep(1000L);
        return k;
    }

    private Integer inc(Integer j) throws InterruptedException {
        try {
            Thread.sleep(new Random().nextLong() % 100 + 100);
            RLock lock = redisson.getLock("lock:test");
            boolean hasLock = lock.tryLock(1, 1, TimeUnit.SECONDS);
            if (hasLock){
                System.out.println(Thread.currentThread().getName() + " get lock");
            } else {
                System.out.println(Thread.currentThread().getName() + " doesn't get lock");
            }
            try {
                System.out.println(k);
                return ++k;
            } finally {
                lock.unlock();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
