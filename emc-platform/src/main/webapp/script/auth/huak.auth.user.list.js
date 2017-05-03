/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    getUserList();

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
/* 查询用户列表 */
function getUserList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/user/list',
        timeout: 5000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#users-from").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getUserList
            });
            // 附上模板
            $("#user-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#user-tbody").processTemplate(result.list);
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

/* 编辑用户 */
function editUser() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的用户");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个用户进行编辑");
        return false;
    }
    menuItemUri(ctx + '/user/edit/' + id);
}

/**
 * 重置密码
 */
function resetPwd(id) {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/user/reset/pwd',
        timeout: 5000,
        type: 'POST',
        dataType: 'json',
        data: {id: id},
        success: function (result) {
            layer.close(index);
            layer.msg(result.msg);
        },
        error: function () {
            layer.close(index);
            layer.msg("重置密码失败");
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            layer.close(index);
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.msg("请求超时");
            }
        }
    });
}

/**
 * 禁用
 */
function disable(id, status, tr) {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/user/status',
        timeout: 5000,
        type: 'POST',
        dataType: 'json',
        data: {id: id, status: status},
        success: function (result) {
            layer.close(index);
            layer.msg(result.msg);
            if (result.flag) {
                var html = '<a class="btn btn-info btn-xs btn-bitbucket user-enable" title="启用" onclick="enable(&quot;' + id + '&quot;,1,this)"><i class="fa fa-unlock"></i></a>'
                $(tr).after(html);
                $(tr).remove();
            }
        },
        error: function () {
            layer.close(index);
            layer.msg("禁用失败");
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            layer.close(index);
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.msg("请求超时");
            }
        }
    });
}

/**
 * 启用
 */
function enable(id, status, tr) {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/user/status',
        timeout: 5000,
        type: 'POST',
        dataType: 'json',
        data: {id: id, status: status},
        success: function (result) {
            layer.close(index);
            layer.msg(result.msg);
            if (result.flag) {
                var html = '<a class="btn btn-danger btn-xs btn-bitbucket user-disable" title="禁用" onclick="disable(&quot;' + id + '&quot;,1,this)"><i class="fa fa-unlock-alt"></i></a>'
                $(tr).after(html);
                $(tr).remove();
            }
        },
        error: function () {
            layer.close(index);
            layer.msg("启用失败");
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            layer.close(index);
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.msg("请求超时");
            }
        }
    });
}

function deleteUsers() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的用户");
        return false;
    }
    layer.confirm('您是否确定删除所选用户？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/user/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/user/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function deleteUser(id) {
    layer.confirm('您是否确定删除用户？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/user/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/user/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

/**
 * 分配角色
 * @returns {boolean}
 */
function userRolePage() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要分配角色的用户");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个用户进行分配角色");
        return false;
    }

    $.get(ctx + '/user/role?id=' + id, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['800px', '600px'],
        type: 1,
        title: '分配角色',
        btn: ['确定', '取消'],
        yes: function () {
            var roleIds = new Array();
            var userId = $("#roleUserId").val();
            $('input[name="roleIds[]"]').each(function (idx, item) {
                roleIds.push(item.value);
            });
            $.ajax({
                url: ctx + '/user/role',
                type: 'POST',
                dataType: 'json',
                traditional: true,//如果Post是string数组或者int数组，则ajax中traditional: true,
                // 如果Post是对象数组，则ajax中traditional: false,否则对象将为空
                data: {userId: userId, roleIds: roleIds},
                success: function (result) {
                    if (result.flag) {
                        layer.closeAll();
                        $("#content-main").empty().load(ctx + '/user/list');
                        layer.msg(result.msg);
                    } else {
                        layer.close(index);
                        layer.msg(result.msg);
                    }
                }
            });
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}
