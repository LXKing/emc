/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
$(function () {
    $('#func-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/func/list',//获取数据的Servlet地址
        method: 'post',//使用POST请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        idField: "ID",
        pagination: false,//是否分页
        search: false,  //是否启用查询
        striped: true,//表格显示条纹
        //showColumns: false,//不显示隐藏列
        sidePagination: "server", //服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        //queryParamsType: "undefined",
        queryParams: function queryParams(params) {
            var param = {
                funcName:$('input[name="funcName"]').val(),
                _method: "PATCH"
            };
            return param;
        }, formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        responseHandler: function (res) {
            return {
                "rows": res.list
                //"total": res.list.page.total
            };
        },
        columns: [
            {
                field: 'id', title: 'ID', visible: false
            },
            /*{
             checkbox: true
             },*/
            {
                title: '序号',
                field: 'sn',
                align: 'center',
                formatter:function(value,row,index){
                    return index+1;
                }
            },
            {
                title: '功能名称',
                field: 'funcName',
                align: 'center'
            },
            {
                title: '唯一标识',
                field: 'uname',
                align: 'center'
            },
            {
                title: '是否查询',
                field: 'issearch',
                align: 'center',
                formatter:function(value,row,index){
                    if(value == 0){
                        return '是';
                    }else if(value == 1){
                        return '否';
                    }
                    return value;
                }
            },
            {
                title: '排序',
                field: 'seq',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    return '<a title="编辑" class="btn btn-xs btn-info" onclick="editFunc(&quot;'+row.id+'&quot;)"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteFunc(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>';
                }
            }

        ]


    });

});
function search(){
    $('#func-table-list').bootstrapTable('refresh');
}
//layer
function addFunc() {
    $.get(_platform + '/func/add', function (result) {
        $('#func-layer-div').html(result);
    });
    layer.open({
        area: ['800px', '580px'],
        type: 1,
        title: '添加功能',
        btn: ['保存', '取消'],
        yes: function () {
            $("#funcAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#func-layer-div')
    });
}

function editFunc(id) {
    $.get(_platform + '/func/edit/' + id, function (result) {
        $('#func-layer-div').html(result);
    });
    layer.open({
        area: ['800px', '580px'],
        type: 1,
        title: '编辑功能',
        btn: ['保存', '取消'],
        yes: function () {
            $("#funcEditForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#func-layer-div')
    });
}

function deleteFunc(id) {
    layer.confirm('您是否确定删除功能？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/func/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    layer.msg(result.msg);
                    $('#func-table-list').bootstrapTable("refresh");
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function funcAuthPage() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要授权的功能");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个功能进行授权");
        return false;
    }
    $("#content-main").empty().load(_platform + '/func/auth?id=' + id);
}