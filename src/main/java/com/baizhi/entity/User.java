package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName User
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:19
 */
/*@Getter// 提供了get方法（类中所有属性）
@Setter// 提供了set方法
@ToString // 提供toString
@EqualsAndHashCode // 提供equals和hashcode方法*/

@Data // 相当于提供了 getter/setter/equals/hashcode/toString
@AllArgsConstructor // 提供全参的构造方法
@NoArgsConstructor // 提供无参构造方法
@Accessors(chain = true) // 生成链式调用的方法
public class User implements Serializable {
    private String u_id;
    private String username;
    private String password;
    private String phone;
    private String postbox;
    private String portrait;
}
