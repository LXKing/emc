<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色列表</title>
    <script src="${ctx}/script/auth/huak.auth.role.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;系统角色</h5>
                </div>
                <div class="ibox-content">
                    <form id="roles-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="control-label col-lg-2">名称</label>

                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入角色名称">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['authRoleAdd']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="addRole()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authRoleUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editRole()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authRoleDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteRoles()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authRoleAuth']}">
                                    <button type="button" class="btn btn-sm btn-warning" onclick="roleAuthPage()">
                                        <i class="fa fa-wrench"></i>角色授权
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getRoleList()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th width="15%">角色名称</th>
                            <th width="25%">描述</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="role-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>

                </div>
            </div>
        </div>
    </div>
</div>


<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.role_id}" name="input[]">
        </td>
        <td>{$T.item.role_name}</td>
        <td>{$T.item.role_des}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <a class="btn btn-white btn-xs btn-bitbucket" title="查看">
                <i class="fa fa-file-text-o"></i>
            </a>
            <c:if test="${sessionScope._auth['authRoleDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteRole('{$T.item.role_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>