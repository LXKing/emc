<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/12/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>选择设备</title>
    <script type="text/javascript">
        $(function () {
            getMaeList();

            var config = {
                '.chosen-select': {},
                '.chosen-select-deselect': {
                    allow_single_deselect: true
                },
                '.chosen-select-no-single': {
                    disable_search_threshold: 10
                },
                '.chosen-select-no-results': {
                    no_results_text: 'Oops, nothing found!'
                },
                '.chosen-select-width': {
                    width: "10%"
                }
            }
            for (var selector in config) {
                $(selector).chosen(config[selector]);
            }
        });

        function getMaeList() {
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/common/mae/select',
                timeout: 5000,
                type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                dataType: 'json',
                data: $("#common-mae-from").serialize(),
                success: function (result) {
                    $(".pagination").pagination({
                        pageNo: result.list.page.curPage,
                        rowTotal: result.list.page.totalRow,
                        _callBack: getMaeList
                    });
                    // 附上模板
                    $("#mae-tbody").setTemplateElement("mae-tpl-list");
                    // 给模板加载数据
                    $("#mae-tbody").processTemplate(result.list);
                    $('.i-checks').iCheck({
                        checkboxClass: 'icheckbox_square-green',
                        radioClass: 'iradio_square-green'
                    });
                    layer.close(index);
                },
                error: function () {
                    layer.close(index);
                    layer.msg("加载失败");
                },
                complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
                    layer.close(index);
                    if (status == 'timeout') {//超时,status还有success,error等值的情况
                        layer.msg("加载超时");
                    }
                }
            });
        }
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <form id="common-mae-from">
                <input type="hidden" name="_method" value="PATCH">
                <input type="hidden" id="pageNo" name="pageNo" value="1">
                <div class="row col-lg-12">
                    <div class="col-lg-5">
                        <div class="form-group">
                            <label class="control-label col-lg-4">单位</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input  name="orgId" value="${orgId}"  type="hidden"/>
                                    <input type="text" value="${orgName}" name="orgName"  id="common-part" placeholder="所属机构" disabled   class="form-control" />
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label class="control-label col-lg-4">类型</label>

                            <div class="col-lg-8">
                                <select name="typeId" class="chosen-select form-control">
                                    <option value="">全部</option>
                                    <c:forEach var="item" items="${maeTypes}">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label class="control-label col-lg-4">名称</label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" name="maeName" placeholder="设备名称">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1" style="margin-bottom: 8px;">
                        <button type="button" class="btn btn-sm btn-primary" onclick="getMaeList()"> 搜索</button>
                    </div>
                </div>


            </form>
            <table class="table table-hover table-bordered table-condensed" id="mae_common">
                <thead>
                <tr style="background-color: #F5F5F6 ;font-weight: bolder;">
                    <td width="7%"></td>
                    <td width="20%">所属单位</td>
                    <td width="10%">设备类型</td>
                    <td width="10%">设备编码</td>
                    <td width="10%">设备名称</td>
                </tr>
                </thead>
                <tbody id="mae-tbody">
                </tbody>
            </table>
            <div class="form-group pagination"></div>
        </div>
    </div>
</div>
<%--jtemplement 模板--%>
<textarea id="mae-tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.mae_id}" data-org="{$T.item.org_id}" name="input[]">
        </td>
        <td class="common-org-name">{$T.item.org_name}</td>
        <td>{formatMaeType($T.item.type_id)}</td>
        <td>{$T.item.mae_num}</td>
        <td class="common-mae-name">{$T.item.mae_name}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>
