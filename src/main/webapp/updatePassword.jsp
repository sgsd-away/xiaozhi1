<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <!--在手机网站，都需要加上视口约束！！！-->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <!--以最新的内核渲染页面-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>登录</title>
    <!--引入css文件-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.min.css"/>
    <style type="text/css">


    </style>
</head>
<body class="container">
<div class="col-md-4 col-md-offset-4">
    <div class="panel" style="width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div>
                <div>

                    <h1 class="logo-name"><img src="${pageContext.request.contextPath}/img/logo.png"></h1>

                </div>
                <h3>修改密码</h3>

                <form role="form" action="${pageContext.request.contextPath}/user/updateUser.do" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入用户名" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="请输入新密码" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">修改完成</button>

                    <p class="text-muted text-center"><a href="${pageContext.request.contextPath}/login.jsp">
                        <small>返回登陆</small>
                    </a> | <a href="${pageContext.request.contextPath}/insertUser.jsp">注册一个新账号</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>
<!--引入jQuery-->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<!--引入js文件-->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>