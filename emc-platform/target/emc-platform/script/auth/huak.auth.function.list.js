/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var tree;

/*单击回调事件*/
function clickCallBack(tr) {
    $('#function-box').show();
    $(tr).siblings('.tree-bg-color').removeClass('tree-bg-color');
    $(tr).addClass('tree-bg-color');
    var id = $(tr).attr('data-id');
    $("#menuId").val(id);
    getFunctions(id);
}

function getFunctions(menuId) {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/function/list',
        type: 'POST',
        dataType: 'json',
        data: {'menuId': menuId, '_method': 'PATCH'},
        success: function (result) {
            // 附上模板
            $("#functionTBody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#functionTBody").processTemplate(result);
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

$(function () {

    tree = getFuncMenuList();

    $('.btn-group').on('click', '.delFunction', function () {
        var ids = getCheckValues();
        if (ids.length == 0) {
            layer.msg("请选择要删除的角色");
            return false;
        }
        layer.confirm('您是否确定删除菜单？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/function/delete',
                data: {ids: ids},
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        layer.closeAll();
                        getFunctions($("#menuId").val());
                        layer.msg(result.msg);
                    } else {
                        layer.close(index);
                        layer.msg(result.msg);
                    }
                }
            });


        });

    }).on('click', '.addFunction', function () {
        $.get(ctx + '/function/add?menuId=' + $("#menuId").val(), function (result) {
            $('#layer-div').html(result);
        });
        layer.open({
            area: ['600px', '600px'],
            type: 1,
            title: '添加功能',
            btn: ['保存', '取消'],
            yes: function () {
                $("#functionAddForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#layer-div')
        });

    }).on('click', '.updFunction', function () {
        var id = getCheckValues();
        if (id.length == 0) {
            layer.msg("请选择要编辑的功能");
            return false;
        }
        if (id.split(',').length > 1) {
            layer.msg("请选择一个功能进行编辑");
            return false;
        }
        $.get(ctx + '/function/edit/' + id, function (result) {
            $('#layer-div').html(result);
        });
        layer.open({
            area: ['600px', '600px'],
            type: 1,
            title: '编辑功能',
            btn: ['保存', '取消'],
            yes: function () {
                $("#functionEditForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#layer-div')
        });

    })


})


function getFuncMenuList() {
    var treeGrid = $('.tree-huak').TreeGrid({
        url: ctx + '/menu/list',//树请求地址（必填）
        params: {
            _method: 'PATCH',
            menuName: $('input[name="menuName"]').val()
        },//参数
        pFieldName: 'pMenuId',
        fieldId: 'menu_id',//主键（必填）
        fieldName: 'menu_name',//展开收起所属字段（必填）
        fields: [
            {name: 'uname'}
        ],//其它字段
        btnTdHtml: '',//按钮td的html
        children: 'children',
        _callBack: clickCallBack
    });
    return treeGrid;
}
