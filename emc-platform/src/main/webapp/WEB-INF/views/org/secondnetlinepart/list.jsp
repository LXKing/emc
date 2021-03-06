<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <title>房间基本信息表</title>
    <jsp:include page="../../head.jsp"></jsp:include>
    <!-- 逻辑js引用-->
    <script src="${platform}/script/org/huak.org.secondnetlinepart.list.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false,
                fontCss:{color:"blue"}
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            edit: {
                enable: true
            }
        };

        var zNodes =[
            {id:0,pId:null,name:"华热科技发展有限公司"},
            { id:1, pId:0, name:"热力集团", open:true},
            { id:11, pId:0, name:"天津大河"},
            { id:12, pId:0, name:"海淀分公司"},
            { id:121, pId:12, name:"双榆树中心"},
            { id:122, pId:12, name:"海淀技术部"},
            { id:123, pId:12, name:"小营热力站"},
            { id:124, pId:12, name:"小营热源厂"}
        ];

        $(document).ready(function(){
            //页面说明
            console.info("页面说明：系统登录用户选择企业或者公司后显示该企业或者公司的组织机构\n" +
                    "功能：\n【添加】【删除】【修改】\n" +
                    "字段：\n组织机构名称、上级组织机构\n" +
                    "以树显示组织机构，所以有隐藏字段：\n创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

        var newCount = 1;
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                return false;
            });
        };
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
        };
        //-->
    </SCRIPT>
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

</body>

</html>
