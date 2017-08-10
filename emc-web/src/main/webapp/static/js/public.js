/*下拉框*/
//公共颜色
var color = ['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c','#99ff33','#FFFF00'];
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 公共方法
 * 格式化数据
 *
 * */
function toFormatNum(val){
    var divisor = 10000;//倍率
    var suffix = '万';//单位
    var f = parseFloat(val);
    if (isNaN(f)) {
        return 0;
    }
    return toOneDecimal(f/divisor) + suffix;
}

/**
 * 公共方法
 * 格式化数据
 *
 * */
function toFormatNumber(val,num){
    num = num == undefined ? 1:num;
    var divisor = 10000;//倍率
    var suffix = '万';//单位
    var f = parseFloat(val);
    if (isNaN(f)) {
        return 0;
    }
    var result = parseFloat(f/divisor).toFixed(num)
    return  result+ suffix;
}

function toFormatNumbers(val,num){
    num = num == undefined ? 1:num;
    var f = parseFloat(val);
    if (isNaN(f)) {
        return 0;
    }
    var result =f.toFixed(num);
    return  result;
}

function toolReplace() {
    var url = document.location.href;
    var reurl = url;
    if (url.indexOf("?") > 0) {
        reurl = url.substr(0, url.indexOf("?"));
    }
    document.location.replace(reurl);
}
/**
 * 保留2位小数
 * @param x
 * @returns {Number}
 */
function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}

/**
 * 保留1位小数
 * @param x
 * @returns {Number}
 */
function toOneDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*10)/10;
    return f;
}

$(function () {





    //条件下拉框
    $.ajax({
        url: _web + "/tools/search/org",
        type: "POST",
        async:false,
        dataType: "json",
        success: function (data) {
            var html = '';
            $.each(data, function (idx, item) {
                if (idx == 0) {
                    if (getCookie("toolOrgId") == null || getCookie("toolOrgId") == "") {
                        $('.x-sfleft1.x-sfw1').html('<input type="text" value="' + item.ORG_NAME + '" readonly="readonly">');
                        $('#toolOrgId').val(item.ID);
                    } else {
                        $('.x-sfleft1.x-sfw1').html('<input type="text" value="' + getCookie("toolOrgName") + '" readonly="readonly">');
                        $('#toolOrgId').val(getCookie("toolOrgId"));
                    }

                }
                html += '<p value="' + item.ID + '">' + item.ORG_NAME + '</p>'
            });
            $(".x-sfoption.x-sfoption1").html(html);

        }
    });
    //默认类型
    if (getCookie("toolFeedType") == null || getCookie("toolFeedType") == "") {
        $('#toolFeedType').val(2);
    } else {
        var toolFeedType = getCookie("toolFeedType");
        $('#toolFeedType').val(toolFeedType);
        if (toolFeedType == 1) {
            $('.btnAlarm').each(function () {
                if ("集中供暖" == $(this).text()) {
                    $(this).removeClass("btnAlarm-on");
                } else if ("区域供暖" == $(this).text()) {
                    $(this).addClass("btnAlarm-on");
                }
            });
        }
    }



    //默认时间段
    if (getCookie("dateType") == null || getCookie("dateType") == "") {
        $.ajax({
            url: _web + "/tools/search/season",
            type: "POST",
            async:false,
            dataType: "json",
            success: function (data) {
                $('#toolStartDate').val(data.startDate);
                $('#toolEndDate').val(data.endDate);
                $('#begin').val(data.startDate);
                $('#end').val(data.endDate);
            }
        });
    } else {
        var dataType = getCookie("dateType");
        $('.btnAlarm').each(function () {
            if ("本年度" == $(this).text() && dataType == 1) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            } else if ("本采暖季" == $(this).text() && dataType == 2) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            } else if ("自定义" == $(this).text() && dataType == 3) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            }
        });
        $('#toolStartDate').val(getCookie("toolStartDate"));
        $('#toolEndDate').val(getCookie("toolEndDate"));
        $('#begin').val(getCookie("toolStartDate"));
        $('#end').val(getCookie("toolEndDate"));
    }




    /*返回顶部*/
    $("#returnTop").click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 1000);
        return false;
    });

    $("body").on("click", ".x-sfbgbox", function () {
        $(this).next().stop(true, false).slideToggle(200, function () {
        });
    });
    //下拉切换事件
    $("body").on("click", ".x-sfoption p", function () {
        var selectval = $(this).html();
        var selectid = $(this).attr("value");

        $('#toolOrgId').val(selectid);
        $(this).parent().prev().find("input").val(selectval);

        setCookie('toolOrgId', selectid, 3);
        setCookie('toolOrgName', selectval, 3);
        //var hidval = $(this).parent().next().val(selectid);
        $(this).parent().slideUp(200, function () {
        });
        toolReplace();

    });
    $("body").on("mouseleave", ".x-selectfree", function () {
        $(this).find(".x-sfoption").slideUp(200, function () {
        });
    });

    //单击按钮事件
    $(".select-boxbtnAlarm .btnAlarm").click(function () {
        var thisText = $(this).text();
        if (thisText != "本采暖季") {
            $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
        }

        if (thisText == "自定义") {
            $(".select-boxWdate input").attr("disabled", false).removeClass("time-input-disable");
            $("#begin").focus();
        } else if (thisText == "本采暖季") {
            $("#selectdate").hide();
            $.ajax({
                url: _web + "/tools/search/season",
                type: "POST",
                async:false,
                dataType: "json",
                success: function (data) {
                    if(data.flag==false){
                        top.layer.alert(data.msg);
                    }else{
                        $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                        $('#toolStartDate').val(data.startDate);
                        $('#toolEndDate').val(data.endDate);
                        $('#begin').val(data.startDate);
                        $('#end').val(data.endDate);
                        setCookie('toolStartDate', data.startDate, 3);
                        setCookie('toolEndDate', data.endDate, 3);
                        setCookie('dateType', 2, 3);
                        toolReplace();
                    }

                }
            });
        } else if (thisText == "本年度") {
            $("#selectdate").hide();
            $.ajax({
                url: _web + "/tools/search/year",
                type: "POST",
                async:false,
                dataType: "json",
                success: function (data) {
                    $('#toolStartDate').val(data.startDate);
                    $('#toolEndDate').val(data.endDate);
                    $('#begin').val(data.startDate);
                    $('#end').val(data.endDate);
                    setCookie('toolStartDate', data.startDate, 3);
                    setCookie('toolEndDate', data.endDate, 3);
                    setCookie('dateType', 1, 3);
                    toolReplace();
                }

            });

        } else if (thisText == "区域供暖") {
            $('#toolFeedType').val(1);
            setCookie('toolFeedType', 1, 3);

            toolReplace();
        } else if (thisText == "集中供暖") {
            $('#toolFeedType').val(2);
            setCookie('toolFeedType', 2, 3);
            toolReplace();
        } else {
            $(".select-boxWdate input").attr("disabled", true).addClass("time-input-disable");
        }

    });

    //				$(".energy_consumption").click(function() {
    //					layer.open({
    //						type: 2,
    //						title: '能耗分析',
    //						shadeClose: true,
    //						shade: 0.8,
    //						area: ['98%', '95%'],
    //						content: 'energy_analysis_detail.html' //iframe的url
    //					});
    //				})

    //选择日期
    updateConfig();
    var startDate = new Date();
    var endDate = new Date();

    function updateConfig() {
        //					var options = {
        //						singleDatePicker: true,
        //						locale: {
        //
        //							customRangeLabel: '自定义',
        //							daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
        //							monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
        //								'七月', '八月', '九月', '十月', '十一月', '十二月'
        //							],
        //							firstDay: 1
        //						}
        //					};
        //
        //					$('#datepicker-config').daterangepicker(options, function(start, end, label) {
        //						console.info("start"+start.format('YYYY-MM-DD'));
        //						console.info("end"+end.format('YYYY-MM-DD'));
        //						$("#begin").val(start.format('YYYY-MM-DD'));
        //						$("#end").val(end.format('YYYY-MM-DD'));
        //
        //					});
        //
        $('#datepicker-config').click(function () {
            $("#selectdate").show();
            $('.calendar.left').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: 'yyyy-mm-dd',
                initialDate:$("#begin").val()
            }).on('changeDate', function (ev) {
                startDate = ev.date;

            });
            $('.calendar.right').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                initialDate:$("#end").val()
            }).on('changeDate', function (ev) {
                endDate = ev.date;

            });

        });

    }

    //繁琐了
    //获取当前时间
    var nowdate = new Date();
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatnowdate = y + '-' + m + '-' + d;

    //获取近一周
    var oneweekdate = new Date(nowdate - 7 * 24 * 3600 * 1000);
    var y = oneweekdate.getFullYear();
    var m = oneweekdate.getMonth() + 1;
    var d = oneweekdate.getDate();
    var formatoneweekdate = y + '-' + m + '-' + d;

    //获取近一月
    nowdate.setMonth(nowdate.getMonth() - 1);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatonemonth = y + '-' + m + '-' + d;

    //获取近两月
    nowdate.setMonth(nowdate.getMonth() - 2);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 2;
    var d = nowdate.getDate();
    var formattwomonth = y + '-' + m + '-' + d;

    $("#nearlyaweek").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formatoneweekdate);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formatoneweekdate, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $("#nearlyamonth").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formatonemonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formatonemonth, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $("#nearlytwomonth").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formattwomonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formattwomonth, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $(".applyBtn").click(function () {
        $("#begin").val(startDate.Format("yyyy-MM-dd"));
        $("#end").val(endDate.Format("yyyy-MM-dd"));
        if (endDate < startDate) {
            $("#end").val("");
        }
        $("#selectdate").hide();
        setCookie('toolStartDate', startDate.Format("yyyy-MM-dd"), 3);
        if (endDate >= startDate) {
            setCookie('toolEndDate', endDate.Format("yyyy-MM-dd"), 3);
        }
        setCookie('dateType', 3, 3);
        toolReplace();
    });

    $(".cancelBtn").click(function () {
        $("#selectdate").hide();
    });

    /*导出列表*/
    $(document).on("click", ".exportlist", function () {
        var $from = $("#searchTools");
        var url = $(this).attr('export-url') + '?' + $from.serialize();
        window.open(url);
    });

    changeFace();
    $("#header .changeface").click(function() {
        faceKey = faceKey == "dark" ? "white" : "dark";
        changeFace();
    });
});


/**
 * 换肤
 * @type {string}
 */
var path = _web+"/static/";
var faceKey = localStorage.faceKey == null||localStorage.faceKey == ""?"dark":localStorage.faceKey; //dark
var chartsColor;

function changeFace() {
    $('html').removeClass();
    localStorage.faceKey = faceKey;
    chartsColor = {
        linefontcolor: faceKey == 'dark' ? '#fff' : '#666',
        areacolor: faceKey == 'dark' ? ['transparent', 'rgba(15,54,66,.7)'] : ['#F3F3F4', '#E4E4E5'],
        chart01: {
            color: faceKey == 'dark' ? ['#33fff8', '#304d60'] : ['#3B96DD', '#c2ccd3'],

        },
        chart02: {
            color: faceKey == 'dark' ? ['#33fff8'] : ['#3B96DD'],

        },
        chart03: {
            color: faceKey == 'dark' ? ['#33fff8', '#304d60'] : ['#3B96DD', '#c2ccd3'],

        },
        chart10: {
            facecolor1: faceKey == 'dark' ? '#618292' : '#9a9a9b',
            facecolor2: faceKey == 'dark' ? '#33fff8' : '#277aba',
            facecolor3: faceKey == 'dark' ? '#30515e' : ''
        },
        chart11: {
            facecolor1: faceKey == 'dark' ? ['#33fff8'] : ['#3B96DD'],

        },
        chart08: {
            facecolor1: faceKey == 'dark' ? ['#21a7a9'] : ['#7fb7e1'],

        },
        chart05: {
            facecolor1: faceKey == 'dark' ? '#fff' : '#8394aa',
            facecolor2: faceKey == 'dark' ? '#33fff8' : '#2eada8',
            facecolor3: faceKey == 'dark' ? '#325e70' : '#ffffff',
            facecolor4: faceKey == 'dark' ? '#093043' : '#f0f1f2',
            facecolor5: faceKey == 'dark' ? ['63%', '64%'] : ['0%', '64%'],
            facecolor6: faceKey == 'dark' ? ['#33fff8', '#32657f', '#1d465b', '#ff6349', '#3db4ff']:['#32bbb6', '#8394aa', '#b7c1cf', '#df5f4a', '#3b96db']
        },
        chart07: {
            facecolor1: faceKey == 'dark' ? '#89b1c3' : '#ccc',
        },
        chart09: {
            facecolor1: faceKey == 'dark' ? '#325e70' : '#ffffff',
            facecolor2: faceKey == 'dark' ? '#1d465b' : '#dce0e5',
            facecolor3: faceKey == 'dark' ? '#33fff8' : '#3b96db',
            facecolor4: faceKey == 'dark' ? '#33fff8' : '#348bce',
            facecolor5: faceKey == 'dark' ? '#f86148' : '#d4513b',
            facecolor6: faceKey == 'dark' ? ['63%', '64%'] : ['0%', '64%'],
        },
        ec1:{
            facecolor1: faceKey == 'dark' ? '#325d6c' : '#dbdcdf',
            facecolor2: faceKey == 'dark' ? '#325d6c' : '#abcd',
            facecolor3: faceKey == 'dark' ? '#618292' : '#9a9a9b',
            facecolor4: faceKey == 'dark' ? '#2f4f5f' : '#dbdcdf',
            facecolor5: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],
        },
        ec2:{
            facecolor1: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],

        },
        ec3:{
            facecolor1: faceKey == 'dark' ? "transparent":"#fff",

        },
        ec4:{
            facecolor1: faceKey == 'dark' ? "#618292":"#9a9a9b",
            facecolor2: faceKey == 'dark' ? "#618292":"#9a9a9b",
            facecolor3: faceKey == 'dark' ? ['#33fff8', '#304d60']:['#3B96DD', '#a1b1c5'],
            facecolor4: faceKey == 'dark' ?['#33fff8']: ['#3B96DD'],
            facecolor5:faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#32657f', '#33fff8', '#ff6349']:  ['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c'],
            facecolor6:faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#33fff8', '#ff6349']: ['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
        }

    }

    //top（tools.jsp） 顶部源、网、站、线、户 的toolOrgType 默认值设置
    if(getCookie("toolOrgType") == null || getCookie("toolOrgType") == ""){
        $('#toolOrgType').val("");
        if(faceKey == "dark"){
            $("#website").attr("src", _web + "/static/imgdark/index/websitet_cs01.png");
        }else{
            $("#website").attr("src", _web + "/static/img/index/websitet_cs01.png");
        }
    }else{
        var toolOrgType = getCookie("toolOrgType");
        if(faceKey == "dark"){
            $("#website").attr("src", _web + "/static/imgdark/index/websitet_cs0" + (Number(toolOrgType)+1) + ".png");
        }else{
            $("#website").attr("src", _web + "/static/img/index/websitet_cs0" + (Number(toolOrgType)+1) + ".png");
        }

        $('#toolOrgType').val(toolOrgType);
    }

    if(faceKey == "dark") {
        $('html').addClass('facedark');
        $(".energy_black img").eq(0).attr("src", path + "imgdark/images/btn01.png");
        $(".energy_black img").eq(1).attr("src", path + "imgdark/images/btn02.png");
        $(".energy_black img").eq(2).attr("src", path + "imgdark/images/btn03.png");
        $(".bordertopnone img").eq(0).attr("src", path + "imgdark/tijian.png");
    }else{
        $(".energy_black img").eq(0).attr("src", path + "img/images/btn01.png");
        $(".energy_black img").eq(1).attr("src", path + "img/images/btn02.png");
        $(".energy_black img").eq(2).attr("src", path + "img/images/btn03.png");
        $(".bordertopnone img").eq(0).attr("src", path + "img/tijian.png");
    }
    loadDataFun();
}

function loadDataFun(){

}



