package com.baizhi.service;

import com.baizhi.dao.Z_user_AnswerDAO;
import com.baizhi.entity.Z_user_Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName Z_user_AnswerServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/11/27  16:42
 */

@Service
public class Z_user_AnswerServiceImpl implements Z_user_AnswerService{
    @Autowired
    private Z_user_AnswerDAO z_user_answerDAO;
    public Z_user_AnswerDAO getZ_user_answerDAO() { return z_user_answerDAO; }
    public void setZ_user_answerDAO(Z_user_AnswerDAO z_user_answerDAO) { this.z_user_answerDAO = z_user_answerDAO; }


    //返回点赞次数
    public Integer showAnswerCount(String a_id){
        Integer i = z_user_answerDAO.showAnswerCount(a_id);
        return i;
    }


    //根据u_id和a_id返回Z_user_Answer
    public Z_user_Answer selectByU_idAndA_id(String u_id, String a_id){
        Z_user_Answer z_user_answer = z_user_answerDAO.selectByU_idAndA_id(u_id, a_id);
        return z_user_answer;
    }

    //创建用户和回答的联系
    public void insertUserAndAnswer(Z_user_Answer za){
        za.setUa_id(UUID.randomUUID().toString());
        z_user_answerDAO.insertUserAndAnswer(za);
    }

    //更改点赞状态
    public void updateZ_user_Answer(String u_id,String q_id){
        Z_user_Answer za = z_user_answerDAO.selectByU_idAndA_id(u_id, q_id);
        System.out.println(za.getLikes());
        if(za.getLikes()==0){
            za.setLikes(1);
            System.out.println("修改之前是0");
            System.out.println("修改之后是"+za.getLikes());
            z_user_answerDAO.updateZ_user_Answer(za);
        }else {
            za.setLikes(0);
            System.out.println("修改之前是1");
            System.out.println("修改之后是"+za.getLikes());
            z_user_answerDAO.updateZ_user_Answer(za);
        }
    }
}
