var $parentNode = window.parent.document;

function $childNode(name) {
    return window.frames[name]
}

// tooltips
$('.tooltip-demo').tooltip({
    selector: "[data-toggle=tooltip]",
    container: "body"
});

// 使用animation.css修改Bootstrap Modal
$('.modal').appendTo("body");

$("[data-toggle=popover]").popover();

//折叠ibox
$('.collapse-link').click(function () {
    var ibox = $(this).closest('div.ibox');
    var button = $(this).find('i');
    var content = ibox.find('div.ibox-content');
    content.slideToggle(200);
    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    ibox.toggleClass('').toggleClass('border-bottom');
    setTimeout(function () {
        ibox.resize();
        ibox.find('[id^=map-]').resize();
    }, 50);
});

//关闭ibox
$('.close-link').click(function () {
    var content = $(this).closest('div.ibox');
    content.remove();
});

//判断当前页面是否在iframe中
if (top == this) {
    var gohome = '<div class="gohome"><a class="animated bounceInUp" href="index.html?v=4.0" title="返回首页"><i class="fa fa-home"></i></a></div>';
    $('body').append(gohome);
}

//animation.css
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //动画完成之前移除class
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

//拖动面板
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable({
            handle: handle,
            connectWith: connect,
            tolerance: 'pointer',
            forcePlaceholderSize: true,
            opacity: 0.8
        })
        .disableSelection();
};

function getHeight() {
    return $(window).height() - 130;
}

//导出EXCEL按钮绑定事件
$(document).on('click', '.excel-export-btn', function () {
    var $from = $(this).parents('from');
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});

//绑定弹出层小
$(document).on('click', '.top-layer-min', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");
    var $top = $(top.document);
    var layerDiv = '<div id="layer-div"></div>';
    $top.find('body').append(layerDiv);
    $.get(url, function (result) {
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: ['800px', '580px'],
            type: 1,
            title: title,
            btn: ['保存', '取消'],
            yes: function () {
                $top.find("#"+form).submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            content: $top.find("#layer-div")
        });
    });
});
//绑定弹出层满
$(document).on('click', '.top-layer-max', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = top.document.body.clientWidth+"px";
    var height = top.document.body.clientHeight+"px";
    var $top = $(top.document);
    var layerDiv = '<div id="layer-div"></div>';
    $top.find('body').append(layerDiv);
    $.get(url, function (result) {
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: [width, height],
            type: 1,
            title: title,
            btn: ['保存', '取消'],
            yes: function () {
                $top.find("#"+form).submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            content: $top.find("#layer-div")
        });
    });
});