<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/31
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="${web}/script/huak.web.system.workinfo.js"></script>
<script src="${web}/script/huak.web.system.workinfo.public.js"></script>

<div class="main-box">
    <form id="workInfoSearch">
        <input type="hidden" name="_method" value="PATCH">
        <input type="hidden" name="pageNumber" value="1">
        <input type="hidden" name="orgId" value="${org.id}">
        <input type="hidden" name="comId" value="${company.id}">
        <div class="selectbg clearfix">
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->

                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <label>工单名称</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <input class="inputs-lg" id="unitNameSearch" name="workOrderName" type="text" placeholder=" 请输入工单名称"/>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <label>工单类型</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox1">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择工单类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1" id="workerOder_type">
                                        <c:forEach items="${sysDic['type']}" var="type">
                                            <p value="${type.seq}">${type.des}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="hidden" id="workOrderTypeSearch" name="workOrderType" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <a href="javascript:void(0);" class="btns btnsfl btns-lookin" onclick="queryAlarmConfig()">查询</a>
                    <a href="javascript:void(0);" class="btns btnsfl btns-reset" onclick =reset()>重置</a>
                </div>

            </div>

        </div>
    </form>
   <%-- <div class="col-xs-12 btngroups   ">
        <a class="btns btnsfl btns-green top-layer-min-work" href="javascript:void(0);"  layer-form-id="indexAddForm" layer-title="添加工单" layer-url="${web}/work/order/info/add">添加</a>
--%>

    <div class="col-xs-12 btngroups">
        <c:if test="${sessionScope._auth['workOrderAdd']}">
            <a class="btns btnsfl btns-green top-layer-min" href="javascript:void(0);"  layer-form-id="indexAddForm" layer-title="添加工单" layer-url="${web}/work/order/info/add">添加</a>
        </c:if>

        <a class="btns btnsfl exportchange btns-green" export-url="${web}/temp/config/export" href="javascript:void(0);">导出</a>

        <a class="btns btnsfl btns-green" href="javascript:void(0);" onclick="uploaderExcel()">导入</a>
        <%--<c:if test="${sessionScope._auth['alarmExport']}">--%>
        <%--<a class="btns btnsfl btns-green" href="javascript:void(0);">导出</a>--%>
        <%--</c:if>--%>

    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="2%">序号</td>
                <td width="4%">
                    <div class="text-left">工单编号</div>
                </td>
                <td width="6%">
                    <div class="text-left">工单名称</div>
                </td>
                <td width="8%">
                    <div class="text-left">工单内容</div>
                </td>
                <td width="11%">
                    <div class="text-left">开始时间</div>
                </td>
                <td width="11%">
                    <div class="text-left">完成时间</div>
                </td>
                <td width="11%">
                    <div class="text-left">创建时间</div>
                </td>
                <td width="5%">
                    <div class="text-left">状态</div>
                </td>
                <td width="5%">
                    <div class="text-left">创建人<i class="icon-sort"></i></div>
                </td>
                <td width="6%">
                    <div class="text-left">班长</div>
                </td>
                <td width="6%">
                    <div class="text-left">接单员</div>
                </td>
                <td width="9%">
                    <div>操作</div>
                </td>
            </tr>
            </thead>
            <tbody id="workOrderBody">

            </tbody>
        </table>
    </div>
    <div class="mainpage clearfix">
        <div class="mianpageCount pull-left">
            共<span class="redtips redtipspad" id="redtipspad"></span>条内容
        </div>
        <div class="mainpageNow  pull-right text-right" id="paging">

        </div>

    </div>

</div>

<!-- 模板内容 -->
<textarea id="tpl-workOrder" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
           <a href="_web/work/order/info/detail/${roleType}/{$T.record.code}"> <div class="text-left">{$T.record.code}</div></a>
        </td>
        <td>
           <a> <div class="text-left">{$T.record.name}</div></a>
        </td>
        <td>
            <div class="text-left" title=${$T.record.content}>{fromatStr($T.record.content,7)}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.startTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.finishTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.createTime}</div>
        </td>
        <td>
            <div class="text-left">{workOrderStatus($T.record.status,${roleType})}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.creator}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.monitor}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.takor}</div>
        </td>
        <td>
                <%--<a href="javascript:send(0);" title="修改" class="operationbtn icon-edit top-layer-min"
                   layer-form-id="indexEditForm" layer-title="修改指标配置"  layer-url="${web}/work/order/info/edit?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>
                <a href="javascript:delAllocation('{$T.record.id}');" title="删除" class="operationbtn icon-delete"></a>--%>

                <c:if test="${sessionScope._auth['workOrderSend'] && $T.record.status==111}">
                    <a href="javascript:void(0);" title="派单" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="派单" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderClose'] && ($T.record.status==321 || $T.record.status==321 || $T.record.status==212)}">
                    <a href="javascript:void(0);" title="关闭" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="关闭" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderConfirm'] && ($T.record.status==311 || $T.record.status==323 ||$T.record.status==213)}">
                    <a href="javascript:void(0);" title="确认" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="确认" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderReset'] && ($T.record.status==212 || $T.record.status==213 || $T.record.status==321 || $T.record.status==323 || $T.record.status== 311 || $T.record.status== 312)}">
                    <a href="javascript:void(0);" title="重新发送" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="重新发送" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderTaking'] && roleType==2 && $T.record.status==112}">
                    <a href="javascript:void(0);" title="接单" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="接单" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderBack'] && $T.record.status==112}">
                    <a href="javascript:void(0);" title="退单" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="退单" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderFinish'] && roleType==2 && $T.record.status==211}">
                    <a href="javascript:void(0);" title="完成" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="完成" layer-url="${web}/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderTaking'] && $T.record.status==113}">
                    <a href="javascript:void(0);" title="接单" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="接单" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderBack'] && ($T.record.status==113 || $T.record.status==214)}">
                    <a href="javascript:void(0);" title="退单" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="退单" layer-url="${web}/work/order/info/"></a>
                </c:if>
                <c:if test="${sessionScope._auth['workOrderFinish'] && ($T.record.status==322 ||  $T.record.status==214)}">
                    <a href="javascript:void(0);" title="完成" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="alarmConfigEditForm" layer-title="完成" layer-url="${web}/work/order/info/"></a>
                </c:if>
        </td>
    </tr>
    {#/for}
</textarea>