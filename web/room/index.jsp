<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Class</title>
        <meta charset="utf-8">
        <script src="js/lib/jquery-1.9.1.js"></script>

        <!--<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>-->
        <script src="js/lib/jquery-ui-1.10.1.custom.min.js"></script>
        <script src="js/lib/jQueryRotate.2.2.js"></script>
        <script src="js/lib/kinetic.js"></script>
        <script src="js/adapter.js"></script>
        <script src="js/screen.js"></script>
        <script src="js/message.js"></script>
        <script src="js/toolkit.js"></script>
        <script src="js/textChat.js"></script>
        <script src="js/socket.js"></script>
        <script src="js/media.js"></script>
        <script src="js/table.js"></script>
        <script src="js/fileManager.js"></script>
        <script src="js/main.js"></script>
        <script src="js/tooltip.js"></script>
        <!--css-->
        <link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/room.css" type="text/css" media="screen">
        <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
        <!--<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>-->
        <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" media="screen">
        <link rel="stylesheet" href="../css/bootstrap-responsive.min.css" type="text/css" media="screen">
        <!--js-->
        <!--<script type="text/javascript" src="../js/superfish.js"></script>-->
        <script type="text/javascript" src="js/sidebar.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>

        <script type="text/javascript">
        </script>
        <!--[if lt IE 8]>
      <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
          <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
       </a>
     </div>
    <![endif]-->

        <!--[if lt IE 9]>
                <script src="js/html5.js"></script>
                <link rel="stylesheet" href="css/ie.css"> 
       <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400italic' rel='stylesheet' type='text/css'>
      <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400' rel='stylesheet' type='text/css'>
        <![endif]-->

    </head>
    <body>

        <!--==============================header=================================-->
        <!--<!--%@ include file="WEB-INF/jspf/header.jspf"%>-->
        <div class="line-top"></div>
        <!--==============================content=================================-->
        <div id="content"> 

            <div id="tools" class="navbar navbar-inverse">
                <div  class="navbar-inner">
                    <div class="span12 offset2">
                        <a class="brand" href="index.jsp">Haijiao</a>
                        <ul class="nav">
                            <li>
                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <button id="pointer" type="button" value="0" class="btn active" data-toggle="tooltip" data-placement="bottom" data-original-title="指针">指针</button>
                                    <button id="pen" type="button" value="1" class="btn" data-toggle="tooltip" data-placement="bottom" data-original-title="画笔"><i class="icon-pencil"></i></button>
                                    <button id="eraser" type="button" value="2" class="btn" data-toggle="tooltip" data-placement="bottom" data-original-title="橡皮"><i class="icon-hdd"></i></button>
                                </div>
                            </li>
                            <li><a id="prePage" data-toggle="tooltip" data-placement="bottom" data-original-title="上一页"><i class="icon-arrow-left icon-white"></i></a></li>
                            <li><a id="nextPage" data-toggle="tooltip" data-placement="bottom" data-original-title="下一页"><i class="icon-arrow-right icon-white" ></i></a></li>
                            <li><a id="scaleUp" data-toggle="tooltip" data-placement="bottom" data-original-title="放大"><i class="icon-zoom-in icon-white"></i></a></li>
                            <li><a id="scaleDown" data-toggle="tooltip" data-placement="bottom" data-original-title="缩小"><i class="icon-zoom-out icon-white"></i></a></li>
                            <li><a class="favor-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="收藏栏"><i class="icon-tags icon-white"></i></a></li>
                            <li><a class="userFile-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="用户文件"><i class="icon-th-list icon-white"></i></a></li>
                            <li><a class="user-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="用户列表"><i class="icon-user icon-white"></i></a></li>
                            <li><a class="file-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="文件列表"><i class="icon-folder-open icon-white"></i></a></li>
                            <li><a class="close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="关闭侧边栏"><i class="icon-remove icon-white"></i></a></li>
                        </ul>
                    </div> 
                </div>
                <div class="well span2" id ="side" style="margin:0px;float: bottom;overflow-y: auto;overflow-x: visible;white-space: nowrap;word-break: break-all">   
                    <div id="favor-content" style="display:none;">
                        <ul id="bookmark"></ul>
                    </div>
                    <div id="userFile-content" style="display:none ">
                        <ul id="userFile"></ul>
                    </div>
                    <div id="user-content" style="display:none">
                        <ul id="users"></ul>
                    </div>
                    <div id="file-content" style="display: none">
                        <ul id="roomFile"></ul>
                    </div>        
                </div>
            </div>
            <div id="desktop" style="position: absolute;top: 50px;overflow: hidden;">
                <div id="table">
                </div>
            </div>

        </div>
        <!--==============================footer=================================-->
        <!--%@ include file="WEB-INF/jspf/footer.jspf"%>
        </body>
        </html>
