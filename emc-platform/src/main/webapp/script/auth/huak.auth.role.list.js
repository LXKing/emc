/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
$(function () {
    //getRoleList();
});
function getRoleList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    /*$.ajax({
        url: platform + '/role/list',
        timeout: 5000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#roles-from").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getRoleList
            });
            // 附上模板
            $("#role-tbody").setTemplateElement("tpl-list");
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
    });*/
}
//layer
function editRole() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的角色");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个角色进行编辑");
        return false;
    }

    $.get(_platform + '/role/edit/' + id, function (result) {
        $('#role-layer-div').html(result);
    });
    layer.open({
        area: ['600px', '500px'],
        type: 1,
        title: '编辑角色',
        btn: ['保存', '取消'],
        yes: function () {
            $("#roleEditForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#role-layer-div')
    });
}

function addRole() {
    $.get(_platform + '/role/add', function (result) {
        $('#role-layer-div').html(result);
    });
    layer.open({
        area: ['800px', '580px'],
        type: 1,
        title: '添加角色',
        btn: ['保存', '取消'],
        yes: function () {
            $("#roleAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#role-layer-div')
    });
}

function deleteRoles() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的角色");
        return false;
    }
    layer.confirm('您是否确定删除所选角色？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/role/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/role/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function deleteRole(id) {
    layer.confirm('您是否确定删除角色？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/role/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/role/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function roleAuthPage(){
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要授权的角色");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个角色进行授权");
        return false;
    }
    $("#content-main").empty().load(_platform + '/role/auth?id=' + id);
}