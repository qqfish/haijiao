/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Timer(panelSession){
    var panel = $('#' + panelSession);
    
    $('#toggleTimer').click(function(){
        var message = {};
        message.type = Request.ToggleTimer;
        console.log(message);
        connection.sendObject(message);
    });
    
    $('#stopTimer').click(function(){
       var message = {};
       message.type = Request.StopTimer;
       connection.sendObject(message);
    });
    
    this.showTimer = function(t){
        panel.text(t.time);
    }
}