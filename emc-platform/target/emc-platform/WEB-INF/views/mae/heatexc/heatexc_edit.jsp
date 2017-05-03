<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;编辑换热器</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="heatexcEditFrom" role="form">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="maeId" value="${heatexc.mae_id}">
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>单位：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="orgId" value="${heatexc.org_id}" type="hidden"/>
                                    <input type="text" name="orgName" value="${heatexc.org_name}" id="common-part" placeholder="所属机构" disabled
                                           class="form-control"/>
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>设备名称：</label>

                            <div class="col-sm-4">
                                <input name="maeName" class="form-control" type="text" maxlength="16" value="${heatexc.mae_name}"
                                       placeholder="请输入设备名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>产品编号：</label>

                            <div class="col-sm-4">
                                <input name="maeNum" class="form-control" type="text" maxlength="32" value="${heatexc.mae_num}"
                                       placeholder="请输入产品编号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>设备型号：</label>

                            <div class="col-sm-4">
                                <input name="maeTypeNum" class="form-control" type="text" maxlength="32" value="${heatexc.mae_type_num}"
                                       placeholder="请输入设备型号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>最大能效：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="eersMax" class="form-control" type="text" maxlength="8" value="${heatexc.eers_max}"
                                           placeholder="请输入最大能效">
                                    <span class="input-group-addon">%</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>最小能效：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="eersMin" class="form-control" type="text" maxlength="8" value="${heatexc.eers_min}"
                                           placeholder="请输入最小能效">
                                    <span class="input-group-addon">%</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>生产厂商：</label>

                            <div class="col-sm-4">
                                <input name="firm" class="form-control" type="text" maxlength="32" value="${heatexc.firm}"
                                       placeholder="请输入生产厂商">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>出厂日期：</label>

                            <div class="col-sm-4">
                                <input name="firmDate" id="firmDate" class="form-control" type="text" value="${heatexc.firm_date}"
                                       placeholder="请选择出厂日期">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">厂商电话：</label>

                            <div class="col-sm-4">
                                <input name="firmTel" class="form-control" type="text" maxlength="32" value="${heatexc.firm_tel}"
                                       placeholder="请输入厂商电话">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">传热量：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="heatTransfer" class="form-control" type="text" maxlength="16" value="${heatexc.heat_transfer}"
                                           placeholder="请输入传热量">
                                    <span class="input-group-addon">MW</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">传热面积：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="transferArea" class="form-control" type="text" maxlength="16" value="${heatexc.transfer_area}"
                                           placeholder="请输入传热面积">
                                    <span class="input-group-addon">㎡</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">一次侧水温度：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="oswTemp" class="form-control" type="text" maxlength="16" value="${heatexc.osw_temp}"
                                           placeholder="请输入一次侧水温度">
                                    <span class="input-group-addon">℃</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">一次侧承压：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="oswPressure" class="form-control" type="text" maxlength="16" value="${heatexc.osw_pressure}"
                                           placeholder="请输入一次侧承压">
                                    <span class="input-group-addon">MPa</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">二次侧水温：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="sswTemp" class="form-control" type="text" maxlength="16" value="${heatexc.ssw_temp}"
                                           placeholder="请输入二次侧水温">
                                    <span class="input-group-addon">℃</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">二次侧承压：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="sswPressure" class="form-control" type="text" maxlength="16" value="${heatexc.ssw_pressure}"
                                           placeholder="请输入二次侧承压">
                                    <span class="input-group-addon">MPa</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">设备重量：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="maeScale" class="form-control" type="text" maxlength="16" value="${heatexc.mae_scale}"
                                           placeholder="请输入设备重量">
                                    <span class="input-group-addon">kg</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">进出口管径：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="dn" class="form-control" type="text" maxlength="16" value="${heatexc.dn}"
                                           placeholder="请输入进出口管径">
                                    <span class="input-group-addon">DN</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>排序：</label>

                            <div class="col-sm-4">
                                <input name="seq" class="form-control" type="text" maxlength="4"  value="${heatexc.seq}" placeholder="请输入排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">备注：</label>

                            <div class="col-sm-4">
                                <textarea class="form-control" rows="6" maxlength="125" name="memo"
                                          placeholder="请输入备注">${heatexc.memo}</textarea>
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
    $('#firmDate').datepicker({startView:'decade'});

    $("#common-part").change(function(){
        $(this).parents('.form-group').removeClass('has-error');
        $(this).parents('.form-group').find('span.help-block').remove();
    });

    $.validator.addMethod("nameUnique", function (value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url: ctx + '/heatexc/check/name',
            type: 'POST',
            async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {maeName: value,maeId:${heatexc.mae_id}},
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
    }, icon + "设备名称已存在");


    //数值型校验
    $.validator.addMethod("isNum", function (value, element) {
        var tel = /^\d+|\d+.\d+$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入数字");


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

    $("#heatexcEditFrom").validate({
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
            maeName: {
                required: true,
                nameUnique: true,
                minlength: 2
            },
            maeNum: {
                required: true
            },
            maeTypeNum: {
                required: true
            },
            eersMax: {
                required: true,
                isNum: true
            },
            eersMin: {
                required: true,
                isNum: true
            },
            firm: {
                required: true
            },
            firmDate: {
                required: true
            },
            heatTransfer: {
                isNum: true
            },
            transferArea: {
                isNum: true
            },
            oswTemp: {
                isNum: true
            },
            oswPressure: {
                isNum: true
            },
            sswTemp: {
                isNum: true
            },
            sswPressure: {
                isNum: true
            },
            maeScale: {
                isNum: true
            },
            dn: {
                isNum: true
            },
            seq: {
                required: true
            }
        },
        messages: {
            orgId: {
                required: icon + '所属单位不能为空'
            },
            maeName: {
                required: icon + '设备名称不能为空',
                minlength: icon + '设备名称至少2个字符'
            },
            maeNum: {
                required: icon + '产品编号不能为空'
            },
            maeTypeNum: {
                required: icon + '设备型号不能为空'
            },
            eersMax: {
                required: icon + '最大能效不能为空'
            },
            eersMin: {
                required: icon + '最小能效不能为空'
            },
            firm: {
                required: icon + '生产厂家不能为空'
            },
            firmDate: {
                required: icon + '出厂日期不能为空'
            },
            seq: {
                required: icon + '排序不能为空'
            }
        },
        submitHandler: function () {
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: ctx + '/heatexc/edit',
                data: $('#heatexcEditFrom').serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        layer.closeAll();
                        $("#content-main").empty().load(ctx + '/heatexc/list');
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