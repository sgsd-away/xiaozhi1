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
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.min.css"/>--%>
    <style type="text/css">


    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>



        form{
            padding: 0 12px 12px;
        }
        label{
            display: block;
            padding-bottom:12px;
        }


        #upload-tip{
            border: 1px dashed #d9d9d9;
            width: 135px;
            height: 135px;
            line-height: 135px;
            cursor: pointer;
            font-size: 36px;
            color:#d9d9d9;
        }
        #img-show{
            width: 135px;
            height: 135px;
            display: block;
            margin: 0 auto;
            object-fit: cover;
        }
    </style>
    <script>
        function imgfileChange() {
            var upload_tip = document.getElementById("upload-tip");
            var img_show = document.getElementById("img-show");
            var imgfile = document.getElementById("imgfile");

            var freader = new FileReader();
            freader.readAsDataURL(imgfile.files[0]);
            freader.onload = function (e) {
                img_show.src = e.target.result;
                img_show.style.display = "inline";
                upload_tip.style.display = "none";
            };
        }
    </script>
</head>
<body class="container">
<div class="col-md-4 col-md-offset-4">
    <div class="panel" style="width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div>
                <div>

                    <h1 class="logo-name"><img src="${pageContext.request.contextPath}/img/logo.png"></h1>

                </div>
                <h3>注册账号</h3>

                <form role="form" action="${pageContext.request.contextPath}/user/insertUser" method="post" enctype="multipart/form-data">

                    <label>
                        <div class="label-text">用户头像：</div>
                        <div style="text-align: center;padding-left: 36%">
                            <div id="upload-tip">+</div>
                            <img src="" alt="" id="img-show" style="display: none">
                            <input type="file" name="upload" id="imgfile" style="display: none" onchange="imgfileChange()">
                        </div>
                    </label>

                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入用户名" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="请输入密码" name="password">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入电话" name="phone">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入邮箱" name="postbox">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                    <p class="text-muted text-center"><a href="${pageContext.request.contextPath}/login.jsp">
                        <small>返回登陆</small>
                    </a>
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