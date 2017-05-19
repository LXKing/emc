/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/19<BR>
 *     树下拉框
 */
(function ($) {
    var treeBox = function (ele, options) {
        this.$element = ele;
        this.defaults = {
            setting: undefined,//树请求地址（必填）
            zNodes:undefined
        };
        this.options = $.extend({}, $.fn.treeBox.defaults, options);

    }

    /**
     * 内部方法
     * @type {{}}
     */
    treeBox.prototype = {
        //生成div
        createElement: function () {
            var position = this.$element.position();
            var left = position.left;
            var top = position.top + this.$element.outerHeight();

            var _ele = '<div id="tree-box" class="tree-box" style="position: absolute; left: ' + left + 'px; top: ' + top + 'px;">' +
                '<div><ul id="tree-box-ztree" class="ztree"></ul></div>' +
                '</div>';
            return _ele;
        }
    }

    $.fn.treeBox = function (options) {
        var box = new treeBox(this, options);

        this.on('click', function (event) {
            $(this).after(box.createElement());
            if(box.zNodes == undefined || opts.url == "" || opts.url == null){
                $.fn.zTree.init($("#tree-box-ztree"), box.setting);
            }else{
                $.fn.zTree.init($("#tree-box-ztree"), box.setting, box.zNodes);
            }
            $(document).bind("click", bodyDown);
            event.stopPropagation();//阻止冒泡
        });

        return box;
    }

    function bodyDown(event) {
        if (!(event.target.id == "tree-box" || $(event.target).parents("#tree-box").length > 0)) {
            $("#tree-box").remove();
            $(document).unbind("click", bodyDown);
        }
    }

})(jQuery, document);