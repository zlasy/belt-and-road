package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 该方式适合于一些在config-center发生了变化，并且在程序中
 * 需要感知配置的变化。使用此类中的get**（）方式可以获得最近的配置。
 */
@Configuration
public class DynamicProperties {
    
    @Value("${server.port}")
    private String key1;
    
    @Value("${dubbo.properties}")
    private String key2;
    
    public String getKey1() {
        return key1;
    }
    
    public void setKey1(String key1) {
        this.key1 = key1;
    }
    
    public String getKey2() {
        return key2;
    }
    
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    
    @Override
    public String toString() {
        return "DynamicProperties{" +
                "key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                '}';
    }
}
