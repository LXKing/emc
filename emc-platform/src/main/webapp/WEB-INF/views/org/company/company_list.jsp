<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公司管理</title>
    <script src="${ctx}/script/org/huak.org.company.treelist.js"></script>
</head>

<body class="gray-bg"  >
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>公司列表</h5>
        </div>
        <div class="ibox-content">
                <form id="company-from">
                    <input type="hidden" name="_method" value="PATCH">
                    <input type="hidden" id="pageNo" name="pageNo" value="1">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="control-label col-lg-3">公司名称</label>
                                <div class="col-lg-6">
                                    <input type="text" id="orgname" class="form-control" name="orgname" >
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="control-label col-lg-4">公司编码</label>
                                <div class="col-lg-6">
                                    <input type="text" id="orgcode" class="form-control" name="orgcode" >
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4" style="margin-bottom: 8px;">
                            <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
                            <button type="reset" class="btn btn-sm btn-success" > 重置</button>
                        </div>

                    </div>
                </form>
                <c:if test="${ empty  list}">
                    <div class="row row-button btn-group">
                        <button type="button" class="btn btn-sm btn-info" onclick="add(1)">
                            <i class="fa fa-plus"></i>添加
                        </button>
                    </div>
                </c:if>


            <table class="table table-hover table-bordered table-condensed">
                <thead>
                <tr style="background-color: #F5F5F6 ;font-weight: bolder;">
                    <td >公司名称</td>
                    <td >公司代码</td>
                    <td >简称</td>
                    <td >公司性质</td>
                    <td >组织机构类型</td>
                    <td >操作</td>
                </tr>
                </thead>
                <tbody class="company-tree" >
                </tbody>
            </table>
        </div>


    </div>
</div>
<div id="layer-div" style="display: none">
    <input id="layerId" type="hidden"/>
</div>

<%-- treegrid的按钮，支持权限控制 --%>
<table style="display: none">
    <tr id="company-tree-btn">
        <td class="tree-copy">
            <%--<c:if test="${sessionScope._auth['authMenuAdd']}">--%>
                <a class=" addCom btn btn-info btn-xs btn-bitbucket" title="添加">
                    <i class="fa fa-plus"></i>
                </a>
            <%--</c:if>--%>
            <%--<c:if test="${sessionScope._auth['authMenuUpdate']}">--%>
                <a class="updCom btn btn-info btn-xs btn-bitbucket" title="修改">
                    <i class="fa  fa-edit"></i>
                </a>
            <%--</c:if>--%>
            <%--<c:if test="${sessionScope._auth['authMenuDelete']}">--%>
                <a class="delCom btn btn-danger btn-xs btn-bitbucket" title="删除">
                    <i class="fa fa-trash-o"></i>
                </a>
            <%--</c:if>--%>
        </td>
    </tr>
</table>
<!-- 全局js -->

<!--统计代码，可删除-->

</body>

</html>
