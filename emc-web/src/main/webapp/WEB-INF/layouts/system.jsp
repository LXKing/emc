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
    <script>
        $(function () {
            //console.info("重置面包屑");
            var nodes = ${menus};
            var setting = {
                view: {
                    dblClickExpand: false,//屏蔽掉双击事件
                    showLine: true,//是否显示节点之间的连线
                    //fontCss:{'color':'black','font-weight':'bold'},//字体样式函数
                    selectedMulti: false //设置是否允许同时选中多个节点
                },

                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick
                }
            };
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);
        });

        function onClick(event,treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);//如果是父节点，则展开该节点
            }else{
                $(".pull-left.yuce-tit").html(treeNode.name);
                openPage(_web + treeNode.href);//ajax
            }
        }


        /**
        * ajax打开页面
        * @param url
         */
        function openPage(url){
            $("#panelright").empty().load(url);
        }
    </script>
</head>
<body>
<%-- 菜单 --%>
<%--<div class="publicmenu">--%>
    <%--<div>当前页面</div>--%>
    <%--<ul>--%>
        <%--<li><a>二级目录A</a></li>--%>
        <%--<li><a>二级目录A</a></li>--%>
        <%--<li class="active more"><a>二级目录A</a>--%>
            <%--<ul>--%>
                <%--<li><a href="#">三级目录</a></li>--%>
                <%--<li class="active"><a href="#">三级目录</a></li>--%>
                <%--<li><a href="#">三级目录</a></li>--%>
                <%--<li><a href="#">三级目录</a></li>--%>
                <%--<li><a href="#">三级目录</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li><a>二级目录A</a></li>--%>
        <%--<li><a>二级目录A</a></li>--%>
        <%--<li><a>二级目录A</a></li>--%>
    <%--</ul>--%>
<%--</div>--%>

<%@include file="/WEB-INF/include/header.jsp" %>
<div class="main-container">
    <div class="clearfix row no-margin index_header">

        <!--面包屑导航-->
        <div class="bread-crumb row no-margin">
            当前位置：
            <a href="${web}/system/index">[<var class="xmhpg">安全与后台 </var>]</a>
        </div>
    </div>

    <!--标题-->
    <div class="titbox clearfix">
        <div class="pull-left yuce-tit">安全与后台 <%--<small class="font-sm">Security and Backstage</small>--%></div>

    </div>
    <div class="main-two-panel">
        <div class="panelleft">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="panelright" id="panelright">
            <sitemesh:body/>
        </div>

    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>