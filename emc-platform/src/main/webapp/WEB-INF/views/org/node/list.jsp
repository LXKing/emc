<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <style type="text/css">

       .org-tree{
           overflow-X:scroll;

       }
    </style>
    <script type="application/javascript">
        function search(){
            $table.bootstrapTable('refresh');
        }
    </script>
    <script src="${platform}/script/org/huak.org.node.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins" >
                    <div class="ibox-content " >组织机构
                        <div class="org-tree" ></div>
                    </div>
            </div>
        </div>

        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="roles-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">站名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" id="orgName" name="orgName" placeholder="请输入站名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">创建者</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" id="creator" name="creator" placeholder="请输入创建者">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">管理类型</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <%--<select name="useStatus" class="chosen-select form-control">--%>
                                            <%--<option value="">全部</option>--%>
                                            <%--<option value="0">启用</option>--%>
                                            <%--<option value="1">禁用</option>--%>
                                        <%--</select>--%>

                                        <select id="manageTypeId" name="manageTypeId" class="chosen-select form-control"  >
                                            <option value="">请选择管理类型</option>
                                            <c:forEach items="${sysDic['managetype']}" var="type">
                                                <option <c:if test="${node.manageTypeId eq type.id}">selected="selected" </c:if> value="${type.id}">${type.des}</option>
                                            </c:forEach>
                                        </select>


                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">

                            <%--<div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">创建时间</label>--%>
                                    <%--<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">--%>
                                        <%--<input id="start" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">--%>
                                        <%--<input id="end" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                        <div class="row">
                            <c:if test = "${sessionScope._auth['nodeAdd']}">
                                <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                    <button type="button"  onclick="addStation()" class="btn btn-sm btn-info "><i class="fa fa-plus"></i>添加</button>
                                </div>
                            </c:if>

                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="getRoleList()"> 导出Excel</button>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="station-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

