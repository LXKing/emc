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

            <form class="form-horizontal" id="menuAddForm" role="form"  >
                <div class="form-group">
                    <label class="col-sm-3  control-label">上级菜单：</label>

                    <div class="col-sm-8">
                        <input name="pMenuName" class="form-control" value="${pmenu.menu_name}" type="text" disabled>
                        <input type="hidden" name="pMenuId" value="${pmenu.menu_id}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单名称：</label>

                    <div class="col-sm-8">
                        <input name="menuName" class="form-control" type="text" maxlength="16" placeholder="请输入菜单名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>唯一标识：</label>

                    <div class="col-sm-8">
                        <input id="uName" name="uName" class="form-control" type="text" maxlength="32" placeholder="请输入唯一标识">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单链接：</label>

                    <div class="col-sm-8">
                        <input name="url"  class="form-control" type="text" maxlength="100" placeholder="请输入菜单链接，#表示无链接">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">图标样式：</label>

                    <div class="col-sm-8">
                        <input name="imgName"  class="form-control" type="text" maxlength="64" placeholder="请输入图标样式">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>前后台：</label>

                    <div class="col-sm-8">
                        <c:if test="${pmenu.isower ne 0}">
                            <input type="hidden" name="isOwer" value="${pmenu.isower}"/>
                        </c:if>
                        <select name="isOwer" class="chosen-select form-control" >
                            <option value="">请选择</option>
                            <option value="0" ${pmenu.isower eq 0?'selected':''}>公共</option>
                            <option value="1" ${pmenu.isower eq 1?'selected':''}>前台</option>
                            <option value="2" ${pmenu.isower eq 2?'selected':''}>后台</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>排序：</label>

                    <div class="col-sm-8">
                        <input name="seq" class="form-control" type="text" maxlength="3" placeholder="请输入排序值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">备注：</label>

                    <div class="col-sm-8">
                        <textarea class="form-control" rows="6" maxlength="125" name="memo"
                                  placeholder="请输入备注"></textarea>
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
        var isOwer = '${pmenu.isower}';
        if(isOwer!='0'){
            $(".chosen-select").attr('disabled', true).trigger("chosen:updated");
        }

        $.validator.addMethod("checkUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:ctx+'/menu/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {uName:$('#uName').val()},
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
        }, "唯一标识已存在");

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
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#menuAddForm").validate({
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
                menuName: {
                    required: true,
                    minlength: 2
                },
                uName: {
                    required: true,
                    checkUnique:true
                },
                url: {
                    required: true
                },
                isOwer: {
                    required: true
                },
                seq: {
                    required: true
                }
            },
            messages: {
                menuName: {
                    required: icon + "请输入菜单名称",
                    minlength: icon + "菜单名称必须2个字符以上"
                },
                uName: {
                    required: icon + "请输入唯一标识"
                },
                url: {
                    required: icon + "请输入菜单链接"
                },
                isOwer: {
                    required: icon + "请选择前后台"
                },
                seq: {
                    required: icon + "请输入排序值"
                }
            },
            submitHandler: function () {
                return parent.addCollBack();
            }
        });

    });
</script>