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

        <!--模糊查询-->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>您要找的和“ ${moHuStr} ”相关的问题</h3>
            </div>
            <!--问题浏览-->
            <div class="panel-body">
                <div class="panel">
                    <c:forEach items="${queryList}" var="q">
                        <div class="panel-heading text-danger">
                            <div class="page-header">
                                <h3><a href="${pageContext.request.contextPath}/answer/show?question=${q.question}">${q.question} </a><small>精选评论</small></h3>
                            </div>
                        </div>
                        <!--评论回答-->
                        <div class="panel-body">

                            <c:forEach items="${q.ans}" var="a">

                                <div class="panel">
                                    <div class="panel-body">
                                        <p>
                                            <img src="${a.user.portrait}" alt="" class="img-circle" width="25px" height="25px">&nbsp;&nbsp;${a.user.username}
                                        </p>
                                        <p>${a.answer}</p>
                                    </div>
                                    <div class="panel-footer" style="background-color: white;">
                                        <a onclick='dianZan(${a.a_id})' style="color:gray;"><span class="glyphicon glyphicon-thumbs-up" id="a${a.a_id}">${a.a_counts}</span> 赞</a>
                                        &nbsp;&nbsp;
                                        <a href="${pageContext.request.contextPath}/answer/show?question=${q.question}" style="color:gray;"  ><span class="glyphicon glyphicon-comment"></span>&nbsp;查看回复</a>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <div class="panel-footer">
                            <a onclick='dianZanQ(${q.q_id})'><span class="glyphicon glyphicon-thumbs-up" id="q${q.q_id}">${q.q_counts}</span>赞</a>
                            &nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/answer/show?question=${q.question}"><span class="glyphicon glyphicon-comment"></span>&nbsp;${q.ans_sum}条评论</a>
                            &nbsp;&nbsp;
                            <c:if test="${q.beiShouCang==0}">
                                <a onclick='shouCang(${q.q_id})'><span class="glyphicon glyphicon-star" id="qs${q.q_id}">收藏</span></a>
                            </c:if>
                            <c:if test="${q.beiShouCang==1}">
                                <a onclick='shouCang(${q.q_id})'><span class="glyphicon glyphicon-star" id="qs${q.q_id}">取消收藏</span></a>
                            </c:if>
                        </div>

                    </c:forEach>
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
<script type="text/javascript">

//回答点赞
    function dianZan(id) {
        console.log(id);
        var v=id;
        var a_id=JSON.stringify(v);
        $.ajax({
            url:'${pageContext.request.contextPath}/answer/updateCount.do',
            type:'post',
            data:a_id,
            contentType:"application/json",
            /*dataType:'json',*/
            success:function (data) {
                $("#a"+a_id+"").html(data);
            }
        });
    }

    //问题点赞
    function dianZanQ(id) {
        console.log(id);
        var v=id;
        var q_id=JSON.stringify(v);
        $.ajax({
            url:'${pageContext.request.contextPath}/question/updateLikes.do',
            type:'post',
            data:q_id,
            contentType:"application/json",
            /*dataType:'json',*/
            success:function (data) {
                $("#q"+q_id).html(data);
            }
        });
    }
//问题收藏
function shouCang(id) {
    console.log(id);
    var v=id;
    var q_id=JSON.stringify(v);
    $.ajax({
        url:'${pageContext.request.contextPath}/question/updateCollect.do',
        type:'post',
        data:q_id,
        contentType:"application/json",
        /*dataType:'json',*/
        success:function (data) {
            $("#qs"+q_id).html(data);
        }
    });

}

</script>
</body>
</html>