<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色列表</title>
    <script type="application/javascript">
        $(function () {
            getRoleList();

            //角色按钮绑定点击事件
            $('#selectRoles').on('click', '.btn', function () {
                $(this).remove();
            });
            //table中的tr绑定单机事件
            $('#role-tbody').on('click', 'tr', function () {
                var dataName = $(this).attr("data-name");
                var flag = true;
                $("#selectRoles").find('a').each(function (idx, item) {
                    if (item.title == dataName) {
                        layer.msg("该角色已分配");
                        flag = false;
                        return;
                    }
                });
                if (flag) {
                    var html = '<a class="btn btn-danger btn-outline btn-sm" title="' + dataName + '" style="margin-right: 5px;">' + dataName + '<input type="hidden" name="roleIds[]" value="' + $(this).attr("data-id") + '"><i class="fa fa-times"> </i></a>';
                    $('#selectRoles').append(html);
                }

            });
        });
        function getRoleList() {
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/role/list',
                timeout: 5000,
                type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                dataType: 'json',
                data: $("#roles-from").serialize(),
                success: function (result) {
                    $(".pagination1").pagination({
                        pageNo: result.list.page.curPage,
                        rowTotal: result.list.page.totalRow,
                        _callBack: getRoleList,
                        pageNoId: 'pageNo1',
                        pageGoId: 'page-go1'
                    });
                    // 附上模板
                    $("#role-tbody").setTemplateElement("tpl-list-1");
                    // 给模板加载数据
                    $("#role-tbody").processTemplate(result.list);
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
    <div class="row float-e-margins">
        <div class="col-sm-12" id="selectRoles">
            <c:forEach items="${roles}" var="role">
                <a class="btn btn-danger btn-outline btn-sm" title="${role.role_name}">
                    <input type="hidden" name="roleIds[]" value="${role.role_id}">
                        ${role.role_name}<i class="fa fa-times"> </i>
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form id="roles-from">
                <input type="hidden" name="_method" value="PATCH">
                <input type="hidden" id="pageNo1" name="pageNo" value="1">
                <input type="hidden" name="userId" id="roleUserId" value="${obj.user_id}">

                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <label class="control-label col-lg-4">名称</label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" name="roleName">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2" style="margin-bottom: 8px;">
                        <button type="button" class="btn btn-sm btn-primary" onclick="getRoleList()"> 搜索</button>
                    </div>
                    <div class="col-lg-6">
                        <p class="text-warning" style="margin-top:8px;">（单击表格的行以选择要分配的角色）</p>
                    </div>

                </div>
            </form>

            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th width="20%">角色名称</th>
                    <th width="25%">描述</th>
                    <th width="45%">备注</th>
                </tr>
                </thead>
                <tbody id="role-tbody"></tbody>
            </table>
            <div class="form-group pagination1"></div>

        </div>

    </div>
</div>

<%--jtemplement 模板--%>
<textarea id="tpl-list-1" style="display: none">
    {#foreach $T.data as item}
    <tr data-id="{$T.item.role_id}" data-name="{$T.item.role_name}">
        <td>{$T.item.role_name}</td>
        <td>{$T.item.role_des}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
    </tr>
    {#/for}
</textarea>
</body>
</html>