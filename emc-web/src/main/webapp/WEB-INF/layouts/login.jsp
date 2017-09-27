<%--
    主框架，带头部、面包屑导航、底部
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
    <%--图标--%>
    <link rel="shortcut icon" href="${web}/static/favicon.ico">
    <link href="${web}/static/css/chosen/chosen.css" rel="stylesheet">
    <!-- bootstrap & fontawesome -->

    <link rel="stylesheet" href="${web}/static/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${web}/static/assets/css/font-awesome.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="${web}/static/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${web}/static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

    <link rel="stylesheet" href="${web}/static/js/ztree/zTreeStyle.css" />

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${web}/static/css/huarestyle.css" />
    <link rel="stylesheet" href="${web}/static/css/homemian.css" />
    <link rel="stylesheet" href="${web}/static/css/dark.css" />
    <link rel="stylesheet" href="${web}/static/css/weather.css" />
    <link rel="stylesheet" href="${web}/static/css/emc.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${web}/static/assets/css/ace-part2.css" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${web}/static/assets/css/ace-ie.css" />
    <![endif]-->

    <!-- ace settings handler -->
    <script src="${web}/static/assets/js/ace-extra.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="${web}/static/assets/js/html5shiv.js"></script>
    <script src="${web}/static/assets/js/respond.js"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="${web}/static/js/Bsie/selectivizr.js"></script>
    <![endif]-->
    <script src="${web}/static/js/jquery/jquery.min.js"></script>
    <script src="${web}/static/js/validate/jquery.validate.min.js"></script>
    <script src="${web}/static/js/echarts/echarts3/echarts.min.js"></script>
    <script src="${web}/static/js/echarts/echarts3/dist/ecStat.js"></script>
    <script src="${web}/static/assets/js/bootstrap.min.js"></script>
    <script src="${web}/static/js/My97DatePicker/WdatePicker.js"></script>
    <%--chosen--%>
    <script src="${web}/static/js/chosen/chosen.jquery.js"></script>


    <script src="${web}/static/js/navscript.js"></script>

    <link rel="stylesheet" type="text/css" media="all" href="${web}/static/js/daterangepicker/daterangepicker.css" />
    <script type="text/javascript" src="${web}/static/js/daterangepicker/moment.js"></script>
    <link rel="stylesheet" type="text/css" media="all" href="${web}/static/js/datetimepicker/css/bootstrap-datetimepicker.css" />
    <script type="text/javascript" src="${web}/static/js/daterangepicker/daterangepicker.js"></script>
    <script type="text/javascript" src="${web}/static/js/datetimepicker/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${web}/static/js/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${web}/static/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${web}/static/js/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${web}/static/js/jquery.page.js"></script>
    <script type="text/javascript" src="${web}/static/js/jquery-jtemplates.js"></script>
    <script type="text/javascript" src="${web}/static/js/layer/laydate/laydate.js"></script>
    <script>
        var _web = '${web}';
        layer.config({
            extend: ['skin/moon/style.css'] //加载新皮肤
            /*skin: 'layer-ext-moon' //一旦设定，所有弹层风格都采用此主题。*/
        });
    </script>
    <script src="${web}/static/js/huak.cookie.js"></script>
    <script src="${web}/static/js/date.js"></script>
    <script src="${web}/static/js/echartsConfig.js"></script>
    <sitemesh:head/>
</head>
<body>
<div class="main-container">
    <sitemesh:body/>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>