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
            <form class="form-horizontal" id="costmanualdataEditForm" role="form">
                <input name="mid" value="${object.mid}" type="hidden"/>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>所属机构：</label>
                        <div class="col-sm-6">
                            <div class="input-group colorpicker-demo3">
                                <input  name="orgId" value="${object.orgId}" type="hidden"/>
                                <input  type="text" placeholder="所属机构" readonly="readonly" disabled value="${org[object.orgId]}" class="form-control" />
                                <span class="input-group-addon" style="cursor: hand"><a class="common-org default" style="color: #000000">选择</a></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">

                        <label class="col-md-3  control-label"><span class="red">*</span>费用类型：</label>
                        <div class="col-sm-6">
                            <input name="typeId" value="${object.typeId}" type="hidden"/>
                            <select data-placeholder="请选择费用类型..." disabled name="typeId" class="chosen-select form-control" tabindex="2">
                                <option value="">请选择费用类型</option>
                                <c:forEach items="${types}" var="dic">
                                    <option <c:if test="${dic.typeId eq object.typeId}">selected="selected" </c:if> value="${dic.typeId}">${dic.pricename}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>费用：</label>
                        <div class="col-md-6">
                            <input name="cost" class="form-control" value="${object.cost}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input id="year" name="year" value="${object.year}" type="hidden"/>
                    <input id="month" name="month" value="${object.month}" type="hidden"/>
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>选择月份：</label>
                        <div class="col-md-6 ">
                            <input type="text" disabled  class="form-control datepicker" value="${object.year}-${object.month}" onchange="setDefulaValue($(this).val())">
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script>
    function setDefulaValue (value){
        var year = value.split("-");
        $("#year").val(year[0]);
        $("#month").val(year[1]);
    }
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
    $('.datepicker').datepicker({
        format: "yyyy-mm",
        changeMonth: true,
        changeYear: true
    });
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
        $("#costmanualdataEditForm").validate({
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
                cost: {required: true }


            },
            messages: {
                cost: {
                    required: icon + "请填写费用"
                }
            },
            submitHandler: function () {
                return parent.editCollBack();
            }
        });

    });
</script>