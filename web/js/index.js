jQuery(document).ready(function($){
        $('.tabs a:last').tab('show');
    
        $('#choosemodal').modal({
            show: false,
            backdrop: "static"
        });
        
        if( $('#getErrorMessage').text()!= null || $('#getErrorMessage').text()!= ""){
            if ($('#getErrorMessage').text()=="userExist") {
                $('#email_tip').text('* 该邮箱已注册！');
                $('#email_tip').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="emailNull") {
                $('#email_tip').text('* 邮箱不能为空！');
                $('#email_tip').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="passwordNull") {
                $('#password_tip1').text('* 密码不能为空！');
                $('#password_tip1').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="passwordShort") {
                $('#password_tip1').text('* 密码长度必须超过六位哦！');
                $('#password_tip1').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="passwordNotEqual") {
                $('#password_tip1').text('* 两次密码必须相同！');
                $('#password_tip1').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="loginFail") {
                $('#login_tip').text('* 用户名或密码错误！');
                $('#login_tip').fadeIn();
                $('#reg-panel').removeClass("in");
                $('#log-panel').addClass("in");
            }
            else if ($('#getErrorMessage').text()=="LemailNull") {
                $('#login_tip').text('* 邮箱不能为空！');
                $('#login_tip').fadeIn();
                $('#reg-panel').removeClass("in");
                $('#log-panel').addClass("in");
            }
            else if ($('#getErrorMessage').text()=="LpasswordNull") {
                $('#login_tip').text('* 密码不能为空！');
                $('#login_tip').fadeIn();
                $('#reg-panel').removeClass("in");
                $('#log-panel').addClass("in");
            }
        }
        
        $('#teacherbutton').click(function(){
            $('#teachersubmit').click();
        });
        
        $('#studentbutton').click(function(){
            $('#studentsubmit').click();
        });
        
        $('#stopButton').click(function(){
           $('#stopSubmit').click(); 
        });
        
        $('#acceptButton').click(function(){
           $('#acceptSubmit').click(); 
        });
        
        $('#declineButton').click(function(){
           $('#declineSubmit').click(); 
        });
});