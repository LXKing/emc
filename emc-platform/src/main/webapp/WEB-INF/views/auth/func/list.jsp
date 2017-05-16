<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>

    <script type="application/javascript">

        $(function() {
            //页面说明
            console.info("页面说明：\n1.左侧为系统菜单树，系统菜单分为用户菜单和系统菜单；\n" +
                    "2.菜单所有节点只有一个检索功能，叶子节点可以有其它的功能；\n" +
                    "3.唯一标识是唯一的且不能修改；\n" +
                    "功能：\n" +
                    "【添加】【删除】【修改】\n" +
                    "字段：\n功能主键、功能名称、唯一标识、是否检索\n" +
                    "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );

            //bootstrapTable
            var bt_data = [{
                "Tid": "1",
                "gnmc": "用户检索",
                "wybs": "userSearch",
                "sfjs": "是",
                "cjjg": "华热科技",
                "cjz": "超级管理员",
                "cjsj": "2017-01-01 15:23:23",
                "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a class="btn btn-xs btn-danger" title="删除" onclick="funcAuthPage()"><i class="fa fa-trash-o"></i></a>'
            }, {
                "Tid": "2",
                "gnmc": "添加用户",
                "wybs": "userAdd",
                "sfjs": "否",
                "cjjg": "华热科技",
                "cjz": "超级管理员",
                "cjsj": "2017-01-01 15:23:23",
                "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a class="btn btn-xs btn-danger" title="删除" onclick="funcAuthPage()"><i class="fa fa-trash-o"></i></a>'
            }, {
                "Tid": "2",
                "gnmc": "添加用户",
                "wybs": "userAdd",
                "sfjs": "否",
                "cjjg": "华热科技",
                "cjz": "超级管理员",
                "cjsj": "2017-01-01 15:23:23",
                "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a class="btn btn-xs btn-danger" title="删除" onclick="funcAuthPage()"><i class="fa fa-trash-o"></i></a>'
            }, {
                "Tid": "2",
                "gnmc": "添加用户",
                "wybs": "userAdd",
                "sfjs": "否",
                "cjjg": "华热科技",
                "cjz": "超级管理员",
                "cjsj": "2017-01-01 15:23:23",
                "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a class="btn btn-xs btn-danger" title="删除" onclick="funcAuthPage()"><i class="fa fa-trash-o"></i></a>'
            }];


            //
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            //下拉框js
            $(".chosen-select").chosen();
        });

        var setting = {
            view: {
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes =[
            {id:0,pId:null,name:"系统菜单", open:true},
            {id:1,pId:null,name:"用户菜单", open:true},
            { id:2, pId:0, name:"权限管理", open:true},
            { id:21, pId:2, name:"用户管理", open:true},
            { id:22, pId:2, name:"角色管理", open:true},
            { id:3, pId:1, name:"组织机构管理", open:true},
            { id:31, pId:3, name:"组织机构"},
            { id:32, pId:3, name:"换热站管理"},
            { id:4, pId:0, name:"系统管理"},
            { id:5, pId:0, name:"系统监控"},
            { id:6, pId:1, name:"权限管理", open:true},
            { id:61, pId:6, name:"用户管理", open:true},
            { id:62, pId:6, name:"角色管理", open:true}
        ];



    </script>
    <script src="${platform}/script/auth/huak.auth.func.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div>
                        <ul id="menuTreeFunc" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="funcs-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="menuId" value="1">

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                <button type="button" class="btn btn-sm btn-info" onclick="addFunc()"><i class="fa fa-plus"></i>添加</button>
                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="func-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="func-layer-div" style="display: none"></div>


<!--<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.func_id}" name="input[]">
        </td>
        <td>{$T.item.func_name}</td>
        <td>{$T.item.func_des}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <a class="btn btn-white btn-xs btn-bitbucket" title="查看">
                <i class="fa fa-file-text-o"></i>
            </a>
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteRole('{$T.item.func_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
        </td>
    </tr>
    {#/for}
</textarea>-->
</body>
</html>