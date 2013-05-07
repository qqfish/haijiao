function validate_required(field, tip)
{
    with (field) {
        if (value==null||value=="") {
            $(tip).text("* 这是必填项哦！");
            $(tip).fadeIn();
            return false;
        }
        else {
            $(tip).fadeOut();
            return true;
        }
    }
}

function validate_email(field, tip){
    with (field)
    {
        apos=value.indexOf("@");
        dotpos=value.lastIndexOf(".");
        if (apos<1||dotpos-apos<2) {
            $(tip).text("* 您输入的邮箱地址有误哦！");
            $(tip).fadeIn();
            return false;
        }
        else {
            $.post("checkEmail.action", {email : value},
                function(data){
                    if(data == "true"){
                        $(tip).text("* 该邮箱已注册！");
                        $(tip).fadeIn();
                        return false;
                    }
                    else{
                        $(tip).fadeOut();
                        return true;
                    }
                });
        }
    }
}

function validate_passwordlength(field, tip)
{
    with (field) {
        var pwd = value;
        if (pwd.length < 6) {
            $(tip).text("* 密码长度必须超过六位哦！");
            $(tip).fadeIn();
            return false;
        }
        else {
            $(tip).fadeOut();
            return true;
        }
    }
}

function validate_passwordequal(field, field2, tip)
{
    if ($(field).val() != $(field2).val()) {
        $(tip).text("* 两次密码必须相同！");
        $(tip).fadeIn();
        return false;
    }
    else {
        $(tip).fadeOut();
        return true;
    }
}