package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Photo
 * @Discription
 * @Acthor
 * @Date 2019/12/1  16:51
 */

@Data // 相当于提供了 getter/setter/equals/hashcode/toString
@AllArgsConstructor // 提供全参的构造方法
@NoArgsConstructor // 提供无参构造方法
@Accessors(chain = true) // 生成链式调用的方法
public class Photo {
    private String p_id;
    private String p_name;
    private String u_id;
    private Integer p_count;
}
