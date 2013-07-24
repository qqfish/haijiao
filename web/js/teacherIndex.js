function getFileList(isDir, groupName) {
    $.post("getFilelist.action", {
        isDir: isDir,
        groupName : groupName
    }, function(data) {
        $('#filelist').html(data);
    });
}

jQuery(document).ready(function($) {

    $(".lessonName").click(function() {
        $("#deal_lesson").val($(this).html());
        $("#addLessonModal").modal();
    });

    $("[id*='delete_click']").click(function() {
        var id = this.id;
        id = id.substring(13, id.length);
        $('#delete_' + id).click();
    });

    $("[id*='dealApply_stop_button_']").click(function() {
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_" + id).submit();
    });

    $("[id*='dealApply_cancel_button_']").click(function() {
        var id = this.id;
        id = id.substring(24, id.length);
        $("#dealApply_cancel_" + id).submit();
    });

    $("[id*='dealApply_accept_button_']").click(function() {
        var id = this.id;
        id = id.substring(24, id.length);
        $("#dealApply_accept_" + id).submit();
    });

    $("[id*='dealApply_decline_button_']").click(function() {
        var id = this.id;
        id = id.substring(25, id.length);
        $("#dealApply_decline_" + id).submit();
    });

    $("#closetip").click(function() {
        $("#tippanel").hide();
    });

    $("#showtip").click(function() {
        $("#tippanel").show();
    });
    
});