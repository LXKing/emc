/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var tree;
$(function () {
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

    tree = getMenuList();

    $('.tree-huak').on('click', '.delMenu', function () {
        var _this = $(this);
        var id = _this.parents('tr').attr('data-id');
        layer.confirm('您是否确定删除菜单？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var pc = _this.parents('tr').attr('class');
            if (pc.indexOf('tree-level-1') > -1) {
                layer.msg('根节点无法删除');
                return false;
            }
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/menu/delete/' + id,
                type: 'DELETE',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        layer.closeAll();
                        tree.defaults.delNode(_this.parents('tr'))
                        layer.msg(result.msg);
                    } else {
                        layer.close(index);
                        layer.msg(result.msg);
                    }
                }
            });


        });

    }).on('click', '.addMenu', function () {
        $.get(ctx + '/menu/add?pid=' + $(this).parents('tr').attr('data-id'), function (result) {
            $('#layer-div').html(result);
        });
        layer.open({
            area: ['600px', '600px'],
            type: 1,
            title: '添加菜单',
            btn: ['保存', '取消'],
            yes: function () {
                $("#menuAddForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#layer-div')
        });

    }).on('click', '.updMenu', function () {
        var _this = $(this);
        var id = _this.parents('tr').attr('data-id');

        $.get(ctx + '/menu/edit/' + id, function (result) {
            $('#layer-div').html(result);
        });
        layer.open({
            area: ['600px', '600px'],
            type: 1,
            title: '编辑角色',
            btn: ['保存', '取消'],
            yes: function () {
                $("#menuEditForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#layer-div')
        });

    })

})


/* 添加菜单回调函数 */
function addCollBack() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/menu/add',
        data: $('#menuAddForm').serialize(),
        type: 'POST',
        dataType: 'json',
        success: function (result) {
            if (result.flag) {
                layer.closeAll();
                tree.defaults.addNode(tree, $('tr[data-id="' + $('input[name="pMenuId"]').val() + '"]'), result.data);
                layer.msg(result.msg);
            } else {
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

/* 修改菜单回调函数 */
function editCollBack() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/menu/edit',
        data: $('#menuEditForm').serialize(),
        type: 'POST',
        dataType: 'json',
        success: function (result) {
            if (result.flag) {
                layer.closeAll();
                tree.defaults.updNode(tree, $('tr[data-id="' + $('input[name="menuId"]').val() + '"]'), result.data);
                //tree.defaults.updNode(tree,$(this).parents('tr'),{'menu_id':22,'menu_name':'测试修改','url':'测试url','uname':'测试name'});
                layer.msg(result.msg);
            } else {
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

function getMenuList() {
    var treeGrid = $('.tree-huak').TreeGrid({
        url: ctx + '/menu/list',//树请求地址（必填）
        params: {
            _method: 'PATCH',
            menuName: $('input[name="menuName"]').val(),
            uName: $('input[name="uName"]').val(),
            isOwer: $('select[name="isOwer"]').val()
        },//参数
        pFieldName: 'pMenuId',
        fieldId: 'menu_id',//主键（必填）
        fieldName: 'menu_name',//展开收起所属字段（必填）
        fields: [
            {
                name: 'url'
            },
            {
                name: 'uname'
            },
            {
                name: 'img_name',
                formater: function (val) {
                    return '<i class="fa ' + val + '"></i>'
                }
            },
            {
                name: 'isower',
                formater: function (val) {
                    if (val == 0) {
                        return '公共';
                    } else if (val == 1) {
                        return '前台';
                    } else {
                        return '后台';
                    }
                }
            },
            {
                name: 'seq'
            },
            {
                name: 'memo'
            }
        ],//其它字段
        btnTdHtml: $('#tree-btn').html(),//按钮td的html
        children: 'children'
    });
    return treeGrid;
}