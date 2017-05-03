<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>计量器具列表</title>
    <script src="${ctx}/script/cos/huak.cos.meter.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;计量器具列表</h5>
                </div>
                <div class="ibox-content">
                    <form id="meter-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row col-lg-12">
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">单位</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="orgName" placeholder="请输入单位名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">设备</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="maeName" placeholder="请输入设备名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-5">能源类型</label>

                                    <div class="col-lg-7">
                                        <select name="typeId" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <c:forEach var="item" items="${energyTypes}">
                                                <option value="${item.type_id}">${item.type_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-5">是否预存</label>

                                    <div class="col-lg-7">
                                        <select name="prestore" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <c:forEach var="item" items="${applicationScope.dicList['prestore']}">
                                                <option value="${item.dicName}">${item.dicDes}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="row col-lg-12 margin-top5 margin-bottom5">
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">编号</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="meterCode" placeholder="请输入器具编号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">名称</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="meterName" placeholder="请输入器具名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label class="control-label col-lg-5">器具类型</label>

                                    <div class="col-lg-7">
                                        <select name="meterType" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <c:forEach var="item" items="${applicationScope.dicList['meterType']}">
                                                <option value="${item.dicName}">${item.dicDes}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['adminMeterAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem"
                                            data-href="${ctx}/meter/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['adminMeterUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editMeter()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['adminMeterDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteMeters()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['adminMeterChange']}">
                                    <button type="button" class="btn btn-sm btn-warning" onclick="userRolePage()">
                                        <i class="fa fa-history"></i>换表
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getMeterList()"> 搜索
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
                            <th>所属单位</th>
                            <th>设备名称</th>
                            <th>能源类型</th>
                            <th>器具编号</th>
                            <th>器具名称</th>
                            <th>器具类型</th>
                            <th>是否预存</th>
                            <th>倍率</th>
                            <th>安装位置</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="meter-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.meter_id}" name="input[]">
        </td>
        <td>{$T.item.org_name}</td>
        <td>{$T.item.mae_name}</td>
        <td>{$T.item.type_name}</td>
        <td>{$T.item.meter_code}</td>
        <td>{$T.item.meter_name}</td>
        <td>{formatId($T.item.meter_type,"${dicDesMap['meterType']}")}</td>
        <td>{formatId($T.item.prestore,"${dicDesMap['prestore']}")}</td>
        <td>{$T.item.rate}</td>
        <td>{$T.item.location}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            {#if $T.item.prestore==1}
            <c:if test="${sessionScope._auth['adminMeterPrestore']}">
                <a class="btn btn-warning btn-xs btn-bitbucket" title="预存"
                   onclick="disable('{$T.item.user_id}',1,this)">
                    <i class="fa fa-credit-card"></i>
                </a>
            </c:if>
            {#/if}
            <c:if test="${sessionScope._auth['adminMeterDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteMeter('{$T.item.meter_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>