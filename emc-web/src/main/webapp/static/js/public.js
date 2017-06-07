/*下拉框*/

$("body").on("click", ".x-sfbgbox", function() {
	$(this).next().stop(true, false).slideToggle(200, function() {});
});
$("body").on("click", ".x-sfoption p", function() {
	var selectval = $(this).html();
	var selectid = $(this).attr("value");

	$(this).parent().prev().find("input").val(selectval);
	var hidval = $(this).parent().next().val(selectid);
	$(this).parent().slideUp(200, function() {});
});
$("body").on("mouseleave", ".x-selectfree", function() {
	$(this).find(".x-sfoption").slideUp(200, function() {});
});

$(function() {
	/*返回顶部*/
	$("#returnTop").click(function() {
		$('body,html').animate({
			scrollTop: 0
		}, 1000);
		return false;
	})
});


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
