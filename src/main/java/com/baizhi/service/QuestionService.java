package com.baizhi.service;

import com.baizhi.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {
        //根据question返回Question
        public Question selectByquestion(HttpServletRequest request, String question);
        //推荐(最热)
        public Question questionTuiJian();
        //关展收藏问题
        public List<Question> questionGuanZhu(String u_id);
        //添加问题
        public void insertQuestion(Question question);
        //模糊查询
        public List<Question> queryQuestion(String str);
}
