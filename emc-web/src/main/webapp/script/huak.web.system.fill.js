
var dataMap =null;
$(function(){
    dataMap=new Map();
    loadDataFun();
})

function loadDataFun() {

    query(1);
    var $div,value,$hidden;
    $('.editTable').on('click','.icon-edit',function(){
        //单击td事件
        var $this = $(this).parents("tr").find(".td-edit");
        $div = $this.find('.div-edit');
        value = Number($div.text());
        $hidden = $this.find('input:hidden');
        var input = '<input class="input-edit" type="text" value="' + value + '">';
        $this.empty().append($hidden).append(input);
        $this.find('.input-edit').focus();
    }).on('click','tr .input-edit',function(event){
        event.stopPropagation();//阻止冒泡
    }).on('focusout','tr .input-edit',function(){
        //失去焦点事件
        var $this = $(this);
        var $td = $this.parent('.td-edit');
        var num = Number($this.val());
        //console.log(num);
        if(num != value){
            // 修改标记
            var $flag = $td.parent('tr').find('input[name="flag"]');
            var $id = $td.parent('tr').find('input[name="id"]');
            $flag.val(1);//1为用修改  2为不用修改
            dataMap.remove($id);
        }
        $hidden.val(num);
        $div.empty().append(num).append($hidden);
        $td.empty().append($div);
    });
}

function reset(){
   $("#unitName").val("");
   $("#name").val("");
   $("#startTime").val("");
   $("#endTime").val("");
}




function query(num){
//table 数据查询
    debugger;
    dataMap.clear();
    var unitName=$("#unitName").val();
    var name=$("#name").val();
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var energyType =$("#energyType").val();
//    alert(unitName);
//    alert(energyType);
//    alert(name);
//    alert(startTime);
//    alert(endTime);
    $.ajax({
        url: _web + '/data/fill/list/data',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: {name:name,startTime:startTime,endTime:endTime,unitName:unitName,energyType:energyType},
        dataType: "json",
        success: function (data) {
            console.info(data);
            if(data.flag){
                $.each(data.object,function(index,value){
                    if(value.realFlag == 0){
                        dataMap.put(value.id,value.id);
                    }
                });
                $("#redtipspad").text(data.object.page.total);
                $("#projectTbody").setTemplateElement("template");
                $("#projectTbody").processTemplate(data);
                /*分页效果*/
                $("#paging").createPage({
                    pageCount: data.object.page.pages, //总页数
                    current: data.object.page.pageNumber, //当前页数
                    backFn: function (p) { //单击回调方法，p是当前页码
                        query(p);
                    }
                });
            }
        }
    });
}
function dataSave (){
//    if(dataMap.size()>0){
//        top.layer.alert("填完再保存！");
//        return ;
//    }
    var data = $("#_editForm").serializeJson();
    console.log(data);
    $.ajax({
        url:_web +"/data/fill/add/data",
        type: "POST",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data:data,            //将Json对象序列化成Json字符串，toJSON()需要引用jquery.json.min.js
        success: function(data){
            if(data == "1"){
                top.layer.alert("数据填报失败！");
            }
            if(data == "0"){
                top.layer.alert("数据已填报！");
            }
        }
    });
    dataMap.clear();
}