package com.example.mybatisdemo.service;

import com.example.mybatisdemo.mybatis.mapper.QuestionClassifyTrainMapper;
import com.example.mybatisdemo.mybatis.model.QuestionClassifyTrain;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QuestionClassifyTrainService implements InitializingBean {

    @Autowired
    private QuestionClassifyTrainMapper questionClassifyTrainMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        QuestionClassifyTrain train = questionClassifyTrainMapper.selectByPrimaryKey(13L);
        QuestionClassifyTrain param = new QuestionClassifyTrain();
        param.setRoomId(1219245L);
        List<QuestionClassifyTrain> res = questionClassifyTrainMapper.selectByCriteria(param);
        System.err.println(train);
        System.err.println(res);
    }
}
