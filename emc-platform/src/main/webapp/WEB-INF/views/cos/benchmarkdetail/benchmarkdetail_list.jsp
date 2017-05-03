<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>对标明细管理</title>
    <script src="${ctx}/script/sys/huak.sys.benchmarkdetail.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;对标明细管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="benchmarkdetail-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="col-lg-4">
                            <div class="form-group">
                                <label class="control-label col-lg-4">能源类型</label>
                                <div class="col-lg-8">
                                    <select data-placeholder="请选择能源类型..." name="typeId" class="chosen-select form-control" tabindex="2">
                                        <option value="">请选择能源类型</option>
                                        <c:forEach items="${dicList}" var="dic">
                                            <option  value="${dic.type_id}">${dic.type_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['benchmarkdetailAdd']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="addbenchmarkdetail()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['benchmarkdetailUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editbenchmarkdetail()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['benchmarkdetailDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger"  onclick="delbenchmarkdetail()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="searchFormulaList()"> 搜索</button>
                                <button type="reset" class="btn btn-sm btn-success" onclick="reset()"> 重置</button>
                                <%--<c:if test="${sessionScope._auth['bfanExport']}">--%>
                                <%--<button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/bfan/export" >导出</button>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered table-striped table-hover" id="benchmarkdetail-table-list">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>类型</th>
                            <th>行业标准</th>
                            <th>国际标准</th>
                            <th>企业标准</th>
                            <th>报警阈值</th>
                            <th>标准单位</th>
                            <th>组织机构</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="benchmarkdetail-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<textarea id="benchmarkdetail-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.did}" name="input[]">
        </td>
        <td>{$T.item.typeName}</td>
        <td>{$T.item.tradeTarget}</td>
        <td>{$T.item.interTarget}</td>
        <td> {$T.item.compTarget} </td>
        <td> {$T.item.alarmValue} </td>
        <td> {$T.item.cell} </td>
        <td> {$T.item.orgName} </td>
        <td>{$T.item.sTime} </td>
        <td>{$T.item.eTime} </td>
        <td>
            <c:if test="${sessionScope._auth['benchmarkdetailUpdate']}">
                <a class="updCom btn btn-info btn-xs btn-bitbucket" title="修改" onclick="editbenchmarkdetail('{$T.item.strid}')">
                    <i class="fa fa-file-text-o"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope._auth['benchmarkdetailDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delbenchmarkdetail('{$T.item.strid}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>