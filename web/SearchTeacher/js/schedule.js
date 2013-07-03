/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var schedule;
$(document).ready(function() {
    function Schedule(index, day){
        var min = 0;
        var status = { 
            notAvailable : 0,
            available : 1,
            notAccept : 2,
            accept : 3
        }
        var TYPE = {
            nothing : 0,
            create : 1,
            remove : 2
        }
        var result = {};
        result.array = new Array(day);
        for(var i = 0; i < day; i++){
            result.array[i] = new Array(index);
            for(var j = 0; j < index; j++)
                result.array[i][j] = TYPE.nothing;
        }
        $('.schedule_table').find('tr').each(function(i){
            $(this).find('td').each(function(j){
                $(this).css("background-color","#CCF");
                $(this).attr("index",i - 1).attr("day",j);
                $(this).attr("times", 0);
                $(this).attr("select", 0);
           
                $(this).mouseover(function() {
                    if($(this).attr("times") != 0 && $(this).attr("select") == 0){
                        $(this).css("background-color", "#CFC");
                        $(this).css("border", "1px solid #CCC");
                    }
                });
                
                $(this).mouseout(function() {
                    $(this).css("border", "0px solid #CCC");
                    if ($(this).attr("times") != 0 && $(this).attr("select") == 0) {
                        $(this).removeAttr("style");
                    }

                });
                
                $(this).click(function() {
                    if ($(this).attr("times") != 0 && $(this).attr("select") == 0) {
                        $(this).css("background-color", "#FC9");
                        $(this).attr("select",1);
                        result.array[$(this).attr("day")][$(this).attr("index")] = TYPE.create;
                        if(min < 0 && $(this).attr("times") > 0){
                            min = $(this).attr("times");
                        } else if(min == 0 || min > $(this).attr("times")){
                            min = $(this).attr("times");
                        }
                    } else if ($(this).attr("select") == 1){
                        result.array[$(this).attr("day")][$(this).attr("index")] = TYPE.nothing;
                        $(this).removeAttr("style");
                        $(this).attr("select",0);
                        if(min > 0 && min == $(this).attr("times")){
                            min = -1;
                            var has = 0;
                            for(var i = 0; i < day; i++){
                                for(var j = 0; j < index; j++){
                                    if(result.array[i][j] == TYPE.create){
                                        has = 1;
                                        if(min < 0 && $("[index='"+ j +"'][day='" + i + "']").attr("times") > 0){
                                            min = $("td[index='"+ j +"'][day='" + i + "']").attr("times");
                                        } else if(min > $("[index='"+ j +"'][day='" + i + "']").attr("times")){
                                            min = $("td[index='"+ j +"'][day='" + i + "']").attr("times");
                                        }
                                    }
                                }
                            }
                        }
                        if(has == 0)
                            min = 0;
                    }
                });  
            });        
        }); 

   
        $('#next').click(function(){
            console.log(JSON.stringify(result));
            if(min != 0){
                $("#schedule_json").val(JSON.stringify(result));
            } else {
                $("#schedule_error").text("请选择预约时间");
            }
        });
        
        $('#upload').click(function(){
            if($("#schedule_times").val() == "") {
                $("#schedule_error").text("请输入课程次数");
            } else if(min > 0 && $("#schedule_times").val() > min) {
                $("#schedule_error").text("课程次数最大为"+ min);
            } else if($('#schedule_lesson').val() == ""){
                $("#schedule_error").text("请选择课程种类");
            }else {
                $("#schedule_form").submit();
            }
        });
        
        this.drawSchedule = function(schedule, iSchedule){
            if(schedule == null){
                return;
            }
            for(var i = 0; i < schedule.clazzes.length; i++){
                var clazz = schedule.clazzes[i];
                if(clazz.status == status.available){
                    $("td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("times",clazz.remain).removeAttr("style");;
                }
            }
            if(iSchedule == null)
                return;
            for(var i = 0; i < iSchedule.clazzes.length; i++){
                var clazz = schedule.clazzes[i];
                $("td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("times",0).css("background-color","#CFF");
            }
        }
    }
    schedule = new Schedule(16,7);
});

