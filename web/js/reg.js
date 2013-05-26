jQuery(document).ready(function($){
    $('#datepicker').datepicker();
    $('#maleButton').click(function(){
        $('#changeInfo_sex男').click();
    });
    $('#femaleButton').click(function(){
        $('#changeInfo_sex女').click();
    });
});

function testSubmit(){
    var name = $("#TAName").val();
    if(name == null || name==""){
        $("#name_tip").text("* 这是必填项哦！");
        $("#name_tip").fadeIn(1,null);
        return;
    } else if(name.length > 8 ){
        return;
    } else {
        $("#form_submit").click();
    }
}