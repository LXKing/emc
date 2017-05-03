/**
 * 公共js文件
 * @type {document|*|HTMLDocument|jQuery.Widget.document}
 */

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

/**
 * 得到所有选择CheckBox的value
 * table 可选 指定table的id
 * */
function getCheckValues(table) {
    var checks;
    if (table != null && table != undefined && table != "") {
        checks = $('#' + table).find("input.i-checks:checked");
    } else {
        checks = $("input.i-checks:checked");
    }
    var vals = "";
    $.each(checks, function (i, item) {
        if (!$(item).hasClass('checkbox-all')) {
            vals += item.value + ",";
        }
    });
    if (vals.length > 0) {
        vals = vals.substr(0, vals.length - 1);
    }
    return vals;
}


/**
 * 得到所有选择CheckBox的行信息封装成object array
 * table 可选 指定table的id
 * */
function getCheckItems(table) {
    var checks;
    if (table != null && table != undefined && table != "") {
        checks = $('#' + table).find("input.i-checks:checked");
    } else {
        checks = $("input.i-checks:checked");
    }
    var arrays = new Array();
    $.each(checks, function (i, item) {
        if (!$(item).hasClass('checkbox-all')) {
            var obj = new Object();
            obj.id = item.value;
            obj.name = $(item).parents('tr').find('#name').text();
            arrays.push(obj);
        }
    });
    return arrays;
}


/**
 * 选择设备
 * 得到所有选择CheckBox的行信息封装成object array
 * table 可选 指定table的id
 * */
function getCheckMae(table) {
    var checks;
    if (table != null && table != undefined && table != "") {
        checks = $('#' + table).find("input.i-checks:checked");
    } else {
        checks = $("input.i-checks:checked");
    }
    var arrays = new Array();
    $.each(checks, function (i, item) {
        if (!$(item).hasClass('checkbox-all')) {
            var obj = new Object();
            obj.maeId = item.value;
            obj.orgId = $(item).attr("data-org");
            obj.maeName = $(item).parents('tr').find('.common-mae-name').text();
            obj.orgName = $(item).parents('tr').find('.common-org-name').text();
            arrays.push(obj);
        }
    });
    return arrays;
}

/**
 * 得到所有选择CheckBox的行信息封装成object array
 * table 可选 指定table的id
 * */
function getCheckObject(table) {
    var checks;
    if (table != null && table != undefined && table != "") {
        checks = $('#' + table).find("input.i-checks:checked");
    } else {
        checks = $("input.i-checks:checked");
    }
    var arrays = new Array();
    $.each(checks, function (i, item) {
        if (!$(item).hasClass('checkbox-all')) {
            var obj = new Object();
            obj.id = item.value;
            obj.name = $(item).parents('tr').find('td.tree-level').text();
            arrays.push(obj);
        }
    });
    return arrays;
}

/* icheck 全选 全不选
 * 只选择本table中的CheckBox
 * */
$(document).delegate('.checkbox-all', 'ifChecked', function (event) {
    $(this).parents('table').find('input.i-checks').iCheck('check');
});
$(document).delegate('.checkbox-all', 'ifUnchecked', function (event) {
    $(this).parents('table').find('input.i-checks').iCheck('uncheck');
});

/*
 * 公共设备插件
 * */
$(document).on('click', '.common-mae', function (event) {
    var $this = $(this);
    var div = "<div class='common-mae-window'></div>";
    $this.parents("body").append(div);
    var orgId = $this.parents('form').find("#orgId").val();
    var orgName = $this.parents('form').find("#orgName").val();
    $.get(ctx + '/common/mae/select?orgId='+orgId+'&orgName='+encodeURI(encodeURI(orgName)), function (result) {
        $('.common-mae-window').html(result);
    });
    layer.open({
        area: ['980px', '630px'],
        type: 1,
        title: '选择设备',
        btn: ['保存', '取消'],
        yes: function () {
            var maeArray = getCheckMae('mae_common');
            $this.parents('.mae-form-class').find("#orgId").val(maeArray[0].orgId);
            $this.parents('.mae-form-class').find("#maeId").val(maeArray[0].maeId);
            $this.parents('.mae-form-class').find("#maeName").val(maeArray[0].maeName);
            $this.parents('.mae-form-class').find("#orgName").val(maeArray[0].orgName);
            layer.closeAll();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('.common-mae-window'),
        end :function(){
            $('.common-mae-window').remove();
        }
    });
});

/*
 * 公共公司插件
 * */
$(document).on('click', '.common-org', function (event) {
    var $this = $(this);
    if(!$this.hasClass("default")){
        var div = "<div class='common-org-window'></div>";
        $this.parents("body").append(div);
        var $div = $(".common-org-window");
        var org = new Org({
            dom: $this,
            windows: $div
        });
        org.show();
    }

});

/*
 * 公共热源插件
 * */
$(document).on('click', '.common-game', function (event) {
    var $this = $(this);
    var div = "<div class='common-game-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-game-window");
    var game = new Game({
        dom: $this,
        windows: $div
    });
    game.show();
});


/*
 * 公共一次网插件
 * */
$(document).on('click', '.common-oncenet', function (event) {
    var $this = $(this);
    var div = "<div class='common-oncenet-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-oncenet-window");
    var oncenet = new Oncenet({
        dom: $this,
        windows: $div
    });
    oncenet.show();
});

/*
 * 公共二次网插件
 * */
$(document).on('click', '.common-secondnet', function (event) {
    var $this = $(this);
    var div = "<div class='common-secondnet-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-secondnet-window");
    var secondnet = new Secondnet({
        dom: $this,
        windows: $div
    });
    secondnet.show();
});


/*
 * 公共热力站插件
 * */
$(document).on('click', '.common-station', function (event) {
    var $this = $(this);
    var div = "<div class='common-station-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-station-window");
    var station = new Station({
        dom: $this,
        windows: $div
    });
    station.show();
});


/*
 * 公共楼栋插件
 * */
$(document).on('click', '.common-ban', function (event) {
    var $this = $(this);
    var div = "<div class='common-ban-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-ban-window");
    var ban = new Ban({
        dom: $this,
        windows: $div
    });
    ban.show();
});

/*
 * 公共单元插件
 * */
$(document).on('click', '.common-unit', function (event) {
    var $this = $(this);
    var div = "<div class='common-unit-window'></div>";
    $this.parents("body").append(div);
    var $div = $(".common-unit-window");
    var unit = new Unit({
        dom: $this,
        windows: $div
    });
    unit.show();
});

/* 格式化字符串，按照长度格式化为  带省略号的字符串
 * str 要格式化的字符串
 * len 超过长度格式化
 * */
function formatText(str, len) {
    if (str.length > len) {
        return str.substring(0, len - 1) + "...";
    } else {
        return str;
    }
}


/*参数1 ：当前后台获取的字典类型id
 参数2 ：获取 类型表map数据 格式：{key = 外键id ，value=显示字段}
 此格式化方法只支持map集合*/
function formatType(type, o) {
    var value;
    if (o == undefined) {
        value = "";
    } else {
        var temp = o.replace("{", "").replace("}", "");
        var st = temp.split(",");
        for (var i = 0; i < st.length; i++) {

            var id = st[i].split("=")[0].replace(/\s/ig, '');
            ;
            var val = st[i].split("=")[1].replace(/\s/ig, '');
            ;
            if (type == id) {
                value = val;
                break;
            } else {
                continue;
            }
        }
    }
    return  value;
}

/*  参数1 ：当前后台获取字典标识
 参数2 ：获取外键表中map数据 格式：{key = 字典标识 ，value=显示字段}
 此格式化方法只支持map集合*/
function formatId(dId, o) {
    var value;
    if (o == undefined) {
        value = "";
    } else {
        var temp = o.replace("{", "").replace("}", "");
        var st = temp.split(",");
        for (var i = 0; i < st.length; i++) {

            var id = st[i].split("=")[0].replace(/\s/ig, '');
            ;
            var val = st[i].split("=")[1].replace(/\s/ig, '');
            ;
            if (dId == id) {
                value = val;
                break;
            } else {
                continue;
            }
        }
    }
    return  value;

}

/**
 * 返回当前日期
 * 2016-01-01
 */
function getFormatDate() {

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10)month = "0" + month;
    if (day < 10)day = "0" + day;
    return year + "-" + month + "-" + day;
}

/**
 * 返回当前时间
 * 2016-01-01 23:01:01
 */
function getFormatTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minutes = date.getMinutes();
    var second = date.getSeconds();
    if (month < 10)month = "0" + month;
    if (day < 10)day = "0" + day;
    if (hour < 10)hour = "0" + hour;
    if (minutes < 10)minutes = "0" + minutes;
    if (second < 10)second = "0" + second;
    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + second;
}

/**
 * 格式化时间
 */
var formatDate = function (value, fomart){
    var $date = value+"";
        return  format($date    , fomart);
}

var format = function (time, format) {
    if(time.lastIndexOf(".0")>0){
        return time.replace(".0","");
    }
    if(!/^(\d{4})\/(\d{1,2})\/(\d{1,2})$/.test(str)){
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }else{
        return time;
    }


}

//导出EXCEL按钮绑定事件
$(document).on('click', '.excel-export-btn', function () {
    var $from = $(this).parents('from');
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});

/**
 * 格式化组织机构类型
 * @param typeId
 */
function formatOrgType(typeId) {
    if(typeId == 1){
        return "公司";
    }else if(typeId == 2){
        return "热源";
    }else if(typeId == 3){
        return "一次网";
    }else if(typeId == 4){
        return "热力站";
    }else if(typeId == 5){
        return "二次线";
    }else if(typeId == 6){
        return "楼栋";
    }else if(typeId == 7){
        return "单元";
    }else if(typeId == 8){
        return "房间";
    }else if(typeId == 9){
        return "部门";
    }else if(typeId == 10){
        return "一次网网段";
    }else if(typeId == 11){
        return "二次线网段";
    }else if(typeId == 12){
        return "小室";
    }else{
        return typeId;
    }
}

/**
 * 格式化设备类型
 * @param typeId
 */
function formatMaeType(typeId) {
    if(typeId == 1){
        return "汽轮机";
    }else if(typeId == 2){
        return "脱硫设备";
    }else if(typeId == 3){
        return "蒸汽锅炉";
    }else if(typeId == 4){
        return "分离器";
    }else if(typeId == 5){
        return "循环泵";
    }else if(typeId == 6){
        return "引风机";
    }else if(typeId == 7){
        return "热水锅炉";
    }else if(typeId == 8){
        return "热泵";
    }else if(typeId == 9){
        return "余热回收设备";
    }else if(typeId == 10){
        return "换热器";
    }else if(typeId == 11){
        return "补水泵";
    }else if(typeId == 12){
        return "脱硝设备";
    }else if(typeId == 13){
        return "除氧器";
    }else if(typeId == 14){
        return "软化水设备";
    }else if(typeId == 15){
        return "CEMS设备";
    }else if(typeId == 16){
        return "燃烧器";
    }else if(typeId == 17){
        return "球磨机";
    }else if(typeId == 18){
        return "送风机";
    }else{
        return typeId;
    }
}


