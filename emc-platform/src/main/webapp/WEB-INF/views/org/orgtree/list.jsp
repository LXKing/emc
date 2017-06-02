<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <title>H+ 后台主题UI框架 - 树形视图</title>
    <jsp:include page="../../head.jsp"></jsp:include>

    <SCRIPT type="text/javascript">
        <!-- -->

        $(document).ready(function(){
            //页面说明
            console.info("页面说明：系统登录用户选择企业或者公司后显示该企业或者公司的组织机构\n" +
                    "功能：\n【添加】【删除】【修改】\n" +
                    "字段：\n组织机构名称、上级组织机构\n" +
                    "以树显示组织机构，所以有隐藏字段：\n创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
       });

    </SCRIPT>
    <script type="application/javascript" src="${platform}/script/org/huak.org.org.list.js"></script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">

            <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">

                <div class="ibox float-e-margins" style="height: 100%">

                    <div class="ibox-content">
                        <h3>组织机构信息</h3>
                        <div id="jstree1">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                     </div>
                 </div>
             </div>
        </div>

    </div>
<div id="orgtree-layer-div" style="display: none"></div>

</body>

</html>
