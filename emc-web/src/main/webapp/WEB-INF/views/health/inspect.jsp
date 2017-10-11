<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/22
  Time: 13:03
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
    <meta name="decorator" content="health"/>
    <title>华热能管系统-健康指数</title>
    <script src="${web}/script/huak.web.health.inspect.js"></script>
</head>
<body>
<div class="index_mainbody  ">

    <div class="index_content row no-margin">
        <div class="col-lg-12 no-padding index_contentList">
            <div class="col-lg-12 mb14  ">
                <div class=" index_contentBox clearfix">
                    <div class="titbox clearfix no-padding no-margin">
                        <div class="pull-left groupEnergyTit energyTit"><i></i>健康指数<small class="font-sm">Health index</small></div>
                    </div>
                    <div class=" col-lg-12  clearfix healthwrap">
                        <div class="left">
                            <div class="charts">
                                <div id="chart01">
                                    <div class="value">
                                        <h1>79.8</h1>
                                        <h2>上次检测分数</h2>
                                        <h3>2018-01-02</h3>
                                    </div>

                                </div>
                            </div>
                            <div id="running" style="display: none">
                                <h1>正在检测作业管理...</h1>
                                <div class="pressbar">
                                    <div></div>
                                </div>
                            </div>
                            <a id="runbtn"></a>
                            <div>
                                <p>...</p>
                                <p>朝阳一区温度100℃</p>
                                <p>朝阳一区温度100℃</p>
                                <p>朝阳一区温度100℃</p>
                                <p>朝阳一区温度100℃</p>
                                <p>朝阳一区温度100℃</p>
                                <p>朝阳一区温度100℃</p>
                                <p>...</p>
                            </div>
                        </div>
                        <div id="resulthealth" class="right clearfix">

                            <div class="resultlist">

                                <div class="healthitem">
                                    <div><h1>发现<span id="healthcount">23</span>项待检测</h1></div>
                                    <div class="panelwrap">
                                        <ul id="healthlistul">
                                            <input type="hidden" id="healthItem" value='${healthItem}'>


                                        </ul>
                                    </div>
                                </div>

                                <div class="erroritem">
                                    <div><h1>发现<span id="errorcount">8</span>项异常</h1></div>
                                    <div class="panelwrap">
                                        <ul id="errorlistul">
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-01-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-02-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-03-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-04-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-05-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-06-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-07-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-08-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-09-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-10-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-11-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-12-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-13-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-14-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-15-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-16-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-17-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-18-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-02-01-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-02-02-abnor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="normalitem">
                                    <div><h1>以下<span id="normalcount">8</span>项无异常</h1></div>
                                    <div class="panelwrap">
                                        <div class="title"><h1>作业管理 - 共<span>2</span>项</h1></div>
                                        <ul>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-03-nor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-04-nor.png">
                                                </div>
                                                <p>万平米工单</p></li>
                                        </ul>
                                    </div>
                                    <div class="panelwrap">
                                        <div class="title"><h1>作业管理2 - 共<span>6</span>项</h1></div>
                                        <ul>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-03-nor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-04-nor.png">
                                                </div>
                                                <p>万平米工单</p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-03-nor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-04-nor.png">
                                                </div>
                                                <p>万平米工单</p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-03-nor.png">
                                                </div>
                                                <p>万平米工单<span>1</span></p></li>
                                            <li>
                                                <div><img
                                                        src="http://localhost:8081/emc-web/static/imgdark/health/test-01-04-nor.png">
                                                </div>
                                                <p>万平米工单</p></li>
                                        </ul>
                                    </div>
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