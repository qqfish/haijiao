/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($) {
    $('#choosemodal').modal({
        show: false,
        backdrop: "static"
    });

    $('#normal_button').click(function() {
        gotopage(null, "normal.action");
    });

    $('#price_button').click(function() {
        gotopage(null, "price.action");
    });

    $('#online_button').click(function() {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
            gotopage(1, null);
        }
        else {
            $(this).addClass("active");
            gotopage(1, null);
        }
    });
    
    $('#lesson').children().click(function() {
        $('#lesson').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    });

    $('#sprt').children().click(function() {
        $('#sprt').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    });

    $('#grade').children().click(function() {
        $('#grade').children().removeClass("active");
        $('#allLesson').click();
        $(this).addClass("active");
        if ($(this).html() == "小学") {
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $('#lesson').fadeIn(1, null);
            $(".pschool").fadeIn(1, null);
            $(".graduate").fadeOut(1, null);
        } else if ($(this).html() == "初中") {
            $(".pschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $('#lesson').fadeIn(1, null);
            $(".mschool").fadeIn(1, null);
            $(".graduate").fadeOut(1, null);
        } else if ($(this).html() == "高中") {
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".english").fadeOut(1, null);
            $('#lesson').fadeIn(1, null);
            $(".hschool").fadeIn(1, null);
            $(".graduate").fadeOut(1, null);
        } else if ($(this).html() == "外语") {
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $('#lesson').fadeIn(1, null);
            $(".english").fadeIn(1, null);
            $(".graduate").fadeOut(1, null);
        } else if ($(this).html() == "考研") {
            $(".pschool").fadeOut(1, null);
            $(".mschool").fadeOut(1, null);
            $(".hschool").fadeOut(1, null);
            $('#lesson').fadeIn(1, null);
            $(".english").fadeOut(1, null);
            $(".graduate").fadeIn(1, null);
        } else {
            $("#lesson").fadeOut(1, null);
        }
        gotopage(1, null);
    });

    $('#net').children().click(function() {
        $('#net').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    });

    $('#sex').children().click(function() {
        $('#sex').children().removeClass("active");
        $(this).addClass("active");
        gotopage(1, null);
    });

    $('#selDistrict').change(function() {
        gotopage(1, null);
    });

    $('#sort').children().click(function() {
        $('#sort').children().children('i').removeClass("icon-white");
        $(this).children('i').addClass("icon-white");
    });

});

function gotopage(pagenum, action) {
    var url;
    if (action != null)
        url = 'searchStudent!' + action;
    else
        url = 'searchStudent.action';
    var status;
    if ($("#online_button").hasClass("active"))
        status = "1";
    else
        status = "";
    var lesson = $('#lesson').children('.active').text();
    var way = $('#sprt').children('.active').text();
    var net = $('#net').children('.active').text();
    var sex = $('#sex').children('.active').text();
    var province = $('#selProvince').find('option:selected').val();
    var city = $('#selCity').find('option:selected').val();
    var district = $('#selDistrict').find('option:selected').val();
    if (lesson == "不限")
        lesson = "";
    if (way == "不限")
        way = "";
    if (net == "不限")
        net = "";
    if (sex == "不限")
        sex = "";
    if (province.substr(7, province.length) == "---省份---") {
        province = "";
        city = "";
        district = "";
    }
    if (city.substr(7, province.length) == "---城市---") {
        city = "";
        district = "";
    }
    if (district.substr(7, province.length) == "---县/区---")
        district = "";
    $.post(url, {currentPage: pagenum,
        searchContent: $('#search_searchContent').val(),
        lesson: lesson,
        way: way,
        net: net,
        sex: sex,
        province: province,
        city: city,
        district: district,
        status: status
    }, function(data) {
        $('#resultdetail').html(data);
    });
}
