/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/10/28<BR>
 *     行政区划插件
 *
 * 页面调用实例
 * new PCAS('province','${province}','110000000000','city','${city}','110200000000','county','${county}','110228000000','town','${town}','110228100000','village','110228100002');
 *
 */


/**
 * 插件入口，支持2-15个参数
 * 前3个参数是省级区划参数，如ele1代表select元素的主键, data1代表省级区划的缓存, id1代表省级区划初始化value等于id1的option
 * 4-6个参数是市级区划参数，同上
 * 7-9个参数是县级区划参数，同上
 * 10-12个参数是镇级区划参数，同上
 * 13-15个参数是村级区划参数，同上，但暂不支持缓存，所以只有参数ele5和id5
 * 其中id1-5都是可选参数
 *
 * @returns {boolean}
 * @constructor
 */
function PCAS() {
    if (arguments.length < 2) {
        layer.alert("调用参数不合法，初始化行政区划失败");
        return false;
    }
    var len = arguments.length;
    if (len > 1 && len <= 3) {
        PCAS1(arguments[0], arguments[1], arguments[2])
    } else if (len > 3 && len <= 6) {
        PCAS2(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5])
    } else if (len > 6 && len <= 9) {
        PCAS3(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8])
    } else if (len > 9 && len <= 12) {
        PCAS4(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11])
    } else if (len > 12) {
        PCAS5(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12], arguments[13], arguments[14])
    }
}
/**
 * 初始化省份,可带默认值
 * @param ele1 省份select的id
 * @param id1 缺失参数，省份主键
 * @param data1 省份缓存数据
 * @constructor
 * adm_code: "110000000000", adm_name: "北京市", p_code: "0", adm_level: "1", lng: "116.395645",p_code:"0"
 *
 */
function PCAS1(ele1, data1, id1) {
    var $element = $("#" + ele1);
    var json = eval(data1);
    $element.html('<option value="">请选择省份</option>');
    $.each(json, function (idx, item) {
        if (id1 == item.adm_code) {
            $element.append('<option selected value="' + item.adm_code + '">' + item.adm_name + '</option>');
        } else {
            $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
        }
    });
}

function PCAS2(ele1, data1, id1, ele2, data2, id2) {
    new PCAS1(ele1, data1, id1);

    var $element = $("#" + ele2);
    var $parent = $("#" + ele1);
    var json = eval(data2);
    if (id1 != null && "" != id1) {
        $element.html('<option value="">请选择市</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                if (id2 == item.adm_code) {
                    $element.append('<option selected value="' + item.adm_code + '">' + item.adm_name + '</option>');
                } else {
                    $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
                }
            }
        });
    }

    $parent.on('change', function () {
        $element.html('<option value="">请选择市</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
            }
        });
        $element.chosen("destroy").chosen();
        $element.change();
    });
}

function PCAS3(ele1, data1, id1, ele2, data2, id2, ele3, data3, id3) {
    new PCAS2(ele1, data1, id1, ele2, data2, id2);

    var $element = $("#" + ele3);
    var $parent = $("#" + ele2);
    var json = eval(data3);
    if (id2 != null && "" != id2) {
        $element.html('<option value="">请选择县</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                if (id3 == item.adm_code) {
                    $element.append('<option selected value="' + item.adm_code + '">' + item.adm_name + '</option>');
                } else {
                    $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
                }
            }
        });
    }

    $parent.on('change', function () {
        $element.html('<option value="">请选择县</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
            }
        });
        $element.chosen("destroy").chosen();
        $element.change();
    });
}

function PCAS4(ele1, data1, id1, ele2, data2, id2, ele3, data3, id3, ele4, data4, id4) {
    new PCAS3(ele1, data1, id1, ele2, data2, id2, ele3, data3, id3);

    var $element = $("#" + ele4);
    var $parent = $("#" + ele3);
    var json = eval(data4);
    if (id3 != null && "" != id3) {
        $element.html('<option value="">请选择镇(乡)</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                if (id4 == item.adm_code) {
                    $element.append('<option selected value="' + item.adm_code + '">' + item.adm_name + '</option>');
                } else {
                    $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
                }
            }
        });
    }

    $parent.on('change', function () {
        $element.html('<option value="">请选择镇(乡)</option>');
        $.each(json, function (idx, item) {
            if ($parent.val() == item.p_code) {
                $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
            }
        });
        $element.chosen("destroy").chosen();
        $element.change();
    });
}

/**
 * 第五级由于数据量大，暂时无法用缓存方式支持
 * @param ele1
 * @param data1
 * @param id1
 * @param ele2
 * @param data2
 * @param id2
 * @param ele3
 * @param data3
 * @param id3
 * @param ele4
 * @param data4
 * @param id4
 * @param ele5
 * @param id5
 * @constructor
 */
function PCAS5(ele1, data1, id1, ele2, data2, id2, ele3, data3, id3, ele4, data4, id4, ele5, id5) {
    new PCAS4(ele1, data1, id1, ele2, data2, id2, ele3, data3, id3, ele4, data4, id4);

    var $element = $("#" + ele5);
    var $parent = $("#" + ele4);
    if (id4 != null && "" != id4) {
        $element.html('<option value="">请选择村</option>');

        $.ajax({
            url: ctx + '/administrative/village',
            type: 'POST',
            data: {pCode: id4},
            dataType: 'json',
            success: function (result) {
                $.each(result.data, function (idx, item) {
                    if (id5 == item.adm_code) {
                        $element.append('<option selected value="' + item.adm_code + '">' + item.adm_name + '</option>');
                    } else {
                        $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
                    }
                });
                $element.chosen("destroy").chosen();
            }
        });


    }

    $parent.on('change', function () {
        $element.html('<option value="">请选择村</option>');

        if($parent.val()!=null&&""!=$parent.val()){
            $.ajax({
                url: ctx + '/administrative/village',
                type: 'POST',
                data: {pCode: $parent.val()},
                dataType: 'json',
                success: function (result) {
                    $.each(result.data, function (idx, item) {
                        $element.append('<option value="' + item.adm_code + '">' + item.adm_name + '</option>');
                    });
                    $element.chosen("destroy").chosen();
                }
            });
        }else{
            $element.chosen("destroy").chosen();
        }
    });
}


