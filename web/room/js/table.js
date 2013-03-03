//class table


function Table(containerName, tool){
    //property of mouse
    var mousedown = false;
    var result = null;
    
    var ready = false;
    //private
    var layerScale = 0.5;
    var stage = new Kinetic.Stage({
        container : containerName, 
        scale: {
            x: layerScale,
            y: layerScale
        },
        width : 300, 
        height : 400,
        draggable : true
    });
    
    var imageSize = {
        width: 0,
        height: 0
    };


    var backLayer = new Kinetic.Layer();

    var drawLayer = new Kinetic.Layer();

    var tmpLayer = new Kinetic.Layer();

    var toolkit = tool;

    stage.add(backLayer);
    stage.add(drawLayer);
    stage.add(tmpLayer);
    
    setMouse();

    this.sendChangeFile = function(uuid){
        stage.toDataURL({
            callback: function(dataUrl) {
                var message = {};
                message.type = Request.ChangeFile;
                message.uuid = uuid;
                message.tmpUrl = dataUrl;
        
                connection.sendObject(message);
            }
        });
    }
    
    this.sendChangePage = function(uuid,page){
        stage.toDataURL({
            callback: function(dataUrl) {
                var message = {};
                message.type = Request.ChangePage;
                message.fileUuid = uuid;
                message.page = page;
                message.tmpUrl = dataUrl;
        
                connection.sendObject(message);
            }
        });
    }

    this.chagePage = function(response){
        backLayer.removeChildren();
        drawLayer.removeChildren();
        tmpLayer.removeChildren();

        var imageObj = new Image();

        imageObj.onload = function(){
            imageSize.width = imageObj.width;
            imageSize.height = imageObj.height;
            var pageImage = new Kinetic.Image({
                x: 0,
                y: 0,
                image: imageObj
            });
            backLayer.add(pageImage);
            
            stage.setX(stage.getWidth() / 2 - imageSize.width * layerScale / 2);
            stage.draw();
        }
        imageObj.src = response.url;
        
        for(var i = 0; i < response.shapeList.length; i++){
            var shape = response.shapeList[i];
            this.drawFromJson(shape.id, shape.json);
        }
        stage.draw();
        
        setDragBound();
    }
        
    this.drawFromJson = function(id, json){
        tmpLayer.removeChildren();
        console.log(tmpLayer.getChildren());
        var shape = Kinetic.Node.create(json);
        shape.setId(id);
        //        shape.on("mouseout touchout", function() {
        //            console.log("hello");
        //            if(!mousedown || toolkit.getTool() != Tooltype.Eraser) return;
        //            result.idArray[result.idArray.length] = shape.getId();
        //        //shape.off("mousemove touchmove");
        //        });
        //        drawLayer.drawHit();
            
        drawLayer.add(shape);
        stage.draw();
    }
        
    this.drawTmpFromJson = function(json){
        var shape = Kinetic.Node.create(json);
        //tmpLayer.clear();
        tmpLayer.add(shape);
        stage.draw();
    }
        
    this.eraseFromArray = function(idArray) {
        tmpLayer.removeChildren();
        var shapeSet = drawLayer.getChildren();
        //console.log(shapeSet);
        var current = 0;
        for(var j = 0; j < shapeSet.length && current < idArray.length; j++){
            //console.log(shapeSet[j].getId());
            while(idArray[current] < shapeSet[j].getId() && current < idArray.length-1){
                current++;
            }
            if(idArray[current] == shapeSet[j].getId()){
                shapeSet[j].remove();
                j--;
                current++;
            }
        }
        stage.draw();
    }
    function setDragBound(){
        stage.setDragBoundFunc(function(pos) {
            //console.log(stage.getPosition());
            if(imageSize.width * layerScale < stage.getWidth() && imageSize.height * layerScale < stage.getHeight()){
                return {
                    x: stage.getWidth() / 2 - imageSize.width * layerScale / 2,
                    y: 0 
                }
            } else if(imageSize.width * layerScale < stage.getWidth() && imageSize.height * layerScale >= stage.getHeight()){
                var newY;
                if(pos.y > 0){
                    newY = 0;
                } else if(pos.y < stage.getHeight() - imageSize.height * layerScale){
                    newY = stage.getHeight() - imageSize.height * layerScale;
                } else {
                    newY = pos.y;
                }
                return {
                    x: stage.getWidth() / 2 - imageSize.width * layerScale / 2,
                    y: newY
                }
            } else if(imageSize.width * layerScale >= stage.getWidth() && imageSize.height * layerScale < stage.getHeight()) {
                var newX;
                if(pos.x > 0){
                    newX = 0;
                } else if(pos.x < stage.getWidth() - imageSize.width * layerScale) {
                    newX = stage.getWidth() - imageSize.width * layerScale;
                } else {
                    newX = pos.x;
                }
                return {
                    x: newX,
                    y: 0
                }
            } else {
                var newY;
                if(pos.y > 0){
                    newY = 0;
                } else if(pos.y < stage.getHeight() - imageSize.height * layerScale){
                    newY = stage.getHeight() - imageSize.height * layerScale;
                } else {
                    newY = pos.y;
                }
                var newX;
                if(pos.x > 0){
                    newX = 0;
                } else if(pos.x < stage.getWidth() - imageSize.width * layerScale) {
                    newX = stage.getWidth() - imageSize.width * layerScale;
                } else {
                    newX = pos.x;
                }
                return {
                    x: newX,
                    y: newY
                }
                
            }
        });
    }
    
    this.setDraggable = function(){
        stage.setDraggable(true);
    }
    
    this.setUndraggable = function(){
        stage.setDraggable(false);
    }
    
    function setMouse(){
        //console.log(stage.getDraggable());
        stage.setDraggable(false);
        if(!ready) {
            ready = true;
	
            stage.on("mousedown touchstart", function() {
                if(toolkit.getTool() == Tooltype.Hand) return;
                
                if(mousedown) return;
                
                mousedown = true;
                var mousePos = stage.getTouchPosition();
                if(mousePos == null)
                    mousePos = stage.getMousePosition();
                
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        result = new Array();
                        result[result.length] = {
                            x:(mousePos.x - stage.getPosition().x) / layerScale, 
                            y:(mousePos.y - stage.getPosition().y) / layerScale
                        };
                        break;
                    case Tooltype.Circle:
                        break;
                    case Tooltype.Line:
                        break;
                    case Tooltype.Eraser:
                        result = {};
                        result.erasePath = new Array();
                        result.idArray = new Array();
                        result.erasePath[result.erasePath.length] = {
                            x:(mousePos.x - stage.getPosition().x) / layerScale, 
                            y:(mousePos.y - stage.getPosition().y) / layerScale
                        };
                        break;
                    
                }
            });

            stage.on("mousemove touchmove", function() {
                if(toolkit.getTool() == Tooltype.Hand) return;
                
                if(!mousedown) return;
                var mousePos = stage.getTouchPosition();
                if(mousePos == null)
                    mousePos = stage.getMousePosition();
                
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        result[result.length] = {
                            x:(mousePos.x - stage.getPosition().x) / layerScale, 
                            y:(mousePos.y - stage.getPosition().y) / layerScale
                        };
                        var tmpLine = new Kinetic.Line({
                            points: [result[result.length-2], result[result.length-1]],
                            stroke: toolkit.getColor(),
                            strokeWidth: toolkit.getWidth()
                        });
                        var message = {};
                        message.type = Request.TmpShape;
                        message.json = tmpLine.toJSON();
                        connection.sendObject(message);
                        tmpLayer.add(tmpLine);
                        stage.draw();
                        break;
                    case Tooltype.Circle:
                        break;
                    case Tooltype.Line:
                        break;
                    case Tooltype.Eraser:
                        result.erasePath[result.erasePath.length] = {
                            x:(mousePos.x - stage.getPosition().x) / layerScale, 
                            y:(mousePos.y - stage.getPosition().y) / layerScale
                        };
                        var tmpLine = new Kinetic.Line({
                            points:result.erasePath,
                            stroke: toolkit.getColor(),
                            strokeWidth: toolkit.getWidth()
                        });
                        var message = {};
                        message.type = Request.TmpShape;
                        message.json = tmpLine.toJSON();
                        connection.sendObject(message);
                        
                        tmpLayer.add(tmpLine);
                        stage.draw();
                        
                        var p1 = result.erasePath[result.erasePath.length - 2];
                        var p2 = result.erasePath[result.erasePath.length - 1];
                        var children = drawLayer.getChildren();
                        for(var i = 0; i < children.length; i++){
                            //console.log(children[i].hasErase);
                            if(children[i].hasErase == true) 
                                continue;
                            var points = children[i].getPoints();
                            for(var j = 0; j < points.length - 1; j++){
                                if((p1.x - points[j].x) * (p2.x - points[j + 1].x) <= 0
                                    && (p1.y-points[j].y) * (p2.y - points[j + 1].y) <= 0){
                    
                                    result.idArray[result.idArray.length] = children[i].getId();
                                    children[i].hasErase = true;
                                    break;
                                }
                                //console.log(result.idArray.length);
                            }
                        }
                        break;
                    
                }
            });

            stage.on("mouseup touchend", function() {
                if(toolkit.getTool() == Tooltype.Hand) return;
                
                if(!mousedown) return;
                var mousePos = stage.getTouchPosition();
                if(mousePos == null)
                    mousePos = stage.getMousePosition();
                
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        result[result.length] = {
                            x:(mousePos.x - stage.getPosition().x) / layerScale, 
                            y:(mousePos.y - stage.getPosition().y) / layerScale
                        };
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
                        message.json = tmpLine.toJSON();
                        connection.sendObject(message);
                        break;
                    case Tooltype.Circle:
                        break;
                    case Tooltype.Line:
                        break;
                    case Tooltype.Eraser:
                        message = {};
                        message.type = Request.EraseShape;
                        message.idArray = result.idArray;
                        connection.sendObject(message);
                        break;
                    
                }
                mousedown = false;
                result = null;
            });
        }
    }
}
    
