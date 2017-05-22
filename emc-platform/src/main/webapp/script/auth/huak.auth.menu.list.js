$(function(){
    /**
     * ztree 初始化配置
     * @type {{async: {enable: boolean, type: string, url: string, autoParam: string[]}, view: {addHoverDom: addHoverDom, removeHoverDom: removeHoverDom, selectedMulti: boolean, fontCss: {color: string}}, check: {enable: boolean}, data: {key: {name: string}, simpleData: {enable: boolean, idKey: string, pIdKey: string, rootPId: null}}, edit: {enable: boolean}, callback: {beforeEditName: beforeEdt, beforeRemove: beforeRemove, onClick: clickNode}}}
     */
        var setting = {
            async: {
                enable: true,
                type: "POST",
                url:_platform+"/menu/list/tree",
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
                drag:{
                    isCopy:false,
                    isMove:false
                },
                enable: true
            },
            callback:{
                beforeEditName:beforeEdt,
                beforeRemove:beforeRemove,
                onClick:clickNode
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

    /**
     * 获取焦点时显示编辑图标
     * @param treeId
     * @param treeNode
     */
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

    /**
     * 点击删除按钮时，出发触发回调函数
     *
     * @param treeId
     * @param treeNode
     */
    function beforeRemove(treeId, treeNode) {
        if(treeNode.isParent){
            layer.confirm('该节点是父节点,是否删除该节点以及其子节点？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                return  onRemove(treeId,treeNode);
            });
        }else{
            layer.confirm('是否删除该节点？', {
                btn: ['确定','否'] //按钮
            }, function(){
                return  onRemove(treeId,treeNode);
            });
        }
        return false;
    };

    /**
     * 失去焦点时，移除编辑按钮
     * @param treeId
     * @param treeNode
     */
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };

    /**
     * 点击编辑按钮时，触发回调函数
     *
     * @param treeId
     * @param treeNode
     */
    function beforeEdt(treeId,treeNode){
        $.get(_platform + '/menu/edit',{id:treeNode.id}, function (result) {
            $('#menu-layer-div').html(result);
        });
        layer.open({
            area: ['600px', '480px'],
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
                refreshParentNode(treeId,treeNode);
            }
        });

    }


    function refreshParentNode(treeId,treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            type = "refresh";
            silent = false;
        /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/
        var parentNode = treeObj.getNodeByTId(treeId);
        /*选中指定节点*/
        treeObj.selectNode(parentNode);
        treeObj.reAsyncChildNodes(parentNode, type, silent);
//        var zTree = $.fn.zTree.getZTreeObj("menuTree");
//        zTree.reAsyncChildNodes(null,"refresh",false);
    }


    /**
     * 删除方法自定义
     * @param treeId
     * @param treeNode
     */
    function onRemove(treeId,treeNode){
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
                zTree.removeNode(treeNode);
            },
            error: function(data) {
                layer.msg(data.msg);
            }
        });

        return true;
    }

    function addData(treeId,treeNode){
        $.get(_platform + '/menu/add',{pId:treeNode.id}, function (result) {
            $('#menu-layer-div').html(result);
        });
        layer.open({
            area: ['600px', '480px'],
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
            end:function(e){
                refreshParentNode(treeId,treeNode);
            }
        });
    };

    function clickNode(e,treeId,treeNode){
        $.ajax({
            type: "post",
            url: _platform+"/menu/detail",
            data: {id:treeNode.id},
            dataType: "json",
            success: function (data) {
                var menutype =data.menu.menuType;
                var type = data.menu.type;
                if(menutype == "0"){
                    $("#menuType").val("前台");
                }else
                    $("#menuType").val("后台");

                if(type == "0"){
                    $("#type").val("模块");
                }else
                    $("#type").val("菜单");
                $("#seq").val(data.menu.seq);
                $("#menuName").val(data.menu.menuName);
                $("#menuUrl").val(data.menu.menuUrl);
            },
            error: function(data) {

            }
        });
    }

});



