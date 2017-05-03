<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="seasonEditForm" role="form">
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" name="seasonId" value="${season.season_id}">

                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>采暖季度：</label>

                    <div class="col-sm-8">
                        <select id="season" name="season" disabled class="chosen-select form-control">
                            <option selected value="${season.season}">${season.season}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>开始时间：</label>

                    <div class="col-sm-8">
                        <input type="text" class="input-sm form-control" value="${season.s_date}" id="s_date"
                               name="sDate" placeholder="请选择开始时间"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>结束时间：</label>

                    <div class="col-sm-8">
                        <input type="text" class="input-sm form-control" value="${season.e_date}" id="e_date"
                               name="eDate" placeholder="请选择结束时间"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">备注：</label>

                    <div class="col-sm-8">
                        <textarea class="form-control" rows="6" maxlength="125" name="memo"
                                  placeholder="请输入备注">${season.memo}</textarea>

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
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.appendTo(element.parent().parent().parent());
            } else {
                error.appendTo(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"


    });

    //以下为官方示例
    $(function () {
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        $("#season").chosen();

        //初始化时间插件
        $('#s_date').datepicker();
        $('#e_date').datepicker();

        //验证时间是否在采暖季度之间
        $.validator.addMethod("dateSeason", function (value, element, param) {
            var season = $(param).val();
            var date1 = season.substr(0, 4) + "-01-01";
            var date2 = season.substr(5, 9) + "-12-31";
            return this.optional(element) || ((value >= date1) && (value <= date2));
        }, icon + "开始时间不在采暖季度之间");

        //验证结束时间是否大于开始时间
        $.validator.addMethod("isGt", function (value, element, param) {
            var startDate = $(param).val();
            return this.optional(element) || (value > startDate);
        }, icon + "结束时间必须大于开始时间");

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

        $("#seasonEditForm").validate({
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
                season: {
                    required: true,
                    checkSeason: true
                },
                sDate: {
                    required: true,
                    dateSeason: '#season'
                },
                eDate: {
                    required: true,
                    dateSeason: '#season',
                    isGt: '#s_date'
                }
            },
            messages: {
                season: {
                    required: icon + "请选择采暖季度"
                },
                sDate: {
                    required: icon + "请选择开始时间"
                },
                eDate: {
                    required: icon + "请选择结束时间"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: ctx + '/season/edit',
                    data: $('#seasonEditForm').serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            layer.closeAll();
                            $("#content-main").empty().load(ctx + '/season/list');
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