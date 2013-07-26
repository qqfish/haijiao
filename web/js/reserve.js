/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var reserve = {};
reserve.priceList = new Array();
reserve.num = 1;
reserve.max = -1;
reserve.min = -1;

reserve.addPrice = function(lesson, price){
    reserve.priceList[lesson] = price;
    if(reserve.max < 0 || reserve.max < price)
        reserve.max = price;
    if(reserve.min < 0 || reserve.min > price)
        reserve.min = price;
    $("#perPrice").text("￥" + reserve.min + ".00 - ￥" + reserve.max + ".00");
}

reserve.setType = function(type){
    $("#reserveType").val(type);
    $("#reserveShowType").text(type);
    reserve.type = type;
    if(reserve.price >= 0){
        $("#reserveButton").removeClass("disabled");
        $("#reserveButton")[0].disabled = false;
    }
}

reserve.setLesson = function(lesson){
    $("#reserveLesson").val(lesson);
    $("#reserveShowLesson").text(lesson);
    $("#perPrice").text("￥" + reserve.priceList[lesson] + ".00");
    reserve.price = reserve.priceList[lesson];
    $("#reserveTotal").text("￥" + (reserve.num * reserve.price) + ".00");
    if(reserve.type){
        $("#reserveButton").removeClass("disabled");
        $("#reserveButton")[0].disabled = false;
    }
}

reserve.setNum = function(num){
    $("#reserveNum").val(num);
    $("#reserveShowNum").text(num);
    reserve.num = num;
    if(reserve.price)
        $("#reserveTotal").text("￥" + (num * reserve.price) + ".00");
}

function checkInput(obj, min, max){
    if(obj.val() < min)
        obj.val(min);
    else if(obj.val() > max){
        obj.val(max);
    }
}