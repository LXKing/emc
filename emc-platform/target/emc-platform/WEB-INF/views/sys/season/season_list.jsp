<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>采暖季列表</title>
    <script src="${ctx}/script/sys/huak.sys.season.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;采暖季列表</h5>
                </div>
                <div class="ibox-content">
                    <form id="seasons-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">采暖季度</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="season" placeholder="请输入采暖季度">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">开始时间</label>

                                    <div class="col-lg-8">
                                        <input type="text" id="s_date" class="form-control" name="sDate" placeholder="请选择开始时间">
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">结束时间</label>

                                    <div class="col-lg-8">
                                        <input type="text" id="e_date" class="form-control" name="eDate" placeholder="请选择结束时间">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>
                    <div class="row btn-margin-bottom0">
                        <div class="col-lg-8  btn-group">
                            <c:if test="${sessionScope._auth['seasonAdd']}">
                            <button type="button" class="btn btn-sm btn-info" onclick="addSeason()">
                                <i class="fa fa-plus"></i>添加
                            </button>
                                </c:if>
                            <c:if test="${sessionScope._auth['seasonUpdate']}">
                            <button type="button" class="btn btn-sm btn-info" onclick="editSeason()">
                                <i class="fa fa-edit"></i>编辑
                            </button>
                            </c:if>
                            <c:if test="${sessionScope._auth['seasonDelete']}">
                            <button type="button" class="btn btn-sm btn-danger" onclick="deleteSeasons()">
                                <i class="fa fa-trash-o"></i>删除
                            </button>
                            </c:if>
                        </div>
                        <div class="col-lg-4 btn-text-align">
                            <button type="button" class="btn btn-sm btn-primary" onclick="getSeasonList()"> 搜索
                            </button>
                            <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                        </div>
                    </div>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th width="15%">采暖季度</th>
                            <th width="25%">开始时间</th>
                            <th width="25%">结束时间</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody id="season-tbody"></tbody>
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
            <input type="checkbox" class="i-checks" value="{$T.item.season_id}" name="input[]">
        </td>
        <td>{$T.item.season}</td>
        <td>{$T.item.s_date}</td>
        <td>{$T.item.e_date}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,20)}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>