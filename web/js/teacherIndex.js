jQuery(document).ready(function($){
     if( $('#nextPageMessage').val() == "�ɹ���ӿ���ʱ��"  
        || $('#nextPageMessage').val() == "��ӿ���ʱ��ʧ��" ){
        $('#schedule_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l2').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "�ɹ���ӿγ�"  
        || $('#nextPageMessage').val() == "�ɹ�ɾ���γ�" ){
        $('#lesson_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l3').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "���۳ɹ���"  
        || $('#nextPageMessage').val() == "���Ѿ����۹���Ŷ��" ){
        $('#bill_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l4').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "�ظ��ɹ���"  
        || $('#nextPageMessage').val() == "���Ѿ��ظ�����Ŷ��" ){
        $('#comment_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l5').addClass("active");
        $('#l1').removeClass("active");
    }
    
    $('#add_click').click(function(){
        $('#changeInfo__addLesson').click();
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