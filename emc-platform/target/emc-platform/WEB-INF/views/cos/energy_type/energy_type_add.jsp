<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form class="form-horizontal" id="energyTypeAddForm" role="form">

                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>名称：</label>

                    <div class="col-sm-8">
                        <input id="typeName" name="typeName" class="form-control" type="text" maxlength="16" placeholder="请输入名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>计算单位：</label>

                    <div class="col-sm-8">
                        <input name="cell" class="form-control" type="text" maxlength="12"
                               placeholder="请输入计算单位,没有单位是无量纲">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>默认单价：</label>

                    <div class="col-sm-8">
                        <input name="price" class="form-control" type="text" maxlength="10"
                               placeholder="请输入默认单价(元)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>经济类型：</label>

                    <div class="col-sm-8">
                        <select name="economicType" class="chosen-select form-control">
                            <c:forEach var="item" items="${applicationScope.dicList['economicType']}">
                                <option value="${item.dicName}">${item.dicDes}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>排序：</label>

                    <div class="col-sm-8">
                        <input name="seq" class="form-control" type="text" maxlength="4" placeholder="请输入排序值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">备注：</label>

                    <div class="col-sm-8">
                        <textarea class="form-control" rows="6" maxlength="125" name="memo"
                                  placeholder="请输入备注"></textarea>

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
        $.validator.addMethod("checkOrgName", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: ctx + '/energy-type/check/name',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {typeName: $('#typeName').val(), typeId:$('#typeId').val()},
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
        }, icon + "能源名称已经存在");

        $("#energyTypeAddForm").validate({
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
                typeName: {
                    required: true,
                    checkOrgName: true
                },
                price:{
                  required: true
                },
                cell: {
                    required: true
                },
                seq: {
                    required: true
                }
            },
            messages: {
                typeName: {
                    required: icon + "请输入名称"
                },
                cell: {
                    required: icon + "请输入计算单位"
                },
                price: {
                    required: icon + "请输入默认单价"
                },
                seq: {
                    required: icon + "请输入排序"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: ctx + '/energy-type/add',
                    data: $('#energyTypeAddForm').serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            layer.closeAll();
                            $("#content-main").empty().load(ctx + '/energy-type/list');
                            layer.msg(result.msg);
                        } else {
                            layer.close(index);
                            layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>