<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>单元管理</title>
    <script src="${ctx}/script/common/huak.org.common.unitlist.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">

                <div class="ibox-content">
                    <form role="form" id="unit-commonsearch-form" >
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="hidden" id="unit_pageNo" name="unit_pageNo" value="1"/>
                        <tr class="row col-md-15">
                            <div class="form-group col-md-3">
                                <input name="unitName" id="unitName"  placeholder="单元名称"  class="form-control">
                            </div>
                            <div class="form-group col-md-3">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="banId" type="hidden"/>
                                    <input  type="text" placeholder="所属楼栋"  readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon"><a class="common-ban" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                            <div class="form-group col-md-4" >
                            <button type="button" class="btn btn-sm btn-primary" onclick="searchCommonList()"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-primary" onclick="setUnitValues()"> 确定</button>
                                </div>
                        </tr>
                    </form>
                    <table class="table table-bordered table-striped table-hover" id="unit-common-Table">
                        <thead>
                        <tr>
                            <th width="70px">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>单元名称</th>
                            <th>所属楼栋</th>
                            <th>公建面积</th>
                            <th>居民面积</th>
                        </tr>
                        </thead>
                        <tbody id="unit-common-tbody"></tbody>
                    </table>
                    <div class="form-group unit_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="unit-common-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="check-all i-checks" value="{$T.item.unitId}" name="input[]">
        </td>
        <td id="name">{$T.item.unitName}</td>
        <td> {formatId($T.item.banId,"${ban}")}</td>
        <td> {$T.item.publicArea}</td>
        <td> {$T.item.dwellArea}</td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>