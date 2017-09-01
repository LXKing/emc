<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/31
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="decorator" content="system"/>
    <title>华热能管系统-安全与后台-demo</title>
    <script src="${web}/script/huak.web.system.js"></script>
</head>
<body>


<div class="main-box">
    <div class="selectbg clearfix">
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->
            <div class="select-box col-xs-12 col-sm-6 col-md-3">
                <label for="">文本框</label>
                <input class="inputs-lg" type="text" placeholder="请输入关键字" />
            </div>
            <div class="select-box col-xs-12 col-sm-6 col-md-3">
                <div class="select-box col-xs-12 col-sm-6 col-md-3">
                    <label for="">下拉框</label>
                </div>
                <div class="select-box col-xs-12 col-sm-6 col-md-3">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl">
                                <div class="x-sfbgbox">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="集团总览" readonly="readonly">
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
                <input id="d521" class="Wdate time-input" type="text" placeholder="开始时间" onFocus="var d522=$dp.$('d522');WdatePicker({onpicked:function(){d522.focus();},maxDate:'#F{$dp.$D(\'d522\')}'})"
                        /> 至
                <input id="d522" class="Wdate time-input" type="text" placeholder="结束时间" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d521\')}'})"
                        />
            </div>
            <div class="col-xs-12 col-sm-6 col-md-2">
                <a class="btns btnsfl btns-lookin" href="javascipt:;">查询</a>
                <a class="btns btnsfl btns-reset" href="javascipt:;">重置</a>
            </div>
        </div>
    </div>

    <div class="col-xs-12 btngroups   ">
        <a class="btns btnsfl btns-green" href="javascipt:;">保存</a>
        <a class="btns btnsfl btns-lookin" href="javascipt:;">修改日期</a>
    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="4%">序号</td>
                <td width="31%">
                    <div class="text-left">项目名称</div>
                </td>
                <td width="10%">
                    <div class="text-left">项目编号</div>
                </td>
                <td width="15%">
                    <div class="text-left">项目内容</div>
                </td>
                <td width="12%">
                    <div class="text-left">实施目的</div>
                </td>
                <td width="7%">
                    <div class="text-left">负责人<i class="icon-sort"></i></div>
                </td>
                <td width="11%">投资强度<i class="icon-sort"></i></td>
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

</body>
</html>