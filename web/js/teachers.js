jQuery(document).ready(function($) {
    $('#choosemodal').modal({
        show: false,
        backdrop: "static"
    });

    function gotopage(pagenum, action) {
        var url;
        if (action != null)
            url = 'searchTeacher!' + action;
        else
            url = 'searchTeacher.action';
        var status;
        if ($("#online_buttton").hasClass("active"))
            status = "1";
        else
            status = "";
        var lesson = $('#lesson').children('.active').text();
        var grade = $('#grade').children('.active').text();
        var net = $('#net').children('.active').text();
        var sex = $('#sex').children('.active').text();
        var city = $('#area').find('option:selected').text();
        if (lesson == "不限")
            lesson = "";
        if (grade == "不限")
            grade = "";
        if (net == "不限")
            net = "";
        if (sex == "不限")
            sex = "";
        if (city == "请选择家教地区")
            city = "";
        $.post(url, {currentPage: pagenum,
            searchContent: $('#search_searchContent').val(),
            lessonGet: lesson,
            gradeGet: grade,
            netGet: net,
            sex: sex,
            status: status,
            province: city
        }, function(data) {
            $('#resultdetail').html(data);
        });
    }

    $('#normal_button').click(function() {

    });

    $('#score_button').click(function() {
        gotopage(null, "score.action");
    });

    $('#price_button').click(function() {
        gotopage(null, "price.action");
    });

    $('#hot_button').click(function() {
        gotopage(null, "hot.action");
    });

    $('#time_button').click(function() {
        gotopage(null, "time.action");
    });

    $('#lesson').children().click(function() {
        gotopage(1, null);
    })

    $('#grade').children().click(function() {
        gotopage(1, null);
    })

    $('#net').children().click(function() {
        gotopage(1, null);
    })

    $('#sex').children().click(function() {
        gotopage(1, null);
    })
    
    $('#area').change(function() {
        gotopage(1,null);
    })
    
});