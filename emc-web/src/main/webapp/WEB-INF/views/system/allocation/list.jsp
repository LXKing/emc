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

<script src="${web}/script/huak.web.system.fill.js"></script>

<form id="formForExport">
<div class="main-box">
    <div class="selectbg clearfix">
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->
            <div class="select-box col-xs-12 col-sm-6 col-md-3">
                <label for="">单位名称</label>
                <input class="inputs-lg" id="unitName" name="unitName" type="text" placeholder="请输入单位名称"/>
            </div>
            <div class="select-box col-xs-12 col-sm-6 col-md-3">
                <div class="select-box col-xs-12 col-sm-6 col-md-3">
                    <label for="">能源类型</label>
                </div>
                <div class="select-box col-xs-12 col-sm-6 col-md-3">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl">
                                <div class="x-sfbgbox">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="参考tools页面下拉框" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption x-sfoption1">
                                    <p value="1111">北京公司</p>
                                    <p value="2222">上海公司</p>
                                    <p value="3333">南京集团</p>
                                </div>
                                <input type="hidden" value="1111" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

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
        <a class="btns btnsfl btns-green top-layer-min" href="javascipt:;"  layer-form-id="indexAddForm" layer-title="添加指标配置" layer-url="${web}/index/allocation/add">添加</a>
    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="4%">序号</td>
                <td width="12%">
                    <div class="text-left">单位名称</div>
                </td>
                <td width="10%">
                    <div class="text-left">计量采集表名</div>
                </td>
                <td width="15%">
                    <div class="text-left">换表时间</div>
                </td>
                <td width="8%">
                    <div class="text-left">旧表表底</div>
                </td>
                <td width="8%">
                    <div class="text-left">新表表底</div>
                </td>
                <td width="6%">
                    <div class="text-left">旧表系数</div>
                </td>
                <td width="6%">
                    <div class="text-left">新表系数</div>
                </td>
                <td width="8%">
                    <div class="text-left">创建人<i class="icon-sort"></i></div>
                </td>
                <td width="15%">创建时间<i class="icon-sort"></i></td>
                <td width="10%">
                    <div>操作</div>
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
        <td>1</td>
        <td>
            <div class="text-left">{$T.record.unitName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.name}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.changeTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.usedNum}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.newNum}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.usedCoef}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.newCoef}</div>
        </td>

        <td>{$T.record.crestor}</td>
        <td>{$T.record.createTime}</td>
        <td>
            <div><a href="javascript:detailId(1);" class="operationbtn icon-edit"></a><a href="javascript:detailId(1);"
                                                                                         class="operationbtn icon-delete"></a>
            </div>
        </td>
    </tr>
    {#/for}
</textarea>