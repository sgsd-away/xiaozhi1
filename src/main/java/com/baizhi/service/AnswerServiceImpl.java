package com.baizhi.service;

import com.baizhi.dao.AnswerDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.dao.Z_user_AnswerDAO;
import com.baizhi.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName AnswerServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/11/27  2:07
 */

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;
    public AnswerDAO getAnswerDAO() { return answerDAO; }
    public void setAnswerDAO(AnswerDAO answerDAO) { this.answerDAO = answerDAO; }
    @Autowired
    private Z_user_AnswerDAO z_user_answerDAO;
    public Z_user_AnswerDAO getZ_user_answerDAO() { return z_user_answerDAO; }
    public void setZ_user_answerDAO(Z_user_AnswerDAO z_user_answerDAO) { this.z_user_answerDAO = z_user_answerDAO; }
    @Autowired
    private UserDAO userDAO;
    public UserDAO getUserDAO() { return userDAO; }
    public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO; }




    public List<Answer> showAllAnswer(String q_id) {
        List<Answer> list = answerDAO.showAllAnswer(q_id);
        for (Answer a:list) {
            a.setA_counts(z_user_answerDAO.showAnswerCount(a.getA_id()));
            a.setUser(userDAO.queryUser(a.getU_id()));
            List<Answer> as = answerDAO.getAllByBetterA_id(a.getA_id());
            for (Answer a2:as){
                System.out.println("我到这儿了！"+a2.getA_id());
                a2.setA_counts(z_user_answerDAO.showAnswerCount(a2.getA_id()));
                a2.setUser(userDAO.queryUser(a2.getU_id()));
            }
            a.setAns(as);
        }
        return list;
    }


    public List<Answer> showTwoAnswer(String q_id) {
        List<Answer> list = answerDAO.showTwoAnswer(q_id);
        for (Answer a:list) {
            a.setA_counts(z_user_answerDAO.showAnswerCount(a.getA_id()));
            a.setUser(userDAO.queryUser(a.getU_id()));
        }
        return list;
    }


    public void insertQuestionAnswer(Answer answer) {
        answer.setA_id(UUID.randomUUID().toString());
        answerDAO.insertQuestionAnswer(answer);
    }


    public void insertAnswerAnswer(Answer answer) {
        answer.setA_id(UUID.randomUUID().toString());
        answerDAO.insertAnswerAnswer(answer);
    }


    //获取当前回答对象
    public Answer getAnswer(String a_id){
        Answer answer = answerDAO.getAnswer(a_id);
        answer.setUser(userDAO.queryUser(answer.getU_id()));
        return answer;
    }

}
