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
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="oncenetEditForm" role="form">
                <input type="hidden" name="id" value="${oncenet.id}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管线类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">

                        <select id="feedType" name="netTypeId" class="form-control" type="text" maxlength="8" data-placeholder="请输入热源性质">
                            <option value =1>1</option>
                            <option value =2>2</option>
                            <option value=3>3</option>
                            <option value=4>4</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管线长度：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="length" class="form-control" value="${oncenet.length}"  type="text" maxlength="16" placeholder="请输入管线长度">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>小室数量：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="cellNum" class="form-control" value="${oncenet.cellNum}" type="text" maxlength="16" placeholder="请输入小室数量">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管段数量：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="partNum" class="form-control" value="${oncenet.partNum}" type="text" maxlength="16" placeholder="请输入管段数量">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>输送介质：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="medium" class="form-control" value="${oncenet.medium}"  type="text" maxlength="16" placeholder="请输入输送介质">
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
        var $form = $(top.document).find("#oncenetEditForm");
        $.validator.addMethod("checkUnique", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _platform + '/feed/check',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {type: $('#type').val()},
                dataType: 'json',
                success: function (result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "角色名称已存在");

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

        $form.validate({
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
                roleName: {
                    required: true,
                    minlength: 2
                    //checkUnique: true
                },
                roleDes: {
                    required: true
                }
            },
            messages: {
                roleName: {
                    required: icon + "请输入角色名称",
                    minlength: icon + "角色名称必须2个字符以上"
                },
                roleDes: {
                    required: icon + "请输入角色描述"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                alert($form.serialize());
                $.ajax({
                    url: _platform + '/oncenet/editvalue',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#oncenet-table-list').bootstrapTable("refresh");
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>