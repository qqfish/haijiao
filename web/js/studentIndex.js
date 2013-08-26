function getFileList(isDir, groupName) {
    $.post("getFilelist.action", {
        isDir: isDir,
        groupName: groupName
    }, function(data) {
        $('#filelist').html(data);
    });
}

function getPublicFilelist(currentPage, name) {
    $.post("getPublicFilelist.action", {
        currentPage: currentPage,
        name: name
    }, function(data) {
        $("#publicFileList").html(data);
    });
}

function getBillList(currentPage) {
    $.post("getBillList.action", {
        currentPage: currentPage,
        isIndex: true,
        status: -1
    }, function(data) {
        $("#billlist").html(data);
    });
}

function getCommentBillList(currentPage) {
    $.post("getCommentBill.action", {
        currentPage: currentPage,
        isIndex: true
    }, function(data) {
        $("#comment_area").html(data);
    });
}

jQuery(document).ready(function($) {

    $("[id*='dealApply_stop_button_']").click(function() {
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_" + id).submit();
    });

    getFileList(true, null);
    getPublicFilelist(1);
    getBillList(1);
    getCommentBillList(1);
    
    var lessons = [
    '小学语文','小学数学','小学英语','小学奥数','初一初二语文','初一初二数学','初一初二英语','初三语文','初三数学',
    '初三英语','初中物理','初中化学','初中历史','初中地理','高一高二语文','高一高二数学','高一高二英语','高三语文',
    '高三数学','高三英语','高中物理','高中化学','高中政治','高中历史','高中生物','高中地理','高考报考咨询','大学四级',
    '大学六级','托福','GRE','雅思','口语','考研英语','考研数学','考研政治','考研专业课'
    ];
    $("#lessonSelect").autocomplete(lessons,{
        minChars: 0,
        max: 5,
        autoFill: true,
        matchContains: true,
        scrollHeight: 220,
        multiple: true,
        formatItem: function(data, i, total) {
            return "<I>"+data[0]+"</I>";
        },
        formatMatch: function(data, i, total) {
            return data[0];
        },
        formatResult: function(data) {
            return data[0];
        }
    });
});