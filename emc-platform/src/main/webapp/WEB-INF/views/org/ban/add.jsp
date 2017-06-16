<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="banAddForm" role="form">
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>楼座名称：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="banName" class="form-control" type="text" maxlength="16" placeholder="请输入楼座名称">
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                   <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>所属公司：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select disabled="disabled" onchange="getSelectHtml()" id="com" name="com" class="form-control m-b" ></select>
	                    	<input type="hidden" id="comId" name="comId" />
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>组织机构：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<ul id="org" class="ban-add-org-tree" style="height: 200px;overflow-y:scroll;border: 1px solid #E5E6E7;"></ul>
	                    	<input type="text" class="form-control" name="orgId" id="orgId" style="visibility: hidden;height: 0px;">
	                    </div>
		            </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>所属小区：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onclick="addCommunity()" id="communityId" name="communityId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <!-- 省市县乡村开始 -->
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>省：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onchange="queryPCCTV(this,'cityId','2',['cityId','countyId','townId','villageId'])" id="provinceId" name="provinceId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>市：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onchange="queryPCCTV(this,'countyId','3',['countyId','townId','villageId'])" id="cityId" disabled="disabled" name="cityId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>县：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onchange="queryPCCTV(this,'townId','4',['townId','villageId'])" id="countyId" disabled="disabled" name="countyId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>乡：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onchange="queryPCCTV(this,'villageId','5',['villageId'])" id="townId" disabled="disabled" name="townId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>村：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select id="villageId" disabled="disabled" name="villageId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>详细地址：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7"> 
	                        <textarea class="form-control" rows="4" maxlength="125" name="addr" placeholder="请输入详细地址"></textarea>
	                    </div>
	                </div>
                </div>
            </form>
        </div>
    </div>
</div>
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
//点击小区下拉框事件
top.addCommunity = function(){
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	if(orgId==null||orgId==''||comId==null||comId==''){
		top.layer.msg('请先选择公司和组织结构！');
		return;
	}
}
//获取小区下拉框html文
top.getSelectHtml = function(){
	top.$("#communityId").html('');
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	if(orgId==null||orgId==''||comId==null||comId=='') return;
	$.get(_platform + '/ban/communitySelectHtmlStr',{
		orgId:orgId,
		comId:comId
	},function(data){
		top.$("#communityId").html(data.html);
	},'json');
}
//查询省市县乡村html文
top.queryPCCTV = function(obj,id,level,sub){
	var _select=$(obj);
	var code = _select.val();
	if(code==null||code==''){
		for(var i=0;i<sub.length;i++){
			top.$('#'+sub[i]).html('');
			top.$('#'+sub[i]).attr('disabled',true);
		}
		return;
	}
	top.$("#"+id).attr("disabled",false);
	
	$.get(_platform + '/community/PCCTVHtmlStr',{
		"pCode":code,
		"level":level
	},function(data){
		top.$("#"+id).html(data.html);
	},'json');
}

//点击组织机构树
function treeNodeClick(){
    var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree");
    var nodes = treeObj.getSelectedNodes();
	var selectedNode = nodes[0];
	top.$('#orgId').val(selectedNode.id);//选择组织机构节点的时候保存所选节点的组织Id
	top.$('#orgId-error').remove();//如果没选择组织结构点击保存会出现 错误提示 ，这样可以在选择节点后消除 错误提示
	top.$('#orgId').closest('.form-group').removeClass('has-error').addClass('has-success');
	top.getSelectHtml();//选择节点后更新此节点相关联的小区信息
}

$(function () {
	//初始化公司下拉框
	top.$('#com').html('${com}');
	console.log(top.$('#com :selected').val());
	top.$('#comId').val(top.$('#com :selected').val());
	top.$('#provinceId').html('${province}');
	
	//初始化组织机构树
	banAddOrg = new Org({
        class:"ban-add-org-tree"
    });
	banAddOrg.initTree();
	
	//获取表单元素
 	var $form = $(top.document).find("#banAddForm");
    var icon = "<i class='fa fa-times-circle'></i> ";
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
	//表单验证
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
        	banName: {
                required: true,
                isName: true,
                minlength: 2
            },
            orgId: {
                required: true
            },
            communityId: {
                required: true  
            },
            provinceId: {
                required: true
            },
            cityId: {
                required: true
            },
            countyId: {
                required: true
            },
            townId: {
                required: true
            },
            villageId: {
                required: true
            },
            addr: {
                required: true
            }
        },
        messages: {
        	banName: {
                required: icon + "请输入楼座名称",
                minlength: icon + "楼座名称必须2个字符以上"
            },
            orgId: {
                required: icon + "请选择所属组织结构"
            },
            communityId:{
            	required: icon + "请选择所属小区"
            },
            provinceId: {
                required: icon + "请选择所属省"
            },
            cityId: {
                required: icon + "请选择所属市"
            },
            countyId: {
                required: icon + "请选择所属县"
            },
            townId: {
                required: icon + "请选择所属乡"
            },
            villageId: {
                required: icon + "请选择所属村"
            },
            addr: {
                required: icon + "请填写详细地址"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _platform + '/ban/add',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#ban-table-list').bootstrapTable("refresh");
                    } else {
                        top.layer.close(index);
                        top.layer.msg(result.msg);
                    }
                }
            });
        }
    });
	
	$.validator.addMethod("isName", function(value, element){
		var name = /^([\u4e00-\u9fa5_a-zA-Z0-9]{1,20}$)/;
	    return this.optional(element) || (name.test(value));
	},icon +  "请输入正确的名称,汉字、字母和数字的组合");
	
});
</script>