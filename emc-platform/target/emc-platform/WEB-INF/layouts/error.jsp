<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%--
  错误页
  User: xiayouxue
  Date:2014/4/25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%
    Logger logger = LoggerFactory.getLogger("com.bimt.jsp");
    logger.error("转至jsp异常!", exception);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${application.desc}-<sitemesh:title/></title>
    <link rel="shortcut icon" href="${ctx}/static/application/system/user/logo.png">

    <script src="${ctx}/static/js/jquery.min.js"></script>

    <link rel="stylesheet" href="${ctx}/static/component/bootstrap/css/bootstrap.min.css">
    <script src="${ctx}/static/component/bootstrap/js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        html, body {
            height: 100%;
            background-color: #f0f0f0;
        }

        .header {
            position: fixed;
            top: 0;
            width: 100%;
            height: 100px;
            padding: 20px 0 0;
            background-color: #f0f0f0;
        }

        .header img {
            width: 60px;
            height: 80px
        }

        .content {
            height: auto;
            padding: 100px 0;
        }

        .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            height: 100px;
            padding-top: 20px;
            background-color: #f0f0f0;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            var second = $(".second");
            var seconds = 5;

            window.time = function () {
                seconds--;
                second.text(seconds);
                if (seconds == 0) {
                    window.history.back();
                } else {
                    window.timer = setTimeout(time, 1000);
                }
            }

            time();

            second.click(function () {window.clearTimeout(window.timer);});

        });
    </script>

    <sitemesh:head/>

</head>
<body>
<div class="header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-2 text-right"><a href="${ctx}/index"><img src="${ctx}/static/application/system/user/logo.png"></a></div>
        </div>
    </div>
</div>
<div class="container text-center content">
    <sitemesh:body/>
    <div><span class="second">5</span>秒后自动返回上一页，<a href="${ctx}/index">点此返回首页</a></div>
</div>
<div class="footer">
    <div class="container text-center">
        <p>京ICP备09056041好||京公网安备110106000705</p>
    </div>
</div>
</body>
</html>