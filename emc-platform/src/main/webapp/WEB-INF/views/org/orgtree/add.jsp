<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    $.validator.setDefaults({
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
        $.validator.addMethod("checkUnique", function(value, element) {
            var deferred = $.Deferred();//
            var orgName = $('.orgNameID').val();
            //alert(orgName);
            $.ajax({
                url:_platform+'/org/checknode',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {orgName:orgName},
                dataType: 'json',
                success:function(result) {
                    //alert(result.flag);
                    //return false;
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "机构名称已存在");

        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if($(this).find('option:first').val()!=$(this).val()){
                $(this).siblings('.help-block').remove();
            }
            return false;
        });
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#orgTreeAddForm").validate({
            onsubmit:true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
        onfocusout: function (element) {
            if ($(element).val()==null||$(element).val()=="") {
                $(element).closest('.form-group').removeClass('has-error');
                $(element).siblings('.help-block').remove();
            }else{
                $(element).valid();
            }
        },
        onkeyup :false,// 是否在敲击键盘时验证
                rules: {
            orgName: {
                required: true,
                        minlength: 2,
                        checkUnique: true
            },
            orgCode: {
                required: true
            },
            shortName: {
                required: true
            }
        },
        messages: {
            orgName: {
                required: icon + "请输入机构名称",
                        minlength: icon + "机构名称必须2个字符以上"
            },
            orgCode: {
                required: icon + "请输入机构代码"
            },
            shortName:{
                required: icon + "请输入简称"
            }
        },
        submitHandler:function(){

            var index = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var data = $('#orgTreeAddForm').serialize();
            //alert(data);
            $.ajax({
                    url:_platform + '/org/addnodevalue',
                    data:$('#orgTreeAddForm').serialize(),
                    type:'post',
                    dataType:'json',
                    success:function(result) {

                        //alert(result.flag);
                        if(result.flag){
                            layer.closeAll();
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
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="orgTreeAddForm" role="form">
                <input type="hidden" name="pOrgId" value="${id}">
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>机构名称：</label>
                    <div class="col-sm-8">
                        <input id="roleName" name="orgName" class="form-control orgNameID" type="text" maxlength="8" placeholder="请输入机构名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>机构代码：</label>

                    <div class="col-sm-8">
                        <input name="orgCode" class="form-control" type="text" maxlength="16" placeholder="请输入机构代码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>机构简称：</label>

                    <div class="col-sm-8">
                        <input name="shortName" class="form-control" type="text" maxlength="16" placeholder="请输入机构简称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>公司：</label>

                    <div class="col-sm-8">
                        <select id="comId" name="comId" class="chosen-select form-control">
                            <option value="">请选择公司</option>
                        <c:forEach items="${company}" var="item" varStatus="status" >
                                <option value="${item.id}">${item.cname}</option>
                        </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>类型：</label>

                    <div class="col-sm-8">
                        <select id="typeId" name="typeId" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <c:forEach items="${sysdic}" var="item" varStatus="status" >
                                <%--　　var value = ${item.cname}; //传递过来的是int或float类型，不需要加引号--%>
                                <%--　　var id = "${status.id}";//加引号--%>
                                <option value="${item.id}">${item.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>排序值：</label>

                    <div class="col-sm-8">
                        <input name="seq" class="form-control" type="text" maxlength="16" placeholder="请输入排序值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">备注：</label>
                    <div class="col-sm-8">
                        <input name="memo"   class="form-control" type="text" maxlength="255" placeholder="请输入备注">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
