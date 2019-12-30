package com.baizhi.service;


import com.baizhi.dao.Z_user_QuestionDAO;
import com.baizhi.entity.Z_user_Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName Z_user_QuestionServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/11/27  2:57
 */

@Service
public class Z_user_QuestionServiceImpl implements Z_user_QuestionService {
    @Autowired
    private Z_user_QuestionDAO z_user_questionDAO;
    public Z_user_QuestionDAO getZ_user_questionDAO() { return z_user_questionDAO; }
    public void setZ_user_questionDAO(Z_user_QuestionDAO z_user_questionDAO) { this.z_user_questionDAO = z_user_questionDAO; }


    //更改点赞状态
    public void updateZ_user_Question(String u_id,String q_id){
        Z_user_Question zq = z_user_questionDAO.selectByU_idAndQ_id(u_id, q_id);
        if(zq==null){
            zq=new Z_user_Question(null,u_id,q_id,1,0);
            zq.setUq_id(UUID.randomUUID().toString());
            z_user_questionDAO.insertUserAndQuestion(zq);
        }else {
            System.out.println(zq.getLikes());
            if (zq.getLikes() == 0) {
                zq.setLikes(1);
                System.out.println("修改之前是0");
                System.out.println("修改之后是" + zq.getLikes());
                z_user_questionDAO.updateZ_user_Question(zq);
            } else {
                zq.setLikes(0);
                System.out.println("修改之前是1");
                System.out.println("修改之后是" + zq.getLikes());
                z_user_questionDAO.updateZ_user_Question(zq);
            }
        }
    }

    //更改收藏状态
    public void updateZ_user_Questioncollect(String u_id,String q_id){
        Z_user_Question zq = z_user_questionDAO.selectByU_idAndQ_id(u_id, q_id);
        if(zq==null){
            zq=new Z_user_Question(null,u_id,q_id,0,1);
            z_user_questionDAO.insertUserAndQuestion(zq);
        }else {
            System.out.println(zq.getCollect());
            if (zq.getCollect() == 0) {
                zq.setCollect(1);
                System.out.println("收藏状态修改之前是0");
                System.out.println("收藏状态修改之后是" + zq.getCollect());
                z_user_questionDAO.updateZ_user_Questioncollect(zq);
            } else {
                zq.setCollect(0);
                System.out.println("收藏状态修改之前是1");
                System.out.println("收藏状态修改之后是" + zq.getCollect());
                z_user_questionDAO.updateZ_user_Questioncollect(zq);
            }
        }
    }

    //问题查询被点赞的次数
    public Integer selectLikesCount(String q_id){
        Integer zq = z_user_questionDAO.selectLikesCount(q_id);
        return zq;
    }

    //展示收藏状态
    public Integer showCollect(String u_id,String q_id){
        Integer i = z_user_questionDAO.showCollect(u_id, q_id);
        return i;
    }

}
