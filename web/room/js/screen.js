/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function lock(){
    $("#closeAlert").hide();
    $('#showAlert').click();
}

function unlock(){
    $('#closeAlert').click();
}

$(document).ready(function(){
    $('#alertMessage').modal({
        show: false,
        backdrop: "static"
    });
});

function lockType(t){
    switch(t){
        case "info":
            $("#alertMessage").attr("class","hide alert alert-block alert-info modal");
            break;
        case "error":
            $("#alertMessage").attr("class","hide alert alert-block alert-error modal");
            break;    
        case "success":
            $("#alertMessage").attr("class","hide alert alert-block alert-success modal");
            break; 
        default:
            $("#alertMessage").attr("class","hide alert alert-block modal");
            break;  
    }
}

function pError(str){
    unlock();
    $('#alertContext').empty();
    $('#alertContext').html("<h3>"+str + '</h3>');
    lockType("error");
    lock();
    $("#closeAlert").show();
}

function disconnected(){
    unlock();
    $('#alertContext').empty();
    $('#alertContext').html("<h3>连接已经断开</h3>" +
        "<button class='btn btn-success pull-right' onclick='window.location.reload();'>重新连接</button>"+
        "<a class='btn pull-right' href='index.action'>退出房间</a>");
    lockType("error");
    lock();
}

function pInfo(str) {
    $('#infoMessageContext').text(str);
    $('#infoMessage').fadeIn();
    setTimeout("$('#infoMessage').fadeOut()",3000); 
}

function processError(errorType){
    switch(errorType){
        case ErrorType.AddFileFromUser:
            pError("没有找到相应的文件");
            break;
        case ErrorType.TimerNoPermission:
            pError("没用控制计时器的权限");
            break;
        case ErrorType.NoStudentEnter:
            pError("学生还未进入房间");
            break;
    }
}

function processInfo(infoType, message){
    switch(infoType){
        case InfoType.ClazzFinish:
            pInfo("课程已经完成");
            break;
        case InfoType.SomeoneEnter:
            pInfo(message + " 进入了房间");
            break;
        case InfoType.SomeoneExit:
            pInfo(message + " 退出了房间");
            break;
    }
}

function lockTool(){
    $("#pointer").click();
    $("#pen").attr("class","btn tooltipButton disabled").attr("disabled","disabled");
    $("#eraser").attr("class","btn tooltipButton disabled").attr("disabled","disabled");
}

function unlockTool(){
    $("#pen").attr("class","btn tooltipButton").removeAttr("disabled");
    $("#eraser").attr("class","btn tooltipButton").removeAttr("disabled");
}