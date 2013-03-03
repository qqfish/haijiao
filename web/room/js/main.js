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
    media = new Media("videoWindows", textChat);
    file = new fileManager("roomFile","bookmark");
    
    //just for test
    $("#nextPage").click(function(){
        file.nextPage();
    });
    $("#prePage").click(function(){
        file.prePage();
    });
    
    $("#videoButton").click(function(){
        $(".video").toggle();
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
});
