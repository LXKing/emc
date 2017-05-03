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
            <form class="form-horizontal" id="editForm" role="form">
               <input name="dicId" value="${object.dicId}" type="hidden"/>-
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>字典类型：</label>

                        <div class="col-sm-6">
                            <select data-placeholder="请选择字典类型..." name="dicTypeId" class="chosen-select form-control" tabindex="2">
                                <option value="">请选择字典类型</option>
                                <c:forEach items="${dictypeList}" var="dic">
                                    <option <c:if test="${dic.dicTypeId eq object.dicTypeId}">selected="selected" </c:if> value="${dic.dicTypeId}">${dic.dicTypeDes}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>类型编码：</label>

                        <div class="col-md-6">
                            <input name="dicCode" class="form-control" value="${object.dicCode}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>类型名称：</label>

                        <div class="col-md-6">
                            <input name="dicDes" value="${object.dicDes}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>类型标识：</label>
                        <div class="col-md-6">
                            <input name="dicName" value="${object.dicName}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3 control-label"><span class="red">*</span>排序：</label>
                        <div class="col-md-6">
                            <input name="seq" class="form-control" type="text" aria-required="true" value="${object.seq}"
                                   aria-invalid="false">
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
        $("#editForm").validate({
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
                dicCode: {required: true, maxlength: 5 },
                dicDes: { required: true,  maxlength: 16},
                dicName: { required: true, maxlength: 32},
                seq: { required: true, number: true, maxlength: 11 }
            },
            messages: {
                dicCode: {
                    required: icon + "请输入字典编码",
                    maxlength: icon + "字典编码必须5个字符以内"
                },
                dicDes: {
                    required: icon + "请输入字典名称",
                    maxlength: icon + "名称必须16个字符以内"
                },
                dicName: { required: icon + "请输入字典标识！",  maxlength: icon + "标识必须32个字符以内" },
                seq: {  required: icon + "请输入序号", number: icon + "请输入正确的数字",maxlength:icon +"序号位数不能超过11位" }
            },
            submitHandler: function () {
                return parent.editCollBack();
            }
        });

    });
</script>