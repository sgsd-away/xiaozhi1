package com.baizhi.dao;

import com.baizhi.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDAO
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:29
 */
@Repository
public interface UserDAO {
    //注册用户
    public void userRegister(User user);
    //根据username查询用户
    public User userSelectByUsername(String username);
    //修改密码
    public void userUpdatePassword(User user);
    //根据ID查询用户
    public User queryUser(String u_id);
}
