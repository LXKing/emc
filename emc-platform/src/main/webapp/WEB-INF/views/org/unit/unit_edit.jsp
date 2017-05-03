<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>单元管理</title>
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
                    error.insertAfter(element.parent().parent());
                }else{
                    error.insertAfter(element.parent());
                }
            },
            errorClass: "help-block m-b-none m-t-xs",
            validClass: "help-block m-b-none m-t-none"
        });


        //以下为官方示例
        $(function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            new PCAS('province','${province}','${object.provinceId}','city','${city}','${object.cityId}','county','${county}','${object.countyId}','town','${town}','${object.townId}');
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

            $.validator.addMethod("unitCodeUnique", function(value, element) {
                debugger;
                var deferred = $.Deferred();//创建一个延迟对象
                var newcode = $("#orgCode").val();
                var oldCode = $("#oldCode").val();
                var pOrgId = $("#pOrgId").val();
                if(oldCode == newcode){
                    deferred.resolve();
                }else{
                    $.ajax({
                        url:ctx+'/common/check/org',
                        type:'POST',
                        async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                        data: {orgCode:newcode,pOrgId:pOrgId},
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
            }, icon + "单元编码已存在");

            $.validator.addMethod("unitNameUnique", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                var orgName = $('input[name="orgName"]').val();
                var oldName = $("#oldName").val();
                var pOrgId = $("#pOrgId").val();
                if(oldName == orgName){
                    deferred.resolve();
                }else{
                    $.ajax({
                        url:ctx+'/common/check/org',
                        type:'POST',
                        async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                        data: {orgCode:newcode,pOrgId:pOrgId},
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
            }, icon + "单元名称已存在");

            $.validator.addMethod("NOT_EMPTY", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                var pOrgId = $("#pOrgId").val();
                if(pOrgId == ""||pOrgId == null||pOrgId =="undefinded"){
                    deferred.resolve();
                }else{
                    deferred.resolve();
                    //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                    return deferred.state() == "resolved" ? true : false;
                }
            });

            $("#unit-add-Form").validate({
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
                    pOrgId: {
                        required: true
                    },
                    orgCode: {
                        required: true,
                        NOT_EMPTY: true,
                        unitCodeUnique:true
                    },
                    orgName: {
                        required: true,
                        unitNameUnique:true
                    },
                    addr: {
                        required: true
                    },seq:{
                        required: true
                    },
                    provinceId:{
                        required: true
                    },
                    cityId:{
                        required: true
                    },
                    countyId:{
                        required: true
                    },
                    townId:{
                        required: true
                    }
//                    ,publicArea:{
//                        required: true
//                    },
//                    dwellArea:{
//                        required: true
//                    }

                },
                messages: {
                    pOrgId: {
                        required: icon + "请选择楼栋"
                    },
                    orgCode: {
                        required:  icon + "请填写单元编号",
                        NOT_EMPTY:  icon + "请先选择上级单位"
                    },
                    orgName: {
                        required: icon + "请填写单元名称"
                    },
                    addr: {
                        required: icon + "请填写详细地址"
                    },seq:{
                        required: icon + "请填写排序"
                    },
                    provinceId:{
                        required: icon + "请选择省份"
                    },
                    cityId:{
                        required: icon + "请选择城市"
                    },
                    countyId:{
                        required: icon + "请选择县城"
                    },
                    townId:{
                        required: icon + "请选择乡镇"
                    }
//                    , publicArea:{
//                        required: icon + "请填写公建面积"
//                    },
//                    dwellArea:{
//                        required: icon + "请填写居民面积"
//                    }
                },
                submitHandler: function () {
                    var index = layer.load(1, {
                        shade: [0.1,'#fff'] //0.1透明度的白色背景
                    });
                    $.ajax({
                        url:ctx + '/unit/edit',
                        data:$('#unit-add-Form').serialize(),
                        type:'POST',
                        dataType:'json',
                        success:function(result) {
                            if(result.flag){
                                layer.closeAll(index);
                                $("#content-main").empty().load(ctx+'/unit/list');
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
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;修改楼栋</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="unit-add-Form" role="form">
                        <input type="hidden" name="orgId" value="${object.orgId}"/>
                        <input type="hidden" id="oldCode" value="${object.orgCode}" disabled/>
                        <input type="hidden" id="oldName" value="${object.orgName}" disabled/>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>上级单位：</label>
                            <div class="col-sm-4">
                                <div class="input-group colorpicker-demo3">
                                    <input id="pOrgId"   name="pOrgId" value="${object.pOrgId}" type="hidden"/>
                                    <input  type="text" placeholder="上级单位" readonly="readonly" disabled value="${org[object.pOrgId]}" class="form-control" />
                                    <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${typeId}" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>单元名称：</label>
                            <div class="col-sm-4">
                                <input name="orgName" class="form-control" value="${object.orgName}" type="text" maxlength="64" placeholder="请输入楼栋名称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>单元号：</label>
                            <div class="col-sm-4">
                                <input name="orgCode" id="orgCode" class="form-control" value="${object.orgCode}" type="text" maxlength="64" placeholder="请输入单元编号">
                            </div>
                        </div>

                        <%--<div class="form-group">--%>
                            <%--&lt;%&ndash;<label class="col-sm-2  control-label"><span class="red">*</span>公建面积：</label>&ndash;%&gt;--%>
                            <%--<label class="col-sm-2  control-label">公建面积：</label>--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<input name="publicArea" value="${object.publicArea}" class="form-control" type="text" maxlength="64" placeholder="请输入公建面积">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--&lt;%&ndash;<label class="col-sm-2  control-label"><span class="red">*</span>居民面积：</label>&ndash;%&gt;--%>
                            <%--<label class="col-sm-2  control-label">居民面积：</label>--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<input name="dwellArea" value="${object.dwellArea}" class="form-control" type="text" maxlength="64" placeholder="请输入居民面积">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-sm-2  control-label">区划区划：</label>
                                <div class="col-sm-2">
                                    <select id="province" name="provinceId" class="chosen-select form-control" >
                                        <option value="">请选择省份</option>
                                    </select>
                                </div>

                                <div class="col-sm-2">
                                    <select id="city" name="cityId" class="chosen-select form-control" >
                                        <option value="">请选择市</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-sm-2  control-label"></label>
                                <div class="col-sm-2">
                                    <select id="county" name="countyId" class="chosen-select form-control" >
                                        <option value="">请选择县</option>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <select id="town" name="townId" class="chosen-select form-control" >
                                        <option value="">请选择镇(乡)</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>详细地址：</label>
                            <div class="col-sm-4">
                                <input name="addr" value="${object.addr}" class="form-control" type="text" maxlength="64" placeholder="请输入区划代码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">备注：</label>
                            <div class="col-sm-4">
                                <input name="memo" value="${object.memo}"  class="form-control" type="text" maxlength="255">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">经度：</label>
                            <div class="col-sm-4">
                                <input name="lng" value="${object.lng}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">纬度：</label>
                            <div class="col-sm-4">
                                <input name="lat" value="${object.lat}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">序号：</label>
                            <div class="col-sm-4">
                                <input name="seq" value="${object.seq}" class="form-control">
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
</body>
<div id="layer-div" ></div>