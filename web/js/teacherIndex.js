jQuery(document).ready(function($){
     if( $('#nextPageMessage').val() == "成功添加空闲时间"  
        || $('#nextPageMessage').val() == "添加空闲时间失败" ){
        $('#schedule_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l2').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "成功添加课程"  
        || $('#nextPageMessage').val() == "成功删除课程" ){
        $('#lesson_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l3').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "评论成功！"  
        || $('#nextPageMessage').val() == "你已经评论过了哦！" ){
        $('#bill_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l4').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "回复成功！"  
        || $('#nextPageMessage').val() == "你已经回复过了哦！" ){
        $('#comment_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l5').addClass("active");
        $('#l1').removeClass("active");
    }
    
    $(".lessonName").click(function(){
        $("#deal_lesson").val($(this).html());
        $("#add").click();
    })
    
    $("[id*='delete_click']").click(function(){
        var id = this.id;
        id = id.substring(13, id.length);
        $('#delete_'+id).click();
    })
    
    $("[id*='dealApply_stop_button_']").click(function(){
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_"+id).submit();
    })
    
    $("[id*='dealApply_accept_button_']").click(function(){
        var id = this.id;
        id = id.substring(24, id.length);
        $("#dealApply_accept_"+id).submit();
    })
    
    $("[id*='dealApply_decline_button_']").click(function(){
        var id = this.id;
        id = id.substring(25, id.length);
        $("#dealApply_decline_"+id).submit();
    })
});