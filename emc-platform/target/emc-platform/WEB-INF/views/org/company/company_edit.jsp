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
<form class="form-horizontal" id="org-edit-Form" role="form">
<input name="orgId" value="${object.orgId}" type="hidden"/>
    <input id="oldCode" value="${object.orgCode}" disabled type="hidden"/>
    <input id="oldName" value="${object.orgName}" disabled type="hidden"/>
<div class="form-group">
    <div class="td">
        <label class="col-md-2  control-label"><span class="red">*</span>公司名称：</label>

        <div class="col-md-6">
            <input name="orgName" class="form-control" value="${object.orgName}" type="text">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2  control-label"><span class="red">*</span>公司编码：</label>

        <div class="col-md-6">
            <input name="orgCode" id="orgCode" value="${object.orgCode}" class="form-control" type="text">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2  control-label">公司简称：</label>

        <div class="col-md-6">
            <input name="shortName" value="${object.shortName}" class="form-control" type="text">
        </div>
    </div>
</div>
    <div class="form-group">
        <div class="td">
            <label class="col-md-2  control-label"><span class="red">*</span>上级单位：</label>
            <div class="col-sm-6">
                <div class="input-group colorpicker-demo3">
                    <input  name="pOrgId" value="${object.pOrgId}" type="hidden"/>
                    <input  type="text" placeholder="上级单位" readonly="readonly" disabled value="${org[object.pOrgId]}" class="form-control" />
                    <span class="input-group-addon" style="cursor: hand"><a class="common-org" style="color: #000000">选择</a></span>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2  control-label">区划区划：</label>
        <div class="col-sm-3">
            <select id="province" name="provinceId" class="chosen-select form-control">
                <option value="">请选择省份</option>
            </select>
        </div>

        <div class="col-sm-3">
            <select id="city" name="cityId" class="chosen-select form-control" >
                <option value="">请选择市</option>
            </select>
        </div>


    </div>
    <div class="form-group">
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
<div class="form-group">
    <div class="td">
        <label class="col-md-2 control-label"><span class="red">*</span>详细地址：</label>

        <div class="col-md-6">
            <input name="addr" value="${object.addr}" class="form-control" type="text" aria-required="true"
                   aria-invalid="false">
        </div>
    </div>

</div>

<%--<div class="form-group">--%>
    <%--<div class="td">--%>
        <%--<label class="col-md-2 control-label"><span class="red">*</span>公建面积：</label>--%>

        <%--<div class="col-md-6">--%>
            <%--<input name="publicArea" value="${object.publicArea}" class="form-control" type="text" aria-required="true"--%>
                   <%--aria-invalid="false">--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
    <%--<div class="td">--%>
        <%--<label class="col-md-2 control-label"><span class="red">*</span>居民面积：</label>--%>

        <%--<div class="col-md-6">--%>
            <%--<input name="dwellArea" value="${object.dwellArea}" class="form-control" type="text" aria-required="true"--%>
                   <%--aria-invalid="false">--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="form-group">
    <label class="col-md-2 control-label"><span class="red">*</span>集团性质：</label>

    <div class="col-sm-4">
        <select data-placeholder="请选择集团性质..." name="groupTypeId" class="chosen-select form-control" tabindex="2">
            <option value="">请选择集团性质</option>
            <option
                    <c:if test="${object.groupTypeId eq '1'}">selected="selected" </c:if> value="1" hassubinfo="true">集团
            </option>
            <option value="2" hassubinfo="true"
                    <c:if test="${object.groupTypeId eq '2'}">selected="selected" </c:if> >集团外
            </option>
        </select>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2 control-label"><span class="red">*</span>排序：</label>

        <div class="col-md-6">
            <input name="seq" class="form-control" type="text" aria-required="true" value="${object.seq}"
                   aria-invalid="false">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2 control-label">经度：</label>

        <div class="col-md-6">
            <input name="lng" value="${object.lng}" class="form-control" type="text" aria-required="true"
                   aria-invalid="false">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2 control-label">纬度：</label>

        <div class="col-md-6">
            <input name="lat" value="${object.lat}" class="form-control" type="text" aria-required="true"
                   aria-invalid="false">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="td">
        <label class="col-md-2 control-label">备注：</label>

        <div class="col-md-6">
            <input name="memo" class="form-control" type="text" aria-required="true" value="${object.memo}"
                   aria-invalid="false">
        </div>
    </div>
</div>
</form>
</div>
</div>
</div>
<script>
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    new PCAS('province','${province}','${object.provinceId}','city','${city}','${object.cityId}','county','${county}','${object.countyId}','town','${town}','${object.townId}');
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

        $.validator.addMethod("companyCodeUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var oldcode = $("#oldCode").val();
            var newcode = $("#orgCode").val();
            if(oldcode == newcode){
                deferred.resolve();
            }else{
                $.ajax({
                    url:ctx+'/common/check/org',
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
        }, icon + "公司编码已存在");

        $.validator.addMethod("companyNameUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var oldName = $("#oldName").val();
            var orgName = $('input[name="orgName"]').val();
            if(oldName == orgName){
                deferred.resolve();
            }else{
                $.ajax({
                    url:ctx+'/common/check/org',
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
        }, icon + "公司名称已存在");

        $("#org-edit-Form").validate({
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
                orgName: {required: true, minlength: 2, maxlength: 32,companyNameUnique:true },
                orgCode: { required: true, minlength: 6, maxlength: 32,companyCodeUnique:true},
                provinceId: { required: true},
                cityId: {  required: true },
                countyId: {  required: true },
                addr: { required: true, maxlength: 64},
//                groupTypeId: { required: true },
//                publicArea: {required: true, number: true},
                dewllArea: {required: true, number: true },
                seq: { required: true, number: true }
            },
            messages: {
                orgName: {
                    required: icon + "请输入公司名称",
                    minlength: icon + "公司名称必须2个字符以上",
                    maxlength: icon + "公司名称必须32个字符以内"
                },
                orgCode: {
                    required: icon + "请输入公司编码",
                    minlength: icon + "公司编码必须6个字符以上",
                    maxlength: icon + "公司编码必须32个字符以内"
                },
                provinceId: { required: icon + "请选省份" },
                cityId: { required: icon + "请选市" },
                countyId: { required: icon + "请选县" },
                addr: { required: icon + "请填写公司所在地址", maxlength: "地址描述必须64个字符以内"},
                groupTypeId: {  required: icon + "请选择集团性质"  },
//                publicArea: {  required: icon + "请输入正确的数字", number: icon + "请输入正确的数字"},
//                dwellArea: { required: icon + "请输入正确的数字", number: icon + "请输入正确的数字"},
                seq: {  required: icon + "请输入排序号", number: icon + "请输入正确的数字" }
            },
            submitHandler: function () {
                return parent.eidtCompanyCollBack();
            }
        });

    });
</script>