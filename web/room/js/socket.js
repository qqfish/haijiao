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
	//每隔10秒发送一条空信息，防止websocket自动断开。
	    setInterval("connection.sendMessage('')",1000 * 10);
    };

    connection.socket.onclose = function () {
	    console.log('Info: WebSocket closed.');
    };

    connection.socket.onmessage = function (message) {
	console.log('S->C: ' + message.data);
        var socketData = Json.parse(message);
    };
});

connection.initialize = function() {
    if (window.location.protocol == 'http:') {
	connection.connect('ws://' + window.location.host + '/WebFc/WebFcSocketServlet');
    } else {
	connection.connect('wss://' + window.location.host + '/WebFc/WebFcSocketServlet');
    }
};

connection.sendObject = function(message) {
    var Jmessage = Json.stringify(message);
    connection.socket.send(Jmessage);
};

connection.initialize();


