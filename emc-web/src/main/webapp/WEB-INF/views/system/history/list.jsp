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

<script src="${web}/script/huak.web.system.history.js"></script>

<form id="formForExport">
    <div class="main-box">
        <div class="selectbg clearfix">
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->

                <div class="select-box col-xs-12 col-sm-6 col-md-4">
                    <label for="">时间</label>
                    <input id="startTime" name="startTime" class="Wdate time-input" type="text" placeholder="开始时间"
                           onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})"
                            /> 至
                    <input id="endTime" name="endTime" class="Wdate time-input" type="text" placeholder="结束时间"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"
                            />
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2">
                    <a class="btns btnsfl btns-lookin" onclick="query(1)">查询</a>
                    <a class="btns btnsfl btns-reset" onclick =reset()>重置</a>
                </div>
            </div>
        </div>

        <div class="col-xs-12 btngroups   ">
            <a class="btns btnsfl exportchange btns-green"  export-url="${web}/history/export" >导出</a>
            <%--<a class="btns btnsfl btns-lookin" href="javascipt:;">修改日期</a>--%>
        </div>


        <div class="col-xs-12 main-table no-padding">
            <table class="table table-striped table-bordered table-hover pgtable">
                <thead>
                <tr>
                    <td width="4%">序号</td>
                    <td width="12%">
                        <div class="text-left">时间</div>
                    </td>
                    <td width="10%">
                        <div class="text-left">供热面积</div>
                    </td>
                    <td width="15%">
                        <div class="text-left">最高温度</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">最低温度</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">平均温度</div>
                    </td>
                    <td width="6%">
                        <div class="text-left">天气情况</div>
                    </td>
                    <td width="6%">
                        <div class="text-left">单日耗热量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">单耗</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">热量累积值</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">供水热量累积值</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">回水热量累积值</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">供水流量累积值</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">回水流量累积值</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">瞬时热量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">瞬时供水热量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">瞬时回水热量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">瞬时供水流量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">瞬时回水流量</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">供水温度</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">回水温度</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">供水压力</div>
                    </td>
                    <td width="8%">
                        <div class="text-left">回水压力</div>
                    </td>

                </tr>
                </thead>
                <tbody id="projectTbody">

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
</form>
<!-- 模板内容 -->
<textarea id="template" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.}</div>
        </td>

        <%--<td>--%>
        <%--<div><a href="javascript:detailId(1);" class="operationbtn icon-edit"></a><a href="javascript:detailId(1);"--%>
        <%--class="operationbtn icon-delete"></a>--%>
        <%--</div>--%>
        <%--</td>--%>
    </tr>
    {#/for}
</textarea>