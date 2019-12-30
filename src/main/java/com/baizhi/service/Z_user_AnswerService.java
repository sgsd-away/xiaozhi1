package com.baizhi.service;

import com.baizhi.entity.Z_user_Answer;

public interface Z_user_AnswerService {

    //根据u_id和a_id返回Z_user_Answer
    public Z_user_Answer selectByU_idAndA_id(String u_id,String a_id);
    //创建用户和回答的联系
    public void insertUserAndAnswer(Z_user_Answer za);
    //更改点赞状态
    public void updateZ_user_Answer(String u_id,String a_id);
    //返回点赞次数
    public Integer showAnswerCount(String a_id);
}
