//class table


function Table(containerName, tool){
    //scale 
    
    var scaleRange = new Array();
    scaleRange[0] = 0.25
    scaleRange[1] = 0.5;
    scaleRange[2] = 1;
    scaleRange[3] = 1.2;
    scaleRange[4] = 1.5;
    scaleRange[5] = 2;
    scaleRange[6] = 2.5;
    scaleRange[7] = 3;
    scaleRange[8] = 4;
    scaleRange[9] = 5;
    
    //property of mouse
    var mousedown = false;
    var result = null;
    
    var ready = false;
    //private
    var scaleChoice = 1;
    var stage = new Kinetic.Stage({
        container : containerName, 
        scale: {
            x: scaleRange[scaleChoice],
            y: scaleRange[scaleChoice]
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
        changePageLock();
        setStageToSave();
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
        changePageLock();
        setStageToSave();
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
    
    this.sendDownload = function(){
        changePageLock();
        setStageToSave();
        stage.toDataURL({
            callback: function(dataUrl) {
                var message = {};
                message.type = Request.DownloadPdf;
                message.tmpUrl = dataUrl;
        
                connection.sendObject(message);
            }
        });
    }

    this.changePage = function(response){
        unlockTool();
        backLayer.removeChildren();
        drawLayer.removeChildren();
        tmpLayer.removeChildren();
        
        setPagePanel(response.page, response.totalPage);

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
            resetStage();
            stage.setX(stage.getWidth() / 2 - imageSize.width * scaleRange[scaleChoice] / 2);
            stage.draw();
        }
        imageObj.src = response.url;
        
        for(var i = 0; i < response.shapeList.length; i++){
            var shape = response.shapeList[i];
            this.drawFromJson(shape.id, shape.json);
        }
        stage.draw();
        
        setDragBound();
        unlock();
    }
        
    this.drawFromJson = function(id, json){
        tmpLayer.removeChildren();
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
            //            if(imageSize.width * scaleRange[scaleChoice] < stage.getWidth() && imageSize.height * scaleRange[scaleChoice] < stage.getHeight()){
            //                return {
            //                    x: stage.getWidth() / 2 - imageSize.width * scaleRange[scaleChoice] / 2,
            //                    y: 0 
            //                }
            //            } else if(imageSize.width * scaleRange[scaleChoice] < stage.getWidth() && imageSize.height * scaleRange[scaleChoice] >= stage.getHeight()){
            //                var newY;
            //                if(pos.y > 0){
            //                    newY = 0;
            //                } else if(pos.y < stage.getHeight() - imageSize.height * scaleRange[scaleChoice]){
            //                    newY = stage.getHeight() - imageSize.height * scaleRange[scaleChoice];
            //                } else {
            //                    newY = pos.y;
            //                }
            //                return {
            //                    x: stage.getWidth() / 2 - imageSize.width * scaleRange[scaleChoice] / 2,
            //                    y: newY
            //                }
            //            } else if(imageSize.width * scaleRange[scaleChoice] >= stage.getWidth() && imageSize.height * scaleRange[scaleChoice] < stage.getHeight()) {
            //                var newX;
            //                if(pos.x > 0){
            //                    newX = 0;
            //                } else if(pos.x < stage.getWidth() - imageSize.width * scaleRange[scaleChoice]) {
            //                    newX = stage.getWidth() - imageSize.width * scaleRange[scaleChoice];
            //                } else {
            //                    newX = pos.x;
            //                }
            //                return {
            //                    x: newX,
            //                    y: 0
            //                }
            //            } else {
            //                var newY;
            //                if(pos.y > 0){
            //                    newY = 0;
            //                } else if(pos.y < stage.getHeight() - imageSize.height * scaleRange[scaleChoice]){
            //                    newY = stage.getHeight() - imageSize.height * scaleRange[scaleChoice];
            //                } else {
            //                    newY = pos.y;
            //                }
            //                var newX;
            //                if(pos.x > 0){
            //                    newX = 0;
            //                } else if(pos.x < stage.getWidth() - imageSize.width * scaleRange[scaleChoice]) {
            //                    newX = stage.getWidth() - imageSize.width * scaleRange[scaleChoice];
            //                } else {
            //                    newX = pos.x;
            //                }
            //                return {
            //                    x: newX,
            //                    y: newY
            //                }
            //                
            //            }
            var newX,newY;
            if(pos.x < - imageSize.width * scaleRange[scaleChoice]){
                newX = -imageSize.width * scaleRange[scaleChoice];
            } else if(pos.x > stage.getWidth() + imageSize.width * scaleRange[scaleChoice]) {
                newX = stage.getWidth() + imageSize.width * scaleRange[scaleChoice];
            } else {
                newX = pos.x;
            }
            
            if( pos.y < - imageSize.height * scaleRange[scaleChoice]){
                newY = - imageSize.height * scaleRange[scaleChoice];
            } else if(pos.y > stage.getHeight() + imageSize.height * scaleRange[scaleChoice]) {
                newY = stage.getHeight() + imageSize.height * scaleRange[scaleChoice];
            } else {
                newY = pos.y;
            }
            return {
                x: newX,
                y: newY
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
                if(toolkit.getTool() == Tooltype.Text) return;
                var mousePos = stage.getTouchPosition();
                if(mousePos == null)
                    mousePos = stage.getMousePosition();
                
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        result = new Array();
                        result[result.length] = {
                            x:(mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice], 
                            y:(mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice]
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
                            x:(mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice], 
                            y:(mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice]
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
                            x:(mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice], 
                            y:(mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice]
                        };
                        var tmpLine = new Kinetic.Line({
                            points: [result[result.length-2], result[result.length-1]],
                            stroke: toolkit.getColor(),
                            strokeWidth: toolkit.getWidth(),
                            alpha: toolkit.getAlpha()
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
                            x:(mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice], 
                            y:(mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice]
                        };
                        var tmpLine = new Kinetic.Line({
                            points:[result.erasePath[result.erasePath.length-2],result.erasePath[result.erasePath.length-1]],
                            stroke: "white",
                            strokeWidth: 15,
                            alpha: 0.5
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
                            switch(children[i].getClassName()){
                                case "Text":
                                    if(p2.x > children[i].getX() && p2.x < children[i].getX() + children[i].getWidth()
                                        && p2.y > children[i].getY() && p2.y < children[i].getY() + children[i].getHeight()){
                                        
                                        result.idArray[result.idArray.length] = children[i].getId();
                                        children[i].hasErase = true;
                                    }
                                    break;
                                case "Line":
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
                                    break;
                            }
                        }
                        break;
                    
                }
            });
            
            $(window).mouseup(function(){
                if(toolkit.getTool() == Tooltype.Hand) return;
                
                if(!mousedown) return;
                
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        var tmpLine = new Kinetic.Line({
                            points: result,
                            stroke: toolkit.getColor(),
                            strokeWidth: toolkit.getWidth(),
                            alpha: toolkit.getAlpha()
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
                    case Tooltype.Text:
                        console.log("hello");
                        var mousePos = stage.getTouchPosition();
                        if(mousePos == null)
                            mousePos = stage.getMousePosition();
                        var textInput = $("<textarea></textarea>").css("position","absolute").css("width","100%").css("height","100%");
                        textInput.css("font-size",toolkit.getTextSize() * scaleRange[scaleChoice]).css("color",toolkit.getTextColor()).css("background","transparent").css("border-style","none");
                        textInput.css("left",mousePos.x - 5).css("top",mousePos.y - 7).css("line-height",1.2);
                        textInput.keydown(function(evt){
                            if(event.ctrlKey && event.keyCode == 13){
                                var simpleText = new Kinetic.Text({
                                    x: (mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice],
                                    y: (mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice],
                                    fontSize: toolkit.getTextSize(),
                                    fontFamily: 'Calibri',
                                    fill: toolkit.getTextColor()
                                });
                                simpleText.setText(textInput.val());
                                textInput.remove();
                                var message = {};
                                message.type = Request.DrawShape;
                                message.json = simpleText.toJSON();
                                connection.sendObject(message);
                            }
                        });
                        textInput.blur(function(){
                            var simpleText = new Kinetic.Text({
                                x: (mousePos.x - stage.getPosition().x) / scaleRange[scaleChoice],
                                y: (mousePos.y - stage.getPosition().y) / scaleRange[scaleChoice],
                                fontSize: toolkit.getTextSize(),
                                fontFamily: 'Calibri',
                                fill: toolkit.getTextColor()
                            });
                            simpleText.setText(textInput.val());
                            textInput.remove();
                            var message = {};
                            message.type = Request.DrawShape;
                            message.json = simpleText.toJSON();
                            connection.sendObject(message);
                        });
                        $("#table").append(textInput);
                        textInput.focus();
                        break;
                }
                mousedown = false;
                result = null;
            });

            stage.on("touchend", function() {
                if(toolkit.getTool() == Tooltype.Hand) return;
                        
                if(!mousedown) return;
                        
                switch(toolkit.getTool()){
                    case Tooltype.Pen:
                        var tmpLine = new Kinetic.Line({
                            points: result,
                            stroke: toolkit.getColor(),
                            strokeWidth: toolkit.getWidth(),
                            alpha: toolkit.getAlpha()
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
    
    this.setStageSize = function(x,y){
        stage.setWidth(x);
        stage.setHeight(y);
        stage.draw();
    }
    
    this.scaleUp = function(){
        if(scaleChoice < scaleRange.length - 1){
            scaleChoice++;
        }
        stage.setScale({
            x: scaleRange[scaleChoice],
            y: scaleRange[scaleChoice]
        });
        setDefaultLocation();
    }
    
    this.scaleDown = function(){
        if(scaleChoice > 1){
            scaleChoice --;
        }
        stage.setScale({
            x: scaleRange[scaleChoice],
            y: scaleRange[scaleChoice]
        });
        setDefaultLocation();
    }
    
    function setDefaultLocation(){
        stage.setX(stage.getWidth() / 2 - imageSize.width * scaleRange[scaleChoice] / 2);
        if(stage.getY() > 0)
            stage.setY(0);
        else if(stage.getY() < stage.getHeight() - imageSize.height * scaleRange[scaleChoice])
            stage.setY(stage.getHeight() - imageSize.height * scaleRange[scaleChoice]);
        stage.draw();
    }

    this.setDefaultLoc = function(){
        setDefaultLocation();
    }
    
    this.uploadImage = function(imageObj){
        var img = new Kinetic.Image({
            image: imageObj,
            x: 0,
            y: 0,
            width: imageObj.width,
            height: imageObj.height,
            draggable: true
        });
        if(img.getWidth() > imageSize.width){
            img.setHeight(img.getHeight() / img.getWidth() * imageSize.width);
            img.setWidth(imageSize.width);
        }
        if(img.getHeight() > imageSize.height){
            img.setWidth(img.getWidth() / img.getHeight() * imageSize.height);
            img.setHeight(imageSize.height);
        }
        lockTool();
        
        tmpLayer.add(img);
        stage.draw();
        img.on("dblclick dbltouch", function(){
            unlockTool()
            changePageLock();
            setStageToSave();
            drawLayer.removeChildren();
            stage.draw();
            stage.toDataURL({
                callback: function(dataUrl) {
                    img.remove();
                    resetStage();
                    var message = {};
                    message.type = Request.UploadFile;
                    message.postfix = "image";
                    message.data = dataUrl;
                    connection.sendObject(message);
                }
            });
        });
    }
    
    this.uploadBackground = function(dataUrl){
        backLayer.removeChildren();
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
            stage.setX(stage.getWidth() / 2 - imageSize.width * scaleRange[scaleChoice] / 2);
            resetStage();
            stage.draw();
            unlock();
        }
        imageObj.src = dataUrl;
    }
    
    function setStageToSave(){
        stage.setScale({
            x: 1,
            y: 1
        });
        stage.setHeight(imageSize.height);
        stage.setWidth(imageSize.width);
        stage.setX(0);
        stage.setY(0);
        stage.draw();
    }
    
    function resetStage(){
        $(window).resize();
        stage.setScale({
            x: scaleRange[scaleChoice],
            y: scaleRange[scaleChoice]
        });
        setDefaultLocation();
    }
    
    function setPagePanel(current, total){
        $("#currentPage").text(current + 1);
        $("#totalPage").text(total);
        $("#nextPage").unbind("click");
        if(current < total - 1)
            $("#nextPage").click(function(){
                file.nextPage();
            });
        $("#prePage").unbind("click");
        if(current > 0)
            $("#prePage").click(function(){
                file.prePage();
            });
    }
    
    function changePageLock(){
        $('#alertContext').empty().html("<strong>请稍等!</strong>信息正在加载中");
        theBar = $("<div></div>").attr("class","bar").css("width","100%");
        progressBar = $("<div></div>").attr("class","progress progress-striped active").append(theBar);
        $('#alertContext').append(progressBar);
        lockType("warn");
        lock();
    }
}
    
