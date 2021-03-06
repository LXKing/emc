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
<link href="${web}/static/css/load.css" rel="stylesheet">
<script src="${web}/script/huak.web.system.fill.add.js"></script>
<div class="main-box">
    <div class="selectbg clearfix">
        <div class="sele-row clearfix row">
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3" >
                <div class="select-box col-xs-2 col-sm-2 col-md-2 col-lg-2">
                    <label for="">表名称</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <input class="inputs-lg" id="collectName" name="collectName" type="text" placeholder="请输入表名称"/>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-12 col-sm-4 col-md-2">
                    <label for="">单位</label>
                </div>
                <div class="select-box col-xs-12 col-sm-4 col-md-2">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl">
                                <div class="x-sfbgbox">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="请选择用能单位" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption1" id="x-sfoption1">
                                    <c:forEach items="${unit}" var="item">
                                        <p value="${item.unitId}">${item.unitName}</p>
                                    </c:forEach>
                                </div>
                                <input id="unitName" type="hidden" value="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-12 col-sm-4 col-md-2">
                    <label for="">类型</label>
                </div>
                <div class="select-box col-xs-12 col-sm-4 col-md-2">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl">
                                <div class="x-sfbgbox">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="请选择能源类型" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption1" id="energy">
                                    <p value="1">水</p>
                                    <p value="2">电</p>
                                    <p value="3">气</p>
                                    <p value="4">热</p>
                                    <p value="5">煤</p>
                                    <p value="6">油</p>
                                </div>
                                <input id="energyType" type="hidden" value="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <label for=''>填报时间</label>
                <input id="collectTime" name="collectTime" class="Wdate time-input" type="text" placeholder="填报时间"
                    onFocus=" WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'});"
                        />
            </div>
        </div>
        <div class="sele-row clearfix row">

            <div class="select-box col-xs-10 col-sm-10 col-md-10 col-lg-10">
            </div>
            <div class="select-box col-xs-2 col-sm-2 col-md-2 col-lg-2">
                <a class="btns btnsfl btns-lookin" style="cursor: pointer;" onclick="query(1)">筛选</a>
                <a class="btns btnsfl btns-reset" style="cursor: pointer;" onclick =resetDataTable()>重置</a>
            </div>
        </div>
    </div>

    <div class="col-xs-12 btngroups   ">
        <a class="btns btnsfl btns-green"  style="cursor: pointer;" onclick="dataSave()" >保存</a>
        <a class="btns btnsfl btns-reset" style="cursor: pointer;" onclick="openPage('${web}/data/fill')">取消</a>
    </div>

    <form id="_editForm">
    <div class="col-xs-12 main-table no-padding">
        <table class="editTable table table-striped table-bordered table-hover pgtable" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <td width="4%">序号</td>
                <td width="14%">
                    <div class="text-left">单位名称</div>
                </td>
                <td width="6%">
                    <div class="text-left">计量编码</div>
                </td>
                <td width="10%">
                    <div class="text-left">计量采集表名</div>
                </td>
                <td width="8%">
                    <div class="text-left">能源类型</div>
                </td>
                <td width="6%">
                    <div class="text-left">实虚表</div>
                </td>
                <td width="6%">
                    <div class="text-left">总分表</div>
                </td>
                <td width="6%">
                    <div class="text-left">手/自动</div>
                </td>
                <td width="12%">创建时间<i class="icon-sort"></i></td>
                <td width="10%">
                    <div class="text-left">表底</div>
                </td>
                <td width="4%">
                    <div class="text-left">系数</div>
                </td>
                <td width="6%">
                    <div class="text-left">换表</div>
                </td>
                <td width="8%">
                    <div class="text-left">换表表底</div>
                </td>
                <%--<td width="6%">--%>
                    <%--<div class="text-left">预存</div>--%>
                <%--</td>--%>
                <%--<td width="6%">--%>
                    <%--<div class="text-left">预存值</div>--%>
                <%--</td>--%>
            </tr>
            </thead>
            <tbody id="projectTbody">

            </tbody>
        </table>
    </div>
    </form>
</div>
<div  id="overlay"></div>
<!-- 模板内容 -->
<textarea id="template" style="display:none">
    {#foreach $T.object as record}
    <tr>
        <input  name = "id" type="hidden" value="{$T.record.id}" />
        <input  name = "flag" type="hidden" value="{$T.record.flag}" />
        <input  name = "realFlag" type="hidden" value="{$T.record.realFlag}" />
        <input  name = "collectTime" type="hidden" value="{$T.record.collectTime}" />
        <input  name = "comId" type="hidden" value="{$T.record.comId}" />
        <td><div class="text-left">{$T.record$index+1}</div></td>
        <td>
            <div class="text-left">{$T.record.unitName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.code}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.collectName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.energyType}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.isReal}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.isTotal}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.isAuto}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.collectTime}</div>
        </td>
        <td class="{#if $T.record.realFlag == 0}td-edit{#/if}">
            <div class="text-left div-edit" title="{$T.record.num}">{#if $T.record.len > 10}{$T.record.s}{#else}{$T.record.num} {#/if}
               <input type="hidden" name="num" value="{$T.record.num}">
            </div>
        </td>
        <td>
            <div class="text-left">{$T.record.coef}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.changestatus}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.changeNum}</div>
        </td>
        <%--<td>--%>
            <%--<div class="text-left">{$T.record.prestorestatus}</div>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<div class="text-left">{$T.record.prestoreNum}</div>--%>
        <%--</td>--%>
    </tr>
    {#/for}
</textarea>