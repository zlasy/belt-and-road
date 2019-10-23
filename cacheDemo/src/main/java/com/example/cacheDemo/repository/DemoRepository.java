package com.example.cacheDemo.repository;

import com.example.cacheDemo.dto.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    /**
     * cacheNames不支持表达式，必须指定
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "test")
    public User selectUser(Integer id){
        System.out.println("select user:" + id);
        User user = new User();
        user.setUserId(id);
        user.setUserName("name" + id);
        return user;
    }
}
