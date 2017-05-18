/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
	var userTable = $('#user-table-list').bootstrapTable({
		height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/user/list',//获取数据的Servlet地址
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
            formsParam(param,'user-form',true);
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
                title: '备注',
                field: 'memo',
                align: 'center',
                formatter:function(value,row,index){
                    if(value.length>20){
                        return '<span title="'+value+'">'+value.substr(0,20)+'</span>';
                    }
                    return value;
                }
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    return '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="roleEditForm" layer-title="编辑角色" layer-url="'+_platform+'/role/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteRole(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;' +
                        '<a title="授权权限" class="btn btn-xs btn-warning" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>';
                }
            }

        ]
	});
	initDateBox(['cStartTime','cEndTime','lStartTime','lEndTime']);
});

/**
 * 初始化日期框
 * @param ids
 */
function initDateBox(ids){
	if(ids!=null&&ids.length>0){
		for(var index in ids){
			if(!isNaN(index)){
				var id = ids[index];
				var datebox = {
			        elem: '#'+id,
			        format: 'YYYY-MM-DD hh:mm:ss',
			        max: '2099-06-16 23:59:59',
			        istime: true,
			        istoday: false,
			        choose: function (datas) {
			            start.max = datas; //结束日选好后，重置开始日的最大日期
			        }
			    };
			    laydate(datebox);
			}
		}
	}
}
/**
 * 点击“搜索”按钮事件
 */
function searchUser(){
	$('#user-table-list').bootstrapTable('refresh');
}

/**
 * 获取页面表单中的input和select元素的name和value，并放入param对象中
 * @param param form请求参数对象
 * @param formId form表单id
 * @param isVisible 是否只获取可见元素
 */
function formsParam(param,formId,isVisible){
	var forms=null;
	if(isVisible){
		forms = $('#'+formId+' input:visible');
		forms.push($('#'+formId+' select:visible'));
	}else{
		forms = $('#'+formId+' input');
		forms.push($('#'+formId+' select'));
	}
    for(var i in forms){
    	if(!isNaN(i)){
    		var k = $(forms[i]).attr("name");
    		var v = $(forms[i]).val();
    		param[k] = v;
    	}
    }
}
/* 编辑用户 */
//function editUser() {
//    var id = getCheckValues();
//    if (id.length == 0) {
//        layer.msg("请选择要编辑的用户");
//        return false;
//    }
//    if (id.split(',').length > 1) {
//        layer.msg("请选择一个用户进行编辑");
//        return false;
//    }
//    menuItemUri(ctx + '/user/edit/' + id);
//}

/**
 * 重置密码
 */
//function resetPwd(id) {
//    var index = layer.load(1, {
//        shade: [0.1, '#fff'] //0.1透明度的白色背景
//    });
//    $.ajax({
//        url: ctx + '/user/reset/pwd',
//        timeout: 5000,
//        type: 'POST',
//        dataType: 'json',
//        data: {id: id},
//        success: function (result) {
//            layer.close(index);
//            layer.msg(result.msg);
//        },
//        error: function () {
//            layer.close(index);
//            layer.msg("重置密码失败");
//        },
//        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
//            layer.close(index);
//            if (status == 'timeout') {//超时,status还有success,error等值的情况
//                layer.msg("请求超时");
//            }
//        }
//    });
//}

/**
 * 禁用
 */
//function disable(id, status, tr) {
//    var index = layer.load(1, {
//        shade: [0.1, '#fff'] //0.1透明度的白色背景
//    });
//    $.ajax({
//        url: ctx + '/user/status',
//        timeout: 5000,
//        type: 'POST',
//        dataType: 'json',
//        data: {id: id, status: status},
//        success: function (result) {
//            layer.close(index);
//            layer.msg(result.msg);
//            if (result.flag) {
//                var html = '<a class="btn btn-info btn-xs btn-bitbucket user-enable" title="启用" onclick="enable(&quot;' + id + '&quot;,1,this)"><i class="fa fa-unlock"></i></a>'
//                $(tr).after(html);
//                $(tr).remove();
//            }
//        },
//        error: function () {
//            layer.close(index);
//            layer.msg("禁用失败");
//        },
//        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
//            layer.close(index);
//            if (status == 'timeout') {//超时,status还有success,error等值的情况
//                layer.msg("请求超时");
//            }
//        }
//    });
//}

/**
 * 启用
 */
//function enable(id, status, tr) {
//    var index = layer.load(1, {
//        shade: [0.1, '#fff'] //0.1透明度的白色背景
//    });
//    $.ajax({
//        url: ctx + '/user/status',
//        timeout: 5000,
//        type: 'POST',
//        dataType: 'json',
//        data: {id: id, status: status},
//        success: function (result) {
//            layer.close(index);
//            layer.msg(result.msg);
//            if (result.flag) {
//                var html = '<a class="btn btn-danger btn-xs btn-bitbucket user-disable" title="禁用" onclick="disable(&quot;' + id + '&quot;,1,this)"><i class="fa fa-unlock-alt"></i></a>'
//                $(tr).after(html);
//                $(tr).remove();
//            }
//        },
//        error: function () {
//            layer.close(index);
//            layer.msg("启用失败");
//        },
//        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
//            layer.close(index);
//            if (status == 'timeout') {//超时,status还有success,error等值的情况
//                layer.msg("请求超时");
//            }
//        }
//    });
//}

//function deleteUsers() {
//
//    var ids = getCheckValues();
//    if (ids.length == 0) {
//        layer.msg("请选择要删除的用户");
//        return false;
//    }
//    layer.confirm('您是否确定删除所选用户？', {
//        btn: ['确定', '取消'] //按钮
//    }, function () {
//        var index = layer.load(1, {
//            shade: [0.1, '#fff'] //0.1透明度的白色背景
//        });
//        $.ajax({
//            url: ctx + '/user/delete',
//            data: {ids: ids},
//            type: 'POST',
//            dataType: 'json',
//            success: function (result) {
//                if (result.flag) {
//                    layer.closeAll();
//                    $("#content-main").empty().load(ctx + '/user/list');
//                    layer.msg(result.msg);
//                } else {
//                    layer.close(index);
//                    layer.msg(result.msg);
//                }
//            }
//        });
//    });
//}

//function deleteUser(id) {
//    layer.confirm('您是否确定删除用户？', {
//        btn: ['确定', '取消'] //按钮
//    }, function () {
//        var index = layer.load(1, {
//            shade: [0.1, '#fff'] //0.1透明度的白色背景
//        });
//        $.ajax({
//            url: ctx + '/user/delete/' + id,
//            type: 'DELETE',
//            dataType: 'json',
//            success: function (result) {
//                if (result.flag) {
//                    layer.closeAll();
//                    $("#content-main").empty().load(ctx + '/user/list');
//                    layer.msg(result.msg);
//                } else {
//                    layer.close(index);
//                    layer.msg(result.msg);
//                }
//            }
//        });
//    });
//}

/**
 * 分配角色
 * @returns {boolean}
 */
//function userRolePage() {
//    var id = getCheckValues();
//    if (id.length == 0) {
//        layer.msg("请选择要分配角色的用户");
//        return false;
//    }
//    if (id.split(',').length > 1) {
//        layer.msg("请选择一个用户进行分配角色");
//        return false;
//    }
//
//    $.get(ctx + '/user/role?id=' + id, function (result) {
//        $('#layer-div').html(result);
//    });
//    layer.open({
//        area: ['800px', '600px'],
//        type: 1,
//        title: '分配角色',
//        btn: ['确定', '取消'],
//        yes: function () {
//            var roleIds = new Array();
//            var userId = $("#roleUserId").val();
//            $('input[name="roleIds[]"]').each(function (idx, item) {
//                roleIds.push(item.value);
//            });
//            $.ajax({
//                url: ctx + '/user/role',
//                type: 'POST',
//                dataType: 'json',
//                traditional: true,//如果Post是string数组或者int数组，则ajax中traditional: true,
//                // 如果Post是对象数组，则ajax中traditional: false,否则对象将为空
//                data: {userId: userId, roleIds: roleIds},
//                success: function (result) {
//                    if (result.flag) {
//                        layer.closeAll();
//                        $("#content-main").empty().load(ctx + '/user/list');
//                        layer.msg(result.msg);
//                    } else {
//                        layer.close(index);
//                        layer.msg(result.msg);
//                    }
//                }
//            });
//        },
//        skin: 'layer-ext-moon', //样式类名
//        closeBtn: 1, //不显示关闭按钮
//        shift: 2,
//        shadeClose: true, //开启遮罩关闭
//        content: $('#layer-div')
//    });
//}
