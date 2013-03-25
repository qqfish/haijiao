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
        <script src="js/lib/kinetic.js"></script>
        <script src="js/message.js"></script>
        <script src="js/toolkit.js"></script>
        <script src="js/textChat.js"></script>
        <script src="js/socket.js"></script>
        <script src="js/media.js"></script>
        <script src="js/table.js"></script>
        <script src="js/fileManager.js"></script>
        <script src="js/main.js"></script>
        <!--css-->
        <link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
        <link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="../css/grid.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/room.css" type="text/css" media="screen">
        <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
        <!--<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>-->
        <link rel="stylesheet" href="../css/flexslider.css" type="text/css" media="screen">
        <!--js-->
        <!--<script type="text/javascript" src="../js/superfish.js"></script>-->
        <script type="text/javascript" src="js/sidebar.js"></script>
        
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
        <section id="content"> 

            <div id="tools">
                <div>
                    <ul class="sf-menu" style="float: right;">
                        <li><a id="pointer">指针</a></li>
                        <li><a id="pen">画笔</a></li>
                        <li><a id="eraser">橡皮</a></li>
                        <li><a id="prePage">上一页</a></li>
                        <li><a id="nextPage">下一页</a></li>
                        <li><a id="scaleUp">放大</a></li>
                        <li><a id="scaleDown">缩小</a></li>
						<li><a class="favor-close-sidebar"><img src="../images/icon.png"/></a></li>
                        <li><a class="userFile-close-sidebar">用户文件</a></li>
                        <li><a class="user-close-sidebar">用户列表</a></li>
                        <li><a class="file-close-sidebar">文件</a></li>
                        <li><a class="close-sidebar" >关闭侧边栏</a></li>
                    </ul>
                </div> 
                <hr style="width:1366px;height:2px;margin:0px 0px 0px 0px;float:right"></hr>
                <div class="side-bar" id ="side" style="margin:0px;float: bottom;overflow-y: auto;overflow-x: visible;white-space: nowrap;word-break: break-all">   
                    <div id="favor-content" style="display:none;">
                        <p>书签</p>
                        <ul id="bookmark"></ul>
                    </div>
                    <div id="userFile-content" style="display:none ">
                        <p>用户文件</p>
                        <div id="userFile"></div>
                    </div>
                    <div id="user-content" style="display:none">
                        <p>用户列表</p>
                        <div id="users"></div>
                    </div>
                    <div id="file-content" style="display: none">
                        <p>房间文件</p>
                        <div id="roomFile"></div>
                    </div>        
                </div>
            </div>
            <div id="desktop" style="position: absolute;top: 50px;overflow: hidden;">
                <div id="table">
                </div>
            </div>

        </section>
        <!--==============================footer=================================-->
        <!--%@ include file="WEB-INF/jspf/footer.jspf"%>
        </body>
        </html>