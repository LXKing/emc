<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/static/Hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/animate.css" rel="stylesheet">

    <link href="${ctx}/static/Hplus/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
        <!-- Sweet Alert -->
    <link href="${ctx}/static/Hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/static/Hplus/css/style.css?v=4.1.0" rel="stylesheet">

    <!-- 全局js -->
    <script src="${ctx}/static/Hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/static/Hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx}/static/Hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/layer/layer.min.js"></script>
    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx}/static/Hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/validate/messages_zh.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctx}/static/Hplus/js/content.js?v=1.0.0"></script>
    <script src="${ctx}/static/Hplus/js/plugins/jquery-yinbin-plugin.js"></script>
    <script src="${ctx}/static/Hplus/js/hplus.js?v=4.1.0"></script>
    <script src="${ctx}/static/Hplus/js/plugins/pace/pace.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/static/Hplus/js/jquery-jtemplates.js"></script>
    <!-- Sweet alert -->
    <script src="${ctx}/static/Hplus/js/plugins/sweetalert/sweetalert.min.js"></script>

    <!-- Bootstrap table -->
    <script src="${ctx}/static/Hplus/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="${ctx}/static/Hplus/js/contabs.js"></script>
    <!-- Peity -->
    <script src="${ctx}/static/Hplus/js/demo/bootstrap-table-demo.js"></script>
    <!-- layerDate plugin javascript -->
    <script src="${ctx}/static/Hplus/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${ctx}/static/Hplus/js/plugins/layer/extend/layer.ext.js"></script>
    <!-- Data picker -->
    <script src="${ctx}/static/Hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
        <!-- Toastr script -->
    <script src="${ctx}/static/Hplus/js/plugins/toastr/toastr.min.js"></script>
    <script src="${ctx}/static/Hplus/js/pagination.js"></script>
    <script src="${ctx}/static/Hplus/js/treegrid.js"></script>
    <script src="${ctx}/static/Hplus/js/pcctv.js"></script>
    <script src="${ctx}/static/Hplus/js/org.js"></script>
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