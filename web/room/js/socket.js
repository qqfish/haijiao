var connection = {};

connection.socket = null;

connection.connect = (function(host) {
    if ('WebSocket' in window) {
        connection.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        connection.socket = new MozWebSocket(host);
    } else {
        console.log('Error: WebSocket is not supported by this browser.');
        return;
    }

    connection.socket.onopen = function () {
        console.log('Info: WebSocket connection opened.');
        setInterval("connection.sendMessage('')",1000 * 10);
    };

    connection.socket.onclose = function () {
        pError("链接已经断开！！");
        console.log('Info: WebSocket closed.');
    };

    connection.socket.onmessage = function (message) {
        console.log('S->C: ' + message.data);
        var socketData = JSON.parse(message.data);
        switch(socketData.type){
            case Response.TmpShape:
                table.drawTmpFromJson(socketData.json);
                break;
            case Response.DrawShape:
                table.drawFromJson(socketData.id, socketData.json);
                break;
            case Response.EraseShape:
                table.eraseFromArray(socketData.idArray);
                break;
            case Response.TextChat:
                textChat.printMeesage(socketData.message);
                break;
            case Response.VideoChat:
                media.processSignalingMessage(socketData);
                break;
            case Response.ChangePage:
                table.changePage(socketData);
                file.setPage(socketData);
                break;
            case Response.ChangeBookmark:
                file.changeBookmark(socketData);
                break;
            case Response.AddRoomFile:
                file.addRoomFile(socketData);
                break;
            case Response.SetUserFile:
                file.setUserFile(socketData);
                break;
            case Response.UploadBackground:
                table.uploadBackground(socketData.dataUrl);
                break;
            case Response.ShowTimer:
                timer.showTimer(socketData);
                break;
            case Response.Error:
                processError(socketData.errorType);
                break;
            case Response.Info:
                processInfo(socketData.infoType, socketData.message);
                break;
        }
    };
});

connection.initialize = function(clazzId, teaEmail, email) {
    console.log(email);
    if (window.location.protocol == 'http:') {
        connection.connect('ws://' + window.location.host + '/haijiao/WebFcSocketServlet?clazzId='+clazzId+'&teaEmail=' + teaEmail + '&email='+email);
    } else {
        connection.connect('wss://' + window.location.host + '/haijiao/WebFcSocketServletclazzId='+clazzId+'&teaEmail=' + teaEmail + '&email='+email);
    }
};

connection.sendObject = function(message) {
    var Jmessage = JSON.stringify(message);
    connection.socket.send(Jmessage);
};

connection.sendMessage = function(message) {
    connection.socket.send(message);
};