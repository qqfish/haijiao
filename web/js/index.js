jQuery(document).ready(function($){
	$('.login-reg').click(function(){
		$('#log-panel').hide();
		$('#reg-panel').show();
	});
            
	$('.login-log').click(function(){
		$('#reg-panel').hide();
		$('#log-panel').show();            
	});
});