package com.baizhi.service;

public interface Z_user_QuestionService {
    //更改点赞状态
    public void updateZ_user_Question(String u_id,String q_id);
    //更改收藏状态
    public void updateZ_user_Questioncollect(String u_id,String q_id);
    //问题查询被点赞的次数
    public Integer selectLikesCount(String q_id);
    //展示收藏状态
    public Integer showCollect(String u_id,String q_id);
}
