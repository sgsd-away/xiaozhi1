package com.baizhi.controller;

import com.baizhi.entity.Answer;
import com.baizhi.entity.Question;
import com.baizhi.entity.User;
import com.baizhi.entity.Z_user_Answer;
import com.baizhi.service.AnswerService;
import com.baizhi.service.QuestionService;
import com.baizhi.service.Z_user_AnswerService;
import com.baizhi.service.Z_user_QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @ClassName AnswerController
 * @Discription
 * @Acthor
 * @Date 2019/11/27  2:10
 */

@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private QuestionService questionService;
    public QuestionService getQuestionService() { return questionService; }
    public void setQuestionService(QuestionService questionService) { this.questionService = questionService; }
    @Autowired
    private Z_user_AnswerService z_user_answerService;
    public Z_user_AnswerService getZ_user_answerService() { return z_user_answerService; }
    public void setZ_user_answerService(Z_user_AnswerService z_user_answerService) { this.z_user_answerService = z_user_answerService; }
    @Autowired
    private Z_user_QuestionService z_user_questionService;
    public Z_user_QuestionService getZ_user_questionService() { return z_user_questionService; }
    public void setZ_user_questionService(Z_user_QuestionService z_user_questionService) { this.z_user_questionService = z_user_questionService; }
    @Autowired
    private AnswerService answerService;
    public AnswerService getAnswerService() { return answerService; }
    public void setAnswerService(AnswerService answerService) { this.answerService = answerService; }


    //单个问题详情展示
    @RequestMapping("/show")
    public String show(HttpServletRequest request,String question){
        HttpSession session = request.getSession();
        Question q = questionService.selectByquestion(request,question);
        request.setAttribute("question",q);
        return "/commen.jsp";
    }

    //更改问题点赞次数
    @RequestMapping("/updateCount")
    @ResponseBody
    public Integer updateCount(HttpServletRequest request,@RequestBody String a_id){
        System.out.println(a_id);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Z_user_Answer za = z_user_answerService.selectByU_idAndA_id(user.getU_id(), a_id);
        if(za==null){
            za=new Z_user_Answer(null,user.getU_id(), a_id,1);
            z_user_answerService.insertUserAndAnswer(za);
        }else {
            z_user_answerService.updateZ_user_Answer(user.getU_id(), a_id);
        }
        Integer i = z_user_answerService.showAnswerCount(a_id);
        return i;
    }

    //添加回答
    @RequestMapping("/insert")
    public String insert(HttpServletRequest request,Answer answer,String question,String q_id){
        System.out.println(answer+"@@@@@@@@@@@@@@@@@@@@@@@@@");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        answer.setU_id(user.getU_id());
        answer.setQ_id(q_id);
        answerService.insertQuestionAnswer(answer);
        Question q = questionService.selectByquestion(request,question);
        request.setAttribute("question",q);
        return "/commen.jsp";
    }
}
