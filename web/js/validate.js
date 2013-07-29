function validate_required(field, tip)
{
    with (field) {
        if (value==null||value=="") {
            $(tip).text("* 这是必填项哦！");
            $(tip).fadeIn(1,null);
            return false;
        }
        else {
            $(tip).fadeOut(1,null);
            return true;
        }
    }
}

function validate_lengthLimit(field, tip, length)
{
    with (field) {
        if (value.length > length) {
            $(tip).text("* 长度不能大于"+length+"个汉字！");
            $(tip).fadeIn(1,null);
            return false;
        }
        else {
            $(tip).fadeOut(1,null);
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
            $(tip).fadeIn(1,null);
            return false;
        }
        else {
            $.post("checkEmail.action", {email : value},
                function(data){
                    if(data == "true"){
                        $(tip).text("* 该邮箱已注册！");
                        $(tip).fadeIn(1,null);
                        return false;
                    }
                    else{
                        $(tip).fadeOut(1,null);
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
            $(tip).fadeIn(1,null);
            return false;
        }
        else {
            $(tip).fadeOut(1,null);
            return true;
        }
    }
}

function validate_passwordequal(field, field2, tip)
{
    if ($(field).val() != $(field2).val()) {
        $(tip).text("* 两次密码必须相同！");
        $(tip).fadeIn(1,null);
        return false;
    }
    else {
        $(tip).fadeOut(1,null);
        return true;
    }
}

function validate_phoneNum(field, tip)
{
    with (field) {
        var phone = value;
        if (phone.length != 11) {
            $(tip).text("* 手机长度不正确！");
            $(tip).fadeIn(1,null);
            return false;
        }
        else {
            $(tip).fadeOut(1,null);
            return true;
        }
    }
}