<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            var icon = "<span class='fa fa-times-circle'></span> ";
            $.validator.addMethod("secondnetCodeUnique", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                var oldcode = $("#oldCode").val();
                var newcode = $('#orgCode').val();
                if(oldcode == newcode){
                    deferred.resolve();
                }else {
                    $.ajax({
                        url: ctx +'/common/check/org',
                        type: 'POST',
                        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                        data: {orgCode: newcode},
                        dataType: 'json',
                        success: function (result) {
                            if (!result.flag) {
                                deferred.reject();
                            } else {
                                deferred.resolve();
                            }
                        }
                    });
                }
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                return deferred.state() == "resolved" ? true : false;
            }, icon + "管网编码已存在");

            $.validator.addMethod("secondnetNameUnique", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                var oldName = $("#oldName").val();
                var orgName = $('#orgName').val();
                if(oldName == orgName){
                    deferred.resolve();
                }else {
                    $.ajax({
                        url: ctx +'/common/check/org',
                        type: 'POST',
                        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                        data: {orgName: orgName},
                        dataType: 'json',
                        success: function (result) {
                            if (!result.flag) {
                                deferred.reject();
                            } else {
                                deferred.resolve();
                            }
                        }
                    });
                }
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                return deferred.state() == "resolved" ? true : false;
            }, icon + "管网名称已存在");

            $("#secondnet_edit_form").validate({
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
                    stationId: {required: true },
                    orgId: { required: true},
                    orgCode: { required: true, secondnetCodeUnique: true,maxlength:20},
                    orgName: { required: true,maxlength:64,secondnetNameUnique:true},
                    netTypeId: { required: true },
                    medium: {required: true,maxlength:30},
                    seq: { required: true, number: true, maxlength: 11 }
                },
                messages: {
                    feedId: {
                        required: icon + "请选择热源"
                    },
                    orgId: {
                        required: icon + "请选择组织机构"
                    },
                    orgCode: { required: icon + "请输入管网编码！",maxlength: icon + "管网编码不能超过20个字符" },
                    orgName: { required: icon + "请输入管网名称！",maxlength: icon + "管网名称不能超过64个字符" },
                    netTypeId: {  required: icon + "请选择管网类型" },
                    medium: {  required: icon + "请填写介质" ,maxlength: icon + "介质不能超过30个字符"},
                    seq: {  required: icon + "请输入序号", number: icon + "请输入正确的数字",maxlength:icon +"序号位数不能超过11位" }
                },
                submitHandler: function () {
                    return parent.editCollBack();
                }
            });

        });
    </script>
</head>
<div id="company" class="wrapper wrapper-content">

    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" id="secondnet_edit_form" role="form">
                <input name="orgId" value="${object.orgId}" type="hidden"/>
                <input id="oldCode" value="${object.orgCode}" type="hidden"/>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>所属机构：</label>
                        <div class="col-sm-6">
                            <div class="input-group colorpicker-demo3">
                                <input name="pOrgId" value="${object.pOrgId}" type="hidden"/>
                                <input type="text" placeholder="所属机构" readonly="readonly" disabled value="${org[object.pOrgId]}" class="form-control" />
                                <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${typeId}" style="color: #000000">选择</a></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>管网编码：</label>
                        <div class="col-md-6">
                            <input name="orgCode" id="orgCode"  class="form-control" value="${object.orgCode}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>管网名称：</label>

                        <div class="col-md-6">
                            <input name="orgName" value="${object.orgName}" class="form-control" type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>管线类型：</label>
                        <div class="col-sm-6">
                            <select data-placeholder="请选择管线类型..." name="netTypeId" class="chosen-select form-control" tabindex="2">
                                <option value="">请选择管线类型</option>
                                <c:forEach items="${dicList['netType']}" var="type">
                                    <option <c:if test="${object.netTypeId eq type.dicName}">selected="selected" </c:if> value="${type.dicName}">${type.dicDes}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3 control-label"><span class="red">*</span>介质：</label>
                        <div class="col-md-6">
                            <input name="medium" class="form-control" type="text" aria-required="true" value="${object.medium}"
                                   aria-invalid="false">
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
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label">备注：</label>

                        <div class="col-md-6">
                            <input name="memo" value="${object.memo}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

