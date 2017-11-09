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
<script src="${web}/script/huak.web.system.workinfo.public.js"></script>

<div class="main-box">

        <div class="selectbg clearfix">
            <div class="col-xs-12 btngroups   ">
                <h4>工单详细信息</h4>
            </div>
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">工单编号</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.code}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">工单类型</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.type eq 1?"非定时任务":"定时任务"}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">工单名称</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.name}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">开始时间</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.startTime}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">完成时间</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.finishTime}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">实际完成时间</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.actualFinishTime}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">工单状态</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.status}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">创建人</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.creatorName}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">创建时间</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.createTime}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">派单员</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.creatorName}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">班长</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.monitorName}</label>
                    </div>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">接单员</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.takorName}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                    <div class="select-box col-xs-4 col-sm-4 col-md-4 col-lg-4">
                        <label for="">完成人</label>
                    </div>
                    <div class="select-box col-xs-8 col-sm-8 col-md-8  col-lg-8">
                        <label for="">${detail.finishName}</label>
                    </div>
                </div>

            </div>
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="select-box col-xs-1 col-sm-1 col-md-1 col-lg-1">
                        <label for="">拒绝原因</label>
                    </div>
                    <div class="select-box col-xs-10 col-sm-10 col-md-10  col-lg-10">
                        <label for="">${detail.reason}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="select-box col-xs-1 col-sm-1 col-md-1 col-lg-1">
                        <label for="">完成原因</label>
                    </div>
                    <div class="select-box col-xs-10 col-sm-10 col-md-10  col-lg-10">
                        <label for="">${detail.finishReason}</label>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="select-box col-xs-1 col-sm-1 col-md-1 col-lg-1">
                        <label for="">工单内容</label>
                    </div>
                    <div class="select-box col-xs-10 col-sm-10 col-md-10  col-lg-10">
                        <label for="">${detail.content}</label>
                    </div>
                </div>
            </div>
        </div>


    <div class="selectbg clearfix" style="margin-top: 10px;">
    <div class="col-xs-12 btngroups   ">
        <h4>工单流程列表</h4>
    </div>
    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="15%">操作时间</td>
                <td width="15%">
                    <div>操作前状态</div>
                </td>
                <td width="15%">
                    <div>操作后状态</div>
                </td>
                <td width="55%">
                    <div>操作说明</div>
                </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${records}" var="item">
                <tr>
                    <td>${item.operateTime}</td>
                    <td>${item.beforStatus}</td>
                    <td>${item.afterStatus}</td>
                    <td class="text-left">${item.empName}${item.reciver}${item.des}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

        <div class="selectbg clearfix" style="margin-top: 10px;">
            <div class="col-xs-12 btngroups   ">
                <h4>工单关联列表</h4>
            </div>
            <div class="col-xs-12 main-table no-padding">
                <table class="table table-striped table-bordered table-hover pgtable">
                    <thead>
                    <tr>
                        <td width="20%">第一次工单编号</td>
                        <td width="20%">
                            <div>工单编号</div>
                        </td>
                        <td width="10%">
                            <div>重发次数</div>
                        </td>
                        <td width="30%">
                            <div>工单名称</div>
                        </td>
                        <td width="20%">
                            <div>工单状态</div>
                        </td>

                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${rels ne null}">
                    <c:forEach items="${rels}" var="item">
                        <tr>
                            <td>${item.parentCode}</td>
                            <td><a href="javascript:void(0);" onclick="openDetail('${item.code}')">${item.code}</a></td>
                            <td>${item.resetNum}</td>
                            <td>${item.name}</td>
                            <td>${item.status}</td>
                        </tr>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
</div>

<!-- 模板内容 -->
<textarea id="tpl-allocation" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <div class="text-left">{$T.record.unitName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.name}({$T.record.unitMeter})</div>
        </td>
        <td>
            <div class="text-left">{$T.record.enterprise}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.local}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.industry}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.indexTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.userName}</div>
        </td>
        <td>{$T.record.createTime}</td>
        <td>
            <div>
                <c:if test="${sessionScope._auth['indexUpdate']}">
                    <a href="javascript:void(0);" title="修改" class="operationbtn icon-edit top-layer-min"
                       layer-form-id="indexEditForm" layer-title="修改指标配置" layer-url="${web}/index/allocation/edit/{$T.record.id}"></a>
                </c:if>
                <c:if test="${sessionScope._auth['indexDelete']}">
                    <a href="javascript:delAllocation('{$T.record.id}');" title="删除" class="operationbtn icon-delete"></a>
                </c:if>
            </div>
        </td>
    </tr>
    {#/for}
</textarea>