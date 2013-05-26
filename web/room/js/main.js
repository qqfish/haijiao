/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var textChat;
var toolkit;
var table;
var media;
var timer;
function init(clazzId, teaEmail, email){
    connection.initialize(clazzId, teaEmail, email);
    timer = new Timer("timerPanel");
    textChat = new TextChat("charShowArea","chatSend","chatInput");
    toolkit = new Toolkit();
    table = new Table("table",toolkit);
    media = new Media("sideVideoArea", textChat, "users");
    media.setDragPlace(0, 42, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
    file = new fileManager("roomFile","bookmark","userFile");
    
    media.userEnter("__localUser","__localUser");
    
    var sideShow = true;
    var sideWidth = 250;
    var sideButton = 25;
    
    
    $(window).resize(function(){
        if(sideShow)
            $("#desktop").height($(window).height()-42).width($(window).width() - sideWidth - sideButton).css("left",sideWidth + sideButton);
        else
            $("#desktop").height($(window).height()-42).width($(window).width());
        table.setStageSize($(window).width(), $("#desktop").height());
        table.setDefaultLoc();
        
        //media.setDragPlace(0, 42, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());

        $("#side").height($(window).height()-44);
        $("#charShowArea").height($("#side").height() - 450);
    });
    
    var w=$(window).width();//可见区域宽度
    var h=$(window).height();//可见区域高度
    $("#desktop").height(h-42).width(w - sideWidth - sideButton).css("left",sideWidth + sideButton);
    table.setStageSize(w,$("#desktop").height());
    $("#side").height(h-44).css("marginLeft","0px");
    
    
    
    //button initialization
    $("#nextPage").click(function(){
        file.nextPage();
    });
    $("#prePage").click(function(){
        file.prePage();
    });

    //$("#pen").attr("data-content",$("#colorPanel").html());
    $("#pen").click(function(){
        toolkit.changeTool(Tooltype.Pen);
        table.setUndraggable();
        $("#colorPanel").dropdown();
    });
    $("#pointer").click(function(){
        toolkit.changeTool(Tooltype.Hand);
        table.setDraggable();
        //$("#pen").popover('hide');
    //table.removeMouse();
    });
    $("#eraser").click(function(){
        toolkit.changeTool(Tooltype.Eraser);
        table.setUndraggable();
        //$("#pen").popover('hide');
    //table.removeMouse();
    });
    
    $("#scaleUp").click(function(){
        table.scaleUp();
    });
    
    $("#scaleDown").click(function(){
        table.scaleDown();
    });
    
    $("#pointer").click();
    
    $("#gotoSubmit").click(function(){
        var value = $("#gotoInput").val();
        if(value == null || value == ""){
            $("#gotoError").text("请输入跳转页面");
            $("#gotoGroup").attr("class","control-group input-append error");
            return;
        } else if(parseInt(value) <= 0 || parseInt(value) > parseInt($("#totalPage").text())){
            $("#gotoError").text("跳转页面在1~"+$("#totalPage").text());
            $("#gotoGroup").attr("class","control-group input-append error");
            return;
        } else {
            $("#gotoError").text("");
            $("#gotoGroup").attr("class","control-group input-append");
            $("#gotoClose").click();
            file.gotoPage(value);
        }
    });
    
    $("#uploadFileInput").change(function(e){
        var fileList = e.target.files;
        for(var i = 0; i < fileList.length; i++){
            file.uploadFile(fileList[0]);
        }
    });
    
    
    //drag to upload
    $("#uploadFile").click(function(){
        $("#uploadFileInput").click();
    });
    
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
    
    
    
    //side bar
    
    function sideReturn(){
        $('#side').animate({
            marginLeft: - sideWidth
        }, 'fast');
        
        $('#desktop').animate({
            left: sideButton,
            width: $(window).width() - sideButton
        },'fast');
        //media.setDragPlace(0, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
        
//        $("#desktop").unbind("click");
        $("#ctlbar").attr("src","images/ctlbar2.png");
        sideShow = false;
    }
 
    $('#sideBarButton').click(function() {
        if(sideShow){
            sideReturn();
        } else {
            $('#desktop').animate({
                left: sideWidth + sideButton,
                width: '-=' + sideWidth + 'px'
            },'fast');
  
            $('#side').animate({
                marginLeft: "0px"
            }, 'fast'); //以1000毫秒让“文章主体部分”的宽度收缩回705px
        
//            $("#desktop").click(function(){
//                sideReturn();
//            });
            $("#ctlbar").attr("src","images/ctlbar1.png");
//            media.setDragPlace(250, 52, $(window).width()-media.getWidth(), $(window).height()-media.getHeight());
            sideShow = true;
        }
       
    });
}