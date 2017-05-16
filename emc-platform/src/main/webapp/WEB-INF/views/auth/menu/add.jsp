<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="menuAddForm" role="form">
                <input id="menuType" name="menuType" value="${menuType}" hidden="hidden"/>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单类型：</label>
                    <select name="menuType" class="chosen-select" disabled="disabled">
                        <option <c:if test="${menuType eq 1}">selected="selected" </c:if>  value="0">前台</option>
                        <option <c:if test="${menuType eq 2}">selected="selected" </c:if>  value="1">后台</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单名称：</label>
                    <div class="col-sm-8">
                        <input id="menuName" name="menuName" class="form-control" type="text" maxlength="8" placeholder="请输入菜单名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单路径：</label>
                    <div class="col-sm-8">
                        <input id="menuUrl" name="menuUrl" class="form-control" type="text" maxlength="128" placeholder="请输入菜单路径">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单类型：</label>
                    <select name="type" class="chosen-select" >
                        <option  value="0">模块</option>
                        <option   value="1">菜单</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单序号：</label>
                    <div class="col-sm-8">
                        <input id="seq" name="seq" class="form-control" type="text" maxlength="128" placeholder="请输入菜单序号">
                    </div>
                </div>
                    <input id="pMenuId" name="pMenuId" value="${pId}" hidden="hidden"/>
            </form>
        </div>
    </div>
</div>
<script>
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.appendTo(element.parent().parent().parent());
            } else {
                error.appendTo(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {

        $.validator.addMethod("checkUnique", function(value, element) {

            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/menu/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {menuName:value},
                dataType: 'json',
                success:function(result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "菜单名称已存在");

        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if($(this).find('option:first').val()!=$(this).val()){
                $(this).siblings('.help-block').remove();
            }
            return false;
        });
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#menuAddForm").validate({
            onsubmit:true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).val()==null||$(element).val()=="") {
                    $(element).closest('.form-group').removeClass('has-error');
                    $(element).siblings('.help-block').remove();
                }else{
                    $(element).valid();
                }
            },
            onkeyup :false,// 是否在敲击键盘时验证
            rules: {
                menuName: {
                    required: true,
                    minlength: 2,
                    checkUnique: true
                },
                menuUrl: {
                    required: true
                },
                seq:{
                    required: true
                },
                type:{
                    required: true
                }
            },
            messages: {
                menuName: {
                    required: icon + "请输入菜单名称",
                    minlength: icon + "菜单名称必须2个字符以上"
                },
                menuUrl: {
                    required: icon + "请输入菜单路径"
                },
                seq :{
                    required: icon + "请输入序号"
                },
                type:{
                    required: icon + "请选择菜单类型"
                }
            },
            submitHandler:function(){
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:_platform + '/menu/add',
                    data:$('#menuAddForm').serialize(),
                    type:'POST',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            layer.closeAll();
                            layer.msg(result.msg);
                        }else{
                            layer.close(index);
                            layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>