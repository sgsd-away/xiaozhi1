package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Answer
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:21
 */

/*@Getter// 提供了get方法（类中所有属性）
@Setter// 提供了set方法
@ToString // 提供toString
@EqualsAndHashCode // 提供equals和hashcode方法*/

@Data // 相当于提供了 getter/setter/equals/hashcode/toString
@AllArgsConstructor // 提供全参的构造方法
@NoArgsConstructor // 提供无参构造方法
@Accessors(chain = true) // 生成链式调用的方法
public class Answer implements Serializable {
    private String a_id;
    private String answer;
    private String u_id;
    private String q_id;
    private String betterA_id;

    private List<Answer> ans;
    private User user;
    private Integer a_counts;
}
