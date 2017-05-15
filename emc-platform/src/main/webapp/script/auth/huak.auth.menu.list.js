var locate = _platform;
$(function(){
        var setting = {
            async: {
                enable: true,
                type: "GET",
                url:_platform+"/menu/listTree",
                autoParam: ["id", "name"]
            },
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: true,
                fontCss:{color:"blue"}
            },

            check: {
                enable: true
            },
            data: {
                key : {
                    name : "name"
                },
                simpleData : {
                    enable : true,
                    idKey : "id",
                    pIdKey : "pId",
                    rootPId : null
                }
            },
            edit: {
                enable: true
            },
            callback:{
                beforeEditName:beforeEdt
            }
        };


        //页面说明
        console.info("页面说明：\n左侧菜单树:是系统菜单的树形结构。\n" +
                "右侧：菜单的详细信息。\n" +
                "功能：\n" +
                "【添加】【删除】【修改】【检索】\n" +
                "字段：\n菜单名称、菜单上级\n" +
                "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
        $.fn.zTree.init($("#treeDemo"), setting);
    var newCount = 1;
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            addData(treeId,treeNode);

            return false;
        });
    };
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };
    function beforeEdt(treeId,treeNode){
        alert("添加菜单！");
    }

    function addData(treeId,treeNode){
        $.get(_platform + '/menu/add',{pId:treeNode.id,menuType:0}, function (result) {
            $('#menu-layer-div').html(result);
        });
        layer.open({
            area: ['500px', '380px'],
            type: 1,
            title: '添加角色',
            btn: ['保存', '取消'],
            yes: function () {
                $("#menuAddForm").submit();
                $.fn.zTree.init($("#treeDemo"), setting);
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#menu-layer-div')
        });
    };

});



