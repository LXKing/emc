/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
$(function () {
    $('#role-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/role/list',//获取数据的Servlet地址
        method: 'post',//使用POST请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        idField: "ID",
        pagination: true,//是否分页
        pageSize: 10,//每页显示的记录数
        pageNumber: 1,//当前第几页
        pageList: [10, 30, 50],//记录数可选列表
        search: false,  //是否启用查询
        striped: true,//表格显示条纹
        //showColumns: false,//不显示隐藏列
        sidePagination: "server", //服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {
            var param = {
                _method: "PATCH",
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
            return param;
        }, formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        responseHandler: function (res) {
            return {
                "rows": res.list.list,
                "total": res.list.page.total
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
                title: '角色名称',
                field: 'roleName',
                align: 'center'
            },
            {
                title: '角色说明',
                field: 'roleDes',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    return '<a title="编辑" class="btn btn-xs btn-info" onclick="editRole("+row.id+")"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteRoles()"><i class="fa fa-trash-o"></i></a>&nbsp;' +
                        '<a title="授权权限" class="btn btn-xs btn-warning" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>';
                }
            }

        ]


    });
});

function getHeight() {
    return $(window).height() - 130;
}
//layer
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

function editRole(id) {
    $.get(_platform + '/role/edit/' + id, function (result) {
        $('#role-layer-div').html(result);
    });
    layer.open({
        area: ['800px', '580px'],
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

function roleAuthPage() {
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