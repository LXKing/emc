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
            <form class="form-horizontal" id="benchmarkdetailEditForm" role="form">
                <input name="did" value="${object.did}" type="hidden"/>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>所属机构：</label>
                        <div class="col-sm-6">
                            <div class="input-group colorpicker-demo3">
                                <input  name="orgId" value="${object.orgId}" type="hidden"/>
                                <input  type="text" placeholder="所属机构" readonly="readonly" disabled value="${org[object.orgId]}" class="form-control" />
                                <span class="input-group-addon" style="cursor: hand"><a class="common-org" style="color: #000000">选择</a></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>能源类型：</label>
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
                        <label class="col-md-3  control-label"><span class="red">*</span>行业标准：</label>
                        <div class="col-md-6">
                            <input name="tradeTarget" class="form-control" value="${object.tradeTarget}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>国际标准：</label>
                        <div class="col-md-6">
                            <input name="interTarget" class="form-control" value="${object.interTarget}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>企业标准：</label>
                        <div class="col-md-6">
                            <input name="compTarget" class="form-control" value="${object.compTarget}" type="text">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>报警阈值：</label>
                        <div class="col-md-6">
                            <input name="alarmValue" value="${object.alarmValue}" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>标准单位：</label>
                        <div class="col-md-6">
                            <input name="cell" class="form-control" value="${object.cell}" type="text">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>开始日期：</label>
                        <div class="col-md-6">
                            <input name="sTime" value="${object.sTime}"  class="form-control datepicker" type="text" placeholder="请选择开始日期">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label"><span class="red">*</span>结束日期：</label>
                        <div class="col-md-6">
                            <input  name="eTime" value="${object.eTime}"  class="form-control datepicker" type="text" placeholder="请选择结束日期">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="td">
                        <label class="col-md-3  control-label">备注：</label>
                        <div class="col-md-6">
                            <textarea name="memo" class="form-control" type="text">${object.memo} </textarea>
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
    $('.datepicker').datepicker();
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
        $("#benchmarkdetailEditForm").validate({
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
                typeId: {required: true },
                orgId: { required: true},
                alarmValue: { required: true},
                tradeTarget: { required: true},
                interTarget: { required: true},
                compTarget: { required: true},
                cell: { required: true},
                sTime: { required: true},
                eTime: { required: true}

            },
            messages: {
                typeId: {
                    required: icon + "请选择能源类型"
                },
                orgId: {
                    required: icon + "请选择所属组织机构"
                },
                alarmValue: {
                    required: icon + "请输入报警阈值"
                },
                tradeTarget: {
                    required: icon + "请输入行业标准"
                },
                interTarget: {
                    required: icon + "请输入国际标准"
                },
                compTarget: {
                    required: icon + "请输入企业标准"
                },
                cell: {
                    required: icon + "请输入单位"
                },
                sTime: {
                    required: icon + "请选择开始时间"
                },
                eTime: {
                    required: icon + "请选择结束时间"
                }
            },
            submitHandler: function () {
                return parent.editCollBack();
            }
        });

    });
</script>