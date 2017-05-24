<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clearfix row no-margin index_header">

    <!--面包屑导航-->
    <div class="bread-crumb pull-left">
        当前位置：<a href="javascript:;">[<var class="xmhpg"  style="color: #666;">首页 - 能源流概况</var>]</a>
    </div>

    <div class="mianTop pull-right">
        <div class="selectbg clearfix col-lg-12">

            <div class="select-box">
                <div class="clearfix h-selectbox">
                    <div class="x-selectfree fl">
                        <div class="x-sfbgbox">
                            <div class="x-sfleft1 x-sfw1">
                                <input type="text" value="集团总览" readonly="readonly">
                            </div>
                            <div class="x-sfright1"></div>
                        </div>
                        <div class="x-sfoption x-sfoption1">
                            <p value="1111">北京公司</p>
                            <p value="2222">上海公司</p>
                            <p value="3333">南京集团</p>
                        </div>
                        <input type="hidden" value="1111"/>
                    </div>
                </div>
            </div>
            <div class="select-box select-boxbtnAlarm clearfix">
                <a href="javascript:;" class="btnAlarm btnAlarm-on">集中供暖</a>
                <a href="javascript:;" class="btnAlarm ">区域供暖</a>
            </div>

            <div class="select-box select-boxbtnAlarm clearfix">
                <a href="javascript:;" class="btnAlarm ">本年度</a>
                <a href="javascript:;" class="btnAlarm btnAlarm-on">本采暖季</a>
                <a href="javascript:;" class="btnAlarm ">自定义</a>
            </div>
            <div class="select-box select-boxWdate">
                <input id="begin" class="Wdate time-input time-input-disable" disabled="disabled" value="2017-05-01" type="text" onFocus="var end=$dp.$('end');WdatePicker({onpicked:function(){end.focus();},readOnly:true,maxDate:'#F{$dp.$D(\'end\')}'})"/>
                <span>至</span>
                <input id="end" class="Wdate time-input time-input-disable" disabled="disabled" value="2017-05-08" type="text" onFocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'begin\')}'})"/>
            </div>

        </div>
    </div>
</div>