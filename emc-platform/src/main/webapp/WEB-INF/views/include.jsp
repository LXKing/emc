<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--项目路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--图标--%>
<link rel="shortcut icon" href="${ctx}/static/favicon.ico">
<%-- css--%>
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx}/static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx}/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctx}/static/css/animate.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="${ctx}/static/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx}/static/css/huak/style.css" rel="stylesheet">

<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/static/js/plugins/layer/layer.min.js"></script>
<!-- jQuery Validation plugin javascript-->
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/plugins/validate/messages_zh.min.js"></script>
<!-- 自定义js -->
<script src="${ctx}/static/js/content.js?v=1.0.0"></script>
<script src="${ctx}/static/js/plugins/jquery-yinbin-plugin.js"></script>
<script src="${ctx}/static/js/hplus.js?v=4.1.0"></script>
<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>
<script src="${ctx}/static/js/plugins/iCheck/icheck.min.js"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<!-- Sweet alert -->
<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Bootstrap table -->
<script src="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx}/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${ctx}/static/js/contabs.js"></script>
<!-- layerDate plugin javascript -->
<script src="${ctx}/static/js/plugins/layer/laydate/laydate.js"></script>
<script src="${ctx}/static/js/plugins/layer/extend/layer.ext.js"></script>
<!-- Data picker -->
<script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<!-- Toastr script -->
<script src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script>
<script src="${ctx}/static/js/pagination.js"></script>
<script src="${ctx}/static/js/treegrid.js"></script>
<script src="${ctx}/static/js/pcctv.js"></script>
<script src="${ctx}/static/js/org.js"></script>
<script type="text/javascript">
    layer.config({
        extend: ['skin/moon/style.css'] //加载新皮肤
        /*skin: 'layer-ext-moon' //一旦设定，所有弹层风格都采用此主题。*/
    });
    toastr.options = {
        closeButton: true,
        debug: true,
        progressBar: true,
        "positionClass": "toast-top-right",
        showDuration: 400,
        hideDuration: 1000,
        timeOut: 2000,
        extendedTimeOut: 1000,
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    }


</script>