/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/11/24<BR>
 */
$(function () {
    getChyList();

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
/* 查询软化水设备列表 */
function getChyList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/chy/list',
        timeout: 5000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#chy-from").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getChyList
            });
            // 附上模板
            $("#chy-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#chy-tbody").processTemplate(result.list);
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
function editChy() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的软化水设备");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个软化水设备进行编辑");
        return false;
    }
    menuItemUri(ctx + '/chy/edit/' + id);
}

function deleteChys() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的软化水设备");
        return false;
    }
    layer.confirm('您是否确定删除所选软化水设备？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/chy/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/chy/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function deleteChy(id) {
    layer.confirm('您是否确定删除软化水设备？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/chy/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/chy/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

