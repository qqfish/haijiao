//class table


function table(containerName, initPage, tool){
	//private
	var layerScale = {
		x:1,
		y:1
	}; //no scale
	var stage = new Kinetic.Stage({
		container : containerName,
		width : 0,
		height : 0,
	    	scale: layerScale
	});


	var backLayer = new Kinetic.Layer();

	var drawLayer = new Kinetic.Layer();

	var tmpLayer = new Kinetic.Layer();

	var page = initPage;

	var toolkit = tool;

	stage.add(backLayer);
	stage.add(drawLayer);
	stage.add(tmpLayer);

	this.initPage = function(){
		if(page.getFileWidth() <= 0 || page.getFileHeight() <= 0 || page.getImageURL() == null){
			console.log("error page");
		}
		var imageObj = new Image();

		imageObj.onload = function(){
			stage.setWidth(page.getFileWidth());
			stage.setHeight(page.getFileHeight());
			var pageImage = new Kinetic.Image({
				x: 0,
				y: 0,
			    	image: imageObj,
			    	width: page.getFileWidth(),
			    	height: page.getFileHeight()
			});
			backLayer.add(pageImage);
			var shapeArray = page.getShapeArray();
			for(var i = 0; i < shapeArray.length; i++){
				drawLayer.add(shapeArray[i]);
			}
			stage.draw();
		}
		imageObj.src = page.getImageURL();
	};

	this.savePage = function(){
		stage.toDataURL({
			callback: function(dataUrl) {
				page.saveChanged(dataUrl);
			}
		});
	}

	this.chagePage = function(newPage){
		savePage();
		page = newPage;
		stage.setWidth(0);
		stage.setHeight(0);
		initPage();
	}
        
        this.drawFromJson = function(json){
            var shape = Kinetic.Node.create(json);
            drawLayer.add(shape);
            stage.draw();
        }
        
        this.drawTmpFromJson = function(json){
            var shape = Kinetic.Node.create(json);
            tmpLayer.clear();
            tmpLayer.add(shape);
            stage.draw();
        }

	var mousedown = false;
	var result = null;
	
	stage.on("mousedown touchstart", function() {
		if(mousedown) return;
		var mousePos = stage.getMousePosition();
		if(toolkit.getTool() == Tooltype.Hand){
			result.x = mousePos.x;
			result.y = mousePos.y;
		} else if(toolkit.getTool() == Tooltype.Pen){
			result[result.length] = {x:mousePos.x, y:mousePos.y};
		} else if(toolkit.getTool() == Tooltype.Circle){
		} else if(toolkit.getTool() == Tooltype.Line){
		} else if(toolkit.getTool() == Tooltype.Eraser){
		} else {
			console.log("tool not support\n");
		}
	});

	stage.on("mousemove touchmove", function() {
		if(!mousedown) return;
		var mousePos = stage.getMousePosition();
		if(toolkit.getTool() == Tooltype.Hand){
			stage.setX(mousePos.x - result.x);
			stage.setY(mousePos.y - result.y);
		} else if(toolkit.getTool() == Tooltype.Pen){
			result[result.length] = {x:mousePos.x, y:mousePos.y};
			var tmpLine = new Kinetic.Line({
				points:result,
			    	stroke: toolkit.getColor(),
			    	strokeWidth: toolkit.getWidth()
			});
			var message = {};
			message.type = Request.TmpShape;
			message.shap = tmpLine.toJSON();
			connection.sendObject(message);
		} else if(toolkit.getTool() == Tooltype.Circle){
		} else if(toolkit.getTool() == Tooltype.Line){
		} else if(toolkit.getTool() == Tooltype.Eraser){
		} else {
			console.log("tool not support\n");
		}	
	});

	stage.on("mouseup touchend", function() {
		if(!mousedown) return;
		var mousePos = stage.getMousePosition();
		if(toolkit.getTool() == Tooltype.Hand){
			stage.setX(mousePos.x - result.x);
			stage.setY(mousePos.y - result.y);
		} else if(toolkit.getTool() == Tooltype.Pen){
			result[result.length] = {x:mousePos.x, y:mousePos.y};
			var tmpLine = new Kinetic.Line({
				points: result,
			    	stroke: toolkit.getColor(),
			    	strokeWidth: toolkit.getWidth()
			});
			tmpLine.on("mousemove touchmove", function(ev) {
				//somethin for erase
			});
			var message = {};
			message.type = Request.DrawShape;
			message.shap = tmpLine.toJSON();
			connection.sendObject(message);
		} else if(toolkit.getTool() == Tooltype.Circle){
		} else if(toolkit.getTool() == Tooltype.Line){
		} else if(toolkit.getTool() == Tooltype.Eraser){
		} else {
			console.log("tool not support\n");
		}
		result = null;
	});


}
