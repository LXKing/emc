<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">

            <form class="form-horizontal" id="functionEditForm" role="form"  >
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" name="funcId" value="${function.func_id}">
                <div class="form-group">
                    <label class="col-sm-3  control-label">所属菜单：</label>

                    <div class="col-sm-8">
                        <input name="pMenuName" class="form-control" value="${menu.menu_name}" type="text" disabled>
                        <input type="hidden" name="menuId" value="${menu.menu_id}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>功能名称：</label>

                    <div class="col-sm-8">
                        <input name="functionName" value="${function.func_name}" class="form-control" type="text" maxlength="16" placeholder="请输入功能名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>唯一标识：</label>

                    <div class="col-sm-8">
                        <input id="uName" value="${function.uname}" readonly name="uName" class="form-control" type="text" maxlength="32" placeholder="请输入唯一标识">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>排序：</label>

                    <div class="col-sm-8">
                        <input name="seq" value="${function.seq}" class="form-control" type="text" maxlength="3" placeholder="请输入排序值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">备注：</label>

                    <div class="col-sm-8">
                        <textarea class="form-control" rows="6" maxlength="125" name="memo"
                                  placeholder="请输入备注">${function.demo}</textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<script>
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    $.validator.setDefaults({
        ignore: ":hidden:not(select)",//校验chosen
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.insertAfter(element.parent().parent());
            } else if(element.is("select")){
                error.insertAfter(element.next());
            }else{
                error.insertAfter(element);
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {

        // 英文名称验证
        $.validator.addMethod("isEnglishName", function(value, element) {
            var tel = /^\w+$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入英文名称");

        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if ($(this).find('option:first').val() != $(this).val()) {
                $(this).siblings('.help-block').remove();
            }
            return false;
        });
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#functionEditForm").validate({
            onsubmit: true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).val() == null || $(element).val() == "") {
                    $(element).closest('.form-group').removeClass('has-error');
                    $(element).siblings('.help-block').remove();
                } else {
                    $(element).valid();
                }
            },
            onkeyup: false,// 是否在敲击键盘时验证
            rules: {
                functionName: {
                    required: true,
                    minlength: 2
                },
                uName: {
                    required: true,
                    minlength: 4,
                    isEnglishName:true
                },
                seq: {
                    required: true
                }
            },
            messages: {
                functionName: {
                    required: icon + "请输入菜单名称",
                    minlength: icon + "功能名称必须2个字符以上"
                },
                uName: {
                    required: icon + "请输入唯一标识",
                    minlength: icon + "唯一标识必须4个字符以上"
                },
                seq: {
                    required: icon + "请输入排序值"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:ctx + '/function/edit',
                    data:$('#functionEditForm').serialize(),
                    type:'POST',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            layer.closeAll();
                            parent.getFunctions($("#menuId").val());
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