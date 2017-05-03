<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公司管理</title>
    <script src="${ctx}/script/common/huak.org.common.orglist.js"></script>
    <script type="text/javascript">
        $(function () {
            commonCompanyload();
        });
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <form id="common-org-from">
                <input type="hidden" id="common-typeid" name="typeId" value="${typeId}"/>
                <tr class="row col-md-15">
                    <div class="form-group col-md-2">
                        <input type="text" placeholder="公司名称" id="common-orgname" class="form-control" name="orgname">
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" placeholder="公司编码" id="common-orgcode" class="form-control" name="orgcode">
                    </div>
                </tr>

                <div class="col-lg-4" style="margin-bottom: 8px;">
                    <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
                    <button type="button" class="btn btn-sm btn-success" onclick="setOrgValues()"> 确定</button>
                </div>

            </form>
            <table class="table table-hover table-bordered table-condensed" id="org_common_id">
                <thead>
                <tr style="background-color: #F5F5F6 ;font-weight: bolder;">
                    <td width="1%"></td>
                    <td width="20%">公司名称</td>
                    <td width="10%">公司编码</td>
                    <td width="10%">简称</td>
                    <td width="10%">组织机构类型</td>
                </tr>
                </thead>
                <tbody class="common-huak-org">
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- 全局js -->
<!--统计代码，可删除-->
</body>
</html>
