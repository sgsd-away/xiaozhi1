package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:44
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    public UserDAO getUserDAO() {return userDAO;}
    public void setUserDAO(UserDAO userDAO) {this.userDAO = userDAO; }



    //注册用户
    public void userRegister(User user){
        user.setU_id(UUID.randomUUID().toString());
        userDAO.userRegister(user);
    }

    //根据username查询用户
    public User userSelectByUsername(String username){
        User user = userDAO.userSelectByUsername(username);
        return user;
    }

    //修改密码
    public void userUpdatePassword(User user){
        userDAO.userUpdatePassword(user);
    }

}
