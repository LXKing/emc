<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="decorator" content="login"/>
    <title>能源管控中心后台管理系统 - 登录</title>
    <script src="${web}/script/huak.login.js"></script>
    <script>
        var ctx = '${web}';
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>能源管控中心</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>后台管理系统</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                </ul>
                <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="form" onsubmit="false">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录到后台管理系统</p>
                <div class="form-group">
                    <input type="text" maxlength="64" name="login" class="form-control uname" placeholder="用户名" />
                </div>
                <div class="form-group">
                    <input type="password" maxlength="64" name="pwd" class="form-control pword m-b" placeholder="密码" />
                </div>
                <div class="form-group">
                    <input type="text" maxlength="4" name="vc" style="width: 63%;margin-top: 0px;color: #333;" class="form-control col-xs-8" placeholder="点击图片切换验证码" />
                        <img class="ver-code-img" src="${web}/generateVc" />
                </div>
                <a href="">忘记密码了？</a>
                <button type="button" id="login" class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2015 All Rights Reserved. 北京华热科技发展有限公司
        </div>
    </div>
</div>
</body>

</html>
