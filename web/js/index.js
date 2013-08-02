jQuery(document).ready(function($){    
        $('#choosemodal').modal({
            show: false,
            backdrop: "static"
        });
        
        if( $('#getErrorMessage').text()!= null || $('#getErrorMessage').text()!= ""){
            if ($('#getErrorMessage').text()=="userExist") {
                $('#email_tip').text('* 该邮箱已注册！');
                $('#email_tip').fadeIn();
                $('#log-panel').removeClass("active in");
                $('#reg-panel').addClass("active in");
            }
            else if ($('#getErrorMessage').text()=="emailNull") {
                $('#email_tip').text('* 邮箱不能为空！');
                $('#email_tip').fadeIn();
                $('#log-panel').removeClass("active in");
                $('#reg-panel').addClass("active in");
            }
            else if ($('#getErrorMessage').text()=="passwordNull") {
                $('#password_tip1').text('* 密码不能为空！');
                $('#password_tip1').fadeIn();
                $('#log-panel').removeClass("active in");
                $('#reg-panel').addClass("active in");
            }
            else if ($('#getErrorMessage').text()=="passwordShort") {
                $('#password_tip1').text('* 密码长度必须超过六位哦！');
                $('#password_tip1').fadeIn();
                $('#log-panel').removeClass("active in");
                $('#reg-panel').addClass("active in");
            }
            else if ($('#getErrorMessage').text()=="passwordNotEqual") {
                $('#password_tip1').text('* 两次密码必须相同！');
                $('#password_tip1').fadeIn();
                $('#log-panel').removeClass("active in");
                $('#reg-panel').addClass("active in");
            }
            else if ($('#getErrorMessage').text()=="loginFail") {
                console.log("aaaa");
                $('#login_tip').text('* 用户名或密码错误！');
                $('#login_tip').fadeIn();
                
            }
            else if ($('#getErrorMessage').text()=="LemailNull") {
                $('#login_tip').text('* 邮箱不能为空！');
                $('#login_tip').fadeIn();
            }
            else if ($('#getErrorMessage').text()=="LpasswordNull") {
                $('#login_tip').text('* 密码不能为空！');
                $('#login_tip').fadeIn();
            }
        }
        
        $('#bg_pic').attr("src",function(){return"images/bkg/bkg"+Math.floor(Math.random()*5+1)+".jpg"});
        $('#bg_pic').animate({opacity:"1"},1000);
        
});