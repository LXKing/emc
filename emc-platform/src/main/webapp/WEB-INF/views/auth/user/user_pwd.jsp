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
            <form class="form-horizontal" id="updatePwdForm" role="form">
                <div class="form-group">
                    <label class="col-sm-4  control-label"><span class="red">*</span>原始密码：</label>
                    <div class="col-sm-8">
                        <input id="originalPwd" name="originalPwd" class="form-control" type="password" maxlength="32" placeholder="请输入原始密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4  control-label"><span class="red">*</span>新密码：</label>
                    <div class="col-sm-8">
                        <input id="newPwd" name="newPwd" class="form-control" type="password" maxlength="32" placeholder="请输入新密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4  control-label"><span class="red">*</span>确认密码：</label>
                    <div class="col-sm-8">
                        <input id="confirmPwd" name="confirmPwd" class="form-control" type="password" maxlength="32" placeholder="请输入确认密码">
                    </div>
                </div>
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
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $.validator.addMethod("checkPwd", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:ctx+'/user/check/pwd',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {originalPwd:$('#originalPwd').val()},
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
        }, "原始密码不正确");

        //密码校验,支持所有数字、英文大小写、英文键盘所有特殊符号
        $.validator.addMethod("isPassword", function(value, element){
            var tel = /^[\w\-\@\.\\\!\#\$\%\^\&\*\(\)\+\=\`\~\,\/\<\>\?\;\:\'\"\[\]\{\}\|]+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "请输入正确的密码");

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

        $("#updatePwdForm").validate({
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
                originalPwd: {
                    required: true,
                    isPassword: true,
                    minlength:6,
                    checkPwd: true
                },
                newPwd: {
                    required: true,
                    isPassword: true,
                    minlength:6
                },
                confirmPwd: {
                    required: true,
                    equalTo:"#newPwd",
                    minlength:6
                }
            },
            messages: {
                originalPwd: {
                    required: icon + "请输入原始密码",
                    minlength: icon + "密码必须6个字符以上"
                },
                newPwd: {
                    required: icon + "请输入新密码",
                    minlength: icon + "密码必须6个字符以上"
                },
                confirmPwd: {
                    required: icon + "请输入确认密码",
                    minlength: icon + "密码必须6个字符以上"
                }
            },
            submitHandler:function(){
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:ctx + '/user/update/pwd',
                    data:$('#updatePwdForm').serialize(),
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