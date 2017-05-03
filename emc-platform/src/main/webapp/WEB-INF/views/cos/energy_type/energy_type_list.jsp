<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>能源类型列表</title>
    <script src="${ctx}/script/cos/huak.cos.energy.type.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;能源类型列表</h5>
                </div>
                <div class="ibox-content">
                    <form id="energy-type-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row col-lg-12 margin-bottom5">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">名称</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="typeName" placeholder="请输入能源类型名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">经济类型</label>

                                    <div class="col-lg-8">
                                        <select name="economicType" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <c:forEach var="item" items="${applicationScope.dicList['economicType']}">
                                                <option value="${item.dicName}">${item.dicDes}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['adminEnergyTypeAdd']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="addEnergyType()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['adminEnergyTypeUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editEnergyType()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['adminEnergyTypeDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteEnergyTypes()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getEnergyTypeList()"> 搜索
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
                            <th>名称</th>
                            <th>计算单位</th>
                            <th>默认单价</th>
                            <th>经济类型</th>
                            <th>排序</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="energy-type-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.type_id}" name="input[]">
        </td>
        <td>{$T.item.type_name}</td>
        <td>{$T.item.cell}</td>
        <td>¥{$T.item.price}元</td>
        <td>{formatId($T.item.economic_type,"${dicDesMap['economicType']}")}
        </td>
        <td>{$T.item.seq}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <c:if test="${sessionScope._auth['adminEnergyTypeDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteEnergyType('{$T.item.type_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>