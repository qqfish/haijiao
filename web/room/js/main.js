/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var textChat;
var toolkit;
var table;
var media;
$(document).ready(function(){
    connection.initialize();
    textChat = new TextChat("textConsole");
    toolkit = new Toolkit();
    table = new Table("table",toolkit);
    table.setStageSize($(window).width(),$(window).height());
    media = new Media("desktop", textChat, "users");
    media.setDragPlace(0, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
    file = new fileManager("roomFile","bookmark","userFile");
    
    $(window).resize(function(){
        $("#desktop").height($(window).height()-52).width($(window).width() - 250 + $(".slide-bar").css("left"));
        table.setStageSize($(window).width(), $("#desktop").height());
        media.setDragPlace(0, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());

        $("#side").height($(window).height()-52);
    });
    
    var w=$(window).width();//可见区域宽度
    var h=$(window).height();//可见区域高度
    $("#desktop").height(h-52).width(w);

    $("#side").height(h-52).css("marginLeft","-250px");
    
    //just for test
    $("#nextPage").click(function(){
        file.nextPage();
    });
    $("#prePage").click(function(){
        file.prePage();
    });
    
    $("#pen").click(function(){
        toolkit.changeTool(Tooltype.Pen);
        table.setUndraggable();
    });
    $("#pointer").click(function(){
        toolkit.changeTool(Tooltype.Hand);
        table.setDraggable();
    //table.removeMouse();
    });
    $("#eraser").click(function(){
        toolkit.changeTool(Tooltype.Eraser);
        table.setUndraggable();
    //table.removeMouse();
    });
    
    $("#scaleUp").click(function(){
        table.scaleUp();
    });
    
    $("#scaleDown").click(function(){
        table.scaleDown();
    });
    
    $("#pointer").click();
    
    $(document).on({
        dragleave:function(e){    //拖离 
            e.preventDefault(); 
        }, 
        drop:function(e){  //拖后放 
            e.preventDefault(); 
        }, 
        dragenter:function(e){    //拖进 
            e.preventDefault(); 
        }, 
        dragover:function(e){    //拖来拖去 
            e.preventDefault(); 
        } 
    });
    
    document.getElementById('desktop').addEventListener("drop",function(e){
        e.preventDefault();
        var fileList = e.dataTransfer.files;
        for(var i = 0; i < fileList.length; i++){
            file.uploadFile(fileList[i]);
        }
    });
});
