<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>热力站编辑</title>
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
    new PCAS('province','${province}','${node.provinceId}','city','${city}','${node.cityId}','county','${county}','${node.countyId}','town','${town}','${node.townId}');
    $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();

    var $editForm = $(top.document).find("#stationEditForm");
    $.validator.addMethod("nodeCodeUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var oldcode = $(top.document).find("#oldCode").val();
        var newcode = $(top.document).find("#orgCode").val();
        if(oldcode == newcode){
            deferred.resolve();
        }else{
            $.ajax({
                url:_platform+'/common/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {orgCode:newcode},
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
    }, icon + "热力站编码已存在");

    $.validator.addMethod("nodeNameUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var oldName = $(top.document).find("#oldName").val();
        var orgName = $(top.document).find("#orgName").val();
        if(oldName == orgName){
            deferred.resolve();
        }else{
            $.ajax({
                url:_platform+'/common/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {orgName:orgName},
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
    }, icon + "热力站名称已存在");

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

    $editForm.validate({
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
            orgId: {
                required: true
            },
            orgCode: {
                required: true,
                nodeCodeUnique: true
            },
            orgName: {
                required: true,
                nodeNameUnique: true
            },
            shortName: {
                required: true
            },
            manageTypeId: {
                required: true
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
        },
        messages: {
            orgId: {
                required: icon + "请选择组织机构"
            },
            orgCode: {
                required: icon + "请填写热力站编码"
            },
            orgName: {
                required: icon + "请填写热力站名称"
            },
            shortName: {
                required: icon + "请填写简称"
            },
            manageTypeId: {
                required: icon + "请选择管理类型"
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
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.5,'#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url:_platform + '/station/edit',
                data:$editForm.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#station-table-list').bootstrapTable("refresh");
                        refreshNodes()
                        return true;
                    } else {
                        top.layer.msg(result.msg);
                        return false;
                    }
                },
                error:function(){
                    top.layer.msg("请求服务器失败！");
                    return false;
                }
            });
            return false;
        }
    });
});
function refreshNodes() {
    var treeNode = top.comm_tree.getSelectedNodes();
    var treeObj =  top.comm_ztree;
    type = "refresh";
    silent = false;
    /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/
    /*选中指定节点*/
    treeObj.selectNode(treeNode[0]);
    treeObj.reAsyncChildNodes(treeNode[0], "refresh");
}
</script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form class="form-horizontal" id="stationEditForm" role="form">
                    <input type="hidden" name="id"  value="${node.id}"/>
                    <input type="hidden" name="status" value="${node.status}"/>
                    <input type="hidden" name="typeId"  value="${node.typeId}"/>
                    <input type="hidden" name="pOrgId" value="${node.pOrgId}"/>
                    <input type="hidden" name="comId" value="${node.comId}"/>
                    <input type="hidden" id="oldName" value="${node.orgName}">
                    <input type="hidden" id="oldCode" value="${node.orgCode}">
                    <div class="form-group">
                        <label class="col-sm-2  control-label"><span class="red">*</span>热力站编码：</label>
                        <div class="col-sm-5">
                            <input id="orgCode" value="${node.orgCode}" name="orgCode" class="form-control"  type="text" maxlength="20" placeholder="请输入热力站编码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2  control-label"><span class="red">*</span>热力站名称：</label>
                        <div class="col-sm-5">
                            <input name="orgName" value="${node.orgName}" id="orgName" class="form-control"  type="text" maxlength="64" placeholder="请输入热力站名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2  control-label"><span class="red">*</span>热力站简称：</label>
                        <div class="col-sm-5">
                            <input name="shortName" value="${node.shortName}" class="form-control"  type="text" maxlength="64" placeholder="请输热力站简称">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="td">
                            <label class="col-md-2  control-label"><span class="red">*</span>管理类型：</label>
                            <div class="col-sm-5">
                                <select id="manageTypeId" name="manageTypeId" class="chosen-select form-control"  >
                                    <option value="">请选择管理类型</option>
                                    <c:forEach items="${sysDic['managetype']}" var="type">
                                        <option <c:if test="${node.manageTypeId eq type.id}">selected="selected" </c:if> value="${type.id}">${type.des}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <%--<label class="col-sm-2  control-label"><span class="red">*</span>公建面积：</label>--%>
                        <label class="col-sm-2  control-label">公建面积：</label>
                        <div class="col-sm-5">
                            <input name="publicArea"  value="${node.publicArea}" class="form-control" type="text" maxlength="64" placeholder="请输入公建面积">
                        </div>
                    </div>
                    <div class="form-group">
                        <%--<label class="col-sm-2  control-label"><span class="red">*</span>居民面积：</label>--%>
                        <label class="col-sm-2  control-label">居民面积：</label>
                        <div class="col-sm-5">
                            <input name="dwellArea" value="${node.dwellArea}" class="form-control" type="text" maxlength="64" placeholder="请输入居民面积">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="td">
                            <label class="col-sm-2  control-label">区划区划：</label>
                            <div class="col-sm-3">
                                <select id="province" name="provinceId" class="chosen-select form-control" >
                                    <option value="">请选择省份</option>
                                </select>
                            </div>

                            <div class="col-sm-3">
                                <select id="city" name="cityId" class="chosen-select form-control" >
                                    <option value="">请选择市</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="td">
                            <label class="col-sm-2  control-label"></label>
                            <div class="col-sm-3">
                                <select id="county" name="countyId" class="chosen-select form-control" >
                                    <option value="">请选择县</option>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <select id="town" name="townId" class="chosen-select form-control" >
                                    <option value="">请选择镇(乡)</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2  control-label"><span class="red">*</span>详细地址：</label>
                        <div class="col-sm-5">
                            <input name="addr" value="${node.addr}"  class="form-control" type="text" maxlength="64" placeholder="请输入区划代码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2  control-label"><span class="red">*</span>排序：</label>
                        <div class="col-sm-5">
                            <input name="seq" value="${node.seq}" class="form-control" type="text" maxlength="64" placeholder="请输入排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2  control-label">备注：</label>
                        <div class="col-sm-5">
                            <input name="memo" value="${node.memo}"   class="form-control" type="text" maxlength="255">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2  control-label">经度：</label>
                        <div class="col-sm-5">
                            <input name="lng" value="${node.lng}" class="form-control"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2  control-label">纬度：</label>
                        <div class="col-sm-5">
                            <input name="lat" value="${node.lat}"  class="form-control">
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