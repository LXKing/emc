<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/5/16
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script>
        $(function () {


            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green'
            });

            $('.i-checks').on("ifChecked", function () {
                $(this).parent().parent().parent().find(".search-checks").iCheck('check');
                $(this).parents('.m-b-sm').find('.btn-text-align').find(".search-checks").iCheck('check');
            });

            $('.search-checks').on("ifUnchecked", function () {
                $(this).parent().parent().parent().find(".i-checks").iCheck('uncheck');
            });
        });
        /*保存权限*/
        function save() {
            var funcIds = getCheckValues();
            console.info(funcIds);
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/role/auth',
                data: {"roleId": $("#roleId").val(), "funcIds": funcIds},
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        layer.closeAll();
                        //$("#content-main").empty().load(ctx+'/role/list');
                        layer.msg(result.msg);
                    } else {
                        layer.close(index);
                        layer.msg(result.msg);
                    }
                }
            });
        }

        /*重置*/
        function authReset() {
            var id = $("#roleId").val();
            $("#content-main").empty().load(ctx + '/role/auth?id=' + id);
        }
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <form class="form-horizontal" id="roleAuthFrom" role="form">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>&nbsp;角色授权<span style="color: #ff0000">（${role.role_name}）</span></h5>
                        <input type="hidden" id="roleId" value="${role.role_id}" name="roleId">
                    </div>
                    <c:forEach var="menu" items="${menus}">
                        <div class="ibox-content m-b-sm">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="ibox float-e-margins">
                                        <div class="col-sm-6">
                                            <h5>&nbsp;${menu.menu_name}
                                                <c:choose>
                                                    <c:when test="${menu.isower eq 0}"><span style="color: #6AC783">（公共）</span></c:when>
                                                    <c:when test="${menu.isower eq 1}"><span style="color: #76D7FF">（前台）</span></c:when>
                                                    <c:when test="${menu.isower eq 2}"><span style="color: rgb(255, 156, 29)">（后台）</span></c:when>
                                                </c:choose>
                                            </h5>
                                        </div>
                                        <div class="col-sm-6 btn-text-align">
                                            <c:forEach var="function" items="${menu.functions}">
                                                <input class="i-checks <c:if test="${function.seq eq 1}">search-checks</c:if>"
                                                <c:forEach var="ra" items="${auth}">
                                                <c:if test="${ra.func_id eq function.func_id}">
                                                       checked
                                                </c:if>
                                                </c:forEach>
                                                       value="${function.func_id}" type="checkbox">${function.func_name}

                                            </c:forEach>
                                        </div>
                                    </div>
                                    <c:forEach var="cmenu" items="${menu.children}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.first}">
                                                <div class="ibox-content">
                                                <div class="row">
                                            </c:when>
                                            <c:when test="${status.index mod 3 eq 0&&!status.first}">
                                                </div>
                                                <div class="row">
                                            </c:when>
                                        </c:choose>
                                        <div class="col-sm-4">
                                            <div class="ibox float-e-margins">
                                                <div class="col-sm-12">
                                                    <h5>&nbsp;${cmenu.menu_name}
                                                        <c:choose>
                                                            <c:when test="${cmenu.isower eq 0}"><span style="color: #6AC783">（公共）</span></c:when>
                                                            <c:when test="${cmenu.isower eq 1}"><span style="color: #76D7FF">（前台）</span></c:when>
                                                            <c:when test="${cmenu.isower eq 2}"><span style="color: rgb(255, 156, 29)">（后台）</span></c:when>
                                                        </c:choose>
                                                    </h5>
                                                </div>
                                            </div>
                                            <div class="ibox-content">
                                                <c:forEach var="function" items="${cmenu.functions}">
                                                    <div class="col-sm-6">
                                                        <input class="i-checks <c:if test="${function.seq eq 1}">search-checks</c:if>"
                                                        <c:forEach var="ra" items="${auth}">
                                                        <c:if test="${ra.func_id eq function.func_id}">
                                                               checked
                                                        </c:if>
                                                        </c:forEach>
                                                               value="${function.func_id}"
                                                               type="checkbox">${function.func_name}
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <c:if test="${status.last}">
                                            </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="row">

                        <div class="col-sm-12" style="text-align: center">
                            <button type="button" class="btn btn-sm btn-success" onclick="save()">
                                <i class="fa fa-check-square-o"></i>保存
                            </button>
                            <button type="button" class="btn btn-sm btn-warning" onclick="authReset()">
                                <i class="fa fa-retweet"></i>重置
                            </button>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>