
package com.baizhi.dao;

import com.baizhi.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @ClassName QuestionDAO
 * @Discription
 * @Acthora
 * @Date 2019/11/27  0:14
 */
@Repository
public interface QuestionDAO {
    //根据question返回Question
    public Question selectByquestion(String question);
    //推荐(最热)
    public Question questionTuiJian();
    //关注
    public List<Question> questionGuanZhu(String u_id);
    //添加问题
    public void insertQuestion(Question question);
    //模糊查询
    public List<Question> queryQuestion(@Param("str")String str);
}

