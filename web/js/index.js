jQuery(document).ready(function($){
        $('.tabs a:last').tab('show');
    
        $('#choosemodal').modal({
            show: false,
            backdrop: "static"
        });
        
        $('#teacherbutton').click(function(){
            $('#teachersubmit').click();
        });
        
        $('#studentbutton').click(function(){
            $('#studentsubmit').click();
        });
});