loadDataFun();

function loadDataFun() {

    chart01Fun();

    var healthRuning = false;
    loadRuning(healthRuning);

    $("#runbtn").click(function() {
        healthRuning = !healthRuning;
        loadRuning(healthRuning);
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

function loadRuning(healthRuning) {

    if (healthRuning) {
        $("#running").show();
        $("#runbtn").hide();
    } else {
        $("#running").hide();
        $("#runbtn").show();
    }

    var data = [{
        title: '作业管理',
        items: [{
            img: 'test-01-01',
            name: '万平米工单',
            state: 1,
            error: 1,
        },
            {
                img: 'test-01-02',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-03',
                name: '万平米工单',
                state: 2,
                error: 1,
            },
            {
                img: 'test-01-04',
                name: '万平米工单',
                state: 0,
                error: 0,

            }
        ]
    }, {
        title: '作业管理2',
        items: [{
            img: 'test-01-01',
            name: '万平米工单',
            state: 1,
            error: 1,
        },
            {
                img: 'test-01-02',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-03',
                name: '万平米工单',
                state: 2,
                error: 1,
            },
            {
                img: 'test-01-04',
                name: '万平米工单',
                state: 0,
                error: 0,

            }, {
                img: 'test-01-01',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-02',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-03',
                name: '万平米工单',
                state: 2,
                error: 1,
            },
            {
                img: 'test-01-04',
                name: '万平米工单',
                state: 0,
                error: 0,

            }, {
                img: 'test-01-01',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-02',
                name: '万平米工单',
                state: 1,
                error: 1,
            },
            {
                img: 'test-01-03',
                name: '万平米工单',
                state: 2,
                error: 1,
            },
            {
                img: 'test-01-04',
                name: '万平米工单',
                state: 0,
                error: 0,

            }
        ]
    }];

    var faceKey = localStorage.faceKey == "dark" ? "imgdark" : "img";
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
            waveImage.src = 'img/wave2.png';
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