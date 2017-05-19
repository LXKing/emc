<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="userEditForm" role="form">
                <div class="form-group">
                	<input type="hidden" id="id" name="id" value="${user.id }">
                	<input type="hidden" id="orgId" name="orgId" value="${user.orgId }">
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>中文名称：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <input name="userName" class="form-control" type="text" maxlength="16" placeholder="请输入用户中文名称" value="${user.userName }">
                    </div>
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>联系电话：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <input name="mobile" class="form-control" type="text" maxlength="16" placeholder="请输入用户联系电话" value="${user.mobile }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>登录账号：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <input name="login" class="form-control" type="text" maxlength="16" placeholder="请输入用户登录账号" value="${user.login }">
                    </div>
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>电子邮箱：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <input name="mail" class="form-control" type="text" maxlength="16" placeholder="请输入用户电子邮箱" value="${user.mail }">
                    </div>
                </div>
                <div class="form-group">
                   <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>登录密码：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <input id="password" name="password" class="form-control" type="text" maxlength="16" placeholder="请输入用户登录密码" value="${user.password }">
                    </div>
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>使用状态：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <select id="useStatus" name="useStatus" class="form-control">
                        	<option value="0">启用</option>
                        	<option value="1">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>组织机构：</label>
	                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
	                    	<ul id="org" class="user-org-tree" style="height: 200px;overflow-y:scroll;border: 1px solid #E5E6E7;" value="${user.orgId }"></ul>
	                    </div>
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>所属员工：</label>
                    <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                        <select id="empId" name="empId" class="form-control" multiple="" style="height: 200px;overflow-y:scroll;">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label"><span class="red">*</span>备注说明：</label>
                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8"> 
                        <textarea class="form-control" rows="4" maxlength="125" id="memo" name="memo" placeholder="请输入备注"></textarea>
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
    
//初始化组织机构树
function initOrgTree(){
	var $this = top.$('#org').html("<div id='temp_org_tree' class='ztree'></div>");
	var $temptree = top.$('#temp_org_tree');
	if($this.length>0){
        $.post(_platform + '/common/org/tree',function(data){
            var setting = {
                view: {selectedMulti: false,fontCss:{color:"blue"}},
                check: { enable: false },
                data: { simpleData: { enable: true, idKey: "id", pIdKey: "pId", system:"Name", rootPId: "" } },
                async : { enable : true },
                edit: {enable: false },
                callback: { onClick:orgTreeNodeClick }
            };
            var nodes='';
            var zNodes ='[';
            for (var i=0;i<data.length;i++){
                var orgName='"' + data[i].orgName + '"';
                var id='"' + data[i].id + '"';
                var pid='"' + data[i].pOrgId + '"';
                zNodes+="{ id:"+id+", pId:"+pid+", name:"+orgName+", open:true},";
            };
            var newnodes=zNodes.substring(0,zNodes.length-1);
            nodes= newnodes+"]";
            top.addUserOrgTree = $.fn.zTree.init($temptree, setting, eval("(" + nodes + ")"));
        });
    }
}

//点击组织机构树
function orgTreeNodeClick(){
	var _tree = top.addUserOrgTree;
	var nodes = _tree.getSelectedNodes();
	var selectedNode = nodes[0];
	//根据机构id，查询所属此机构的员工
	$.post(_platform + '/user/org/emp',{
		orgId:selectedNode.id
	},function(result){
		top.$('#empId').html('');
		var selectHtmlStr = "";
		if(result!=null&&result!=""){
			for(var i=0;i<result.length;i++){
				var empId = result[i].empId;
				var empName = result[i].empName;
				selectHtmlStr += "<option value='"+empId+"'>"+empName+"</option>";
			}
			top.$('#empId').html(selectHtmlStr);
			top.$('#orgId').val(selectedNode.id);
		}
	},'json');
}

$(function () {
	top.$('#memo').text('${user.memo}');
	//初始化组织机构树
	initOrgTree();
	//默认选中
	//获取表单元素
 	var $form = $(top.document).find("#userEditForm");
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
        	userName: {
                required: true,
                isName: true,
                minlength: 2
            },
            mobile: {
                required: true,
                isPhone: true
            },
            login: {
                required: true,
                isLogin: true
            },
            password: {
                required: true,
                isPassword: true,
                minlength:6
            },
            useStatus: {
                required: true
            },
            mail:{
                isEmail:true
            }
        },
        messages: {
        	userName: {
                required: icon + "请输入用户名称",
                minlength: icon + "用户名称必须2个字符以上"
            },
            mobile: {
                required: icon + "请输入手机号码"
            },
            login: {
                required: icon + "请输入登录账号"
            },
            password: {
                required: icon + "请输入密码",
                minlength:icon + "密码最少6个字符以上"
            },
            useStatus: {
                required: icon + "请选择使用状态"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _platform + '/user/edit',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#user-table-list').bootstrapTable("refresh");
                    } else {
                        layer.close(index);
                        top.layer.msg(result.msg);
                    }
                }
            });
        }
    });
	
	//名称校验
	$.validator.addMethod("isName", function(value, element){
	    var tel = /^[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*$/;
	    return this.optional(element) || (tel.test(value));
	},icon +  "请输入正确的名称");
	
	//手机号码校验
	$.validator.addMethod("isPhone", function(value, element){
	    var tel = /^(1\d{10})$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "请输入正确的手机号码");
	
	//登录账号校验
	$.validator.addMethod("isLogin", function(value, element){
	    var tel = /^[\w\-\@\.]+$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "请输入正确的登录账号");
	
	//密码校验,支持所有数字、英文大小写、英文键盘所有特殊符号
	$.validator.addMethod("isPassword", function(value, element){
	    var tel = /^[\w\-\@\.\\\!\#\$\%\^\&\*\(\)\+\=\`\~\,\/\<\>\?\;\:\'\"\[\]\{\}\|]+$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "请输入正确的密码");
	
	//电子邮箱校验
	$.validator.addMethod("isEmail", function(value, element){
	    var tel = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "邮箱格式不正确，请重新输入");
	
//     $.validator.addMethod("loginUnique", function(value, element) {
//         var deferred = $.Deferred();//创建一个延迟对象
//         $.ajax({
//             url:ctx+'/user/check/login',
//             type:'POST',
//             async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
//             data: {login:$('#login').val()},
//             dataType: 'json',
//             success:function(result) {
//                 if (!result.flag) {
//                     deferred.reject();
//                 } else {
//                     deferred.resolve();
//                 }
//             }
//         });
//         //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
//         return deferred.state() == "resolved" ? true : false;
//     }, icon + "登录账号已存在");

});
</script>