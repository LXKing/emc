<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>一次网信息</title>
    <script src="${ctx}/script/common/huak.org.common.oncenetlist.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                        <form role="form" id="common-search-oncenetform" >
                            <input type="hidden" name="_method" value="PATCH"/>
                            <input type="hidden" id="oncenet_pageNo" name="oncenet_pageNo" value="1"/>
                            <tr class="row col-md-15">
                                <div class="form-group col-md-3">
                                    <input placeholder="名称" name="oncenetName"  class="form-control">
                                </div>
                                <div class="form-group col-md-3">
                                    <select data-placeholder="请选择管线类型..." id="netTypeId" name="netTypeId"
                                            class="chosen-select form-control" tabindex="2" >
                                        <option value="">无管线类型</option>
                                        <c:forEach items="${dicList['netType']}" var="type">
                                            <option value="${type.dicName}">${type.dicDes}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <input placeholder="编码" name="oncenetCode"  class="form-control">
                                </div>
                                <div class="form-group col-md-3">
                                    <button type="button" class="btn btn-sm btn-primary" onclick="searchNetList()"> 搜索</button>
                                    <button type="button" class="btn btn-sm btn-success" onclick="setValues()"> 确定</button>
                                </div>
                            </tr>
                        </form>
                    <table class="table table-bordered table-striped table-hover" id="common-oncenet-list">
                        <thead>
                        <tr>
                            <th width="10%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>编码</th>
                            <th>名称</th>
                            <th>类型</th>
                        </tr>
                        </thead>
                        <tbody id="common-oncenet-tbody"></tbody>
                    </table>
                    <div class="form-group oncenet_pagination"></div>
            </div>
        </div>
    </div>
</div>

<div id="layer-div" style="display: none"></div>
<div id="layer-child-div" style="display: none"></div>
<textarea id="common-oncenet-datalist" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.oncenetId}" name="input[]">
        </td>
        <td>{$T.item.oncenetCode}</td>
        <td id="name">{$T.item.oncenetName}</td>
        <td>{formatId($T.item.netTypeId,"${dicDesMap['netType']}")}</td>
    </tr>
    {#/for}
</textarea>
</body>

</html>
