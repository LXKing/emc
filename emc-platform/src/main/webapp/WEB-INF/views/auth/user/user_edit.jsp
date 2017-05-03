<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;编辑用户</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userEditFrom" role="form">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="userId" value="${obj.user_id}">
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>中文名称：</label>

                            <div class="col-sm-4">
                                <input name="userName" value="${obj.user_name}" class="form-control" type="text" maxlength="16" placeholder="请输入用户中文名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>手机号码：</label>

                            <div class="col-sm-4">
                                <input name="phone" id="phone" value="${obj.phone}"  class="form-control" type="text" maxlength="11" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>工号：</label>

                            <div class="col-sm-4">
                                <input name="jobNum" id="jobNum" value="${obj.job_num}"  class="form-control" type="text" placeholder="请输入工号，支持_-@">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>登录账号：</label>

                            <div class="col-sm-4">
                                <input name="login" id="login" value="${obj.login}" readonly class="form-control" type="text" maxlength="64" placeholder="请输入登录账号，支持_-@.">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>单位：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input  name="orgId" value="${obj.step_id}"   type="hidden"/>
                                    <input type="text" name="orgName"  id="common-part" placeholder="所属机构" disabled  value="${obj.step_name}" class="form-control" />
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>部门：</label>

                            <div class="col-sm-4">
                                <select id="partId" name="partId" class="chosen-select form-control">
                                    <option value="">请先选择单位</option>
                                    <option value="${obj.part_id}" selected>${obj.part_name}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>性别：</label>

                            <div class="col-sm-4">
                                <select name="sex" class="chosen-select form-control">
                                    <option value="">请选择性别</option>
                                    <option value="0" ${obj.sex eq 0?'selected':''}>男</option>
                                    <option value="1" ${obj.sex eq 1?'selected':''}>女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">出生日期：</label>

                            <div class="col-sm-4">
                                <input id="birthday" name="birthday" value="${obj.birthday}"  class="form-control" type="text" placeholder="请选择出生日期">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">年龄：</label>

                            <div class="col-sm-4">
                                <input name="age" id="age" value="${obj.age}"  class="form-control" type="text" maxlength="3" placeholder="请输入用户年龄">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">联系电话：</label>

                            <div class="col-sm-4">
                                <input name="tel"  value="${obj.tel}" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">电子邮箱：</label>

                            <div class="col-sm-4">
                                <input name="email"  value="${obj.email}" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">备注：</label>

                            <div class="col-sm-4">
                                <textarea class="form-control" rows="6" maxlength="125" name="memo" placeholder="请输入备注">${obj.memo}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6" style="text-align: center">
                                <button type="submit" class="btn btn-sm btn-success">
                                    <i class="fa fa-check-square-o"></i>保存
                                </button>
                                <button type="reset" class="btn btn-sm btn-warning">
                                    <i class="fa fa-retweet"></i>重置
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    ignore: ":not(select):not(input)",//校验chosen
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.insertAfter(element.parent().parent().parent());
        } else if(element.is("select")){
            error.insertAfter(element.parent());
        }else if(element.is(":hidden")){
            error.insertAfter(element.parent().parent());
        }else{
            error.insertAfter(element.parent());
        }
    },
    errorClass: "help-block m-b-none m-t-xs",
    validClass: "help-block m-b-none m-t-none"
});

//以下为官方示例
$(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $('#birthday').datepicker();
    $('#birthday').change(function(){
        var thisYear = new Date().getFullYear();
        var year = $('#birthday').val().substr(0,4);
        $('#age').val(thisYear-year);
    });

    //初始化部门
    $("#common-part").change(function(){
        $(this).parents('.form-group').removeClass('has-error');
        $(this).parents('.form-group').find('span.help-block').remove();
        $.ajax({
            url:ctx+'/dept/select/list',
            type:'POST',
            data: {pOrgId:$('#common-part').siblings(':hidden').val(),_method:'PATCH'},
            dataType: 'json',
            success:function(result) {
                var html = '<option value="">请选择部门</option>';
                $.each(result.list,function(idx,item){
                    html+='<option value="'+item.orgId+'">'+item.orgName+'</option>';
                });
                $('#partId').html(html);
                $('#partId').chosen('destroy').chosen();
            }
        });
    });
    //new PCAS('province','${province}','110000000000','city','${city}','110200000000','county','${county}','110228000000','town','${town}','110228100000','village','110228100002');
    $("#phone").blur(function(){
        if($(this).val()!=null&&$(this).val()!=""&&$("input[name='selectLogin']").val()==1){
            $("#login").val($(this).val());
        }
    });
    $("#jobNum").blur(function(){
        if($(this).val()!=null&&$(this).val()!=""&&$("input[name='selectLogin']").val()==2){
            $("#login").val($(this).val());
        }
    });

    $("input[name='selectLogin']").click(function(){
        if($(this).val()==1){
            $("#login").val($("#phone").val());
        }else if($(this).val()==2){
            $("#login").val($("#jobNum").val());
        }else{
            $("#login").val("");
        }
    });

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

    $.validator.addMethod("phoneUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url:ctx+'/user/check/phone',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {phone:$('#phone').val()},
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
    }, icon + "手机号码已存在");

    $.validator.addMethod("loginUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url:ctx+'/user/check/login',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {login:$('#login').val()},
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
    }, icon + "登录账号已存在");

    $.validator.addMethod("jobNumUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url:ctx+'/user/check/jobNum',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {jobNum:$('#jobNum').val()},
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
    }, icon + "工号已存在");

    // 英文名称验证
    $.validator.addMethod("isEnglishName", function(value, element) {
        var tel = /^\w+$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入英文名称");

    //名称校验
    $.validator.addMethod("isName", function(value, element){
        var tel = /^[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*$/;
        return this.optional(element) || (tel.test(value));
    },icon +  "请输入正确的名称");

    //手机号码校验
    $.validator.addMethod("isPhone", function(value, element){
        var tel = /^(1\d{10})$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入正确的手机号码");

    //工号校验
    $.validator.addMethod("isJobNum", function(value, element){
        var tel = /^[\w\-\@]{1,16}$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入正确的工号");

    //登录账号校验
    $.validator.addMethod("isLogin", function(value, element){
        var tel = /^[\w\-\@\.]+$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入正确的登录账号");

    //密码校验,支持所有数字、英文大小写、英文键盘所有特殊符号
    $.validator.addMethod("isPassword", function(value, element){
        var tel = /^[\w\-\@\.\\\!\#\$\%\^\&\*\(\)\+\=\`\~\,\/\<\>\?\;\:\'\"\[\]\{\}\|]+$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入正确的密码");

    //年龄校验
    $.validator.addMethod("isAge", function(value, element){
        var tel = /^(\d|[1-9]\d|1[0-1]\d|120)$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入0-120的年龄");

    //联系电话校验
    $.validator.addMethod("isTel", function(value, element){
        var tel = /^(0\d{2,3}\-)(([2-9]\d{6,7})|([2-9]\d{6,7})(\-\d{1,4}))$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入有效的固定电话。如010-4888888-8");

    //电子邮箱校验
    $.validator.addMethod("isEmail", function(value, element){
        var tel = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "邮箱格式不正确，请重新输入");

    //出生日期校验
    $.validator.addMethod("isBirthday", function(value, element){
        return this.optional(element) || (value < getFormatDate());
    }, icon + "出生日期必须大于当前时间");

    //提示信息绑定
    $('input:not(:submit):not(:button)').mousedown(function () {
        $(this).closest('.form-group').removeClass('has-error');
        $(this).parent().siblings('.help-block').remove();
    });
    //下拉框信息绑定
    $('select').change(function () {
        if ($(this).find('option:first').val() != $(this).val()) {
            $(this).parent().siblings('.help-block').remove();
        }
        return false;
    });
    // validate signup form on keyup and submit

    $("#userEditFrom").validate({
        onsubmit: true,// 是否在提交是验证
        //移开光标:如果有内容,则进行验证
        onfocusout: function (element) {
            if ($(element).is(":radio")){
                return;
            }
            if ($(element).val() == null || $(element).val() == "") {
                $(element).closest('.form-group').removeClass('has-error');

                if($(element).parent(".chosen-search").length==1){
                    $(element).parents(".chosen-container").parent().siblings('.help-block').remove();
                }else{
                    $(element).parent().siblings('.help-block').remove();
                }

            } else {
                $(element).valid();
            }
        },
        onkeyup: false,// 是否在敲击键盘时验证
        onclick:false,
        rules: {
            userName: {
                required: true,
                isName: true,
                minlength: 2
            },
            phone: {
                required: true,
                isPhone: true
            },
            jobNum: {
                required: true,
                isJobNum: true
            },
            login: {
                required: true,
                isLogin: true
            },
            password: {
                required: true,
                isPassword: true,
                minlength:6
            },
            password1: {
                required: true,
                equalTo:"#password",
                minlength:6
            },
            birthday:{
                isBirthday:true
            },
            age: {
                isAge:true
            },
            sex: {
                required: true
            },
            orgId: {
                required: true
            },
            partId: {
                required: true
            },
            tel:{
                isTel:true
            },
            email:{
                isEmail:true
            }

        },
        messages: {
            userName: {
                required: icon + "请输入用户名称",
                minlength: icon + "用户名称必须2个字符以上"
            },
            phone: {
                required: icon + "请输入手机号码"
            },
            jobNum: {
                required: icon + "请输入工号"
            },
            login: {
                required: icon + "请输入登录账号"
            },
            password: {
                required: icon + "请输入密码",
                minlength:icon + "密码最少6个字符以上"
            },
            password1: {
                required: icon + "请再次输入密码",
                equalTo: icon + "两次输入密码不一致",
                minlength:icon + "密码最少6个字符以上"
            },
            sex: {
                required: icon + "请选择性别"
            },
            orgId: {
                required: icon + "请选择组织机构"
            },
            partId: {
                required: icon + "请选择部门"
            }
        },
        submitHandler: function () {
            var index = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url:ctx + '/user/edit',
                data:$('#userEditFrom').serialize(),
                type:'POST',
                dataType:'json',
                success:function(result) {
                    if(result.flag){
                        layer.closeAll();
                        $("#content-main").empty().load(ctx+'/user/list');
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