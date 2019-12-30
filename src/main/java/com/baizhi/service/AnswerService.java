package com.baizhi.service;

import com.baizhi.entity.Answer;

import java.util.List;

public interface AnswerService {
    //展示当前所有回答
    public List<Answer> showAllAnswer(String q_id);
    //展示精选的回答
    public List<Answer> showTwoAnswer(String q_id);
    //添加问题的回答
    public void insertQuestionAnswer(Answer answer);
    //添加回答的回答
    public void insertAnswerAnswer(Answer answer);
    //获取当前回答对象
    public Answer getAnswer(String a_id);
}
