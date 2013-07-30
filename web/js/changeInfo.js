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
    
    if( $('#nextPageMessage').val() == "密码错误"  
        || $('#nextPageMessage').val() == "两次输入的密码不一致"
        || $('#nextPageMessage').val() == "修改密码成功" ){
        $('#2').addClass("active in");
        $('#1').removeClass("active in");
        $('#l2').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "修改详细介绍成功！"  
        || $('#nextPageMessage').val() == "修改详细介绍失败！"){
        $('#4').addClass("active in");
        $('#1').removeClass("active in");
        $('#l4').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "修改详细信息成功！"  
        || $('#nextPageMessage').val() == "囧，修改详细信息失败啦！"
        || $('#nextPageMessage').val() == "您可以在下方输入框输入您的线下授课区域"){
        $('#5').addClass("active in");
        $('#1').removeClass("active in");
        $('#l5').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "上传完成"  
        || $('#nextPageMessage').val() == "没有找到对应文件"
        || $('#nextPageMessage').val() == "上传出错" ){
        $('#3').addClass("active in");
        $('#1').removeClass("active in");
        $('#l3').addClass("active");
        $('#l1').removeClass("active");
    }
});

function testSubmit(){
    var name = $("#TAName").val();
    var phoneNum = $('#phoneNum').val();
    if(name == null || name==""){
        $("#name_tip").text("* 这是必填项哦！");
        $("#name_tip").fadeIn(1,null);
        return;
    } else if(name.length > 8 ){
        return;
    } else if(phoneNum == null || phoneNum==""){
        $("#phone_tip").text("* 这是必填项哦！");
        $("#phone_tip").fadeIn(1,null);
        return;
    } else if(phoneNum.length != 11){
        return;
    } else {
        $("#form_submit").click();
    }
}