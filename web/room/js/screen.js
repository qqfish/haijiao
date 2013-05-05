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