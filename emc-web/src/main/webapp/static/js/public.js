/*下拉框*/
			
$("body").on("click", ".x-sfbgbox", function(){
	$(this).next().stop(true, false).slideToggle(200,function() {});
});
$("body").on("click", ".x-sfoption p", function(){
	var selectval = $(this).html();
	var selectid = $(this).attr("value");

	$(this).parent().prev().find("input").val(selectval);
	var hidval = $(this).parent().next().val(selectid);
	$(this).parent().slideUp(200, function(){});
});
$("body").on("mouseleave", ".x-selectfree", function(){
		$(this).find(".x-sfoption").slideUp(200, function(){});
});



