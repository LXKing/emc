<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>小室管理</title>
    <script src="${ctx}/script/org/huak.org.cell.list.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;小室管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="cell-search-form" >
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="hidden" id="pageNo" name="pageNo" value="1"/>
                        <input type="hidden"  name="typeId" value="${typeId}"/>
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">小室名称</label>
                            <div class="form-group col-md-2">
                                <input name="orgName" id="orgName" placeholder="小室名称"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">上级单位</label>
                            <div class="form-group col-md-2">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="pOrgId" type="hidden"/>
                                    <input  type="text" placeholder="所属单位" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>

                        </tr>

                        <%--<tr class="row col-md-15">--%>
                            <%--<div class="form-group col-md-8">--%>
                                <%--<button type="button" class="btn btn-sm btn-info J_menuItem"  data-href="${ctx}/cell/add">--%>
                                    <%--<i class="fa fa-plus"></i>添加--%>
                                <%--</button>--%>
                                <%--<%--<button type="button" class="btn btn-sm btn-danger"  onclick="delcell()">--%>
                                    <%--<%--<i class="fa fa-trash-o"></i>删除--%>
                                <%--<%--</button>--%>
                            <%--</div>--%>
                        <%--</tr>--%>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['bfanAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem" data-href="${ctx}/cell/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>

                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="searchcell()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success" onclick="resetcellSearch()"> 重置</button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered table-striped table-hover" id="cellListTable">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>小室名称</th>
                            <th>所属楼栋</th>
                            <th>沟底高程</th>
                            <th>地面高程</th>
                            <th>创建时间</th>
                            <th>小室类型</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="cell-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="cell-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.orgId}" name="input[]">
        </td>
        <td>{$T.item.orgName}</td>
        <td> {formatId($T.item.pOrgId,"${org}")}</td>
        <td> {$T.item.trenchHeight}</td>
        <td> {$T.item.groundHeight}</td>
        <td>{formatDate($T.item.createTime,'yyyy-MM-dd HH:mm:ss')} </td>
        <td>{formatId($T.item.cellTypeId,"${dicDesMap['celltype']}")}</td>
        <td>
            <a class="btn btn-info btn-xs btn-bitbucket J_menuItem"  data-href="${ctx}/cell/edit/{$T.item.orgId}" title="修改">
                <i class="fa fa-file-text-o"></i>
            </a>
            <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delcell('{$T.item.orgId}')">
                <i class="fa fa-trash-o"></i>
            </a>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>