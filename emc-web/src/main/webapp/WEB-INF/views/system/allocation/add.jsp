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
            <form class="form-horizontal" id="indexAddForm" dic="form">
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
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>指标类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="typeId" name="typeId" class="chosen-select form-control">
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>企业指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="typeZh" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入字典类型(中文)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>地方指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="typeZh" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入字典类型(中文)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>行业指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="typeZh" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入字典类型(中文)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>生效时间：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="typeZh" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入字典类型(中文)">
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
        var $form = $(top.document).find("#indexAddForm");
        $("#unitType,#typeId,#unitId").chosen();
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

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
        $.ajax({
            url: _web + '/select/index/type',
            type: 'POST',
            data: {unitType:$("#unitType").val()},
            dataType: 'json',
            success: function (result) {
                var $element = $("#typeId");
                $element.empty();
                $.each(result, function (idx, item) {
                    $element.append('<option value="' + item.INDEXID + '">' + item.INDEXNAME + '</option>');
                });
                $element.chosen("destroy").chosen();
            }
        });

        $.validator.addMethod("checkName", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform + '/sys/dic/check/name',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {des:value,typeUs:$(top.document).find('input[name="typeUs"]').val()},
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
        }, '该字典类型下字典名称已存在');

        $.validator.addMethod("checkSeq", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform + '/sys/dic/check/seq',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {seq:value,typeUs:$(top.document).find('input[name="typeUs"]').val()},
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
        }, '该字典类型下排序已存在');

        $.validator.addMethod("checkTypeUs", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform + '/sys/dic/check/type/us',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {typeZh:$(top.document).find('input[name="typeZh"]').val(),typeUs:$(top.document).find('input[name="typeUs"]').val()},
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
        }, '字典类型(英文)和字典类型(中文)不匹配');

        $.validator.addMethod("checkTypeZh", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform + '/sys/dic/check/type/zh',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {typeZh:$(top.document).find('input[name="typeZh"]').val(),typeUs:$(top.document).find('input[name="typeUs"]').val()},
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
        }, '字典类型(中文)和字典类型(英文)不匹配');

        //排序校验
        $.validator.addMethod("isSeq", function(value, element){
            var tel = /^\d+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "请输入数字");

        //提示信息绑定
        $(top.document).on('mousedown','input:not(:submit):not(:button)',function(){
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        //下拉框js
//        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen().on('change',function () {
//            if ($(this).find('option:first').val() != $(this).val()) {
//                $(this).siblings('.help-block').remove();
//            }
//            return false;
//        });

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
                des: {
                    required: true,
                    checkName:true
                },
                typeUs: {
                    required: true,
                    checkTypeUs:true
                },
                typeZh: {
                    required: true,
                    checkTypeZh:true
                },
                seq: {
                    required: true,
                    isSeq: true,
                    checkSeq:true
                }
            },
            messages: {
                des: {
                    required: icon + "请输入字典名称"
                },
                typeUs: {
                    required: icon + "请输入字典类型(英文)"
                } ,
                typeZh: {
                    required: icon + "请输入字典类型(中文)"
                },
                seq: {
                    required: icon + "请输入排序"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/sys/dic/add',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#dic-table-list').bootstrapTable("refresh");
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>