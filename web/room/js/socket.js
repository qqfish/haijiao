var connection = {};

connection.socket = null;

connection.pin = 0;

connection.connect = (function(host) {
    if ('WebSocket' in window) {
        connection.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        connection.socket = new MozWebSocket(host);
    } else {
        console.log('Error: WebSocket is not supported by this browser.');
        pError("该浏览器版本太旧，不支持视频白板功能，请到<a href='http://www.onlinedown.net/soft/7993.htm'>这里</a>下载最新版Chrome浏览器",true);
        return;
    }

    connection.socket.onopen = function () {
        console.log('Info: WebSocket connection opened.');
        setInterval("connection.checkPin()",1000 * 5);
    };

    connection.socket.onclose = function () {
        disconnected();
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
                connection.pin++;
                table.drawFromJson(socketData.id, socketData.json);
                break;
            case Response.EraseShape:
                connection.pin++;
                table.eraseFromArray(socketData.idArray);
                break;
            case Response.TextChat:
                textChat.printMeesage(socketData.from, socketData.message);
                break;
            case Response.VideoChat:
                media.processSignalingMessage(socketData);
                break;
            case Response.ChangePage:
                connection.pin++;
                table.changePage(socketData);
                file.setPage(socketData);
                break;
            case Response.ChangeBookmark:
                file.changeBookmark(socketData);
                break;
            case Response.AddRoomFile:
                file.addRoomFile(socketData);
                break;
            //            case Response.SetUserFile:
            //                file.setUserFile(socketData);
            //                break;
            case Response.UploadBackground:
                table.uploadBackground(socketData.dataUrl);
                break;
            case Response.ShowTimer:
                timer.showTimer(socketData);
                break;
            case Response.UserEnter:
                media.userEnter(socketData.email, socketData.name);
                break;
            case Response.DownloadPDF:
                file.downloadResponse(socketData.path);
                break;
            case Response.Error:
                processError(socketData.errorType);
                break;
            case Response.Info:
                processInfo(socketData.infoType, socketData.message);
                break;
            case Response.SetPin:
                connection.pin = socketData.pin;
                break;
        }
    };
});

connection.initialize = function(clazzId, teaEmail, email) {
    console.log(email);
    if (window.location.protocol == 'http:') {
        connection.connect('ws://' + window.location.host + '/haijiao/WebFcSocketServlet?stuEmail='+stuEmail+'&teaEmail=' + teaEmail + '&email='+email);
    //connection.connect('ws://202.120.1.47:8080/WebFcSocketServlet?clazzId='+clazzId+'&teaEmail=' + teaEmail + '&email='+email);
    } else {
        connection.connect('wss://' + window.location.host + '/haijiao/WebFcSocketServlet?stuEmail='+stuEmail+'&teaEmail=' + teaEmail + '&email='+email);
    //connection.connect('wss://202.120.1.47:8080/WebFcSocketServletclazzId='+clazzId+'&teaEmail=' + teaEmail + '&email='+email);
    }
};

connection.sendObject = function(message) {
    var Jmessage = JSON.stringify(message);
    connection.socket.send(Jmessage);
};

connection.sendMessage = function(message) {
    connection.socket.send(message);
};

connection.checkPin = function(){
    var message = {};
    message.pin = connection.pin;
    message.type = Request.CheckPin;
    connection.sendObject(message);
}