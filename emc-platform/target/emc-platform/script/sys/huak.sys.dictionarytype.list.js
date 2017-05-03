/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
   getDictionaryList();
});
function init(){
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
}
/* 查询用户列表 */
function getDictionaryList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/dictionaryType/list',
        timeout: 15000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#dictionaryType-form").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getDictionaryList
            });
            // 附上模板
            $("#dictionaryType-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#dictionaryType-tbody").processTemplate(result.list);
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
/*重置查询条件*/
var reset = function(){
    $("#dicTypeCode").val("");
    $("#dicTypeDes").val("");
    $("#dicTypeName").val("");
}

/*查询*/
var search = function(){
    getDictionaryList();
}


/* 跳转到添加页并弹出窗口 */
function addDictionary() {
    $('#layer-div').html("");
    $.get(ctx + '/dictionaryType/add', function (result) {
        $('#dictionarytype-div').html(result);
    });
    window =layer.open({
        area: ['550px', '440px'],
        type: 1,
        title: '字典类型',
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 1,
        btn: ['保存', '取消'],
        scrollbar: false,
        yes: function () {
            $("#addForm").submit();
        },
        content: $('#dictionarytype-div')
    });
}

var editDictionary = function (id){
    debugger;
    var ids;
    if(id == undefined){
        ids= getCheckValues();
        if (ids.length == 0) {
            layer.msg("请选择一条记录进行编辑！");
            return false;
        }
        if (ids.length > 1) {
            layer.msg("只能选择一条记录进行编辑！");
            return false;
        }
    }else{
        ids = id;
    }

    $.get(ctx + '/dictionaryType/edit/' + ids, function (result) {
        $('#dictionarytype-div').html(result);
    });
    layer.open({
        area: ['600px', '500px'],
        type: 1,
        title: '字典类型',
        btn: ['保存', '取消'],
        yes: function () {
            $("#editForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#dictionarytype-div')
    });
}

var delDictionary = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues();
        if (ids.length == 0) {
            layer.msg("至少选择一条记录进行删除！");
            return false;
        }
    }else{
        ids = id;
    }
    layer.confirm('是否删除数据？', {
        btn: ['删除', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/dictionaryType/delete',
            type: 'POST',
            dataType: 'json',
            data: {dicTypeId: ids},
            success: function (result) {

                if (result.flag) {
                    layer.closeAll();
                    getDictionaryList();
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });



}


/* 添加菜单回调函数 */
function addCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/dictionaryType/add' ,
        data:$("#addForm").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getDictionaryList();
                layer.msg(result.msg);
                layer.close(index);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

/* 添加菜单回调函数 */
function editCallback(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/dictionaryType/edit' ,
        data:$("#editForm").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getDictionaryList();
                layer.msg(result.msg);
                layer.close(index);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

/**
 * 关闭添加窗口
 */
var closeWindow = function (index) {
    layer.closeAll();
}


