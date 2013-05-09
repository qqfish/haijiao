jQuery(document).ready(function($){
    
    $('#add_click').click(function(){
        $('#changeInfo__addLesson').click();
    })
    
    $("[id*='delete_click']").click(function(){
        var id = this.id;
        id = id.substring(13, id.length);
        $('#delete_'+id).click();
    })
    
});