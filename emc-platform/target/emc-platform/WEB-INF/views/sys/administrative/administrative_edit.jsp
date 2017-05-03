<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;修改行政区</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="addForm" role="form">
                        <input name="admLevel" value="${object.admLevel}" class="form-control"  type="hidden">
                        <input name="pCode"  value="${object.pCode}" class="form-control" type="hidden" >
                        <input id="oldCode"  value="${object.admCode}" class="form-control" type="hidden" >
                        <div class="form-group">
                            <label class="col-sm-2  control-label">区划上级：</label>
                            <div class="col-sm-2">
                                <select id="province" name="province" class="chosen-select form-control" onchange="showValue()" >
                                    <option value="">请选择省份</option>
                                </select>
                            </div>

                            <div class="col-sm-2">
                                <select id="city" name="city" class="chosen-select form-control" onchange="showValue()">
                                    <option value="">请选择市</option>
                                </select>
                            </div>

                            <div class="col-sm-2">
                                <select id="county" name="sex" class="chosen-select form-control" onchange="showValue()">
                                    <option value="">请选择县</option>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <select id="town" name="sex" class="chosen-select form-control" onchange="showValue()">
                                    <option value="">请选择镇(乡)</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>区划代码：</label>
                            <div class="col-sm-4">
                                <input name="admCode" id="admCode"  value="${object.admCode}" class="form-control" type="text" maxlength="16" placeholder="请输入区划代码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>区划名称：</label>
                            <div class="col-sm-4">
                                <input name="admName" value="${object.admName}" class="form-control" type="text" maxlength="11" placeholder="请输入区划名称">
                            </div>
                        </div>

                        <div class="form-group"
                             <c:if test="${object.admLevel ne '5'}"> style="display: none"</c:if>
                             id = "admTypeDiv" >
                            <label class="col-sm-2  control-label">城乡分类：</label>
                            <div class="col-sm-4">
                                <select id="admtype" name="admType" class="chosen-select form-control" >
                                    <option value="">请选择城乡分类</option>
                                    <c:forEach items="${dicList['admtype']}" var="types">
                                        <option <c:if test="${object.admType eq types.dicName}"> selected="selected" </c:if>
                                                value="${types.dicName}">${types.dicDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">经度：</label>

                            <div class="col-sm-4">
                                <input name="lng" value="${object.lng}"  class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">纬度：</label>
                            <div class="col-sm-4">
                                <input name="lat" value="${object.lat}" class="form-control">
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
                error.insertAfter(element.parent().parent().parent());
            } else if(element.is("select")){
                error.insertAfter(element.parent());
            }else{
                error.insertAfter(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    var showValue = function (){
        var province = $("#province").val();
        var city = $("#city").val();
        var county = $("#county").val();
        var town = $("#town").val();
        if(town != ""){
            $("#admTypeDiv").show();
            $("#admtype").chosen("destroy").chosen();
            $("input[name='admLevel']").val("5");
            $("input[name='pCode']").val(town);
        }else{
            $("#admTypeDiv").hide();
            if(county != ""){
                $("input[name='admLevel']").val("4");
                $("input[name='pCode']").val(county);
            }else{
                if(city != ""){
                    $("input[name='admLevel']").val("3");
                    $("input[name='pCode']").val(city);
                }else{
                    if(province != ""){
                        $("input[name='admLevel']").val("2");
                        $("input[name='pCode']").val(province);
                    }else{
                        $("input[name='admLevel']").val("1");
                        $("input[name='pCode']").val("");
                    }
                }
            }
        }
    };

    //以下为官方示例
    $(function () {
        var icon = "<i class='fa fa-times-circle'></i> ";
        var province ="${object.province}";
        var city ="${object.city}";
        var county ="${object.county}";
        var town ="${object.town}";
        var level ="${object.admLevel}";
        if(level=="2"){
            new PCAS('province','${province}',province,'city','${city}','','county','${county}','','town','${town}','','village','');
            $("#province").attr("disabled","disabled");
        }else if( level == "3"){
            new PCAS('province','${province}',province,'city','${city}',city,'county','${county}','','town','${town}','','village','');
            $("#province").attr("disabled","disabled");
            $("#city").attr("disabled","disabled");
        }else if( level == "4"){
            new PCAS('province','${province}',province,'city','${city}',city,'county','${county}',county,'town','${town}','','village','');
            $("#province").attr("disabled","disabled");
            $("#city").attr("disabled","disabled");
            $("#county").attr("disabled","disabled");
        }else if( level == "5"){
            new PCAS('province','${province}',province,'city','${city}',city,'county','${county}',county,'town','${town}',town,'village','');
            $("#province").attr("disabled","disabled");
            $("#city").attr("disabled","disabled");
            $("#county").attr("disabled","disabled");
            $("#town").attr("disabled","disabled");
        }else{
            new PCAS('province','${province}','','city','${city}','','county','${county}','','town','${town}','','village','');
            $("#province").attr("disabled","disabled");
            $("#city").attr("disabled","disabled");
            $("#county").attr("disabled","disabled");
            $("#town").attr("disabled","disabled");
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
        for (var selector in config) {
            $(selector).chosen(config[selector]);
        }
        $.validator.addMethod("admCodeUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var oldcode = $("#oldCode").val();
            var newcode = $("input[name='admCode']").val();
            if(oldcode == newcode){
                deferred.resolve();
            }else{
                $.ajax({
                    url:ctx+'/administrative/check/admCode',
                    type:'POST',
                    async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {admCode:$('#admCode').val()},
                    dataType: 'json',
                    success:function(result) {
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
        }, icon + "区域编码已存在");

        $("#addForm").validate({
            onsubmit: true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).is(":radio")){
                    return;
                }
                if ($(element).val() == null || $(element).val() == "") {
                    $(element).closest('.form-group').removeClass('has-error');

                    if($(element).parent(".chosen-search").length==1){
                        $(element).parents(".chosen-container").parent().siblings('.help-block').remove();
                    }else{
                        $(element).parent().siblings('.help-block').remove();
                    }

                } else {
                    $(element).valid();
                }
            },
            onkeyup: false,// 是否在敲击键盘时验证
            onclick:false,
            rules: {
                admCode: {
                    required: true,
                    minlength: 11,
                    maxlength: 12,
                    admCodeUnique: true
                },
                admName: {
                    required: true,
                    maxlength:60
                }


            },
            messages: {
                admCode: {
                    required: icon + "请输入区划编码",
                    minlength: icon + "区划编码必须在11-12个字符",
                    maxlength: icon + "区划编码长度不能超过12个字符"
                },
                admName: {
                    required: icon + "请输入区划名称",
                    maxlength: icon + "区划名称不能超过60个字符"
                }
            },
            submitHandler: function () {
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:ctx + '/administrative/edit',
                    data:$('#addForm').serialize(),
                    type:'POST',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            layer.closeAll();
                            $("#content-main").empty().load(ctx+'/administrative/list');
                            layer.msg(result.msg);
                        }else{
                            layer.close(index);
                            layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>