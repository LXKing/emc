<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script type="application/javascript">
            function search(){
                $table.bootstrapTable('refresh');
            }
    </script>
    <script src="${platform}/script/auth/huak.auth.role.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="roles-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-xs-3 col-md-3 col-lg-3">角色名称</label>
                                    <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入角色名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">创建时间</label>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="start" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="end" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-xs-3 col-md-3 col-lg-3">创建机构</label>
                                    <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入创建机构">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-xs-3 col-md-3 col-lg-3">创建者</label>
                                    <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入创建者">
                                    </div>
                                </div>
                            </div>

                           <!-- <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-xs-3 col-md-3 col-lg-3">下拉框</label>
                                    <div class="input-group col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <select name="useStatus" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                        </select>
                                    </div>
                                </div>
                            </div>-->
                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">

                                    <button type="button" class="btn btn-sm btn-info top-layer-min" layer-form-id="roleAddForm" layer-title="添加角色" layer-url="${platform}/role/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>

                                    <!--<button type="button" class="btn btn-sm btn-info" onclick="editRole()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>

                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteRoles()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>

                                    <button type="button" class="btn btn-sm btn-warning" onclick="roleAuthPage()">
                                        <i class="fa fa-wrench"></i>角色授权
                                    </button>-->
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/user/export"> 导出Excel
                                </button>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="role-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="role-layer-div" style="display: none"></div>

</body>
</html>