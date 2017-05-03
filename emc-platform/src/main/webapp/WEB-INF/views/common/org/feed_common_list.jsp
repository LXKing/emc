<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>热源管理</title>
    <script src="${ctx}/script/common/huak.org.common.feedlist.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" id="feed-common-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="feed_pageNo" name="feed_pageNo" value="1">
                        <input name="orgId" id="orgId" type="hidden"/>
                        <tr class="row col-md-15">

                            <div class="form-group col-md-3">
                                <input name="feedName" id="feedName" placeholder="热源名称"  class="form-control">
                            </div>
                            <button type="button" class="btn btn-sm btn-primary" onclick="findAll()"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-success" onclick="setValues()"> 确定</button>
                        </tr>
                    </form>
                    <table class="table table-bordered table-striped table-hover" id="common-feed-list">
                        <thead>
                        <tr>
                            <th width="10%">
                                <input id="check-all" type="checkbox" class="i-checks" name="input[]">全选
                            </th>
                            <th width="30%" >热源名称</th>
                            <th width="30%" >所属组织机构</th>
                            <th width="30%">简称</th>
                        </tr>
                        </thead>
                        <tbody id="feed-common-tbody" ></tbody>
                    </table>
                    <div class="form-group feed_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<textarea id="common-feed-datalist" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="check-all i-checks" value="{$T.item.feedId}" name="input[]">
        </td>
        <td id="name">{$T.item.feedName}</td>
        <td > {formatId($T.item.orgId,"${org}")}</td>
        <td  >{$T.item.shortName}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>