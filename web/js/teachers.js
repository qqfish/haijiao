jQuery(document).ready(function($) {
    $('#choosemodal').modal({
        show: false,
        backdrop: "static"
    });

    function gotopage(pagenum) {
        $.post('searchTeacher.action', {currentPage: pagenum,
            searchContent: $('#search_searchContent').val(),
            lessonGet: $('#lesson').children('.active').text(),
            gradeGet: $('#grade').children('.active').text(),
            netGet: $('#net').children('.active').text()
        }, function(data) {
            console.log(data);
            $('#resultPanel').html(data);
        });
    }

    $('#normal_button').click(function() {

    });

    $('#score_button').click(function() {
        $.post('searchTeacher!score.action', {
            searchContent: $('#search_searchContent').val(),
            lessonGet: $('#lesson').children('.active').text(),
            gradeGet: $('#grade').children('.active').text(),
            netGet: $('#net').children('.active').text()
        }, function(data) {
            $('resultPanel').html(data);
        });
    });

    $('#price_button').click(function() {
        $.post('searchTeacher!price.action', {
            searchContent: $('#search_searchContent').val(),
            lessonGet: $('#lesson').children('.active').text(),
            gradeGet: $('#grade').children('.active').text(),
            netGet: $('#net').children('.active').text()
        }, function(data) {
            $('resultPanel').html(data);
        });
    });
    $('#hot_button').click(function() {
        $.post('searchTeacher!hot.action', {
            searchContent: $('#search_searchContent').val(),
            lessonGet: $('#lesson').children('.active').text(),
            gradeGet: $('#grade').children('.active').text(),
            netGet: $('#net').children('.active').text()
        }, function(data) {
            $('resultPanel').html(data);
        });
    });

    $('#lesson').children().click(function() {
        gotopage(1);
    })

    $('#grade').children().click(function() {
        gotopage(1);
    })

    $('#net').children().click(function() {
        gotopage(1);
    })

});