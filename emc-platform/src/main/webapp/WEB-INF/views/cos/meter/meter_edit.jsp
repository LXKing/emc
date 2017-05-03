<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/1/10
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;编辑计量器具</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal mae-form-class" id="meterEditFrom" role="form">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" id="meterId" name="meterId" value="${meter.meter_id}">
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>所属单位：</label>

                            <div class="col-sm-4">
                                <div class="input-group col-sm-12">
                                    <input id="orgId" name="orgId" value="${meter.org_id}" type="hidden"/>
                                    <input type="text" id="orgName" value="${meter.org_name}"  name="orgName" placeholder="所属机构" disabled class="form-control"/>
                                    <%--<span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>--%>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">所属设备：</label>

                            <div class="col-sm-4">
                                <div class="input-group col-sm-12">
                                    <input id="maeId" name="maeId"  value="${meter.mae_id}"  type="hidden"/>
                                    <input type="text" id="maeName" name="maeName"  value="${meter.mae_name}"  placeholder="所属设备" disabled class="form-control"/>
                                    <%--<span class="input-group-addon"><a class="common-mae" style="color: #000000">选择</a></span>--%>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>能源类型：</label>

                            <div class="col-sm-4">
                                <select id="typeId" name="typeId" class="chosen-select form-control">
                                    <option value="">请选择能源</option>
                                    <c:forEach var="item" items="${energyTypes}">
                                        <option value="${item.type_id}" ${meter.type_id eq item.type_id?'selected':''}>${item.type_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>器具编码：</label>

                            <div class="col-sm-4">
                                <input name="meterCode" id="meterCode" class="form-control" type="text" maxlength="16" value="${meter.meter_code}"
                                       placeholder="请输入器具编码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>器具名称：</label>

                            <div class="col-sm-4">
                                <input name="meterName" id="meterName" class="form-control" type="text" maxlength="16" value="${meter.meter_name}" placeholder="请输入器具名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>器具类型：</label>

                            <div class="col-sm-4">
                                <select name="meterType" class="chosen-select form-control">
                                    <c:forEach var="item" items="${applicationScope.dicList['meterType']}">
                                        <option value="${item.dicName}">${item.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>倍率：</label>

                            <div class="col-sm-4">
                                <input name="rate" id="rate" class="form-control" type="text" maxlength="8" value="${meter.rate}" placeholder="请输入倍率">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>是否预存：</label>

                            <div class="col-sm-4">
                                <select name="prestore" class="chosen-select form-control">
                                    <c:forEach var="item" items="${applicationScope.dicList['prestore']}">
                                        <option value="${item.dicName}"  ${meter.prestore eq item.dicName?'selected':''}>${item.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">安装位置：</label>

                            <div class="col-sm-4">
                                <input name="location" id="location" class="form-control" value="${meter.location}" maxlength="32" type="text" placeholder="请输入安装位置">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">备注：</label>

                            <div class="col-sm-4">
                                <textarea class="form-control" rows="6" maxlength="125" name="memo"  value="${meter.memo}" placeholder="请输入备注"></textarea>
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
            } else if (element.is("select")) {
                error.insertAfter(element.parent());
            } else if (element.is(":hidden")) {
                error.insertAfter(element.parent().parent());
            } else {
                if (element.parent().hasClass('input-group')) {
                    error.insertAfter(element.parent().parent());
                } else {
                    error.insertAfter(element.parent());
                }
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {
        var icon = "<i class='fa fa-times-circle'></i> ";

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

        $("#orgName").change(function(){
            $("#maeName").val("");
            $("#maeId").val("");
            $(this).parents('.form-group').removeClass('has-error');
            $(this).parents('.form-group').find('span.help-block').remove();
        });

        $.validator.addMethod("codeUnique", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: ctx + '/meter/check/code',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {meterCode: value,meterId:$('#meterId').val()},
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
        }, icon + "器具编码已经存在");

        $.validator.addMethod("nameUnique", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: ctx + '/meter/check/name',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {meterName: value,orgId:$("#orgId").val(),meterId:$('#meterId').val()},
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
        }, icon + "该单位下器具名称已经存在");

        // 倍率验证
        $.validator.addMethod("isRate", function (value, element) {
            var tel = /^(([0-9].[0-9]{0,2})|([1-9][0-9]*))$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "倍率为大于0的数字");


        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            if($(this).parent().hasClass('input-group')){
                $(this).parent().parent().siblings('.help-block').remove();
            }else{
                $(this).parent().siblings('.help-block').remove();
            }
        });
        //下拉框信息绑定
        $('select').change(function () {
            if ($(this).find('option:first').val() != $(this).val()) {
                $(this).parent().siblings('.help-block').remove();
            }
            return false;
        });
        // validate signup form on keyup and submit

        $("#meterEditFrom").validate({
            onsubmit: true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).is(":radio")) {
                    return;
                }
                if ($(element).val() == null || $(element).val() == "") {
                    $(element).closest('.form-group').removeClass('has-error');

                    if ($(element).parent(".chosen-search").length == 1) {
                        $(element).parents(".chosen-container").parent().siblings('.help-block').remove();
                    } else {
                        $(element).parent().siblings('.help-block').remove();
                    }

                } else {
                    $(element).valid();
                }
            },
            onkeyup: false,// 是否在敲击键盘时验证
            onclick: false,
            rules: {
                orgId: {
                    required: true
                },
                typeId: {
                    required: true
                },
                meterCode: {
                    required: true,
                    codeUnique: true
                },
                meterName: {
                    required: true,
                    nameUnique:true
                },
                rate: {
                    required: true,
                    isRate:true
                }

            },
            messages: {
                orgId: {
                    required: icon + "请选择所属单位"
                },
                typeId: {
                    required: icon + "请选择所属单位"
                },
                meterCode: {
                    required: icon + "请输入器具编码"
                },
                meterName: {
                    required: icon + "请输入器具名称"
                },
                rate: {
                    required: icon + "请输入倍率"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: ctx + '/meter/edit',
                    data: $('#meterEditFrom').serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            layer.closeAll();
                            $("#content-main").empty().load(ctx + '/meter/list');
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