package com.baizhi.dao;

import com.baizhi.entity.Answer;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @ClassName AnswerDAO
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:35
 */
@Repository
public interface AnswerDAO {

    //展示当前所有回答
    public List<Answer> showAllAnswer(String q_id);
    //展示精选的回答
    public List<Answer> showTwoAnswer(String q_id);
    //获取当前回答对象
    public Answer getAnswer(String a_id);
    //获取当前回答的所有回答
    public List<Answer> getAllByBetterA_id(String betterA_id);
    //添加问题的回答
    public void insertQuestionAnswer(Answer answer);
    //添加回答的回答
    public void insertAnswerAnswer(Answer answer);

}
