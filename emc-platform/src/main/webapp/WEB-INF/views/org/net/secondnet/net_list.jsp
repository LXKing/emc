<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>二次网管理</title>
    <script src="${ctx}/script/org/huak.org.townet.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;二次网管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="secondnet-search-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1"/>
                        <input type="hidden"  name="typeId" value="${typeId}">
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">名称</label>
                            <div class="form-group col-md-2">
                                <input placeholder="名称" name="orgName"  class="form-control">
                            </div>

                            <label class="control-label col-md-1">管线类型</label>
                            <div class="form-group col-md-2">
                                <select data-placeholder="请选择机构类型..." id="netTypeId" name="netTypeId"
                                        class="chosen-select form-control" tabindex="2" >
                                    <option value="">无机构类型</option>
                                    <c:forEach items="${dicList['netType']}" var="type">
                                        <option value="${type.dicName}">${type.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label col-md-1">所属机构</label>
                            <div class="form-group col-md-2">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="pOrgId" value=""  type="hidden"/>
                                    <input  type="text" placeholder="所属机构" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>

                        </tr>
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">编码</label>
                            <div class="form-group col-md-2">
                                <input placeholder="编码" name="orgCode"  class="form-control">
                            </div>
                        </tr>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['bfanAdd']}">
                                    <button type="button" class="btn btn-sm btn-inf" onclick="addSecondnet()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['bfanUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editSecondnet()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="searchSecondnetList()"> 搜索</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="resetSearchForm()"> 重置</button>
                                <%--<c:if test="${sessionScope._auth['bfanExport']}">--%>
                                <%--<button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/bfan/export" >导出</button>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered table-striped table-hover" id="secondnet-list-table">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>编码</th>
                            <th>名称</th>
                            <th>类型</th>
                            <th>长度</th>
                            <th>上级单位</th>
                            <th>输送介质</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="secondnet-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="layer-div" style="display: none"></div>
<div id="secondnet-add-div" style="display: none"></div>
<textarea id="secondnet-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox"class="i-checks" value="{$T.item.orgId}" name="input[]">
        </td>
        <td>{$T.item.orgCode}</td>
        <td>{$T.item.orgName}</td>
        <td>{formatId($T.item.netTypeId,"${dicDesMap['netType']}")}</td>
        <td>{$T.item.length}</td>
        <%--
              参数1 ：当前后台获取字典标识
              参数2 ：获取外键表中map数据 格式：{key = 字典标识 ，value=显示字段}
          此格式化方法只支持map集合--%>
        <td> {formatId($T.item.pOrgId,"${org}")}</td>
        <td> {$T.item.medium}</td>
        <td >{$T.item.memo}</td>
        <td>
            <a class="btn btn-white btn-xs btn-bitbucket" title="修改" onclick="editSecondnet('{$T.item.orgId}')">
                <i class="fa fa-file-text-o"></i>
            </a>
            <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delSecondnet('{$T.item.orgId}')">
                <i class="fa fa-trash-o"></i>
            </a>
        </td>
    </tr>
    {#/for}
</textarea>
</body>

</html>
