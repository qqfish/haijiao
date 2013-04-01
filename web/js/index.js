jQuery(document).ready(function($){
	$('.login-reg').click(function(){
                var a = document.getElementById('regText');
                var b = document.getElementById('logText');
                a.style.opacity = "1";
                b.style.opacity = "0.5";
                a.style.borderBottom = "0px solid #CCC";
                b.style.borderBottom = "1px solid #CCC";
                a.style.backgroundColor = "white";
                b.style.backgroundColor = "#e8e7de";
		$('#log-panel').hide();
		$('#reg-panel').show();
	});
            
	$('.login-log').click(function(){
                var a = document.getElementById('regText');
                var b = document.getElementById('logText');
                a.style.opacity = "0.5";
                b.style.opacity = "1";
                a.style.borderBottom = "1px solid #CCC";
                b.style.borderBottom = "0px solid #CCC";
                a.style.backgroundColor = "#e8e7de";
                b.style.backgroundColor = "white";
		$('#reg-panel').hide();
		$('#log-panel').show();            
	});
       
        $('#choosemodal').modal({
            show: false,
            backdrop: "static"
        });
});