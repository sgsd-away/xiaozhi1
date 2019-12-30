package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName UserController
 * @Discription
 * @Acthor
 * @Date 2019/11/26  20:48
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    public UserService getUserService() {return userService;}
    public void setUserService(UserService userService) {this.userService = userService; }

    @RequestMapping("/login")
    public String loginUser(HttpServletRequest request, User user){

        User u = userService.userSelectByUsername(user.getUsername());
        if(u==null){
            //用户名不存在
            return "/login.jsp";
        }else{
            if(!u.getPassword().equals(user.getPassword())){
                //密码错误
                return "/login.jsp";
            }
            //登陆成功
            HttpSession session = request.getSession();
            session.setAttribute("user",u);
            return "forward:/question/show";
        }
    }

    @RequestMapping("/insertUser")
    public String insertUser(HttpServletRequest request,User user, MultipartFile upload) throws IOException {

        User u = userService.userSelectByUsername(user.getUsername());
        if(u!=null){
            //用户名已存在
            return "/insertUser.jsp";
        }else{
            System.out.println("upload"+upload);

            if(upload!=null){
                ServletContext context = request.getServletContext();
                String realPath = context.getRealPath("upload");
                System.out.println("realPath"+realPath);
                String originalFilename = upload.getOriginalFilename();
                if(!originalFilename.equals("")){
                    System.out.println("originalFilename"+originalFilename);
                    System.out.println("原始文件名" + originalFilename);
                    upload.transferTo(new File(realPath + "/" + originalFilename));
                    user.setPortrait(request.getContextPath()+"\\upload\\"+originalFilename);
                }
            }
            System.out.println(user);
            userService.userRegister(user);
            return "/login.jsp";
        }


    }

    @RequestMapping("/updateUser")
    public String updateUser(User user){
        System.out.println("修改方法"+user);
        User u = userService.userSelectByUsername(user.getUsername());
        System.out.println(u);
        if(u==null){
            //用户名不存在
            return "/updatePassword.jsp";
        }else{
            userService.userUpdatePassword(user);
            return "/login.jsp";
        }
    }

}
