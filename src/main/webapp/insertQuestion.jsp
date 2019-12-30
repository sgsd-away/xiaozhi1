<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <!--在手机网站，都需要加上视口约束！！！-->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <!--以最新的内核渲染页面-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>主页</title>
    <!--引入css文件-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
    <style type="text/css">


    </style>
</head>
<body>
<div class="container">
    <!--顶部导航-->
    <div class="navbar navbar-default " id="navbar-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="" class="navbar-brand"><span class="glyphicon glyphicon-th-large"></span></a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-1">
                    <span class="caret"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="menu-1">

                <ul class="nav navbar-nav">
                    <li><a href="">关于小知</a></li>
                    <li><a href="${pageContext.request.contextPath}/question/show">首页</a></li>
                    <li><a href="">等你来答</a></li>
                </ul>
                <form action="${pageContext.request.contextPath}/question/queryQuestion.do" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" name="str" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
                <a href="${pageContext.request.contextPath}/insertQuestion.jsp">
                    <button type="button" class="navbar-btn btn btn-primary">提问</button>
                </a>
                <div class="navbar-btn navbar-right" style="background-color: transparent">
                    欢迎 ${sessionScope.user.username}&nbsp;<img src="${sessionScope.user.portrait}" class="img-circle" alt="" width="30px" height="30px"></a></li>
                </div>

            </div>
        </div>
    </div>
    <!--左侧-->
    <div class="col-md-10">
        <!--添加-->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>提问</h3>
            </div>
            <!--问题添加-->
            <div class="panel-body">
                <div class="panel">
                    <div class="panel-heading text-danger">
                        <div class="page-header">
                            <h1>请！</h1>
                        </div>
                    </div>
                    <!--评论回答-->
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/question/insertQuestion.do" method="post">
                            <div class="panel">
                                <div class="panel-body">
                                    <input type="text" name="question" class="form-control" placeholder="出招吧小CuaCua....">
                                </div>
                                <button type="submit" class="btn btn-primary block full-width m-b">提交</button>
                            </div>
                        </form>

                    </div>
                    <div class="panel-footer">
                        <a href="${pageContext.request.contextPath}/question/updateLikes.do?q_id=${tuiJianQuestion.q_id}"><span class="glyphicon glyphicon-thumbs-up"></span>${tuiJianQuestion.q_counts}赞</a>
                        &nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/answer/show?question=${tuiJianQuestion.question}"><span class="glyphicon glyphicon-comment"></span>&nbsp;${tuiJianQuestion.ans_sum}条评论</a>
                        &nbsp;&nbsp;
                        <c:if test="${tuiJianQuestion.beiShouCang==0}">
                            <a href="${pageContext.request.contextPath}/question/updateCollect.do?q_id=${tuiJianQuestion.q_id}"><span class="glyphicon glyphicon-star"></span>&nbsp;收藏</a>
                        </c:if>
                        <c:if test="${tuiJianQuestion.beiShouCang==1}">
                            <a href="${pageContext.request.contextPath}/question/updateCollect.do?q_id=${tuiJianQuestion.q_id}"><span class="glyphicon glyphicon-star"></span>&nbsp;取消收藏</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <!--右侧-->
    <div class="col-md-2">
        <ul class="list-group">
            <li class="list-group-item">写回答</li>
            <li class="list-group-item">我的草稿</li>
            <li class="list-group-item">我的收藏</li>
            <li class="list-group-item">我关注的问题</li>
            <li class="list-group-item">我的邀请</li>
            <li class="list-group-item">
                <a href="${pageContext.request.contextPath}/photo/show.do">
                    我的相册
                </a>
            </li>
        </ul>
    </div>
</div>


<!--引入jQuery-->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<!--引入js文件-->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>