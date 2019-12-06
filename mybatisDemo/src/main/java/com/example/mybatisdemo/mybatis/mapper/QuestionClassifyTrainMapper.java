package com.example.mybatisdemo.mybatis.mapper;

import com.example.mybatisdemo.mybatis.model.QuestionClassifyTrain;

public interface QuestionClassifyTrainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionClassifyTrain record);

    int insertSelective(QuestionClassifyTrain record);

    QuestionClassifyTrain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionClassifyTrain record);

    int updateByPrimaryKey(QuestionClassifyTrain record);
}