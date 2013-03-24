jQuery(document).ready(function($){

	$('#identify').change(function(){
			var id = document.getElementByName("identify");
			if(id.value == 2){
				$('#regpanel').animate({height:1000}, 1000);
			}
			if(id.value == 1){
				$('#regpanel').animate({height:800}, 1000);
			}
	});
	
});