jQuery(document).ready(function($){
    $('#datepicker').datepicker();
    
    var sex = $('input:radio[name="sex"]:checked').val();
    if (sex=="男") {
        $('#maleButton').click();
    } else if (sex=="女") {
        $('#femaleButton').click();
    }
    
    $('#maleButton').click(function(){
        $('#changeInfo_sex男').click();
    });
    $('#femaleButton').click(function(){
        $('#changeInfo_sex女').click();
    });
    
    var telType = $('input:radio[name="telType"]:checked').val();
    if (telType=="student") {
        $('#meButton').click();
    } else if (telType=="parent") {
        $('#parentButton').click();
    }
    
    $('#meButton').click(function(){
        $('#changeInfo_telTypestudent').click();
    });
    $('#parentButton').click(function(){
        $('#changeInfo_telTypeparent').click();
    });
});