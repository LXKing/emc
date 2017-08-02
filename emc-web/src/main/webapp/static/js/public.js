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

var faceKey = localStorage.faceKey == null?"dark":localStorage.faceKey; //dark
var chartsColor;
changeFace();
$("#header .changeface").click(function() {
	faceKey = faceKey == "dark" ? "" : "dark";
	changeFace();
});

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

	if(faceKey == "dark") {
		$("#website").attr("src", "imgdark/index/websitet_cs01.png");
		$('html').addClass('facedark');
		$(".energy_black img").eq(0).attr("src", "imgdark/images/btn01.png");
		$(".energy_black img").eq(1).attr("src", "imgdark/images/btn02.png");
		$(".energy_black img").eq(2).attr("src", "imgdark/images/btn03.png");
		$(".bordertopnone img").eq(0).attr("src", "imgdark/tijian.png");
	}else{
		$("#website").attr("src", "img/index/websitet_cs01.png");
		$(".energy_black img").eq(0).attr("src", "img/images/btn01.png");
		$(".energy_black img").eq(1).attr("src", "img/images/btn02.png");
		$(".energy_black img").eq(2).attr("src", "img/images/btn03.png");
		$(".bordertopnone img").eq(0).attr("src", "img/tijian.png");
	}
	loadDataFun();
}

function loadDataFun() {

}