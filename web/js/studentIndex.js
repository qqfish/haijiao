jQuery(document).ready(function($){
    
    $("[id*='dealApply_stop_button_']").click(function(){
        var id = this.id;
        id = id.substring(22, id.length);
        $("#dealApply_stop_"+id).submit();
    })
    
});