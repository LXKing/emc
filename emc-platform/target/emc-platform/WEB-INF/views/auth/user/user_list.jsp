<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>系统用户</title>
    <script src="${ctx}/script/auth/huak.auth.user.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;系统用户</h5>
                </div>
                <div class="ibox-content">
                    <form id="users-from">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row col-lg-12">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">姓名</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="userName" placeholder="请输入用户姓名">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">工号</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="jobNum" placeholder="请输入用户工号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">手机号</label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="phone" placeholder="请输入用户手机号">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row col-lg-12 margin-top5 margin-bottom5">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">使用状态</label>

                                    <div class="col-lg-8">
                                        <select name="useStatus" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-lg-4">单位</label>

                                    <div class="col-lg-8">
                                        <div class="input-group">
                                            <input  name="orgId"   type="hidden"/>
                                            <input type="text" name="orgName"  id="common-part" placeholder="所属机构" disabled   class="form-control" />
                                            <span class="input-group-addon"><a class="common-org" style="color: #000000">选择</a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row btn-margin-bottom0">
                            <div class="col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['authUserAdd']}">
                                    <button type="button" class="btn btn-sm btn-info J_menuItem"
                                            data-href="${ctx}/user/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authUserUpdate']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="editUser()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authUserDelete']}">
                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteUsers()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope._auth['authUserRole']}">
                                    <button type="button" class="btn btn-sm btn-warning" onclick="userRolePage()">
                                        <i class="fa fa-wrench"></i>分配角色
                                    </button>
                                </c:if>
                            </div>
                            <div class="col-lg-4 btn-text-align">
                                <button type="button" class="btn btn-sm btn-primary" onclick="getUserList()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                                <c:if test="${sessionScope._auth['authUserExport']}">
                                <button type="button" class="btn btn-sm btn-outline btn-info excel-export-btn" export-url="${ctx}/user/export" >导出</button>
                                </c:if>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="7%">
                                <input type="checkbox" class="checkbox-all i-checks" name="input[]">全选
                            </th>
                            <th>名称</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>工号</th>
                            <th>手机号</th>
                            <th>登录账号</th>
                            <th>使用状态</th>
                            <th>单位(类型)</th>
                            <th>部门</th>
                            <th>备注</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="user-tbody"></tbody>
                    </table>
                    <div class="form-group pagination"></div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.user_id}" name="input[]">
        </td>
        <td>{$T.item.user_name}</td>
        <td>{#if $T.item.sex==0}男{#else}女{#/if}</td>
        <td>{$T.item.age}</td>
        <td>{$T.item.job_num}</td>
        <td>{$T.item.phone}</td>
        <td>{$T.item.login}</td>
        <td>{#if $T.item.use_status==0}启用{#else}禁用{#/if}</td>
        <td>{$T.item.step_name}({formatOrgType($T.item.step_type)})</td>
        <td>{$T.item.part_name}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            {#if $T.item.use_status==0}
            <c:if test="${sessionScope._auth['authUserDisable']}">
                <a class="btn btn-danger btn-xs btn-bitbucket user-disable" title="禁用"
                   onclick="disable('{$T.item.user_id}',1,this)">
                    <i class="fa fa-unlock-alt"></i>
                </a>
            </c:if>
            {#else}
            <c:if test="${sessionScope._auth['authUserEnable']}">
                <a class="btn btn-info btn-xs btn-bitbucket user-enable" title="启用"
                   onclick="enable('{$T.item.user_id}',0,this)">
                    <i class="fa fa-unlock"></i>
                </a>
            </c:if>
            {#/if}

            <c:if test="${sessionScope._auth['authUserResetPwd']}">
                <a class="btn btn-warning btn-xs btn-bitbucket" title="重置密码" onclick="resetPwd('{$T.item.user_id}')">
                    <i class="fa  fa-key"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope._auth['authUserDelete']}">
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteUser('{$T.item.user_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
            </c:if>
        </td>
    </tr>
    {#/for}
</textarea>
</body>
</html>