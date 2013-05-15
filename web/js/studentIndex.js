jQuery(document).ready(function($){
    if( $('#nextPageMessage').val() == "评论成功！"  
        || $('#nextPageMessage').val() == "你已经评论过了哦！" ){
        $('#bill_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l3').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "回复成功！"  
        || $('#nextPageMessage').val() == "你已经回复过了哦！" ){
        $('#comment_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l4').addClass("active");
        $('#l1').removeClass("active");
    }
    
    $("[id*='dealApply_stop_button_']").click(function(){
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_"+id).submit();
    })
    
});