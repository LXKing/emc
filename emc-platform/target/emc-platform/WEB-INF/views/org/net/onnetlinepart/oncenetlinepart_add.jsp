<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>一次网段编辑</title>
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
            new PCAS('province','${province}','','city','${city}','','county','${county}','','town','${town}','');
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
            $.validator.addMethod("orgCodeUnique", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                $.ajax({
                    url:ctx+'/oncenetlinepart/check/oncenetlinepartCode',
                    type:'POST',
                    async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {orgCode:$('input[name="orgCode"]').val()},
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
            }, icon + "一次网段编码已存在");

            $.validator.addMethod("orgNameUnique", function(value, element) {
                var deferred = $.Deferred();//创建一个延迟对象
                $.ajax({
                    url:ctx+'/common/check/org',
                    type:'POST',
                    async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {orgName:$('input[name="orgName"]').val()},
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
            }, icon + "一次网段名称已存在");

            $("#oncenetlinepart_add_Form").validate({
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
                        orgCodeUnique:true
                    },
                    orgName: {
                        required: true,
                        orgNameUnique:true
                    },
                    sId: {
                        required: true
                    },
                    eId: {
                        required: true
                    },
                    partTypeId: {
                        required: true
                    },
                    length: {
                        required: true
                    },
                    layTypeId: {
                        required: true
                    },seq:{
                        required: true
                    }
                },
                messages: {

                    pOrgId: {
                        required: icon + "请选择组织机构"
                    },
                    orgCode: {
                        required: icon + "请填写一次网段编码"
                    },
                    orgName: {
                        required: icon + "请填写一次网段名称"
                    },
                    sId: {
                        required: icon + "请填写一次网段起点位置"
                    },
                    eId: {
                        required:  icon + "请填写一次网段终端位置"
                    },
                    partTypeId: {
                        required:  icon + "请填写一次网段管段类型"
                    },
                    length: {
                        required:  icon + "请填写一次网段长度"
                    },
                    layTypeId: {
                        required:  icon + "请填写一次网段敷设方式"
                    },seq:{
                        required: icon + "请填写一次网段序号"
                    }

                },
                submitHandler: function () {
                    var index = layer.load(1, {
                        shade: [0.1,'#fff'] //0.1透明度的白色背景
                    });
                    $.ajax({
                        url:ctx + '/oncenetlinepart/add',
                        data:$('#oncenetlinepart_add_Form').serialize(),
                        type:'POST',
                        dataType:'json',
                        success:function(result) {
                            if(result.flag){
                                layer.closeAll();
                                $("#content-main").empty().load(ctx+'/oncenetlinepart/list');
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
                    <h5>&nbsp;添加热源</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="oncenetlinepart_add_Form" role="form">
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>所属机构：</label>
                            <div class="col-sm-4">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="pOrgId" value="" type="hidden"/>
                                    <input type="text"  placeholder="所属机构" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${typeId}" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>一次网段码：</label>
                            <div class="col-sm-4">
                                <input name="orgCode" class="form-control" value="${object.orgCode}" type="text" maxlength="16" placeholder="请输入一次网段编码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>一次网段名称：</label>
                            <div class="col-sm-4">
                                <input name="orgName" class="form-control" value="${object.orgName}" type="text" maxlength="64" placeholder="请输入一次网段名称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>起点：</label>
                            <div class="col-sm-4">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="sId" value="" type="hidden"/>
                                    <input type="text"  placeholder="起点" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${sTypeId}" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2  control-label">起点管底高程：</label>
                            <div class="col-sm-4">
                                <input name="sPipeHeight" class="form-control" type="text" maxlength="16" placeholder="请输入起点管底高程">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">起点沟底高程：</label>
                            <div class="col-sm-4">
                                <input name="sTrenchHeight" class="form-control" type="text" maxlength="64" placeholder="请输入起点沟底高程">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">起点地面高程：</label>
                            <div class="col-sm-4">
                                <input name="sGroundHeight" class="form-control" type="text" maxlength="64" placeholder="请输入起点地面高程">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>终点：</label>
                            <div class="col-sm-4">
                                <div class="input-group colorpicker-demo3">
                                    <input  name="eId" value="" type="hidden"/>
                                    <input type="text"  placeholder="终点" readonly="readonly" disabled value="" class="form-control" />
                                    <span class="input-group-addon" style="cursor: hand"><a class="common-org" typetext="${etypeId}" style="color: #000000">选择</a></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">终点管底高程：</label>
                            <div class="col-sm-4">
                                <input name="ePipeHeight" class="form-control" type="text" maxlength="16" placeholder="请输入终点管底高程">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">终点沟底高程：</label>
                            <div class="col-sm-4">
                                <input name="eTrenchHeight" class="form-control" type="text" maxlength="64" placeholder="请输入终点沟底高程">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">终点地面高程：</label>
                            <div class="col-sm-4">
                                <input name="eGroundHeight" class="form-control" type="text" maxlength="64" placeholder="请输入终点地面高程">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>管段类型：</label>
                                <div class="col-sm-4">
                                    <select id="partTypeId" name="partTypeId" class="chosen-select form-control" onchange="addMessage()" >
                                        <option value="">请选择管段类型</option>
                                        <c:forEach items="${dicList['parttype']}" var="type">
                                            <option value="${type.dicName}">${type.dicDes}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>管段长度：</label>
                            <div class="col-sm-4">
                                <input name="length" class="form-control" type="text" maxlength="64" placeholder="请输入管段长度">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">地面长度：</label>
                            <div class="col-sm-4">
                                <input name="groundLength" class="form-control" type="text" maxlength="64" placeholder="请输入地面长度">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>敷设方式：</label>
                                <div class="col-sm-4">
                                    <select id="layTypeId" name="layTypeId" class="chosen-select form-control" onchange="addMessage()" >
                                        <option value="">请选择敷设方式</option>
                                        <c:forEach items="${dicList['laytype']}" var="type">
                                            <option value="${type.dicName}">${type.dicDes}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">供回水方向：</label>
                            <div class="col-sm-4">
                                <input name="gHDirection" class="form-control" type="text" maxlength="64" placeholder="请输入供回水方向">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">沟宽：</label>
                            <div class="col-sm-4">
                                <input name="trenchWidth" class="form-control" type="text" maxlength="64" placeholder="请输入沟宽">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">沟高：</label>
                            <div class="col-sm-4">
                                <input name="trenchHeight" class="form-control" type="text" maxlength="64" placeholder="请输入沟高">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">管材类型：</label>
                            <div class="col-sm-4">
                                <input name="pipeMaterial" class="form-control" type="text" maxlength="64" placeholder="请输入管材类型">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">管材厂家：</label>
                            <div class="col-sm-4">
                                <input name="pipeFactory" class="form-control" type="text" maxlength="64" placeholder="请输入管材厂家">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">供水保温厚度：</label>
                            <div class="col-sm-4">
                                <input name="gWarmPly"  class="form-control" placeholder="请输入供水保温厚度" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">回水保温厚度：</label>
                            <div class="col-sm-4">
                                <input name="hWarmPly" class="form-control"  placeholder="请输入回水保温厚度" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">管壁厚度：</label>
                            <div class="col-sm-4">
                                <input name="pipeThickness"  class="form-control"   placeholder="请输入管壁厚度" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">保温材料：</label>
                            <div class="col-sm-4">
                                <input name="material" class="form-control" placeholder="请输入保温材料" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>排序：</label>
                            <div class="col-sm-4">
                                <input name="seq" value="${object.seq}" class="form-control" type="text" maxlength="64" placeholder="请输入排序">
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