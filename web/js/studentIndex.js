jQuery(document).ready(function($){
    if( $('#nextPageMessage').val() == "���۳ɹ���"  
        || $('#nextPageMessage').val() == "���Ѿ����۹���Ŷ��" ){
        $('#bill_area').addClass("active in");
        $('#student_area').removeClass("active in");
        $('#l3').addClass("active");
        $('#l1').removeClass("active");
    }
    
    if( $('#nextPageMessage').val() == "�ظ��ɹ���"  
        || $('#nextPageMessage').val() == "���Ѿ��ظ�����Ŷ��" ){
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