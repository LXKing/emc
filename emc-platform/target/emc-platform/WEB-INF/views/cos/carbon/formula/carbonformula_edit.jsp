<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="company" class="wrapper wrapper-content">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" id="carbonformulaEditForm" role="form">
                <input name="formulaId" value="${object.formulaId}" type="hidden"/>
                <div class="form-group">
                    <div class="td">


                        <label class="col-md-3  control-label"><span class="red">*</span>能源类型 ${object.typeId}：</label>
                        <div class="col-sm-6">
                            <select data-placeholder="请选择能源类型..." name="typeId" class="chosen-select form-control" tabindex="2">
                                <option value="">请选择能源类型</option>
                                <c:forEach items="${dicList}" var="dic">
                                    <option <c:if test="${dic.type_id eq object.typeId}">selected="selected" </c:if> value="${dic.type_id}">${dic.type_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>单位热值含碳量：</label>
                        <div class="col-md-6">
                            <input name="corbonSize" class="form-control" value="${object.corbonSize}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>碳氧化率：</label>
                        <div class="col-md-6">
                            <input name="corbonOxide" value="${object.corbonOxide}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>二氧化碳与碳分子量比：</label>
                        <div class="col-md-6">
                            <input name="corbonRatio" value="${object.corbonRatio}" class="form-control" type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>燃料地位热值：</label>
                        <div class="col-md-6">
                            <input name="corbonValue" value="${object.corbonValue}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3 control-label">备注：</label>
                        <div class="col-md-6">
                            <textarea name="memo" class="form-control" type="text" aria-required="true" aria-invalid="false">${object.memo}</textarea>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script>
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
            } else if (element.is("select")) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {
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
        var icon = "<span class='fa fa-times-circle'></span> ";
        $("#carbonformulaEditForm").validate({
            onsubmit: true,// 是否在提交是验证
            onfocusout: function (element) {
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
            rules: {
                typeId: {required: true, maxlength: 5 },
                corbonSize: { required: true},
                corbonOxide: { required: true},
                corbonRatio: { required: true},
                corbonValue: { required: true}

            },
            messages: {
                typeId: {
                    required: icon + "请选择能源类型"
                },
                corbonSize: {
                    required: icon + "请输入单位热值含碳量"
                },
                corbonOxide: {
                    required: icon + "请输入碳氧化率"
                },
                corbonRatio: {
                    required: icon + "请输入二氧化碳与碳分子量比"
                },
                corbonValue: {
                    required: icon + "请输入燃料地位热值"
                }
            },
            submitHandler: function () {
                return parent.editCollBack();
            }
        });

    });
</script>