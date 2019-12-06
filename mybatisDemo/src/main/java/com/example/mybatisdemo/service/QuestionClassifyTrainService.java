package com.example.mybatisdemo.service;

import com.example.mybatisdemo.mybatis.mapper.QuestionClassifyTrainMapper;
import com.example.mybatisdemo.mybatis.model.QuestionClassifyTrain;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionClassifyTrainService implements InitializingBean {

    @Autowired
    private QuestionClassifyTrainMapper questionClassifyTrainMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        QuestionClassifyTrain train = questionClassifyTrainMapper.selectByPrimaryKey(13L);
        System.err.println(train);
    }
}
