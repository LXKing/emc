<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>行政区划管理</title>
    <script type="text/javascript">
        $(function(){
            new PCAS('province','${province}','','city','${city}','','county','${county}','','town','${town}','','village','');
        });
    </script>
    <script src="${ctx}/script/sys/huak.sys.administrative.list.js"></script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;行政区划管理</h5>
                </div>

                <div class="ibox-content">
                    <form role="form" id="administrative-form" >
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                        <input name="admLevle" value="" class="form-control" type="hidden">
                        <input name="pCode" class="form-control" type="hidden" >
                        <tr class="row col-md-15">
                            <label class="control-label col-md-1">省</label>
                            <div class="form-group col-md-2">
                                    <select id="province" name="province" class="chosen-select form-control" onchange="addMessage()">
                                        <option value="">请选择省份</option>
                                    </select>
                            </div>
                            <label class="control-label col-md-1">市</label>
                            <div class="form-group col-md-2">
                                <select id="city" name="city" class="chosen-select form-control" onchange="addMessage()" >
                                    <option value="">请选择市</option>
                                </select>
                            </div>
                            <label class="control-label col-md-1">县</label>
                            <div class="form-group col-md-2">
                                <select id="county" name="county" class="chosen-select form-control" onchange="addMessage()">
                                    <option value="">请选择县</option>
                                </select>
                            </div>
                            <label class="control-label col-md-1">乡</label>
                            <div class="form-group col-md-2">
                                <select id="town" name="town" class="chosen-select form-control" onchange="addMessage()">
                                    <option value="">请选择镇(乡)</option>
                                </select>
                            </div>

                            <label class="control-label col-md-1">代码</label>
                            <div class="form-group col-md-2">
                                <input  name="admCode" id="dicTypeCode" placeholder="代码"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">名称</label>
                            <div class="form-group col-md-2">
                                <input name="admName" id="dicTypeDes" placeholder="名称"  class="form-control">
                            </div>
                            <label class="control-label col-md-1">级别</label>
                            <div class="form-group col-md-2">
                                <select data-placeholder="请选级别..." name="admLevel" class="chosen-select form-control" tabindex="2">
                                    <option value="">请选级别</option>
                                    <option  value="1">1</option>
                                    <option  value="2">2</option>
                                    <option  value="3">3</option>
                                    <option  value="4">4</option>
                                    <option  value="5">5</option>
                                </select>
                            </div>
                        </tr>
                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['bfanAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem"  data-href="${ctx}/administrative/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['bfanUpdate']}">
                                    <button type="button" class="btn btn-sm btn-danger"  onclick="delDictionary()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
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
                            <th>行政名称</th>
                            <th>直属上级</th>
                            <th>级别</th>
                            <th>经度</th>
                            <th>纬度</th>
                            <th>城乡分类</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="administrative-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.admCode}" name="input[]">
        </td>
        <td>{$T.item.admName}</td>
        <td>{$T.item.pCode}</td>
        <td>{$T.item.admLevel}</td>
        <td>{$T.item.lng}</td>
        <td>{$T.item.lat}</td>
        <td>
            <%--
                参数1 ：当前后台获取字典标识
                参数2 ：获取外键表中map数据 格式：{key = 字典标识 ，value=显示字段}
            此格式化方法只支持map集合--%>
            {formatId($T.item.admType,"${dicDes}")}
        </td>
        <td>
            <a class="btn btn-info btn-xs btn-bitbucket J_menuItem"  data-href="${ctx}/administrative/edit/{$T.item.admCode}/{$T.item.admLevel}" title="修改">
                <i class="fa fa-file-text-o"></i>
            </a>
            <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="delDictionary('{$T.item.admCode}')">
                <i class="fa fa-trash-o"></i>
            </a>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
<div id="layer-div" ></div>
</html>