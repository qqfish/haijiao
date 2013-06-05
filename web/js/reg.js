jQuery(document).ready(function($){
    $('#datepicker').datepicker();
    $('#maleButton').click(function(){
        $('#registerThen_sex男').click();
    });
    $('#femaleButton').click(function(){
        $('#registerThen_sex女').click();
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

function testStudentSubmit(){
    var name = $("#SAName").val();
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