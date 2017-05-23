//当前日期
function startTime(id_) {
 //获取当前系统日期
 var today=new Date();
 var y=today.getFullYear();
 var mo=today.getMonth();
 var da=today.getDate();
 var weekString="日一二三四五六";
 
 $(id_).html(y + "-" + (mo+1) + "-" + da + " 星期" + weekString.charAt(today.getDay()));
}


//导航选中
$(document).ready(function(){
    $("#ace-nav li.navlh>a").each(function(){  
        $this = $(this);  
        if($this[0].href==String(window.location)){ 
            $(this).parent("li").addClass("navlh-act").siblings().removeClass("navlh-act");
        }  
    });
});

