<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>字典管理</title>
    <script src="${ctx}/script/sys/huak.sys.dictionary.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;字典管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" id="dictionary-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">编码</label>
                            <div class="form-group col-md-2">
                                <input  name="dicCode" id="dicCode" placeholder="编码"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">名称</label>
                            <div class="form-group col-md-2">
                                <input name="dicDes" id="dicDes" placeholder="名称"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">标识</label>
                            <div class="form-group col-md-2">
                                <input name="dicName" id="dicName" placeholder="标识"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">类型</label>
                            <div class="form-group col-md-2">
                                    <select data-placeholder="请选择字典类型..." name="dicTypeId" class="chosen-select form-control" tabindex="2">
                                        <option value="">请选择字典类型</option>
                                        <c:forEach items="${dictypeList}" var="dic">
                                            <option  value="${dic.dicTypeId}">${dic.dicTypeDes}</option>
                                        </c:forEach>
                                    </select>
                            </div>

                        </tr>
                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['bfanAdd']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="addDictionary()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['bfanUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editDictionary()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['bfanDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger"  onclick="delDictionary()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
                                <button type="reset" class="btn btn-sm btn-success" onclick="reset()"> 重置</button>
                                <%--<c:if test="${sessionScope._auth['bfanExport']}">--%>
                                    <%--<button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/bfan/export" >导出</button>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>编码</th>
                            <th>类型</th>
                            <th>名称</th>
                            <th>标识</th>
                            <th>排序</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="dictionary-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.dicId}" name="input[]">
        </td>
        <td>{$T.item.dicCode}</td>
        <td>
        <%--
            参数1 ：当前后台获取的字典类型id
            参数2 ：获取 类型表map数据 格式：{key = 外键id ，value=显示字段}
        此格式化方法只支持map集合--%>
        {formatType($T.item.dicTypeId,"${dicType}")}
    </td>
        <td>{$T.item.dicDes}</td>
        <td>{$T.item.dicName}</td>
        <td title="{$T.item.seq}">{formatText($T.item.seq,10)}</td>
        <td>
            <a class="updCom btn btn-info btn-xs btn-bitbucket" title="修改" onclick="editDictionary('{$T.item.dicId}')">
                <i class="fa fa-file-text-o"></i>
            </a>
            <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delDictionary('{$T.item.dicId}')">
                <i class="fa fa-trash-o"></i>
            </a>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>