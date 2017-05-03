<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>菜单列表</title>
    <script src="${ctx}/script/auth/huak.auth.menu.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;系统菜单</h5>
                </div>
                <div class="ibox-content">
                    <form id="menus-from">
                        <div class="row col-lg-12 margin-bottom5">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">菜单名称</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="menuName" placeholder="请输入菜单名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">唯一标识</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="uName" placeholder="请输入唯一标识">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">前后台</label>

                                    <div class="col-lg-8">
                                        <select name="isOwer" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">公共</option>
                                            <option value="1">前台</option>
                                            <option value="2">后台</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8">
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getMenuList()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th width="25%">菜单名称</th>
                            <th width="17%">菜单链接</th>
                            <th width="10%">唯一标识</th>
                            <th width="8%">图标样式</th>
                            <th width="8%">前后台</th>
                            <th width="5%">排序</th>
                            <th width="15%">备注</th>
                            <th width="12%">操作</th>
                        </tr>
                        </thead>
                        <tbody class="tree-huak">
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
</div>


<div id="layer-div" style="display: none"></div>
<%-- treegrid的按钮，支持权限控制 --%>
<table style="display: none">
    <tr id="tree-btn">
        <td class="tree-copy">
            <c:if test="${sessionScope._auth['authMenuAdd']}">
                <a class=" addMenu btn btn-info btn-xs btn-bitbucket" title="添加">
                    <i class="fa fa-plus"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope._auth['authMenuUpdate']}">
                <a class="updMenu btn btn-info btn-xs btn-bitbucket" title="修改">
                    <i class="fa  fa-edit"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope._auth['authMenuDelete']}">
                <a class="delMenu btn btn-danger btn-xs btn-bitbucket" title="删除">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>