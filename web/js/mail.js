$(function() {
    if ($("#newMsg").children("div").length == 0)
        $("#markAll").addClass("disabled");
    if($("#inputEmail").val() == "")
        $("#send").addClass("disabled");
    if ($("#allMsg").children("div").length == 0)
        $("#removeAll").addClass("disabled");
});

function validate_email(field, tip) {
    with (field)
    {
        apos = value.indexOf("@");
        dotpos = value.lastIndexOf(".");
        if (apos < 1 || dotpos - apos < 2) {
            $(tip).text("* 您输入的邮箱地址有误哦！");
            $("#send").addClass("disabled");
            $(tip).fadeIn();
            return false;
        }
        else {
            $.post("checkEmail.action", {email: value},
            function(data) {
                if (data == "true") {
                    $("#send").removeClass("disabled");
                    $(tip).fadeOut();
                    return true;
                }
                else {
                    $(tip).text("* 不存在该邮箱的用户！");
                    $("#send").addClass("disabled");
                    $(tip).fadeIn();
                    return false;
                }
            });
        }
    }
}

function mark(id) {
    $.ajax({
        url: "markMail.action",
        type: "POST",
        context: event.srcElement,
        data: {id: id},
        success: function(data) {
            if ($(this).parent().parent().children("div").length == 1) {
                $(this).parent().parent().append("暂无未读消息哦~！");
                $(this).parent().remove();
            }
            else
                $(this).parent().remove();
        }});
}

