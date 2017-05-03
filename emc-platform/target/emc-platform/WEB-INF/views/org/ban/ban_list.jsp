<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>楼栋管理</title>
    <script src="${ctx}/script/org/huak.org.ban.list.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;楼栋管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="ban-search-form" >
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="hidden" id="pageNo" name="pageNo" value="1"/>
                        <input type="hidden"  name="typeId" value="${typeId}"/>
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">楼栋名称</label>
                            <div class="form-group col-md-2">
                                <input name="orgName" id="orgName" placeholder="楼栋名称"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">楼栋编号</label>
                            <div class="form-group col-md-2">
                                <input name="orgCode" id="orgCode" placeholder="楼栋编号"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">所属机构</label>
                            <div class="form-group col-md-2">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="orgId" type="hidden"/>
                                    <input  type="text" placeholder="所属机构" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>

                            <label class="control-label col-md-1">管理类型</label>
                            <div class="form-group col-md-2">
                                <select id="searchManageTypeId" name="manageTypeId" class="chosen-select form-control" >
                                    <option value="">请选择管理类型</option>
                                    <c:forEach items="${dicList['managetype']}" var="type">
                                        <option value="${type.dicName}">${type.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%--<button type="button" class="btn btn-sm btn-primary" onclick="searchBan()"> 搜索</button>--%>
                            <%--<button type="button" class="btn btn-sm btn-primary" onclick="resetBanSearch()"> 重置</button>--%>
                        </tr>

                        <%--<tr class="row col-md-15">--%>
                            <%--<div class="form-group col-md-8">--%>
                                <%--<button type="button" class="btn btn-sm btn-info J_menuItem"  data-href="${ctx}/ban/add">--%>
                                    <%--<i class="fa fa-plus"></i>添加--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-sm btn-danger"  onclick="delBan()">--%>
                                    <%--<i class="fa fa-trash-o"></i>删除--%>
                                <%--</button>--%>
                            <%--</div>--%>
                        <%--</tr>--%>



                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['bfanAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem" data-href="${ctx}/ban/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['bfanDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="delBan()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="searchBan()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success" onclick="resetBanSearch()"> 重置</button>
                                <%--<c:if test="${sessionScope._auth['bfanExport']}">--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/bfan/export" >导出</button>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>


                    </form>
                    <table class="table table-bordered table-striped table-hover" id="banListTable">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>楼栋名称</th>
                            <th>编号</th>
                            <th>上级单位</th>
                            <th>公建面积</th>
                            <th>居民面积</th>
                            <th>详细地址</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="ban-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<textarea id="ban-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="check-all i-checks" value="{$T.item.orgId}" name="input[]">
        </td>
        <td>{$T.item.orgName}</td>
        <td>{$T.item.orgCode}</td>
        <td> {formatId($T.item.pOrgId,"${org}")}</td>
        <td>{$T.item.publicArea}</td>
        <td>{$T.item.dwellArea}</td>
        <td>{$T.item.addr}</td>
        <td>
            <a class="btn btn-info btn-xs btn-bitbucket J_menuItem"  data-href="${ctx}/ban/edit/{$T.item.orgId}" title="修改">
                <i class="fa fa-file-text-o"></i>
            </a>
            <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delBan('{$T.item.orgId}')">
                <i class="fa fa-trash-o"></i>
            </a>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>