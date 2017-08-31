<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clearfix row no-margin index_header">

    <!--面包屑导航-->
    <div class="bread-crumb row no-margin">
       <%-- 当前位置：
        <a href="index.html">[<var class="xmhpg">首页 </var>]</a> &gt; [<var class="xmhpg" style="color: #666;">能源流概况</var>]--%>
    </div>
</div>
<script>
    $(function () {
        //console.info("重置面包屑");
        var mbhtml = "";
        var mbxdh =  ${navigations};
        var ymurl = document.location.href;
        var prefix = "";
        if(ymurl.indexOf('?')>0){
            prefix = ymurl.substr(0, ymurl.indexOf('?'));
        }else{
            prefix = ymurl;
        }

        $.each(mbxdh, function (idx, item) {
            /*console.info(item);
             console.info(document.location.href==_web + item.url)*/

            var hturl = _web + item.url;
            if(hturl.indexOf('*')>=0){
                hturl = hturl.replace('*','');
                if (prefix.indexOf(hturl)>=0 ) {
                    mbhtml = getMbHtml(item, mbhtml,prefix);
                }
            }else{
                if (prefix == hturl) {
                    mbhtml = getMbHtml(item, mbhtml,prefix);
                }
            }

        });
        $('.bread-crumb.row.no-margin').html("当前位置：" + mbhtml);
    });
    function getMbHtml(navigation, html,prefix) {
        if (navigation.navigation == "undefined" || navigation.navigation == null || navigation.navigation == "") {
            html += '<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            return html;
        } else {
            if(navigation.url.indexOf('*')>=1){
                html = getMbHtml(navigation.navigation, html,navigation.navigation.url) + '&gt;<a href="' + _web + prefix + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            }else{
                html = getMbHtml(navigation.navigation, html,navigation.navigation.url) + '&gt;<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
            }
            return html;
        }
    }
</script>