<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>菜单列表</title>
    <script src="${ctx}/script/auth/huak.auth.function.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;系统菜单</h5>
                </div>
                <div class="ibox-content">
                    <form id="func-menus-from">
                        <div class="row col-lg-12 margin-bottom5">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">名称</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="menuName" placeholder="请输入菜单名称">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-6">
                            </div>
                            <div class="col-lg-6 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getFuncMenuList()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th width="70%">菜单名称</th>
                            <th>唯一标识</th>
                        </tr>
                        </thead>
                        <tbody class="tree-huak">
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;系统功能</h5>
                </div>
                <div class="ibox-content" id="function-box" style="display: none;">
                    <form id="function-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" name="menuId" id="menuId">
                    </form>
                    <div class="row row-button btn-group">
                        <c:if test="${sessionScope._auth['authFunctionAdd']}">
                            <button type="button" class="btn btn-sm btn-info addFunction">
                                <i class="fa fa-plus"></i>添加
                            </button>
                        </c:if>
                        <c:if test="${sessionScope._auth['authFunctionUpdate']}">
                            <button type="button" class="btn btn-sm btn-info updFunction">
                                <i class="fa fa-edit"></i>编辑
                            </button>
                        </c:if>
                        <c:if test="${sessionScope._auth['authFunctionDelete']}">
                            <button type="button" class="btn btn-sm btn-danger delFunction">
                                <i class="fa fa-trash-o"></i>删除
                            </button>
                        </c:if>
                    </div>
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th width="10%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th width="25%">功能名称</th>
                            <th width="25%">唯一标识</th>
                            <th width="25%">备注</th>
                            <th width="15%">排序</th>
                        </tr>
                        </thead>
                        <tbody id="functionTBody">
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>


<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.list as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.func_id}" name="input[]">
        </td>
        <td>{$T.item.func_name}</td>
        <td>{$T.item.uname}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>{$T.item.seq}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>