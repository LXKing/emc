<%--
    后台主框架，带头部、面包屑导航、底部
    lc 2017年5月24日15:47:52
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="HUAK,能源管控中心,能管,能管后台">
    <meta name="description" content="HUAK 能源管控中心">
    <title><sitemesh:title/></title>
    <%@include file="/WEB-INF/include/include.jsp" %>
    <sitemesh:head/>
</head>
<body>
<%-- 菜单 --%>

<div id="iframepublicmenu" class="iframepublicmenu">
   	<iframe  src="${web}/process/menu"  height="100%" style="border:none" ></iframe>
</div>




<%@include file="/WEB-INF/include/header.jsp" %>
<div class="main-container">
    <div class="clearfix row no-margin index_header">

        <!--面包屑导航-->
        <div class="bread-crumb row no-margin">
            当前位置：
            <a href="${web}/process/index">[<var class="xmhpg">流程调度 </var>]</a>
        </div>
    </div>

    <!--标题-->
    <div class="titbox clearfix">
        <div class="pull-left yuce-tit">流程调度 <%--<small class="font-sm">Security and Backstage</small>--%></div>

    </div>
    <div class="main-two-panel">
        <div class="panelright" id="panelright">
            <sitemesh:body/>
        </div>

    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>