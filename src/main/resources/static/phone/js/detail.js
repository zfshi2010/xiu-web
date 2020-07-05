var xin = true, // true向右偏移,false向左偏移，首次为true，默认向右偏移
    yin = true; // true向下偏移,false向上偏移，首次为true，默认向下偏移
var step = 1; // 每次移动偏移量
var delay = 10; // 定时函数定时时间
var $obj;
$(function () {
    $obj = $("#imgDiv");
    var time = window.setInterval("move()", delay);
    $obj.mouseover(function () {
        clearInterval(time)
    });
    $obj.mouseout(function () {
        time = window.setInterval("move()", delay)
    });
});

function move() {
    var left = $obj.offset().left;
    var top = $obj.offset().top;
    var L = T = 0; //左边界和顶部边界
    var R = $(window).width() - $obj.width(); // 右边界
    var B = $(window).height() - $obj.height(); // 下边界

    //难点:怎样判断广告的4个边框有没有超出可视化范围!
    if (left < L) {
        xin = true; // 水平向右移动
    }
    if (left > R) {
        xin = false; // 水平向左移动
    }
    if (top < T) {
        yin = true; // 垂直向下移动
    }
    if (top > B) {
        yin = false; // 垂直向上移动
    }
    //根据有没有超出范围来确定广告的移动方向
    left += step * (xin == true ? 1 : -1);
    top += step * (yin == true ? 1 : -1);
    // 给div 元素重新定位
    $obj.offset({
        top: top,
        left: left
    })
}
//关闭
$(function () {
    $("#a").click(function () {
        var b = $("#a").parent();
        $(b).remove();
    })
})