package com.baizhi.dao;

import com.baizhi.entity.Z_user_Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Z_user_QuestionDAO {
    //根据u_id和q_id返回Z_user_Question
    public Z_user_Question selectByU_idAndQ_id(@Param("u_id") String u_id,@Param("q_id") String q_id);
    //更改点赞状态
    public void updateZ_user_Question(Z_user_Question uq);
    //问题查询被点赞的次数
    public Integer selectLikesCount(String q_id);
    //更改收藏状态
    public void updateZ_user_Questioncollect(Z_user_Question uq);
    //展示收藏状态
    public Integer showCollect(@Param("u_id") String u_id,@Param("q_id") String q_id);
    //添加新的连接对象
    public void insertUserAndQuestion(Z_user_Question zq);
}
