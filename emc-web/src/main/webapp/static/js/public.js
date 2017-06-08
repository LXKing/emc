/*下拉框*/

Date.prototype.Format = function(fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
$(function() {
	/*返回顶部*/
	$("#returnTop").click(function() {
		$('body,html').animate({
			scrollTop: 0
		}, 1000);
		return false;
	});

    $("body").on("click", ".x-sfbgbox", function() {
        $(this).next().stop(true, false).slideToggle(200, function() {});
    });
    //下拉切换事件
    $("body").on("click", ".x-sfoption p", function() {
        var selectval = $(this).html();
        var selectid = $(this).attr("value");

        $('#toolOrgId').val(selectid);
        $(this).parent().prev().find("input").val(selectval);
        //var hidval = $(this).parent().next().val(selectid);
        $(this).parent().slideUp(200, function() {});
        document.location.reload();
    });
    $("body").on("mouseleave", ".x-selectfree", function() {
        $(this).find(".x-sfoption").slideUp(200, function() {});
    });

    //单击按钮事件
    $(".select-boxbtnAlarm .btnAlarm").click(function() {
        $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");

        var thisText = $(this).text();
        if(thisText == "自定义") {
            $(".select-boxWdate input").attr("disabled", false).removeClass("time-input-disable");
            $("#begin").focus();
        } else if(thisText == "本采暖季"){
            $("#selectdate").hide();

        }else if(thisText == "本年度"){
            $("#selectdate").hide();
            $.ajax({
                url : _web+"/tools/search/year",
                type : "POST",
                dataType: "json",
                success:function(data){
                    $('#toolStartDate').val(data.startDate);
                    $('#toolEndDate').val(data.endDate);
                    $('#begin').val(data.startDate);
                    $('#end').val(data.endDate);
                }
            });
        }else if(thisText == "区域供暖"){
            $('#toolFeedType').val(1);
        }else if(thisText == "集中供暖"){
            $('#toolFeedType').val(2);
        }else {
            $(".select-boxWdate input").attr("disabled", true).addClass("time-input-disable");
        }
        document.location.reload();
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
        $('#datepicker-config').click(function() {
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
                format: 'yyyy-mm-dd'
            }).on('changeDate', function(ev) {
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
                forceParse: 0
            }).on('changeDate', function(ev) {
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

    $("#nearlyaweek").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formatoneweekdate);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $("#nearlyamonth").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formatonemonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $("#nearlytwomonth").click(function() {
        $(".btn-confirm").click();
        $("#begin").val(formattwomonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
    });

    $(".applyBtn").click(function() {
        $("#begin").val(startDate.Format("yyyy-MM-dd"));
        $("#end").val(endDate.Format("yyyy-MM-dd"));
        if(endDate<startDate){
            $("#end").val("");
        }
        $("#selectdate").hide();
        document.location.reload();
    });

    $(".cancelBtn").click(function() {
        $("#selectdate").hide();
    });
});



