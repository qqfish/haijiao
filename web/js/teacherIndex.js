jQuery(document).ready(function($){
    
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