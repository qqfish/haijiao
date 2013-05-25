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
        if($("#online_button").hasClass("active"))
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
            province: city,
            status:status
        }, function(data) {
            $('#resultdetail').html(data);
        });
    }

    $('#normal_button').click(function() {
        gotopage(null, "normal.action")
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
    
    $('#online_button').click(function() {
        if($(this).hasClass("active")){
            $(this).removeClass("active");
            gotopage(1, null);
        }
        else{
            $(this).addClass("active");
            gotopage(1, null);
        }
            
    });

    $('#lesson').children().click(function() {
        $('#lesson').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    })

    $('#grade').children().click(function() {
        $('#grade').children().removeClass("active");
        $('#allLesson').click();
        $(this).addClass("active");
        if($(this).html()=="小学"){
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $(".pschool").fadeIn();
        } else if($(this).html()=="初中"){
            $(".pschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $(".mschool").fadeIn();
        } else if($(this).html()=="高中"){
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $(".hschool").fadeIn();
        } else if($(this).html()=="英语"){
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeIn();
        } else {
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
        }
        gotopage(1, null);
    })

    $('#net').children().click(function() {
        $('#net').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    })

    $('#sex').children().click(function() {
        $('#sex').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    })
    
    $('#area').change(function() {
        gotopage(1,null);
    })
    
});