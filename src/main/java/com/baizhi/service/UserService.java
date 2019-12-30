package com.baizhi.service;

import com.baizhi.entity.User;

public interface UserService {
    //注册用户
    public void userRegister(User user);
    //根据username查询用户
    public User userSelectByUsername(String username);
    //修改密码
    public void userUpdatePassword(User user);
}
