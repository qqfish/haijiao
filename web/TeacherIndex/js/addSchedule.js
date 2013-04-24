/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var addSchedule;
$(document).ready(function() {
    function AddSchedule(index, day){
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
        $('.schedule_table').find('tr').each(function(i){
            $(this).find('td').each(function(j){
                //$(this).css("background-color","#CCF");
                $(this).attr("index",i - 1).attr("day",j);
                $(this).attr("status", 0);
                $(this).attr("select", 0);
           
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
        
        $('#upload').click(function(){
            if(select == 0) {
                $("#chooseError").text("没有选择添加课程");
            } else {
                $("#schedule_json").val(JSON.stringify(result));
                $("#schedule_form").submit();
                console.log($("#schedule_json").val());
            }
        });
        
        this.drawSchedule = function(schedule){
            if(schedule == null){
                return;
            }
            for(var i = 0; i < schedule.clazzes.length; i++){
                var clazz = schedule.clazzes[i];
                $(".schedule_table td[index='" + clazz.index + "'][day='" + clazz.day +"']").attr("status",clazz.status).css("background-color","#CCF");
            }
        }
    }
    addSchedule = new AddSchedule(13,7);
});

