<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>费用类型管理</title>
    <script src="${ctx}/script/cos/huak.cos.costtype.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;费用类型管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="costtype-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <%--<div class="col-lg-4">--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="control-label col-lg-4">能源类型</label>--%>
                                <%--<div class="col-lg-8">--%>
                                    <%--<select data-placeholder="请选择能源类型..." name="typeId" class="chosen-select form-control" tabindex="2">--%>
                                        <%--<option value="">请选择能源类型</option>--%>
                                        <%--<c:forEach items="${dicList}" var="dic">--%>
                                            <%--<option  value="${dic.type_id}">${dic.type_name}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['costtypeAdd']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="addcosttype()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['costtypeUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editcosttype()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['costtypeDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger"  onclick="delcosttype()">
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
                    <table class="table table-bordered table-striped table-hover" id="costtype-table-list">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>类型名称</th>
                            <th>创建时间</th>
                            <th>排序</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="costtype-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<textarea id="costtype-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.typeId}" name="input[]">
        </td>
        <td>{$T.item.pricename}</td>
        <td>{formatDate($T.item.createTime,'yyyy-MM-dd HH:mm:ss')} </td>
        <td> {$T.item.seq} </td>
        <td> {$T.item.memo} </td>
        <td>
            <c:if test="${sessionScope._auth['costtypeUpdate']}">
                <a class="updCom btn btn-info btn-xs btn-bitbucket" title="修改" onclick="editcosttype('{$T.item.strid}')">
                    <i class="fa fa-file-text-o"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope._auth['costtypeDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delcosttype('{$T.item.strid}')">
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