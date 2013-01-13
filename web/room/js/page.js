//class page


function page(id, num){
	var fileId = id;
	var fileWidth = 0;
	var fileHeight = 0;
	var page = num;

	var imageURL = null;
	var shapeArray = null;

	this.setInfo = function(width, height, image, shapes){
		fileWidth = width;
		fileHeight = height;
		imageURL = image;
		shapeArray = shapes;
	}

	this.saveChanged = function(dataUrl){
		var message = {};
		message.type = Request.SavePage;
		message.fileId = fileId;
		message.page = page;
		message.dataUrl = dataUrl;
		connection.sendObject(message);

	}

	this.getFileId = function(){
		return fileId;
	}
	
	this.getFileWidth = function(){
		return fileWidth;
	}

	this.getFileHeight = function(){
		return fileHeight;
	}

	this.getPage = function(){
		return page;
	}

	this.getImageURL = function(){
		return imageURL;
	}

	this.getShapeArray = function(){
		return shapeArray;
	}
}
