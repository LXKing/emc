//bootstraptable 高
function getHeight() {
    return $(window).height() - 130;
}

//导出EXCEL按钮绑定事件
$(document).on('click', '.excel-export-btn', function () {
    var $from = $(this).parents('from');
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});

//绑定弹出层小
$(document).on('click', '.top-layer-min', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 0.7;
    var height = 0.85;
    openLayer(url,title,form,width,height);
});
//绑定弹出层满
$(document).on('click', '.top-layer-max', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 1;
    var height = 1;
    openLayer(url,title,form,width,height);
});

function openLayer(url,title,form,width,height){
    if(width==null||width=='undefined'){
        width = top.document.body.clientWidth*0.7+"px";
    }else{
        width = top.document.body.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.body.clientHeight*0.85+"px";
    }else{
        height = top.document.body.clientHeight*height+"px";
    }

    var $top = $(top.document);
    var layerDiv = '<div id="layer-div"></div>';
    $top.find('body').append(layerDiv);
    $.get(url, function (result) {
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: [width, height],
            type: 1,
            title: title,
            btn: ['保存', '取消'],
            yes: function () {
                $top.find("#"+form).submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            content: $top.find("#layer-div")
        });
    });
}
//绑定查询
$(document).on('click', '.emc-search', function () {
    var tableId = $(this).attr("bootstrap-table-id");
    $("#"+tableId).bootstrapTable("refresh");
});


/**
 * 单位公共树
 */
$(document).ready( function (e) {
    var $this =$(this.getElementsByClassName("org-tree"));
    $this.html("<div id='temp_org_tree' class='ztree'></div>");
    if($this.length>0){
        $.post(_platform + '/common/org/tree',function(data){
            var setting = {
                view: {selectedMulti: false,fontCss:{color:"blue"}},
                check: { enable: false },
                data: { simpleData: { enable: true, idKey: "id", pIdKey: "pId", system:"Name", rootPId: "" } },
                async : { enable : true },
                edit: {enable: false },
                callback: { onClick:treeNodeClick }
            };
            var nodes='';
            var zNodes ='[';
            for (var i=0;i<data.length;i++){
                var orgName='"' + data[i].orgName + '"';
                var id='"' + data[i].id + '"';
                var pid='"' + data[i].pOrgId + '"';
                zNodes+="{ id:"+id+", pId:"+pid+", name:"+orgName+", open:true},";
            };
            var newnodes=zNodes.substring(0,zNodes.length-1);
            nodes= newnodes+"]";
            top.orgTree = $.fn.zTree.init($("#temp_org_tree"), setting, eval("(" + nodes + ")"));
        });

    }

});

var treeNodeClick = function(e,treeId,treeNode){ };