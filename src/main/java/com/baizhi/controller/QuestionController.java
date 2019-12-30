package com.baizhi.controller;

import com.baizhi.entity.Question;
import com.baizhi.entity.User;
import com.baizhi.service.AnswerService;
import com.baizhi.service.QuestionService;
import com.baizhi.service.Z_user_QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName QuestionController
 * @Discription
 * @Acthor
 * @Date 2019/11/27  1:26
 */

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    public QuestionService getQuestionService() { return questionService; }
    public void setQuestionService(QuestionService questionService) { this.questionService = questionService; }
    @Autowired
    private AnswerService answerService;
    public AnswerService getAnswerService() { return answerService; }
    public void setAnswerService(AnswerService answerService) { this.answerService = answerService; }
    @Autowired
    private Z_user_QuestionService z_user_questionService;
    public Z_user_QuestionService getZ_user_questionService() { return z_user_questionService; }
    public void setZ_user_questionService(Z_user_QuestionService z_user_questionService) { this.z_user_questionService = z_user_questionService; }


    //展示
    @RequestMapping("/show")
    public String show(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Question question = questionService.questionTuiJian();
        question.setAns(answerService.showTwoAnswer(question.getQ_id()));
        question.setAns_sum(answerService.showAllAnswer(question.getQ_id()).size());
        question.setQ_counts(z_user_questionService.selectLikesCount(question.getQ_id()));
        Integer i = z_user_questionService.showCollect(user.getU_id(),question.getQ_id());
        if(i==null){ i=0; }question.setBeiShouCang(i);
        List<Question> list = questionService.questionGuanZhu(user.getU_id());
        for (Question q:list) {
            q.setAns(answerService.showTwoAnswer(q.getQ_id()));
            q.setAns_sum(answerService.showAllAnswer(q.getQ_id()).size());
            q.setQ_counts(z_user_questionService.selectLikesCount(q.getQ_id()));
            Integer i2 = z_user_questionService.showCollect(user.getU_id(),q.getQ_id());
            if(i2==null){ i2=0; }q.setBeiShouCang(i2);
        }
        request.setAttribute("tuiJianQuestion",question);
        request.setAttribute("guanZhuQuestion",list);
        return "/main.jsp";
    }

  //点赞
    @RequestMapping("/updateLikes")
    @ResponseBody
    public Integer updateLikes(HttpServletRequest request,@RequestBody String q_id){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("u_id:"+user.getU_id()+"   q_id:"+q_id);
        z_user_questionService.updateZ_user_Question(user.getU_id(),q_id);
        Integer i = z_user_questionService.selectLikesCount(q_id);
        return i;
    }

    //收藏
    @RequestMapping("/updateCollect")
    @ResponseBody
    public String updateCollect(HttpServletRequest request,@RequestBody String q_id){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("u_id:"+user.getU_id()+"   q_id:"+q_id);
        z_user_questionService.updateZ_user_Questioncollect(user.getU_id(),q_id);
        String str=null;
        Integer integer = z_user_questionService.showCollect(user.getU_id(), q_id);
        if(integer==0){
            str="收藏";
        }else{
            str="取消收藏";
        }
        return str;
    }

    //添加
    @RequestMapping("/insertQuestion")
    public String insertQuestion(HttpServletRequest request,Question question){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        question.setQ_counts(0);
        question.setU_id(user.getU_id());
        questionService.insertQuestion(question);
        Question q = questionService.selectByquestion(request,question.getQuestion());
        request.setAttribute("question",q);
        return "/commen.jsp";
    }

    //模糊查询
    @RequestMapping("/queryQuestion")
    public String queryQuestion(HttpServletRequest request,String str){
        List<Question> list = questionService.queryQuestion(str);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        for (Question question:list) {
            question.setAns(answerService.showTwoAnswer(question.getQ_id()));
            question.setAns_sum(answerService.showAllAnswer(question.getQ_id()).size());
            question.setQ_counts(z_user_questionService.selectLikesCount(question.getQ_id()));
            Integer i2 = z_user_questionService.showCollect(user.getU_id(),question.getQ_id());
            if(i2==null){ i2=0; }question.setBeiShouCang(i2);
        }
        request.setAttribute("queryList",list);
        request.setAttribute("moHuStr",str);
        return "/moHuChaXun.jsp";
    }

}
