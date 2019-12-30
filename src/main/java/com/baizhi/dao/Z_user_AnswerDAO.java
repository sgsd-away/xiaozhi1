package com.baizhi.dao;

import com.baizhi.entity.Z_user_Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Z_user_AnswerDAO {
    //根据u_id和a_id返回Z_user_Answer
    public Z_user_Answer selectByU_idAndA_id(@Param("u_id") String u_id, @Param("a_id") String a_id);
    //创建用户和回答的联系
    public void insertUserAndAnswer(Z_user_Answer za);
    //返回点赞次数
    public Integer showAnswerCount(String a_id);
    //更改点赞状态
    public void updateZ_user_Answer(Z_user_Answer za);
}
