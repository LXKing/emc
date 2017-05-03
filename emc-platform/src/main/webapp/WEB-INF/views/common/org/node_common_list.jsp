<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>热力站管理</title>
    <script src="${ctx}/script/common/huak.org.common.nodelist.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" id="common-nodeSearch-form" >
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="hidden" id="node_pageNo" name="node_pageNo" value="1"/>
                        <tr class="row col-md-15">
                            <div class="form-group col-md-2">
                                <input name="stationName" id="stationName" placeholder="热力站名称"  class="form-control">
                            </div>
                            <div class="form-group col-md-2">
                                <input name="stationCode" id="stationCode" placeholder="热力站编号"  class="form-control">
                            </div>
                            <div class="form-group col-md-2">
                                <select id="searchManageTypeId" name="manageTypeId" class="chosen-select form-control" >
                                    <option value="">请选择管理类型</option>
                                    <c:forEach items="${dicList['managetype']}" var="type">
                                        <option value="${type.dicName}">${type.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="button" class="btn btn-sm btn-primary" onclick="searchNode()"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-primary" onclick="setValues()"> 确认</button>
                        </tr>
                    </form>
                    <table class="table table-bordered table-striped table-hover" id="common-station-list">
                        <thead>
                        <tr>
                            <th width="10%">
                                <input id="check-all" type="checkbox" class="i-checks" name="input[]">全选
                            </th>
                            <th>热力站名称</th>
                            <th>单位</th>
                            <th>热源</th>
                            <th>一次网</th>
                        </tr>
                        </thead>
                        <tbody id="common-station-tbody"></tbody>
                    </table>
                    <div class="form-group node_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="common-station-datalist" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="check-all i-checks" value="{$T.item.stationId}" name="input[]">
        </td>
        <td id="name">{$T.item.stationName}</td>
        <td> {formatId($T.item.orgId,"${org}")}</td>
        <td> {formatId($T.item.feedId,"${feed}")}</td>
        <td> {formatId($T.item.oncenetId,"${oncenet}")}</td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
<div id="node-layer-div" ></div>
</html>