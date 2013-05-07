<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Class</title>
        <meta charset="utf-8">
        <script src="/haijiao/room/js/lib/jquery-1.9.1.js"></script>

        <!--<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>-->
        <script src="/haijiao/room/js/lib/jquery-ui-1.10.1.custom.min.js"></script>
        <script src="/haijiao/room/js/lib/jQueryRotate.2.2.js"></script>
        <script src="/haijiao/room/js/lib/kinetic.js"></script>
        <script src="/haijiao/room/js/message.js"></script>
        <script src="/haijiao/room/js/adapter.js"></script>
        <script src="/haijiao/room/js/screen.js"></script>
        <script src="/haijiao/room/js/timer.js"></script>
        <script src="/haijiao/room/js/toolkit.js"></script>
        <script src="/haijiao/room/js/textChat.js"></script>
        <script src="/haijiao/room/js/socket.js"></script>
        <script src="/haijiao/room/js/media.js"></script>
        <script src="/haijiao/room/js/table.js"></script>
        <script src="/haijiao/room/js/fileManager.js"></script>
        <script src="/haijiao/room/js/main.js"></script>
        <script src="/haijiao/room/js/tooltip.js"></script>
        <!--css-->
        <link rel="stylesheet" href="/haijiao/css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="/haijiao/room/css/room.css" type="text/css" media="screen">
        <link rel="icon" href="/haijiao/images/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="/haijiao/images/favicon.ico" type="image/x-icon" />
        <!--<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>-->
        <link rel="stylesheet" href="/haijiao/css/bootstrap.min.css" type="text/css" media="screen">
        <link rel="stylesheet" href="/haijiao/css/bootstrap-responsive.min.css" type="text/css" media="screen">
        <script src="/haijiao/room/js/lib/bootstrap.file-input.js"></script>
        <!--js-->
        <!--<script type="text/javascript" src="../js/superfish.js"></script>-->
        <script type="text/javascript" src="/haijiao/room/js/sidebar.js"></script>
        <script type="text/javascript" src="/haijiao/js/bootstrap.min.js"></script>

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
    <body onload="init(<s:property value='clazzId' default='null' />, '<s:property value='teaEmail' default='null' />', '<s:property value='#session.email' default='null' />');">

        <!--==============================header=================================-->
        <!--<!--%@ include file="WEB-INF/jspf/header.jspf"%>-->
        <div class="line-top"></div>
        <!--==============================content=================================-->
        <div id="content"> 
            <a id="showAlert" data-toggle="modal" data-target="#alertMessage" class="btn btn-danger btn-large" style="display: none;"/>
            <div id="tools" class="navbar navbar-inverse">
                <div  class="navbar-inner">
                    <div class="span10 offset2">
                        <a class="brand" href="index.jsp">Haijiao</a>
                        <ul class="nav">
                            <s:if test="isHolder!=0">
                                <li><a id="toggleTimer" data-toggle="tooltip" data-placement="bottom" data-original-title="开始/暂停"><i class="icon-play icon-white"></i></a></li>
                                <li><a id="stopTimerButton" data-target="#finishModal" role="button" data-toggle="modal"data-placement="bottom" data-original-title="停止"><i class="icon-stop icon-white"></i></a></li>
                                <div id="finishModal" class="modal hide fade" tabindex="-1" role="dialog">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h3>完成课程</h3>
                                    </div>
                                    <div class="modal-body">
                                        <p>该操作将结束本次课程并完成扣费操作。</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                                        <button class="btn btn-primary" id="stopTimer"" data-dismiss="modal" aria-hidden="true">确认</button>
                                    </div>
                                </div>
                            </s:if>
                            <li><session id="timerPanel">00:00:00</session></li>
                            <li><session id="currentPage"></session> / <session id="totalPage"></session></li>
                            <li>
                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <button id="pointer" type="button" value="0" class="btn active" data-toggle="tooltip" data-placement="bottom" data-original-title="指针">指针</button>
                                    <button id="pen" type="button" value="1" class="btn" data-toggle="popover" data-trigger="click" data-placement="bottom" data-html="true" data-title="调色板" data-container="#popoverContainer"><i class="icon-pencil"></i></button>
                                    <div id="colorPanel" style="display:none;">
                                        <div>
                                            <p>颜色</p>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button class="btn" onclick="toolkit.changeColor('white');"></button>
                                                <button class="btn btn-inverse" onclick="toolkit.changeColor('black');"></button>
                                                <button class="btn btn-danger" onclick="toolkit.changeColor('red');"></button>
                                                <button class="btn btn-warning" onclick="toolkit.changeColor('yellow');"></button>
                                                <button class="btn btn-success" onclick="toolkit.changeColor('green');"></button>
                                                <button class="btn btn-primary" onclick="toolkit.changeColor('blue');"></button>
                                            </div>
                                            <p>粗细:</p>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button class="btn" onclick="toolkit.changeWidth(1)">1</button>
                                                <button class="btn" onclick="toolkit.changeWidth(2)">2</button>
                                                <button class="btn" onclick="toolkit.changeWidth(5)">5</button>
                                                <button class="btn" onclick="toolkit.changeWidth(15)">15</button>
                                                <button class="btn" onclick="toolkit.changeWidth(25)">25</button>
                                                <button class="btn" onclick="toolkit.changeWidth(50)">50</button>
                                            </div>
                                        </div>
                                    </div>
                                    <button id="eraser" type="button" value="2" class="btn" data-toggle="tooltip" data-placement="bottom" data-original-title="橡皮"><i class="icon-hdd"></i></button>
                                </div>
                                <div id="popoverContainer">
                                </div>
                            </li>
                            <li><a id="prePage" data-toggle="tooltip" data-placement="bottom" data-original-title="上一页"><i class="icon-arrow-left icon-white"></i></a></li>
                            <li><a id="nextPage" data-toggle="tooltip" data-placement="bottom" data-original-title="下一页"><i class="icon-arrow-right icon-white" ></i></a></li>  
                            <div class="modal hide fade" id="gotoModal">
                                <div class="modal-header">
                                    <button type="button" id="gotoClose" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h3>跳转页面</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="control-group input-append" id="gotoGroup">
                                        <input class="span2" id="gotoInput" type="text">
                                        <button class="btn" type="button" id="gotoSubmit">跳转</button>
                                        <label class="control-label" id="gotoError"></label>
                                    </div>
                                </div>
                            </div>
                            <li><a id="gotoPage" data-toggle="modal" data-target="#gotoModal" data-placement="bottom" data-original-title="跳转"><i class="icon-share-alt icon-white" ></i></a></li>
                            <li><a id="scaleUp" data-toggle="tooltip" data-placement="bottom" data-original-title="放大"><i class="icon-zoom-in icon-white"></i></a></li>
                            <li><a id="scaleDown" data-toggle="tooltip" data-placement="bottom" data-original-title="缩小"><i class="icon-zoom-out icon-white"></i></a></li>
                            <li><a class="favor-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="书签"><i class="icon-tags icon-white"></i></a></li>
                            <li><a class="userFile-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="用户文件"><i class="icon-th-list icon-white"></i></a></li>
                            <li><a class="user-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="用户列表"><i class="icon-user icon-white"></i></a></li>
                            <li><a class="file-close-sidebar" data-toggle="tooltip" data-placement="bottom" data-original-title="文件列表"><i class="icon-folder-open icon-white"></i></a></li>
                            <li><a id="uploadFile" data-toggle="tooltip" data-placement="bottom" data-original-title="上传文件"><i class="icon-circle-arrow-up icon-white"></i></a></li>
                            <div class="control-group input-append" style="display: none;">
                                <input class="span2" id="uploadFileInput" type="file" title="上传"/>
                            </div>
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

            <div class="modal hide" id="alertMessage">
                <button type="button" class="close" id="closeAlert" data-dismiss="modal" style="display: none;">&times;</button>
                <div id="alertContext">
                    <session>Loading...</session>
                </div>
            </div>
            <div id="desktop" style="position: absolute;top: 50px;overflow: hidden;">
                <div id="table">
                </div>
            </div>

        </div>
        <!--==============================footer=================================-->
        <!--%@ include file="WEB-INF/jspf/footer.jspf"%>
        -->
    </body>
</html>