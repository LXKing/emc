<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>软化水设备</title>
    <script src="${ctx}/script/mae/huak.mae.chy.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;软化水设备</h5>
                </div>
                <div class="ibox-content">
                    <form id="chy-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row col-lg-12">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">设备名称</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="maeName" placeholder="请输入设备名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">设备编号</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="maeNum" placeholder="请输入设备编号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">设备型号</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="maeNumType" placeholder="请输入设备型号">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-lg-12 margin-top5 margin-bottom5">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">单位</label>

                                    <div class="col-lg-8">
                                        <div class="input-group">
                                            <input  name="orgId"   type="hidden"/>
                                            <input type="text" name="orgName"  id="common-part" placeholder="所属机构" disabled   class="form-control" />
                                            <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['chyAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem" data-href="${ctx}/chy/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['chyUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editChy()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['chyDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteChys()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getChyList()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                                <c:if test="${sessionScope._auth['chyExport']}">
                                <button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/chy/export" >导出</button>
                                </c:if>
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
                            <th>编号</th>
                            <th>型号</th>
                            <th>单位(类型)</th>
                            <th>能效</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="chy-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.mae_id}" name="input[]">
        </td>
        <td>{$T.item.mae_name}</td>
        <td>{$T.item.mae_num}</td>
        <td>{$T.item.mae_type_num}</td>
        <td>{$T.item.org_name}({formatOrgType($T.item.type_id)})</td>
        <td>{$T.item.eers_min}%-{$T.item.eers_max}%</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <c:if test="${sessionScope._auth['chyDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteChy('{$T.item.mae_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>