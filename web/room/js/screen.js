/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function lock(){
    $('#showAlarm').click();
}

function unlock(){
    $('#closeAlarm').click();
}

function pError(str){
    alert(str);
}

$(document).ready(function(){
    $('#alarmMessage').modal({
        show: false,
        backdrop: "static"
    });
});