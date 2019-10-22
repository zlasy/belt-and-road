package com.example.demo.stream;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JavaStream {

    @PostConstruct
    public void dfs(){


    }


    public void run(){
        List<String> keywords = Arrays.asList("315", "投诉", "shit","你好");
        List<String> msgList = Arrays.asList("我要在315当天投诉", "test投诉", "test315");
//        System.out.println(keywords.stream().filter(msg::contains).collect(Collectors.joining(",")));
        System.out.println(keywords.stream().anyMatch(msgList::contains));
    }
}
