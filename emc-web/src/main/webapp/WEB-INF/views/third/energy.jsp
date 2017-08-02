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
    <meta name="decorator" content="main_top"/>
    <title>华热能管系统-能耗分析</title>
    <script src="${web}/script/huak.web.third.energy.js"></script>
</head>
<body>
<div class="index_mainbody  ">

    <div class="index_content row no-margin">
        <div class="col-lg-12 no-padding index_contentList">
            <div class="col-lg-12 mb14  ">
                <div class=" index_contentBox clearfix">
                    <div class="titbox clearfix no-padding no-margin">
                        <div class="pull-left groupEnergyTit energyTit"><i></i>集团总能耗<small class="font-sm">Energy Monitoring</small></div>
                    </div>

                    <div class="clearfix energy-list col-lg-12 ">
                        <div class="energy-list-box energy-list-box-first">
                            <div class="energy-head ">
                                <span class="energy-list-name">源</span>

                            </div>

                            <div class="energy-chart">
                                <div id="chart1"></div>
                            </div>
                        </div>

                        <div class="energy-list-box">
                            <div class="energy-head ">
                                <span class="energy-list-name">网</span>

                            </div>
                            <div class="energy-chart">
                                <div id="chart2"></div>
                            </div>
                        </div>

                        <div class="energy-list-box">
                            <div class="energy-head ">
                                <span class="energy-list-name">站</span>

                            </div>

                            <div class="energy-chart">
                                <div id="chart3"></div>
                            </div>
                        </div>

                        <div class="energy-list-box">
                            <div class="energy-head ">
                                <span class="energy-list-name">线</span>

                            </div>

                            <div class="energy-chart">
                                <div id="chart4"></div>
                            </div>
                        </div>

                        <div class="energy-list-box energy-list-box-last">
                            <div class="energy-head ">
                                <span class="energy-list-name">户</span>

                            </div>

                            <div class="energy-chart">
                                <div id="chart5"></div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>

        <div class="col-lg-12 no-padding index_contentList">
            <div class="col-lg-12 mb14">
                <div class="index_contentBox clearfix">
                    <div class="titbox clearfix no-padding no-margin">
                        <div class="pull-left energyTit analy_tit_as"><i></i>能源流明细<small class="font-sm">Assessment indicators</small></div>

                    </div>
                    <div class="AssessmentBox rconttable col-lg-12 no-padding">

                        <div class="col-lg-12 no-padding mt20" style="border-bottom: 1px solid #d0d4d9;">
                            <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                                <div id="piechart_as" style="width: 100%;height:268px;"></div>
                                <div class="piechartTit">
                                    换热站排名
                                </div>
                            </div>
                            <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                                <div id="linechart_as" style="width: 100%;height:268px;"></div>

                                <div class="piechartTit">
                                    热源排名
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>
    </div>
</body>
</html>