<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>能源单价列表</title>
    <script src="${ctx}/script/cos/huak.cos.energy.price.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;能源单价列表</h5>
                </div>
                <div class="ibox-content">
                    <form id="energy-price-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row col-lg-12">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">能源类型</label>

                                    <div class="col-lg-8">
                                        <select id="typeId" name="typeId" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <c:forEach var="item" items="${energyTypes}">
                                                <option value="${item.type_id}">${item.type_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">开始时间</label>

                                    <div class="col-lg-8">
                                        <input name="sTime" id="sTime" class="form-control" type="text" placeholder="请选择开始时间">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">结束时间</label>

                                    <div class="col-lg-8">
                                        <input name="eTime" id="eTime" class="form-control" type="text" placeholder="请选择结束时间">
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
                                <c:if test="${sessionScope._auth['energyPriceAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem" data-href="${ctx}/energy-price/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['energyPriceUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info " onclick="editEnergyPrice()">
                                    <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['energyPriceDel']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteEnergyPrices()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getEnergyPriceList()"> 搜索
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
                            <th>能源类型</th>
                            <th>平均单价</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="energy-price-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.price_id}" name="input[]">
        </td>
        <td>{$T.item.org_name}</td>
        <td>{$T.item.type_name}</td>
        <td>¥{$T.item.avg_price}元</td>
        <td>{$T.item.s_time}</td>
        <td>{$T.item.e_time}</td>
        <td>
            <c:if test="${sessionScope._auth['energyPriceDel']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteEnergyPrice('{$T.item.price_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>