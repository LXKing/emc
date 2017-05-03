function menuItem() {
    $("#content-main").empty().load($(this).attr('data-href'));
}
function menuItemUri(url) {
    $("#content-main").empty().load(url);
}
$(function () {
    //lzj
    //变更菜单栏绑定事件方式，已适应动态添加元素事件绑定
    //$('.J_menuItem').on('click', menuItem);
    $(document).on('click', '.J_menuItem', menuItem);


    /**
     * 设置全局session超时ajax处理
     *
     * */
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        complete: function (XMLHttpRequest, textStatus) {
            var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");
            var locat = window.location.protocol + window.location.host + window.location.port + "/login";
            if (sessionStatus == "timeout") {
                layer.alert("会话超时，请重新登录",function(){
                    window.location.replace(locat);
                });
                //使用 setTimeout 时需注意，当该代码执行时，JS 会立即编译函数第一个参数“code”
                //所以该函数的第一个参数应该为：需要编译的代码、或者一个函数
                //例1：setTimeout("alert('x')", 2000);
                //例2：setTimeout(function () { alert('x'); }, 2000);

                //错误示例：setTimeout(alert('x'), 2000); "x" 会立马跳出来，延时没有效果
                setTimeout(function(){
                    window.location.replace(locat);
                }, 5000);
            }
        }
    })
});
