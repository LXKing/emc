/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var bTable;
$(function () {
    bTable = $('#station-table-list').bootstrapTable({
        height: "100%",//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/station/list',//获取数据的Servlet地址
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
        queryParams: queryParams,
        formatLoadingMessage: function () {
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
                field: 'seq',
                align: 'center',
                formatter:function(value,row,index){
                    return index+1;
                }
            },
            {
                title: '热力站名称',
                field: 'stationName',
                align: 'center'
            },
            {
                title: '上级单位',
                field: 'org',
                align: 'center'
            },

            {
                title: '地址',
                field: 'addr',
                align: 'center',
                formatter:function(value,row,index){
                    if(value.length>20){
                        return '<span title="'+value+'">'+value.substr(0,20)+'</span>';
                    }
                    return value;
                }
            },
            {
                title: '经度',
                field: 'lng',
                align: 'center'
            },
            {
                title: '纬度',
                field: 'lat',
                align: 'center'
            },
            {
                title: '公建面积',
                field: 'publicArea',
                align: 'center'
            },
            {
                title: '居民面积',
                field: 'dwellArea',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    return '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="stationEditForm" layer-title="编辑角色" layer-url="'+_platform+'/station/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a title="删除" class="btn btn-xs btn-danger" onclick="deletestation(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;' +
                        '<a title="授权权限" class="btn btn-xs btn-warning" onclick="stationAuthPage()"><i class="fa fa-wrench"></i></a>';
                }
            }

        ]


    });


    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

    //下拉框js
    $(".chosen-select").chosen();

});

function queryParams(params) {
    return {
        orgId:$("#orgId").val(),
        stationName:$('input[name="stationName"]').val(),
        _method: "PATCH",
        pageNumber: params.pageNumber,
        pageSize: params.pageSize
    };

}

function treeNodeClick(e,treeId,treeNode){
    search();
}

function search(){
    $('#station-table-list').bootstrapTable('refresh');
}

function deletestation(id) {
    layer.confirm('您是否确定删除角色？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/station/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#station-table-list').bootstrapTable("refresh");
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}

