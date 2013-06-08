jQuery(document).ready(function($) {
    if ($('#nextPageMessage').val() == "成功添加空闲时间"
            || $('#nextPageMessage').val() == "添加空闲时间失败") {
        $('#schedule_area').addClass("active in");
        $('#lesson_area').removeClass("active in");
        $('#l2').addClass("active");
        $('#l3').removeClass("active");
    }

    if ($('#nextPageMessage').val() == "成功暂停一周"
            || $('#nextPageMessage').val() == "成功接受该课程"
            || $('#nextPageMessage').val() == "成功拒绝接受该课程"
            || $('#nextPageMessage').val() == "您可以在下面的页面中接受或拒绝学生的课程预约") {
        $('#student_area').addClass("active in");
        $('#lesson_area').removeClass("active in");
        $('#l1').addClass("active");
        $('#l3').removeClass("active");
    }

    if ($('#nextPageMessage').val() == "评论成功！"
            || $('#nextPageMessage').val() == "你已经评论过了哦！") {
        $('#bill_area').addClass("active in");
        $('#lesson_area').removeClass("active in");
        $('#l4').addClass("active");
        $('#l3').removeClass("active");
    }

    if ($('#nextPageMessage').val() == "回复成功！"
            || $('#nextPageMessage').val() == "你已经回复过了哦！") {
        $('#comment_area').addClass("active in");
        $('#lesson_area').removeClass("active in");
        $('#l5').addClass("active");
        $('#l3').removeClass("active");
    }

    $(".lessonName").click(function() {
        $("#deal_lesson").val($(this).html());
        $("#add").click();
    })

    $("[id*='delete_click']").click(function() {
        var id = this.id;
        id = id.substring(13, id.length);
        $('#delete_' + id).click();
    })

    $("[id*='dealApply_stop_button_']").click(function() {
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_" + id).submit();
    })

    $("[id*='dealApply_cancel_button_']").click(function() {
        var id = this.id;
        id = id.substring(24, id.length);
        $("#dealApply_cancel_" + id).submit();
    })

    $("[id*='dealApply_accept_button_']").click(function() {
        var id = this.id;
        id = id.substring(24, id.length);
        $("#dealApply_accept_" + id).submit();
    })

    $("[id*='dealApply_decline_button_']").click(function() {
        var id = this.id;
        id = id.substring(25, id.length);
        $("#dealApply_decline_" + id).submit();
    })

    $("#userfile").hide();
});