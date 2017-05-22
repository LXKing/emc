<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 个人资料</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <script type="application/javascript" src="${platform}/script/auth/huak.auth.menu.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理</h5>
                </div>
                <div  class="ibox-content">
                    <ul id="menuTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <input id="menuAddAuth" VALUE="${sessionScope._auth['menuAdd']}" type="hidden"/>
        <input id="menuUpdateAuth" VALUE="${sessionScope._auth['menuUpdate']}"  type="hidden"/>
        <input id="menuDeleteAuth" VALUE="${sessionScope._auth['menuDelete']}"  type="hidden"/>
        <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单详情</h5>
                </div>
                <div class="ibox-content" style="height: 100%">
                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
                            <form class="form-horizontal" id="addForm" role="form">
                                <input name="id"  type="hidden"/>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单属性：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="menuType" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单名称：</label>

                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input class="form-control" id="menuName" readonly  type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单链接：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input  class="form-control" id="menuUrl" readonly  type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单序号：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input  class="form-control" id="seq" readonly  type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单类型：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="type"  readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="menu-layer-div" style="display: none"></div>
</body>
</html>
