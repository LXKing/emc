/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var $table = $('#exampleTableFromData'),
    $remove = $('#remove'),
    selections = [];
$(function () {
    alert("1");
    $table.bootstrapTable({
        height: getHeight()+20,//高度
        cache:false,//禁用 AJAX 数据缓存
        url:_platform+'/role/list?_method=PATCH',//获取数据的Servlet地址
        method: 'POST',//使用POST请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        idField:"ID",
        pagination:true,//是否分页
        pageSize:10,//每页显示的记录数
        pageNumber:1,//当前第几页
        pageList:[10,30,50],//记录数可选列表
        search: false,  //是否启用查询
        striped:true,//表格显示条纹
        showColumns: false,//不显示隐藏列
        sidePagination: "server", //服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "limit",
        queryParams: function queryParams(params){
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
            return param;
        },formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        }


        /*responseHandler: function(res){
         return {
         "rows": res.rows,
         "total": res.total
         };
         },
         columns: [
         [
         {
         field:'ID',title:'ID',visible:false
         },{
         title: '角色名称',
         field: 'roleName',
         align: 'center'
         }, {
         title: '类型',
         field: 'CONFIGNAME',
         align: 'center'
         }, {
         title: '分支名称',
         field: 'MNAME',
         align: 'center'
         }, {
         title: '计量器具编号',
         field: 'MEASURINGINSNO',
         align: 'center'
         }, {
         title: '计量器具名称',
         field: 'MEASURINGINSNAME',
         align: 'center'
         }, {
         title: '安装位置',
         field: 'INSTALLATIONLOCATION',
         align: 'center'
         }, {
         title: '运行状态',
         field: 'FUNCTIONSTATUS',
         align: 'center'
         },{
         title: '操作',
         field: 'ID',
         align: 'center',
         formatter: function(value, row, index){
         return  '<a  class="btn btn-mini btn-info"   onclick="add(\''+row.UNID+'\',\''+row.MID+'\',\''+row.FUNCTIONSTATUS+'\');"  title="添加"><i class="icon-plus"></i></a>'+
         '<a  class="btn btn-mini btn-info"   onclick="update(\''+row.UNID+'\',\''+row.MID+'\',\''+row.FUNCTIONSTATUS+'\',\''+row.ID+'\');"  title="修改"><i class="icon-edit"></i></a>'+
         '<a class="btn btn-mini btn-primary" onclick="change(\''+row.UNID+'\',\''+row.MID+'\',\''+row.FUNCTIONSTATUS+'\',\''+row.ID+'\');"  title="换表"><i class="icon-wrench"></i></a>';
         }
         }
         ]
         ]*/
        // mobileResponsive: true,
    });
    alert("2");
    $table.bootstrapTable('refresh');
});
function getRoleList() {

    /*var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });*/
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

function getHeight() {
    return $(window).height() - 130;
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