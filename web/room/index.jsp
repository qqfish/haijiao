<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<!DOCTYPE html>
<html lang="en">
    <base href="<%=basePath%>">
    <head>
        <title>Class</title>
        <meta charset="utf-8">
        <script src="room/js/lib/jquery-1.9.1.js"></script>

        <!--<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>-->
        <script src="room/js/lib/jquery-ui-1.10.1.custom.min.js"></script>
        <script src="room/js/lib/jQueryRotate.2.2.js"></script>
        <script src="room/js/lib/kinetic.js"></script>
        <script src="room/js/message.js"></script>
        <script src="room/js/adapter.js"></script>
        <script src="room/js/screen.js"></script>
        <script src="room/js/timer.js"></script>
        <script src="room/js/toolkit.js"></script>
        <script src="room/js/textChat.js"></script>
        <script src="room/js/socket.js"></script>
        <script src="room/js/media.js"></script>
        <script src="room/js/table.js"></script>
        <script src="room/js/fileManager.js"></script>
        <script src="room/js/main.js"></script>
        <script src="room/js/tooltip.js"></script>
        <!--css-->
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="room/css/room.css" type="text/css" media="screen">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
        <!--<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>-->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css" type="text/css" media="screen">
        <script src="js/bootstrap.file-input.js"></script>
        <!--js-->
        <!--<script type="text/javascript" src="../js/superfish.js"></script>-->
        <script type="text/javascript" src="room/js/lib/bootstrap.js"></script>

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
    <body unselectable="off" style="-moz-user-select:text;-webkit-user-select:text;"
          onload="init('<s:property value='stuEmail' default='null' />', '<s:property value='teaEmail' default='null' />', '<s:property value='email' default='null' />', '<s:property value='user.fileGroupsToJson()' default='null' />');">
        <img id="emptyImg" src="" style="display: none;"/>
        <script type="text/javascript">
            var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
            document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F71a6bb265c2824dde5414a59737f563b' type='text/javascript'%3E%3C/script%3E"));
            $(document).ready(function(){
                $("a").first().css("display","none");
            });
        </script>
        <s:if test="user.loginNum < 3">
            <div class="modal fade" id="beginInfo" style="width:700px;">
                <div class="modal-header">
                    <h3>视频开启步骤</h3>
                </div>
                    <image src="room/image/help.png"  />
                <div class="modal-footer">
                    <button class="btn btn-primary" data-dismiss="modal">知道了</button>
                </div>
            </div>
            <script>
                $("#beginInfo").modal();
            </script>
        </s:if>
        <!--==============================header=================================-->
        <!--<!--%@ include file="WEB-INF/jspf/header.jspf"%>-->
        <div class="line-top"></div>
        <!--==============================content=================================-->
        <div id="content"> 
            <div id="infoMessage" class="alert span5" style="display: none;z-index: 4;width:200px;position:absolute;top:0px;left:35%;">
                <button id="infoMessageClose" type="button" class="close" onclick="$('#infoMessage').fadeOut();">&times;</button>
                <p id="infoMessageContext"></p>
            </div>
            <a id="showAlert" data-toggle="modal" data-target="#alertMessage" class="btn btn-danger btn-large" style="display: none;"/>
            <div id="tools" class="navbar navbar-inverse" style="margin-bottom: 0px;">
                <div  class="navbar-inner">
                    <div style="width:880px;margin-left:auto;margin-right:auto">
                        <ul class="nav" style="width: 1080px;">
                            <a class="brand" href="index.action">Haijiao<span style="color: #008db8;">EDU</span></a>
                            <li><div class="well-mini"><span id="timerPanel">00:00:00</span></div></li>
                            <li>
                                <div class="btn-group">
                                    <a class="btn btn-link dropdown-toggle tooltipButton" data-toggle="dropdown" data-placement="bottom" data-original-title="书签"><i class="icon-tags icon-white"></i></a>
                                    <ul class="dropdown-menu" id="bookmark"></ul>
                                </div>
                            </li>
                            <li>
                                <div class="btn-group">
                                    <a class="btn btn-link dropdown-toggle tooltipButton" data-toggle="dropdown" data-placement="bottom" data-original-title="用户文件"><i class="icon-th-list icon-white"></i></a>
                                    <ul class="dropdown-menu" id="userFile"></ul>
                                </div>
                            </li>
                            <li>
                                <div class="btn-group">
                                    <a class="btn btn-link dropdown-toggle tooltipButton" data-toggle="dropdown" data-placement="bottom" data-original-title="用户列表"><i class="icon-user icon-white"></i></a>
                                    <ul class="dropdown-menu" id="users">
                                        <li><a tabindex="-1" id="videoButton">视频开启</a></li>
                                        <li><a tabindex="-1" id="audioButton">音频开启</a></li>
                                        <li class="divider"></li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <div class="btn-group">
                                    <a class="btn btn-link dropdown-toggle tooltipButton" data-toggle="dropdown" data-placement="bottom" data-original-title="文件列表"><i class="icon-folder-open icon-white"></i></a>
                                    <ul class="dropdown-menu" id="roomFile">
                                        <li class="divider"></li>
                                        <li><a tabindex="-1" id="uploadFile">上传文件</a></li>
                                        <li><a tabindex="-1" onclick="file.downloadFile()">下载文件</a></li>
                                    </ul>
                                </div>
                            </li>
                            <div class="control-group input-append" style="display: none;">
                                <input class="span2" id="uploadFileInput" type="file" title="上传"/>
                            </div>
                            <!--
                            <li><a class="close-sidebar tooltipButton" data-toggle="tooltip" data-placement="bottom" data-original-title="关闭侧边栏"><i class="icon-remove icon-white"></i></a></li>
                            -->
                            <li>
                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <button class="btn active tooltipButton" id="pointer" type="button" value="0" data-toggle="tooltip" data-placement="bottom" data-original-title="指针"><i class="icon-hand-up"></i></button>
                                    <button class="btn tooltipButton" id="text" type="button" value="2" data-toggle="tooltip" data-placement="bottom" data-original-title="文字"><i class="icon-font"></i></button>
                                    <button class="btn tooltipButton" id="eraser" type="button" value="2" data-toggle="tooltip" data-placement="bottom" data-original-title="橡皮"><i class="icon-hdd"></i></button>
                                    <button class="btn tooltipButton" id="pen" type="button" value="1" data-toggle="tooltip" data-placement="bottom" data-original-title="画笔"><i class="icon-pencil"></i></button>
                                    <button class="btn dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                    </button>
                                    <div class="dropdown-menu" role="menu"  id="colorPanel" style="padding-top: 0px;">
                                        <div class="popover-title"><p style="margin-bottom: 0px;padding-bottom: 2px;">调色板</p></div>
                                        <div style="margin: 12px;">
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
                                            <p>字体颜色:</p>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button class="btn" style="color: white;" onclick="toolkit.setTextColor('white')">A</button>
                                                <button class="btn" style="color: black;" onclick="toolkit.setTextColor('black')">A</button>
                                                <button class="btn" style="color: red;" onclick="toolkit.setTextColor('red')">A</button>
                                                <button class="btn" style="color: yellow;" onclick="toolkit.setTextColor('yellow')">A</button>
                                                <button class="btn" style="color: green;" onclick="toolkit.setTextColor('green')">A</button>
                                                <button class="btn" style="color: blue;" onclick="toolkit.setTextColor('blue')">A</button>
                                            </div>
                                            <p>字体大小:</p>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button class="btn" style="font-size: 10px;" onclick="toolkit.setTextSize(10)">A</button>
                                                <button class="btn" style="font-size: 20px;" onclick="toolkit.setTextSize(20)">A</button>
                                                <button class="btn" style="font-size: 30px;" onclick="toolkit.setTextSize(30)">A</button>
                                                <button class="btn" style="font-size: 50px;" onclick="toolkit.setTextSize(50)">A</button>
                                                <button class="btn" style="font-size: 100px;" onclick="toolkit.setTextSize(100)">A</button>
                                                <button class="btn" style="font-size: 150px;" onclick="toolkit.setTextSize(150)">A</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="popoverContainer">
                                </div>
                            </li>
                            <li><a class="tooltipButton" id="prePage" data-toggle="tooltip" data-placement="bottom" data-original-title="上一页"><i class="icon-arrow-left icon-white"></i></a></li>
                            <li>
                                <div class="well-mini">
                                    <span id="currentPage"></span> / <span id="totalPage"></span>
                                </div>
                            </li>
                            <li><a class="tooltipButton" id="nextPage" data-toggle="tooltip" data-placement="bottom" data-original-title="下一页"><i class="icon-arrow-right icon-white" ></i></a></li>  
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
                            <div class="modal hide fade" id="exitAlert">
                                <div class="modal-header">
                                    <h3>确认退出房间？</h3>
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-success" data-toogle="tooltip" href="index.action">确认</a>
                                    <button class="btn" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <li><a class="tooltipButton" id="gotoPage" data-toggle="modal" data-target="#gotoModal" data-placement="bottom" data-original-title="跳转"><i class="icon-share-alt icon-white" ></i></a></li>
                            <li><a class="tooltipButton" id="scaleUp" data-toggle="tooltip" data-placement="bottom" data-original-title="放大"><i class="icon-zoom-in icon-white"></i></a></li>
                            <li><a class="tooltipButton" id="scaleDown" data-toggle="tooltip" data-placement="bottom" data-original-title="缩小"><i class="icon-zoom-out icon-white"></i></a></li>
                            <li><a class="tooltipButton" data-toggle="modal" data-target="#exitAlert" data-placement="bottom" data-original-title="退出房间"><i class="icon-remove icon-white"></i></a></li>
                            <li><a class="tooltipButton" href="contact.jsp" target="_blank" data-toggle="tooltip" data-placement="bottom" data-original-title="帮助反馈"><i class="icon-question-sign icon-white"></i></a></li>
                        </ul>
                    </div> 
                </div>
                <div class="well span3" id ="side" style="width:270px;margin:0px;float:bottom;padding:0;">
                    <div style="width:240px;float: left">
                        <div id="sideVideoArea" style="height:380px;width:240px;overflow-y: visible;"></div>
                        <div style="float: bottom;">
                            <div class="span2 input-xlarge uneditable-input" id="charShowArea" style="width:228px;height: 250px;white-space:normal; overflow-y: auto;cursor: default; "></div>
                            <p style="margin: 0px;"/>
                            <div class="input-append">
                                <input class="span2" id="chatInput" type="text">
                                <button class="btn" type="button" id="chatSend">发送</button>
                            </div>
                        </div>
                    </div>
                    <div class="ctlbar" id="sideBarButton" style="width:25px;height:100%;background-color: white;float: right;opacity: 0.5;"><img id="ctlbar" style="height:100%;width:100%" src="images/ctlbar2.png"></div>
                </div>
            </div>

            <div class="modal hide" id="alertMessage">
                <div id="alertContext">
                    <session>Loading...</session>
                </div>
                <button type="button" class="btn btn-success pull-right" id="closeAlert" data-dismiss="modal">知道了</button>
            </div>

            <s:form action="downloadPDF.action" id="downlaodForm" style="display: none;">
                <s:textfield name="path" id="downloadPath"></s:textfield>
                <s:submit id="downlaodButton"></s:submit>
            </s:form>
            <div id="desktop" style="position: absolute;overflow: hidden;">
                <div id="table">
                </div>
            </div>

        </div>
        <!--==============================footer=================================-->
        <!--%@ include file="WEB-INF/jspf/footer.jspf"%>
        -->
    </body>
</html>