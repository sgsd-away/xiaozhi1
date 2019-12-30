package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.List;
import java.io.Serializable;

/**
 * @ClassName Question
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:20
 */
/*@Getter// 提供了get方法（类中所有属性）
@Setter// 提供了set方法
@ToString // 提供toString
@EqualsAndHashCode // 提供equals和hashcode方法*/

@Data// 相当于提供了 getter/setter/equals/hashcode/toString
@AllArgsConstructor // 提供全参的构造方法
@NoArgsConstructor // 提供无参构造方法
@Accessors(chain = true) // 生成链式调用的方法
public class Question implements Serializable {
    private String q_id;
    private String question;
    private String u_id;

    private List<Answer> ans;
    private Integer ans_sum;
    private Integer beiShouCang;
    private Integer q_counts;
}
