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
});