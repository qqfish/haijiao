/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var schedule;
$(document).ready(function() {
    function Schedule(index, day){
        var TYPE = {
            create : 0,
            remove : 1
        }
        var result = new Array(day);
        for(var i = 0; i < day; i++){
            result[i] = new Array(index);
        }
        $('.schedule_table').find('tr').each(function(i){
            $(this).find('td').each(function(j){
                $(this).css("background-color","#CCF");
                $(this).attr("index",i - 1).attr("day",j);
                $(this).attr("available", 0);
                $(this).attr("free", 0);
                $(this).attr("own", 0);
           
                $(this).mouseover(function() {
                    if($(this).attr("available") == 1 && $(this).attr("free") == 1){
                        $(this).css("background-color", "#CFC");
                        $(this).css("border", "1px solid #CCC");
                    }
                });
                
                $(this).mouseout(function() {
                    $(this).css("border", "0px solid #CCC");
                    if ($(this).attr("available") == 1 && $(this).attr("free") == 1) {
                        $(this).removeAttr("style");
                    }

                });
                
                $(this).click(function() {
                    if ($(this).attr("available") == 1 && $(this).attr("free") == 1) {
                        $(this).css("background-color", "#FC9");
                        $(this).attr("free",0);
                        $(this).attr("own", 1);
                        result[$(this).attr("index")][$(this).attr("day")] = TYPE.create;
                    } else if ($(this).attr("own") == 1){
                        if(result[$(this).attr("day")][$(this).attr("index")] == TYPE.create){
                            result[$(this).attr("day")][$(this).attr("index")] = null;
                        } else {
                            result[$(this).attr("day")][$(this).attr("index")] = TYPE.remove;
                        }
                        $(this).removeAttr("style");
                        $(this).attr("own",0).attr("free",1);
                    }
                });  
            });        
        }); 
        this.drawSchedule = function(schedule, email){
            if(schedule == null){
                return;
            }
            for(var i = 0; i < schedule.clazzes.length; i++){
                var clazz = schedule.clazzes[i];
                for(var j = clazz.beginIndex; j <= clazz.endIndex; j++){
                    console.log(clazz.studentEmail == null);
                    if(clazz.studentEmail == null){
                        console.log("hello");
                        $("td[index='" + j + "'][day='" + i +"']").attr("available",1).attr("free",1).removeAttr("style");;
                    } else if(clazz.studentEmail == email){
                        $("td[index='" + j + "'][day='" + i +"']").attr("own",1).attr("available",1).attr("free",0).css("background-color", "#FC9");
                    } else {
                        $("td[index='" + j + "'][day='" + i +"']").attr("own",0).attr("available",1).attr("free",0).css("background-color", "#FFF");
                    } 
                }
            }
        }
    }
    schedule = new Schedule(13,7);
});