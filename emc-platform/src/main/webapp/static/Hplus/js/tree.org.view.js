/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/19<BR>
 *     树下拉框
 *     基于ztree写的
 *     使用时先定义ztree 的setting
 *     然后调用就行了，如
 *     var setting={};
 *     $('input').treeBox({setting:setting});
 */
    var Org = function(options) {
        return new Org.fn.init(options);
    }

    Org.fn = Org.prototype = {
        constructor: Org,
        init: function(options) {
            this.options = options;
            this.initTree = function() {
                this.view(this.options);
            }
        },
        view: function(options) {
            this.class ="."+options.class;
            var $this =$(top.document).find(this.class);
            if($this.length<1){
                $this = $(this.class)
            }
            if($this.length>0) {

                top.lightId = $this.attr("light");
                $this.html("<div id='temp_org_tree' light='"+ top.lightId+"' class='ztree'></div>");
                var setting = {
                    async: { enable: true, url: _platform + '/common/org/tree', autoParam: ["id"]},
                    view: {selectedMulti: false, fontCss: {color: "black"}},
                    check: { enable: false },
                    data: { simpleData: { enable: true, idKey: "id", pIdKey: "pId", system: "Name", rootPId: "" } },
                    edit: {enable: false },
                    callback: { onClick: treeNodeClick,onAsyncSuccess: zTreeOnAsyncSuccess}
                };
                var obj = $(top.document).find("#temp_org_tree");
                if(obj.length<1){
                    obj = $("#temp_org_tree");
                }
                top.comm_tree = $.fn.zTree.init(obj, setting);
                top.comm_ztree = $.fn.zTree.getZTreeObj("temp_org_tree");
            }
            return top.comm_tree;
        }
    }
    var zTreeOnAsyncSuccess = function(){
        var lightId = top.lightId;
        top.comm_ztree = $.fn.zTree.getZTreeObj("temp_org_tree");
        var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree");
        var nodes = [];
        if (lightId != "" && lightId != undefined) {
            nodes = lightId.split(",");
            for (var i = 0; i < nodes.length; i++) {
                var node = treeObj.getNodeByParam("id", nodes[i]);
                treeObj.selectNode(node, true);
            }
        }
    }
    Org.fn.init.prototype = Org.fn;














