$(function(){
    loadDataFun();


})
/**
 * 绑定插件到table - tr - td - class
 * 单击td 出现input
 * 失去焦点，修改隐藏域值，调用回调方法
 *
 * <td class="td-edit">
 <div class="text-left div-edit">{$T.record.usedNum}
 <input type="hidden" name="" value="{$T.record.usedNum}">
 </div>
 </td>
 */
function editTd(){
    var $div,value,$hidden;
    $('.editTable').on('click','tr .td-edit',function(){
        //单击td事件
        var $this = $(this);
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
        if(num != value){
            // 修改标记
            var $flag = $this.parent('tr').find('input[name="flag"]');
            $flag.val(1);//1为用修改  2为不用修改
        }
        $hidden.val(num);
        $div.empty().append(num).append($hidden);
        $td.empty().append($div);
    });
}
function loadDataFun() {

    // http://www.treejs.cn/v3/main.php#_zTreeInfo
    query(1);
    editTd();

}

function reset(){
   $("#unitName").val("");
   $("#name").val("");
   $("#startTime").val("");
   $("#endTime").val("");
}

function query(num){

//table 数据查询
    var unitName=$("#unitName").val();
    var name=$("#name").val();
    var sTime=$("#startTime").val();
    var eTime=$("#endTime").val();
    console.log(unitName);
    $.ajax({
        url: _web + '/meterData/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: {unitName:unitName,name:name,startTime:sTime,endTime:eTime,pageNumber:num},
        dataType: "json",
        success: function (data) {
            debugger;
            console.log(data.object);
            // 附上模板
            $("#projectTbody").setTemplateElement("template");
            // 给模板加载数据
            $("#projectTbody").processTemplate(data);
        }
    });
}
