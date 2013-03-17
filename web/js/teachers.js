jQuery(document).ready(function($){
	$('#search-btn').click(function(){
			$('#seacrchBar').animate({height:230}, 1000);	
	});
        $('#close-btn').click(function(){
			$('#seacrchBar').animate({height:100}, 1000);	
	});
});