<%-- 
    Document   : index
    Created on : Jan 11, 2013, 3:59:11 PM
    Author     : fish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/media.css" type="text/css" media="screen">
        <script src="js/lib/jquery.js"></script>
        <script src="js/lib/kinetic.js"></script>
        <script src="js/message.js"></script>
        <script src="js/toolkit.js"></script>
        <script src="js/textChat.js"></script>
        <script src="js/socket.js"></script>
        <script src="js/media.js"></script>
        <script src="js/table.js"></script>
        <script src="js/main.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="stable">
            <div id="toolbar">
                <button id="pointer">指针</button>
                <button id="pen">画笔</button>
                <button id="eraser">橡皮</button>
                <button id="videoButton"">视频</button>
            </div>
            <div id="videoWindows"></div>
            <div id="textChat">
                <div id="textConsole"></div>
            </div>
        </div>
        <div id="table"></div>
    </body>
</html>
