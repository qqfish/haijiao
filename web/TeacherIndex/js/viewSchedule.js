/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var viewSchedule;
$(document).ready(function() {
    function ViewSchedule(index, day){
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
        var select = 0;
        var result = {};
        result.array = new Array(day);
        for(var i = 0; i < day; i++){
            result.array[i] = new Array(index);
            for(var j = 0; j < index; j++)
                result.array[i][j] = TYPE.nothing;
        }
        $('.static_schedule_table').find('tr').each(function(i){
            $(this).find('td').each(function(j){
                //$(this).css("background-color","#CCF");
                $(this).attr("index",i - 1).attr("day",j);
                $(this).attr("status", 0);
           
                $(this).mouseover(function() {
                    if($(this).attr("status") == 0 && $(this).attr("select") == 0){
                        $(this).css("background-color", "#CFC");
                        $(this).css("border", "1px solid #CCC");
                    }
                });
                
                $(this).mouseout(function() {
                    $(this).css("border", "0px solid #CCC");
                    if ($(this).attr("status") == 0 && $(this).attr("select") == 0) {
                        $(this).removeAttr("style");
                    }

                });
                
                $(this).click(function() {
                    if ($(this).attr("status") == 0 && $(this).attr("select") == 0) {
                        select++;
                        $(this).css("background-color", "#FC9");
                        $(this).attr("select",1);
                        result.array[$(this).attr("day")][$(this).attr("index")] = TYPE.create;
                    } else if ($(this).attr("select") == 1){
                        select--;
                        result.array[$(this).attr("day")][$(this).attr("index")] = TYPE.nothing;
                        $(this).removeAttr("style");
                        $(this).attr("select",0);
                    }
                });  
            });        
        }); 
        
        this.drawSchedule = function(schedule){
            if(schedule == null){
                return;
            }
            for(var i = 0; i < schedule.clazzes.length; i++){
                var clazz = schedule.clazzes[i];
                if(clazz.status == status.notAvailable){
                    $(".static_schedule_table td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("status",clazz.status).html("<p>暂停</p>");
                } else if(clazz.status == status.available){
                    $(".static_schedule_table td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("status",clazz.status).html("<p>空闲</p>");
                } else if(clazz.status == status.notAccept){
                    $(".static_schedule_table td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("status",clazz.status).html("<p>" + clazz.lesson + "(未确定)</p>")
                } else if(clazz.status == status.accept){
                    $(".static_schedule_table td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("status",clazz.status).html("<p>" + clazz.lesson + "</p>")

                }
            }
        }
    }
    viewSchedule = new ViewSchedule(16,7);
});

