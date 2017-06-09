<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clearfix row no-margin index_header">
    <form id="searchTools">
        <input type="hidden" id="toolOrgId" name="toolOrgId" value="">
        <input type="hidden" id="toolFeedType" name="toolFeedType" value="2">
        <input type="hidden" id="toolStartDate" name="toolStartDate" value="">
        <input type="hidden" id="toolEndDate" name="toolEndDate" value="">
    </form>
    <!--面包屑导航-->
    <div class="bread-crumb pull-left">
       <%-- 当前位置：
        <a href="index.html">[<var class="xmhpg">首页 </var>]</a> &gt; [<var class="xmhpg" style="color: #666;">能源流概况</var>]--%>
    </div>

    <div class="mianTop pull-right">
        <div class="selectbg clearfix col-xs-12">

            <div class="select-box">
                <div class="clearfix h-selectbox">
                    <div class="x-selectfree fl">
                        <div class="x-sfbgbox">
                            <div class="x-sfleft1 x-sfw1">
                                <input type="text" value="" readonly="readonly">
                            </div>
                            <div class="x-sfright1"></div>
                        </div>
                        <div class="x-sfoption x-sfoption1">
                            <p value=" "> </p>
                            <%--<p value="2222">上海公司</p>
                            <p value="3333">南京集团</p>--%>
                        </div>
                        <input type="hidden" value="1111" />
                    </div>
                </div>
            </div>
            <div class="select-box select-boxbtnAlarm clearfix">
                <a href="javascript:;" class="btnAlarm btnAlarm-on">集中供暖</a>
                <a href="javascript:;" class="btnAlarm ">区域供暖</a>
            </div>

            <div class="select-box select-boxbtnAlarm clearfix" style="position:relative">
                <a href="javascript:;" class="btnAlarm ">本年度</a>
                <a href="javascript:;" class="btnAlarm btnAlarm-on">本采暖季</a>
                <a href="javascript:;" class="btnAlarm " id="datepicker-config">自定义</a>

                <div id="selectdate" class="daterangepicker dropdown-menu ltr show-calendar opensright" style="position:absolute;z-index: 9999;     top: 30px;
    left: -100px; width: 533px;  ">
                    <div class="datepictitle"><span style="font-size:14px;color:#aaa">您也可能想看</span><span class="datepictime" id="nearlyaweek">近一周</span><span class="datepictime" id="nearlyamonth">近一月</span><span class="datepictime" id="nearlytwomonth">近两月</span></div>
                    <div class="calendar left" style="width: 50%;padding:0px 10px">

                    </div>
                    <div class="calendar right">

                    </div>
                    <div style="clear:both"></div>
                    <div class="ranges">
                        <div class="range_inputs"><button class="applyBtn btn-sm btn-confirm" type="button">确定</button> <button class="cancelBtn btn-sm btn-cancel" type="button">取消</button></div>
                    </div>

                </div>
            </div>
            <div class="select-box select-boxWdate">
                <input id="begin" class="Wdate time-input time-input-disable" disabled="disabled" readonly="readonly" value="2017-05-01" type="text" />
                <span>至</span>
                <input id="end" class="Wdate time-input time-input-disable" disabled="disabled" readonly="readonly" value="2017-05-08" type="text" />
            </div>

        </div>
    </div>
</div>
<script>
    $(function(){

        //console.info("重置面包屑");
        var mbhtml = "";
        var mbxdh =  ${navigations};
        $.each( mbxdh ,function(idx, item){
            /*console.info(item);
            console.info(document.location.href==_web + item.url)*/
            if(document.location.href==_web + item.url){
                mbhtml = getMbHtml(item,mbhtml);
            }
        });
        $('.bread-crumb.pull-left').html("当前位置："+mbhtml);


        //条件下拉框
        $.ajax({
            url : _web+"/tools/search/org",
            type : "POST",
            dataType: "json",
            success:function(data){
                var html = '';
                $.each(data,function(idx,item){
                    if(idx == 0){
                        if(getCookie("toolOrgId")==null || getCookie("toolOrgId")==""){
                            $('.x-sfleft1.x-sfw1').html('<input type="text" value="'+item.ORG_NAME+'" readonly="readonly">');
                            $('#toolOrgId').val(item.ID);
                        }else{
                            $('.x-sfleft1.x-sfw1').html('<input type="text" value="'+getCookie("toolOrgName")+'" readonly="readonly">');
                            $('#toolOrgId').val(getCookie("toolOrgId"));
                        }

                    }
                    html += '<p value="'+item.ID+'">'+item.ORG_NAME+'</p>'
                });
                $(".x-sfoption.x-sfoption1").html(html);

            }
        });
        //默认类型
        if(getCookie("toolFeedType")==null || getCookie("toolFeedType")==""){
            $('#toolFeedType').val(2);
        }else{
            var  toolFeedType = getCookie("toolFeedType");
            $('#toolFeedType').val(toolFeedType);
            if(toolFeedType == 1){
                $('.btnAlarm').each(function(){
                    if("集中供暖"==$(this).text()){
                        $(this).removeClass("btnAlarm-on");
                    }else if("区域供暖"==$(this).text()){
                        $(this).addClass("btnAlarm-on");
                    }
                });
            }
        }

        //默认时间段
        if(getCookie("dateType")==null || getCookie("dateType")==""){
            $.ajax({
                url : _web+"/tools/search/season",
                type : "POST",
                dataType: "json",
                success:function(data){
                    $('#toolStartDate').val(data.startDate);
                    $('#toolEndDate').val(data.endDate);
                    $('#begin').val(data.startDate);
                    $('#end').val(data.endDate);
                }
            });
        }else{
            var dataType = getCookie("dateType");
            $('.btnAlarm').each(function(){
                if("本年度"==$(this).text() && dataType == 1){
                    $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                }else if("本采暖季"==$(this).text() && dataType == 2){
                    $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                }else if("自定义"==$(this).text() && dataType == 3){
                    $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                }
            });
            $('#toolStartDate').val(getCookie("toolStartDate"));
            $('#toolEndDate').val(getCookie("toolEndDate"));
            $('#begin').val(getCookie("toolStartDate"));
            $('#end').val(getCookie("toolEndDate"));
        }


    })
    function getMbHtml(navigation,html){
        if(navigation.navigation=="undefined"||navigation.navigation==null||navigation.navigation==""){
            html +='<a href="'+_web+navigation.url+'">[<var class="xmhpg">'+navigation.title+'</var>]</a>';
            return html;
        }else{
            html = getMbHtml(navigation.navigation,html)+ '&gt;<a href="'+_web+navigation.url+'">[<var class="xmhpg">'+navigation.title+'</var>]</a>';
            return html;
        }
    }
</script>