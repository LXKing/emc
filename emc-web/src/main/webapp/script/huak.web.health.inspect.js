var faceKey =_web + "/static/" + (localStorage.faceKey == "dark" ? "imgdark" : "img");

function loadDataFun() {
    initRuning();
    chart01Fun();

    $("#runbtn").click(function() {
        polling();
        loadRuning1();
    });

    $(".normalitem h1").click(function() {
        $(this).parent().parent().find("ul").toggle();
        if ($(this).parent().parent().find("ul").is(":hidden")) {
            $(this).addClass('open');
        } else {
            $(this).removeClass('open');

        }

    });

}
function loadRuning1(){
    $.ajax({
        url : _web+"/health/testing",
        type : "POST",
        cache : false,
        dataType: "json",
        success : function(data) {
        }
    });
}

function polling(){
    $.ajax({
        url : _web+"/health/polling",
        type : "GET",
        timeout:30000,
        cache : false,
        dataType:"JSON",
        success : function(data) {
            if(data != null && data != ""){
                console.info(data);
                console.info(data.msg);
                console.info(data.end);
                if(data.end==null||data.end==""||data.end=="undefined"){
                    polling();
                }
            }else{
                polling();
            }

        },
        complete:function(XMLHttpRequest,status){
            console.info("status"+status);
            if(status=='timeout') {//超时,status还有success,error等值的情况
                polling();
            }
        }
    });
}
/**
 * 初始化待检测项
 */
function initRuning(){
    var healthItem = eval($("#healthItem").val());

    $("#healthcount").html(healthItem.length);
    $.each(healthItem,function(idx,item){
        var  $li = '<li><div><img src="'+faceKey+'/health/'+item.img+'-nor.png"></div><p>'+item.title+'</p></li>';
        $("#healthlistul").append($li);
    });
}

function loadRuning() {

    $("#running").show();
    $("#runbtn").hide();

    var data =[{
        title:'',
        item:[{
            img: 'test-01-01',
            name: '万平米工单',
            error: 1
        },{
            img: 'test-01-01',
            name: '万平米工单',
            error: 3
        }]
    },{

    },{

    },{

    }];


    if (healthRuning) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += '<div class="panelwrap">';
            html += '	<div class="title"><h1>' + data[i].title + ' - 共<span>' + data[i].items.length + '</span>项</h1></div>';
            html += '	<ul>';
            for (var j = 0; j < data[i].items.length; j++) {
                var statestr = "";
                if (data[i].items[j].state == 1) {
                    statestr = '-abnor';
                } else if (data[i].items[j].state == 2) {
                    statestr = '-nor';
                }
                html += '		<li>';
                html += '<div class="state' + data[i].items[j].state + '"><img src="' + faceKey + '/health/' + data[i].items[j].img + statestr + '.png" /></div>';
                html += '			<p>' + data[i].items[j].name + (data[i].items[j].error == 0 ? "" : "<span>" + data[i].items[j].error + "</span>") + '</p>';
                html += '		</li>';
            }
            html += '	</ul>';
            html += '</div>';
        }
        $("#resulthealth").html(html);
    } else {
        var html = "<div class='resultlist'>";
        html += '<div class="erroritem">';
        html += '<div>';
        html += '<h1>发现<span id="errorcount">0</span>项异常</h1>';
        html += '</div>';
        html += '<div class="panelwrap">';
        html += '<ul id="errorlistul">';
        html += '</ul>';
        html += '</div>';
        html += '</div>';
        html += '<div class="normalitem">';
        html += '<div>';
        html += '<h1>以下<span id="normalcount">0</span>项无异常</h1>';
        html += '</div>';
        var normalcount = 0;
        var errorcount = 0;
        var count = 0;
        var normalHtml = "";
        var errorHtml = "";
        for (var i = 0; i < data.length; i++) {

            html += '<div class="panelwrap">';

            count = 0;
            normalHtml = ""

            for (var j = 0; j < data[i].items.length; j++) {
                var itemshtml = "";
                itemshtml += '		<li>';
                itemshtml += '<div><img src="' + faceKey + '/health/' + data[i].items[j].img + (data[i].items[j].state == 1 ? '-abnor' : '-nor') + '.png" /></div>';
                itemshtml += '			<p>' + data[i].items[j].name + (data[i].items[j].error == 0 ? "" : "<span>" + data[i].items[j].error + "</span>") + '</p>';
                itemshtml += '		</li>';

                if (data[i].items[j].state == 1) {
                    errorcount++;
                    errorHtml += itemshtml;
                } else {
                    count++;
                    normalHtml += itemshtml;
                }
            }
            html += '	<div class="title"><h1>' + data[i].title + ' - 共<span>' + count + '</span>项</h1></div>';
            html += "<ul>" + normalHtml + "</ul>";
            html += '</div>';
            normalcount += count;
        }


        html += '</div></div>';
        $("#resulthealth").html(html);
        $("#normalcount").html(normalcount);
        $("#errorcount").html(errorcount);
        $("#errorlistul").html(errorHtml);
    }


}

function chart01Fun() {

    //占比-赋值即可
    var level_ = 0.75;
    //$("#level_num").text((level_ * 100) + '%');
    var wavehealth = (function() {
        var ctx;
        var waveImage;
        var canvasWidth;
        var canvasHeight;
        var needAnimate = false;

        function init(callback) {
            var wave = document.getElementById('chart01');
            if ($(wave).find("canvas").length > 0) {
                return;
            }
            var canvas = document.createElement('canvas');
            if (!canvas.getContext) return;
            ctx = canvas.getContext('2d');
            canvasWidth = wave.offsetWidth;
            canvasHeight = wave.offsetHeight;
            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            wave.appendChild(canvas);
            waveImage = new Image();
            waveImage.onload = function() {
                waveImage.onload = null;
                callback();
            }
            waveImage.src = _web + '/static/img/wave2.png';
        }

        function animate() {
            var waveX = 0;
            var waveY = 0;
            var waveX_min = -220;
            var waveY_max = canvasHeight * level_;
            var requestAnimationFrame =
                window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame ||
                function(callback) {
                    window.setTimeout(callback, 1000 / 60);
                };

            function loop() {
                ctx.clearRect(0, 0, canvasWidth, canvasHeight);
                if (!needAnimate) return;
                if (waveY < waveY_max) waveY += 1.5;
                if (waveX < waveX_min) waveX = 0;
                else waveX -= 3;

                ctx.globalCompositeOperation = 'source-over';
                ctx.beginPath();
                ctx.arc(canvasWidth / 2, canvasHeight / 2, canvasHeight / 2, 0, Math.PI * 2, true);
                ctx.closePath();
                ctx.fill();

                ctx.globalCompositeOperation = 'source-in';
                ctx.drawImage(waveImage, waveX, canvasHeight - waveY);

                requestAnimationFrame(loop);
            }
            loop();
        }

        function start() {
            if (!ctx) return init(start);
            needAnimate = true;
            setTimeout(function() {
                if (needAnimate) animate();
            }, 500);
        }

        function stop() {
            needAnimate = false;
        }
        return {
            start: start,
            stop: stop
        };
    }());
    wavehealth.start();
}