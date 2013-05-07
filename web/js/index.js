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
        
        $('#stopButton').click(function(){
           $('#stopSubmit').click(); 
        });
        
        $('#acceptButton').click(function(){
           $('#acceptSubmit').click(); 
        });
        
        $('#declineButton').click(function(){
           $('#declineSubmit').click(); 
        });
});