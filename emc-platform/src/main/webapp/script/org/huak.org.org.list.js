
/*
 * 2017年5月10日 14:41:38 lrm
 * */

var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false,
        fontCss:{color:"blue"}
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            system:"Name",
            rootPId: ""
        }
    },
    async : { // 是否异步加载 相当于ajax

        enable : true//设置 zTree 是否开启异步加载模式
                      //默认值：false

    },

    edit: {
        enable: true
    },
    callback: {
        beforeRemove:beforeRemove,//点击删除时触发，用来提示用户是否确定删除
        beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
        beforeRename:beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求
        onRemove:onRemove,//删除节点后触发，用户后台操作
        onRename:onRename,//编辑后触发，用于操作后台
        beforeDrag:beforeDrag, //用户禁止拖动节点
        onClick:clickNode//点击节点触发的事件

    }
};

$(document).ready(function(){
    //页面说明
    var  url ="${platform}/org/ztreeValue";
    console.info("页面说明：系统登录用户选择企业或者公司后显示该企业或者公司的组织机构\n" +
        "功能：\n【添加】【删除】【修改】\n" +
        "字段：\n组织机构名称、上级组织机构\n" +
        "以树显示组织机构，所以有隐藏字段：\n创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
    ztreeValue();
});
var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var id1 =treeNode.id;
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var id =treeNode.id;
        $.get(_platform + '/org/addnode/' + id, function (result) {
            $('#orgtree-layer-div').html(result);
        });
        layer.open({
            area: ['800px', '580px'],
            type: 1,
            title: '添加节点',
            btn: ['保存', '取消'],
            yes: function () {
                $("#orgTreeAddForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#orgtree-layer-div'),
            end:function(){
                /*根据 treeId 获取 zTree 对象*/
//                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
//                    type = "refresh",
//                    silent = false,
//                /*获取 zTree 当前被选中的节点数据集合*/
//                    nodes = zTree.getSelectedNodes();
//                /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
//                zTree.reAsyncChildNodes(nodes[id1], type, silent);
                ztreeValue();
            }
        });
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {

    // alert(treeNode.tId);
    $("#addBtn_"+treeNode.tId).unbind().remove();

};
function beforeRemove(treeId,treeNode){
    layer.confirm('确认删除吗', {
        btn: ['确定','取消'] //按钮
    }, function(){
        onRemove(treeId,treeNode);
        return true;
    });
    return false;
}
function beforeEditName(treeId,treeNode){
    if(treeNode.isParent){
        layer.msg('不准编辑非叶子节点！');
        return false;
    }
    return true;
}

function onRemove(treeId,treeNode){
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var paramsArray = new Array();
    if(treeNode.isParent){
        var childNodes = zTree.removeChildNodes(treeNode);
        for(var i = 0; i < childNodes.length; i++){
            paramsArray.push(childNodes[i].id);
        }
        //alert("删除父节点的id为："+treeNode.id+"\r\n他的孩子节点有："+paramsArray.join(","));
        paramsArray.push(treeNode.id);
        //alert("将要删除的所有节点为："+paramsArray.join(","));
    }else{
        paramsArray.push(treeNode.id);
    }
    //alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id+"---"+paramsArray.join(","));
         var ids = paramsArray.join(",");
            //alert(ids);
    $.ajax({
        type: "post",
        url: _platform+"/org/delete",
        data: {ids:ids},
        dataType: "json",
        success: function (data) {
            console.log(data);
            //alert(data.flag);
            zTree.removeNode(treeNode);
            layer.msg(data.msg);
        },
        error: function(data) {
            layer.msg("data is erro");
        }
    });

    return false;
}
function beforeRename(treeId,treeNode,newName,isCancel){

    if(newName.length < 3){
        alert("名称不能少于3个字符！");
        return false;
    }
    return true;
}
function onRename(e, treeId, treeNode, isCancel) {
    //需要对名字做判定的，可以来这里写~~
    //alert(treeNode.id+"---"+treeNode.name);
       var id = treeNode.id;
    $.get(_platform + '/org/editnode/' + id, function (result) {
        $('#orgtree-layer-div').html(result);
    });
    layer.open({
        area: ['800px', '580px'],
        type: 1,
        title: '编辑节点',
        btn: ['保存', '取消'],
        yes: function () {
            $("#orgTreeAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#orgtree-layer-div'),
        end:function(){
            ztreeValue();
        }
    });
    return false;
}
function beforeDrag(treeId,treeNodes){
    return false;
}
function clickNode(treeId,treeNodes){
    return false;
}
function ztreeValue(){
    $.ajax({
        type: "get",
        url: _platform+"/org/ztreeValue",
        data: {},
        dataType: "json",
        success: function (data) {
            //console.log(data);
            var nodes='';
            var zNodes ='[';
            for (var i=0;i<data.length;i++){
                var orgName='"' + data[i].orgName + '"';
                var id= data[i].id ;
                var pid=data[i].pOrgId;
                zNodes+="{ id:"+id+", pId:"+pid+", name:"+orgName+", open:true},";
            };
            var newnodes=zNodes.substring(0,zNodes.length-1);
            nodes= newnodes+"]"
            //alert(nodes);
            $.fn.zTree.init($("#treeDemo"), setting, eval("(" + nodes + ")"));
        },
        error: function(data) {
            alert("data is erro");
        }
    });
}
