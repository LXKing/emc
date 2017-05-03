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
        $.validator.addMethod("roomCodeUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var pOrgId = $('#pOrgId').val();
            $.ajax({
                url:ctx+'/room/check/roomCode',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {orgCode:$('input[name="orgCode"]').val(),pOrgId:pOrgId},
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
        }, icon + "房间编码已存在");

        $.validator.addMethod("NOT_EMPTY", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var pOrgId = $('#pOrgId').val();
            if(pOrgId == ""||pOrgId == null||pOrgId =="undefinded"){
                deferred.resolve();
            }else{
                deferred.resolve();
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                return deferred.state() == "resolved" ? true : false;
            }
        });

        $("#room_add_form").validate({
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
                pOrgId: {required: true },
                orgCode:{required:true,NOT_EMPTY:true,roomCodeUnique:true},
                orgName: { required: true,maxlength:15,NOT_EMPTY:true},
                seq: { required: true, number: true, maxlength: 11 }
            },
            messages: {
                pOrgId: {
                    required: icon + "请选择楼栋"
                },
                orgCode:{
                    required: icon + "请输入房间编号！",
                    NOT_EMPTY: icon + "请选择上级单位！ "
                },
                orgName: { required: icon + "请输入房间名称！",maxlength: icon + "房间名称不能超过15个字符"
                },
                seq: {  required: icon + "请输入序号", number: icon + "请输入正确的数字",maxlength:icon +"序号位数不能超过11位" }
            },
            submitHandler: function () {
                return parent.addRoomCollBack();
            }
        });

    });
</script>
</head>
<div id="company" class="wrapper wrapper-content">

    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" id="room_add_form" role="form">
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>所属组织机构：</label>
                        <div class="col-sm-6">
                            <div class="input-group colorpicker-demo3">
                                <input id="pOrgId" name="pOrgId" value="${object.pOrgId}" type="hidden"/>
                                <input  type="text" placeholder="所属组织机构" readonly="readonly" disabled value="" class="form-control" />
                                <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${typeId}" style="color: #000000">选择</a></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>房间名称：</label>

                        <div class="col-md-6">
                            <input name="orgName" value="${object.orgName}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>房间编码：</label>
                        <div class="col-md-6">
                            <input name="orgCode"  class="form-control" value="${object.orgCode}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>居民面积：</label>
                        <div class="col-md-6">
                            <input name="dwellArea"  class="form-control" value="${object.dwellArea}" type="text">
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

