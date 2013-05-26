/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function TextChat(windowId, sendButtonId, chatInputId){
    var chatWindow = $("#"+windowId);
    var sendButton = $("#"+sendButtonId);
    var chatInput = $("#"+chatInputId);
    
    sendButton.click(function(){
        if(chatInput.val() != null && chatInput.val()!=""){
            var message = {};
            message.type = Request.TextChat;
            message.message = chatInput.val();
            connection.sendObject(message);
            chatInput.val("");
        } else {
            
        }
    });
    
    this.printMeesage = function(user,message){
        var newM = $("<p></p>");
        newM.text(user + " : " + message);
        chatWindow.append(newM);
        chatWindow.scrollTop(100000000000000);
    }
    
    chatInput.keypress(function(e){
        if(e.keyCode==13){
            sendButton.click();
        }
    });
    
}