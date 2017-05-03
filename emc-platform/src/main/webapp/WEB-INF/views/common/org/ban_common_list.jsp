<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>楼栋管理</title>
    <script src="${ctx}/script/common/huak.org.common.banlist.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" id="ban-common-form" >
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="hidden" id="ban_pageNo" name="ban_pageNo" value="1"/>
                        <tr class="row col-md-15">
                            <label class="control-label col-md-2">楼栋名称</label>
                            <div class="form-group col-md-2">
                                <input name="stationName" id="stationName" placeholder="楼栋名称"  class="form-control">
                            </div>
                            <label class="control-label col-md-2">楼栋编号</label>
                            <div class="form-group col-md-2">
                                <input name="stationCode" id="stationCode" placeholder="楼栋编号"  class="form-control">
                            </div>
                            <button type="button" class="btn btn-sm btn-primary" onclick="searchBan()"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-primary" onclick="setBanValue()">确定</button>
                        </tr>
                    </form>
                    <table class="table table-bordered table-striped table-hover" id="ban-common-list">
                        <thead>
                        <tr>
                            <th width="70px">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>楼栋名称</th>
                            <th>单位</th>
                            <th>热源</th>
                            <th>一次网</th>
                            <th>热力站</th>
                            <th>二次网</th>
                        </tr>
                        </thead>
                        <tbody id="ban-common-tbody"></tbody>
                    </table>
                    <div class="form-group ban_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="ban-common-datalist" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="check-all i-checks" value="{$T.item.banId}" name="input[]">
        </td>
        <td id="name">{$T.item.banName}</td>
        <td> {formatId($T.item.orgId,"${org}")}</td>
        <td> {formatId($T.item.feedId,"${feed}")}</td>
        <td> {formatId($T.item.oncenetId,"${oncenet}")}</td>
        <td> {formatId($T.item.stationId,"${node}")}</td>
        <td> {formatId($T.item.secondnetId,"${secondnet}")}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>