<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper wrapper-content">
    <div class="row col-sm-12 col-xs-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="alarmConfigAddForm" dic="form">
                <input type="hidden" id="comId" name="comId" value="${company.id}">
                <input type="hidden" id="orgId" name="orgId" value="${org.id}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>单位类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="unitType" name="unitType" class="chosen-select form-control">
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>用能单位：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="unitId" name="unitId" class="chosen-select form-control">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>点名：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="tag" class="form-control inputs-lg"  type="text" maxlength="32" placeholder="请输入点名">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警描述：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="alarmName" class="form-control inputs-lg"  type="text" maxlength="32" placeholder="请输入报警描述">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmType" name="alarmType" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警等级：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmLevel" name="alarmLevel" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmLevel']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" style="display: none">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>报警模式：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmModel" name="alarmModel" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmModel']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警阈值：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="num" class="form-control inputs-lg" type="text" maxlength="16" placeholder="请输入报警阈值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>是否启用：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="isenable" name="isenable" class="chosen-select form-control">
                            <option value="0">启用</option>
                            <option value="1">停用</option>
                        </select>
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
        var $form = $(top.document).find("#alarmConfigAddForm");

        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        $("#unitType").chosen().on('change',function () {
            //加载用能单位
            getUnitSelect();
        });

        $("#alarmType").chosen().on('change',function () {
            if(0==$(this).val()){
//                $("#alarmModel").chosen("destroy");
                $("#alarmModel").parents('.form-group').hide();
            }else{
//                $("#alarmModel").chosen("destroy").chosen();
                $("#alarmModel").parents('.form-group').show();
            }
        });
        //加载用能单位
        getUnitSelect();
        //加载tag
        //getTagSelect();

        //小数校验
        $.validator.addMethod("isFloat", function(value, element){
            var tel = /^\d+(\.\d{1,4})?$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "请输入正整数或者小数,小数点后不超过四位");

        //提示信息绑定
        $(top.document).on('mousedown','input:not(:submit):not(:button)',function(){
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        //下拉框js
        $(top.document).find(".chosen-select").chosen().on('change',function () {
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
                unitType: {
                    required: true
                },
                unitId: {
                    required: true
                },
                tag: {
                    required: true
                },
                alarmName: {
                    required: true
                },
                num: {
                    required: true,
                    isFloat: true
                }
            },
            messages: {
                unitType: {
                    required: icon + "请选择单位类型"
                },
                unitId: {
                    required: icon + "请选择用能单位"
                },
                tag: {
                    required: icon + "请输入点名"
                },
                alarmName: {
                    required: icon + "请输入报警描述"
                },
                num: {
                    required: icon + "请输入报警阈值"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _web + '/alarm/config/add',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            queryAlarmConfig();
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });

    function getUnitSelect() {
        $.ajax({
            url: _web + '/select/org/unit',
            type: 'POST',
            data: {comId: $("#comId").val(),orgId:$("#orgId").val(),unitType:$("#unitType").val()},
            dataType: 'json',
            success: function (result) {
                var $element = $("#unitId");
                $element.empty();
                $.each(result, function (idx, item) {
                    $element.append('<option value="' + item.UNITID + '">' + item.UNITNAME + '</option>');
                });
                $element.chosen("destroy").chosen();
            }
        });
    }

//    function getTagSelect() {
//        $.ajax({
//            url: _web + '/select/tags',
//            type: 'POST',
//            dataType: 'json',
//            success: function (result) {
//                var $element = $("#tag");
//                $element.empty();
//                $.each(result, function (idx, item) {
//                    $element.append('<option value="' + item.TAG + '">' + item.TAG + '</option>');
//                });
//                $element.chosen("destroy").chosen();
//            }
//        });
//    }
</script>