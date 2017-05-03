<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>二次网管理</title>
    <script src="${ctx}/script/common/huak.org.common.secondnetlist.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" id="secondnet-commonsearch-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="secondnet_pageNo" name="secondnet_pageNo" value="1"/>
                        <tr class="row col-md-15">
                            <div class="form-group col-md-3">
                                <input placeholder="名称" name="secondnetName"  class="form-control">
                            </div>

                            <div class="form-group col-md-3">
                                <select data-placeholder="请选择管线类型..." id="netTypeId" name="netTypeId"
                                        class="chosen-select form-control" tabindex="2" >
                                    <option value="">请选择管线类型</option>
                                    <c:forEach items="${dicList['netType']}" var="type">
                                        <option value="${type.dicName}">${type.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </tr>
                        <tr class="row col-md-15">

                            <div class="form-group col-md-3">
                                <button type="button" class="btn btn-sm btn-primary" onclick="searchCommonSecondnetList()"> 搜索</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="setSecondnetValues()">确定</button>
                            </div>
                        </tr>
                    </form>
                    <table class="table table-bordered table-striped table-hover" id="secondnet-common-list">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>名称</th>
                            <th>类型</th>
                            <th>所属机构</th>
                        </tr>
                        </thead>
                        <tbody id="secondnet-common-tbody"></tbody>
                    </table>
                    <div class="form-group secondnet_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="secondnet-common-datalist" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox"  class="i-checks" value="{$T.item.secondnetId}" name="input[]">
        </td>
        <td id="name">{$T.item.secondnetName}</td>
        <td>{formatId($T.item.netTypeId,"${dicDesMap['netType']}")}</td>
        <td> {formatId($T.item.orgId,"${org}")}</td>
    </tr>
    {#/for}
</textarea>
</body>

</html>
