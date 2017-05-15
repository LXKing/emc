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
                selectedMulti: false,
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
                beforeEditName:beforeEdt,
                beforeRemove:beforeRemove
            }
        };


        //页面说明
        console.info("页面说明：\n左侧菜单树:是系统菜单的树形结构。\n" +
                "右侧：菜单的详细信息。\n" +
                "功能：\n" +
                "【添加】【删除】【修改】【检索】\n" +
                "字段：\n菜单名称、菜单上级\n" +
                "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
        $.fn.zTree.init($("#menuTree"), setting);
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
    function beforeRemove(treeId, treeNode) {
        if(treeNode.isParent){
            layer.confirm('该节点是父节点,是否删除该节点以及其子节点？', {
                btn: ['确定','否'] //按钮
            }, function(){

            }, function(){

            });
        }else{
            layer.confirm('是否删除该节点？', {
                btn: ['确定','否'] //按钮
            }, function(){

            }, function(){

            });
        }

    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };

    function beforeEdt(treeId,treeNode){
        $.get(_platform + '/menu/edit',{id:treeNode.id,menuType:0}, function (result) {
            $('#menu-layer-div').html(result);
        });
        layer.open({
            area: ['500px', '380px'],
            type: 1,
            title: '修改菜单',
            btn: ['保存', '取消'],
            yes: function () {
                $("#menuEditForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#menu-layer-div'),
            end:function(){
                $.fn.zTree.init($("#menuTree"), setting);
            }
        });

    }

    function onRemove(e,treeId,treeNode){
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        var paramsArray = new Array();
        if(treeNode.isParent){
            var childNodes = zTree.removeChildNodes(treeNode);
            for(var i = 0; i < childNodes.length; i++){
                paramsArray.push(childNodes[i].id);
            }
            paramsArray.push(treeNode.id);
        }else{
            paramsArray.push(treeNode.id);
        }
        var ids = paramsArray.join(",");
        $.ajax({
            type: "post",
            url: _platform+"/menu/delete",
            data: {ids:ids},
            dataType: "json",
            success: function (data) {
                layer.msg(data.msg);

            },
            error: function(data) {
                layer.msg(data.msg);
            }
        });

        return;
    }

    function addData(treeId,treeNode){
        $.get(_platform + '/menu/add',{pId:treeNode.id,menuType:0}, function (result) {
            $('#menu-layer-div').html(result);
        });
        layer.open({
            area: ['500px', '380px'],
            type: 1,
            title: '添加菜单',
            btn: ['保存', '取消'],
            yes: function () {
                $("#menuAddForm").submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            shadeClose: true, //开启遮罩关闭
            content: $('#menu-layer-div'),
            end:function(){
                $.fn.zTree.init($("#menuTree"), setting);
            }
        });
    };

});



