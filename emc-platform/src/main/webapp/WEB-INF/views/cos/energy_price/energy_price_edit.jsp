<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h5>&nbsp;编辑能源单价</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="energyPriceEditFrom" role="form">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" id="priceId" name="priceId" value="${energyPrice.price_id}">
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>所属单位：</label>

                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input id="orgId" name="orgId" value="${energyPrice.org_id}" type="hidden"/>
                                    <input type="text" name="orgName"  value="${energyPrice.org_name}" placeholder="所属机构" disabled class="form-control"/>
                                    <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>能源类型：</label>

                            <div class="col-sm-4">
                                <select id="typeId" name="typeId" class="chosen-select form-control">
                                    <option value="">请选择能源</option>
                                    <c:forEach var="item" items="${energyTypes}">
                                        <option value="${item.type_id}" <c:if test="${item.type_id eq energyPrice.type_id}">selected </c:if>>${item.type_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>平均单价：</label>

                            <div class="col-sm-4">
                                <input name="avgPrice" id="avgPrice" class="form-control" type="text"  value="${energyPrice.avg_price}"
                                       placeholder="请输入平均单价,如0.56">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>开始时间：</label>

                            <div class="col-sm-4">
                                <input name="sTime" id="sTime" class="form-control" type="text"   value="${energyPrice.s_time}" placeholder="请选择开始时间">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>结束时间：</label>

                            <div class="col-sm-4">
                                <input name="eTime" id="eTime" class="form-control" type="text" value="${energyPrice.e_time}"  placeholder="请选择结束时间">
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
                error.insertAfter(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $('#sTime').datepicker();
        $('#eTime').datepicker();

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

        $.validator.addMethod("checkSETime", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: ctx + '/energy-price/check/time',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {priceId:$('#priceId').val(),typeId: $('#typeId').val(), orgId: $('#orgId').val(), sTime: $('#sTime').val(), eTime: $('#eTime').val()},
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
        }, icon + "该单位的该能源在这个时间段存在单价记录");

        // 平均单价验证
        $.validator.addMethod("isAvgPrice", function (value, element) {
            var tel = /^(([1-9][0-9]*)|0|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "平均单价为数字");


        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).parent().siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if ($(this).find('option:first').val() != $(this).val()) {
                $(this).parent().siblings('.help-block').remove();
            }
            return false;
        });
        // validate signup form on keyup and submit

        $("#energyPriceEditFrom").validate({
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
                avgPrice: {
                    required: true,
                    isAvgPrice: true
                },
                sTime: {
                    required: true
                },
                eTime: {
                    required: true,
                    checkSETime: true
                },
                orgId: {
                    required: true
                },
                typeId: {
                    required: true
                }

            },
            messages: {
                avgPrice: {
                    required: icon + "请输入平均单价"
                },
                sTime: {
                    required: icon + "请选择开始时间"
                },
                eTime: {
                    required: icon + "请选择结束时间"
                },
                orgId: {
                    required: icon + "请选择所属单位"
                },
                typeId: {
                    required: icon + "请选择能源类型"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: ctx + '/energy-price/edit',
                    data: $('#energyPriceEditFrom').serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            layer.closeAll();
                            $("#content-main").empty().load(ctx + '/energy-price/list');
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