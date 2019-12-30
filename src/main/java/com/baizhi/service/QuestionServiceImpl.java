package com.baizhi.service;

import com.baizhi.dao.*;
import com.baizhi.entity.Answer;
import com.baizhi.entity.Question;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName QuestionServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/11/27  1:23
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;
    public QuestionDAO getQuestionDAO() { return questionDAO;}
    public void setQuestionDAO(QuestionDAO questionDAO) { this.questionDAO = questionDAO; }
    @Autowired
    private AnswerDAO answerDAO;
    public AnswerDAO getAnswerDAO() { return answerDAO; }
    public void setAnswerDAO(AnswerDAO answerDAO) { this.answerDAO = answerDAO; }
    @Autowired
    private Z_user_QuestionDAO z_user_questionDAO;
    public Z_user_QuestionDAO getZ_user_questionDAO() { return z_user_questionDAO; }
    public void setZ_user_questionDAO(Z_user_QuestionDAO z_user_questionDAO) { this.z_user_questionDAO = z_user_questionDAO; }
    @Autowired
    private Z_user_AnswerDAO z_user_answerDAO;
    public Z_user_AnswerDAO getZ_user_answerDAO() { return z_user_answerDAO; }
    public void setZ_user_answerDAO(Z_user_AnswerDAO z_user_answerDAO) { this.z_user_answerDAO = z_user_answerDAO; }
    @Autowired
    private UserDAO userDAO;
    public UserDAO getUserDAO() { return userDAO; }
    public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO; }



    //根据question返回Question
    public Question selectByquestion(HttpServletRequest request, String question){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Question q = questionDAO.selectByquestion(question);
        List<Answer> list = answerDAO.showAllAnswer(q.getQ_id());
        for (Answer a:list) {
            a.setA_counts(z_user_answerDAO.showAnswerCount(a.getA_id()));
            List<Answer> as = answerDAO.getAllByBetterA_id(a.getA_id());
            a.setUser(userDAO.queryUser(a.getU_id()));
            for (Answer a2:as){
                System.out.println("我到这儿了！"+a2.getA_id());
                a2.setA_counts(z_user_answerDAO.showAnswerCount(a2.getA_id()));
                a2.setUser(userDAO.queryUser(a2.getU_id()));
            }
            a.setAns(as);
            System.out.println("现在的count："+a.getA_counts());
        }
        q.setAns(list);
        q.setAns_sum(answerDAO.showAllAnswer(q.getQ_id()).size());
        q.setQ_counts(z_user_questionDAO.selectLikesCount(q.getQ_id()));
        q.setBeiShouCang(z_user_questionDAO.showCollect(user.getU_id(), q.getQ_id()));
        return q;
    }

    public Question questionTuiJian() {
        Question q = questionDAO.questionTuiJian(); //对象中只有question
        System.out.println(q.getQuestion());
        Question question = questionDAO.selectByquestion(q.getQuestion());
        return question;
    }

    //关展收藏问题
    public List<Question> questionGuanZhu(String u_id) {
        List<Question> questions = questionDAO.questionGuanZhu(u_id);
        return questions;
    }

    //添加问题
    public void insertQuestion(Question question){
        Question q = questionDAO.selectByquestion(question.getQuestion());
        if(q==null){
            question.setQ_id(UUID.randomUUID().toString());
            questionDAO.insertQuestion(question);
        }
    }

    //模糊查询
    public List<Question> queryQuestion(String str){
        List<Question> list = questionDAO.queryQuestion(str);
        return list;
    }

}
