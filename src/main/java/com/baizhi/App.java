package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName App
 * @Discription
 * @Author
 * @Date 2019/11/25 0025 16:04
 * @Version 1.0
 */

/**
 * 用注解声明当前类是一个启动类
 */
@SpringBootApplication
//指定dao接口所在的包结构
@MapperScan("com.baizhi.dao")
public class App {
        public static void main(String[] args) {
            System.out.println("xiaoZhi1项目启动啦");
            //  指定启动类，运行后会启动整个WEB应用
            SpringApplication.run(App.class, args);
        }
}
