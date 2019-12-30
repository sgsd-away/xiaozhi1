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
    <style type="text/css"></style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // 文件上传的验证
            $('#submitBtn').click(function () {
                console.log("hello");
                // 获取上传文件的名字
                var filename = $('#up1').val();
                // 获取到文件扩展名
                var extension = filename.substring(filename.lastIndexOf('.')+1);
                //if ('jpeg' == extension || 'png' == extension) {
                if (true) {
                    // 获取表单中上传文件的数据
                    var domObj = $('#fom')[0];
                    var formData = new FormData(domObj);
                    // 执行上传
                    $.ajax({
                        url:'${pageContext.request.contextPath}/photo/insertPhoto.do',
                        type:'post',
                        data:formData,
                        contentType:false,// 不去处理请求头的类型，默认使用表单上声明
                        processData:false,// 不需要以传统方式处理表单的数据
                        success:function (data) {
                            window.location.href="${pageContext.request.contextPath}/photo/show.do";
                            /*var i=data.length%4==0?data.length/4:data.length/4+1;
                            console.log("i="+i);
                            var v;
                            for(var n=1;n<=i;n++){
                                v="<div class='row text-center' style='padding-top: 30px;padding-left:15px;padding-right: 15px' >";
                                for(var t=(i-1)*4;t<=(i-1)*4+3;t++){
                                    console.log("t="+t);
                                    console.log("data[t]="+data[t]);
                                    v+="<div class='col-xs-2 col-sm-3' >&nbsp;&nbsp;<img src='" +data[t].p_name+
                                        "'width='160px' height='160px'><p style='padding-top: 10px'>" +
                                        "图片下载次数:"+data[t].p_count+"次</p><a><span class='glyphicon" +
                                        " glyphicon-download-alt'></span></a></div>"
                                }
                            }
                            $("#shangchuan1").html(v);*/
                        }
                    });
                } else {
                    alert('您上传文件的类型不合法，请重新选择.jpeg/.png 这样的图片类型');
                }
            });
        })
    </script>
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

        <!--页头-->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>${sessionScope.user.username}  的相册</h3>
            </div>
            <div class="panel-body" id="shangchuan1">
                <c:forEach items="${hangs}" var="i">
                    <div class="row text-center" style="padding-top: 30px;padding-left:15px;padding-right: 15px" >
                        <c:forEach items="${list}" var="p" begin="${(i-1)*4}" end="${(i-1)*4+3}">

                            <div class="col-xs-2 col-sm-3" >
                                &nbsp;&nbsp;
                                <img src="${pageContext.request.contextPath}/upload/${p.p_name}" width="160px" height="160px">
                                <p id="p${p.p_id}" style="padding-top: 10px">图片下载次数:${p.p_count}次</p>

                                <a href="${pageContext.request.contextPath}/photo/downPhoto.do?filename=${p.p_name}" onclick='xiaZai(${p.p_id})'>
                                    <span class="glyphicon glyphicon-download-alt"></span>
                                </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="${pageContext.request.contextPath}/photo/deletePhoto.do?p_id=${p.p_id}">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </div>

                        </c:forEach>
                    </div>
                </c:forEach>
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
                添加相册:
                <div style="text-align: center;">
                <form id="fom" enctype="multipart/form-data">
                    <input type="file" name="upload" accept="image/jpeg,image/png" id="up1">
                    <button id="submitBtn" type="button" class="btn btn-default">上传</button>
                </form>
                </div>
            </li>
        </ul>
    </div>
</div>


<!--引入jQuery-->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<!--引入js文件-->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">

    function xiaZai(id) {
        console.log(id);
        var v=id;
        var p_id=JSON.stringify(v);
        $.ajax({
            url:'${pageContext.request.contextPath}/photo/gengGaiCiShu.do',
            type:'post',
            data:p_id,
            contentType:"application/json",
            /*dataType:'json',*/
            success:function (data) {
                var v="图片下载次数:"+data+"次";
                $("#p"+p_id).html(v);
            }
        });
    }

</script>
</body>
</html>