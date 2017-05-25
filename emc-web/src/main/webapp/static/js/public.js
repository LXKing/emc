/*下拉框*/
			
$(document).on("click", ".x-sfbgbox", function(){
	$(this).next().stop(true, false).slideToggle(200,function() {});
});
$(document).on("click", ".x-sfoption p", function(){
	var selectval = $(this).html();
	var selectid = $(this).attr("value");

	$(this).parent().prev().find("input").val(selectval);
	var hidval = $(this).parent().next().val(selectid);
	$(this).parent().slideUp(200, function(){});
});
$(document).on("mouseleave", ".x-selectfree", function(){
		$(this).find(".x-sfoption").slideUp(200, function(){});
});

/**
 * 时间范围
 */
$(document).on("click",".select-boxbtnAlarm .btnAlarm",function(){
    $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");

    var thisText = $(this).text();
    if (thisText == "自定义" ) {
        $(".select-boxWdate input").attr("disabled",false).removeClass("time-input-disable");
    }else{
        $(".select-boxWdate input").attr("disabled",true).addClass("time-input-disable");
    }
})



