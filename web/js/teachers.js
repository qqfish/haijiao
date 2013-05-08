jQuery(document).ready(function($){
        $('#choosemodal').modal({
            show: false,
            backdrop: "static"
        });
        
        $('#score_button').click(function(){
            $('#searchTeacher_desc').val("1");
            $('#score_submit').click();
        });
        
        $('#price_button').click(function(){
            $('#searchTeacher_desc').val("1");
            $('#price_submit').click();
        });
        
        $('#hot_button').click(function(){
            $('#searchTeacher_desc').val("1");
            $('#hot_submit').click();
        });
});