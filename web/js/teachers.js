jQuery(document).ready(function($) {
    $('#choosemodal').modal({
        show: false,
        backdrop: "static"
    });

    $('#normal_button').click(function() {
        $('#normal_submit').click();
    });

    $('#score_button').click(function() {
        $('#search2_desc').val("1");
        $('#score_submit').click();
    });

    $('#price_button').click(function() {
        $('#search2_desc').val("1");
        $('#price_submit').click();
    });

    $('#hot_button').click(function() {
        $('#search2_desc').val("1");
        $('#hot_submit').click();
    });

    var lesson = null;
    var grade = null;

    if ($("#search_lessonGet").val() != null) {
        lesson = $("#search_lessonGet").val();
        if (lesson == "语文") {
            $('#chinese').click();
        } else if (lesson == "数学") {
            $('#math').click();
        } else if (lesson == "英语") {
            $('#english').click();
        } else if (lesson == "物理") {
            $('#physics').click();
        } else if (lesson == "化学") {
            $('#chem').click();
        } else if (lesson == "生物") {
            $('#bio').click();
        } else if (lesson == "政治") {
            $('#poli').click();
        } else if (lesson == "地理") {
            $('#geo').click();
        } else if (lesson == "历史") {
            $('#history').click();
        }
    }

    if ($("#search_gradeGet").val() != null) {
        grade = $("#search_gradeGet").val();
        if (grade == "小学") {
            $('#prischool').click();
        } else if (grade == "六年级") {
            $('#grade6').click();
        } else if (grade == "初一") {
            $('#grade7').click();
        } else if (grade == "初二") {
            $('#grade8').click();
        } else if (grade == "初三") {
            $('#grade9').click();
        } else if (grade == "高一") {
            $('#grade10').click();
        } else if (grade == "高二") {
            $('#grade11').click();
        } else if (grade == "高三") {
            $('#grade12').click();
        }
    }

    $('.lesson').click(function() {
        lesson = $(this).text();
        $("#search_lessonGet").val(lesson);
        $("#search2_lessonGet").val(lesson);
    })

    $('.grade').click(function() {
        grade = $(this).text();
        $("#search_gradeGet").val(grade);
        $("#search2_gradeGet").val(grade);
    })

});

function gotopage(pagenum) {
    $.post('searchTeacher.action', {currentPage: pagenum}, function(data) {
        $('resultPanel').html(data);
    });
}