<%-- 
    Document   : studentIndex
    Created on : 2013-3-26, 22:11:27
    Author     : Bx Yang
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Teachers</title>
<meta charset="utf-8">
<!--css-->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/teachers.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/schedule.js"></script>
<script type="text/javascript" src="js/index.js"></script>

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
<%@ include file="WEB-INF/jspf/header.jspf"%>
<!--==============================content=================================-->
<section id="content" style="height:1200px">
    <div id="sideInfo" style="height: 600px; width: 250px;margin: 0px 0px 0px 50px ;float: left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <figure id="personImage" class="figureBar" style="height: 230px; width:230px;">
              <img src="<s:property value="tea.picUrl"/>" style="height: 230px;width: 230px;"/>
        </figure>
        <div id="teacherName" style="height:50px;width: auto;float:right;font-size: 36px;color: #000000;margin: 0px 10px 0px 0px;line-height: 50px"><s:property value="tea.name"/></div>          
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div id="teacherCourses" style="height: 30px;width:250px;float:left;margin: 0px 0px 10px 10px;">
              <s:iterator value="tea.lessons" status="st">
                  <div class="label-1" style="height: 25px;margin:2px 0px 0px 2px"><s:property value="name"/></div>
              </s:iterator>
        </div>
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div style="margin:0px 0px 0px 20px "><s:property value="tea.intro"/></div>
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div style="margin:40px 0px 0px 40px ">已有 30 人选择了TA</div>
        <a class="login-btn" data-toggle="modal" data-target="#choosemodal" style="font-size: 20px; margin: 40px 0px 0px 40px;padding: 10px 40px;float: left">我要预约</a>
    </div>
    <div id="topbar" style="height:100px;width: 900px;margin: 0px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
    </div>
    <div id="middlebar" style="height:300px;width: 900px;margin: 20px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <div id="leftbar" style="height:300px;width:500px;float:left;margin:0px 0px 0px 0px">
            
        </div>
        <hr style="height: 300px;width: 1px;float:left;margin:0px 0px 0px 20px"/>
        <div id="rightbar" style="height:260px;width:300px;float:left;margin:20px 0px 0px 20px">
            <div style="height:60px;width:300px;margin: 20px 0px 0px 20px;line-height: 60px;border-bottom: 1px solid #CCC">预约数<div class="label-1" style="height: 25px;float: right;margin:15px 0px 0px 0px;padding:0px 10px;line-height: 25px;"><s:property value="tea.reserveNum"/></div></div>
            <div style="height:60px;width:300px;margin: 0px 0px 0px 20px;line-height: 60px;border-bottom: 1px solid #CCC">学校<div class="label-1" style="height: 25px;float: right;margin:15px 0px 0px 0px;padding:0px 10px;line-height: 25px"><s:property value="tea.school"/></div></div>
            <div style="height:60px;width:300px;margin: 0px 0px 0px 20px;line-height: 60px;border-bottom: 1px solid #CCC">收费<div class="label-1" style="height: 25px;float: right;margin:15px 0px 0px 0px;padding:0px 10px;line-height: 25px"><s:property value="tea.wagePerhour"/></div></div>
            <div style="height:60px;width:300px;margin: 0px 0px 0px 20px;line-height: 60px">评分<div class="label-1" style="height: 25px;float: right;margin:15px 0px 0px 0px;padding:0px 10px;line-height: 25px"><s:property value="tea.score"/>/10.0</div></div>
        </div>
    </div>
    <div id="infobar" style="height:300px;width: 900px;margin: 20px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <div style="font-size: 36px;margin:20px 0px 0px 40px">详细资料</div>
        <hr style="height: 1px;width: 860px;margin:10px auto"/>
        <div id="leftbar" style="height:200px;width:400px;float:left;margin:0px 0px 0px 20px">
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">大学<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px"><s:property value="tea.school"/></div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">毕业年份<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">毕不了业</div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">专业<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">软件工程</div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">GPA<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">0/4.0</div></div>
        </div>
        <hr style="height: 200px;width: 1px;float:left;margin:0px 0px 0px 20px"/>
        <div id="rightbar" style="height:200px;width:400px;float:left;margin:0px 0px 0px 20px">
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">喜欢的电影<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">葫芦娃</div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">喜欢的书籍<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">葫芦娃连环画</div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">喜欢的音乐<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">葫芦娃主题曲</div></div>
            <div style="height:50px;width:300px;margin: 0px 0px 0px 20px;line-height: 50px">喜欢的运动<div class="label-1" style="height: 25px;float: right;margin:10px 0px 0px 0px;padding:0px 10px;line-height: 25px">**</div></div>
        </div>
    </div>
    <div id="bottombar" style="height:300px;width: 900px;margin: 20px 0px 0px 320px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <div style="font-size: 36px;margin:20px 0px 0px 40px">评论</div>
        <hr style="height: 1px;width: 860px;margin:10px auto"/>
        <s:iterator value="tea.commentsToThis" status="st">
            <div style="font-size: 36px;margin:20px 0px 0px 40px"><s:property value="tea.commentsToThis.content"/></div>
            <hr style="height: 1px;width: 860px;margin:10px auto"/>
        </s:iterator>
    </div>
            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                    <div class="modal-header" style="height: 50px">
                      <a class="close" data-dismiss="modal">×</a>
                      <h3 style="margin-top:-20px">请选择课程时间</h3>
                    </div>
                    <div class="modal-body">
                        <div class="schedule_panel">
                            <table class="schedule_table" style="margin-left:10px" width="100%" border="0" style="z-index: 1">
                            <tbody>
                            <tr>
                                    <th ></th>
                                    <th>Mon.</th>
                                    <th>Tue.</th>
                                    <th>Wed.</th>
                                    <th>Thu.</th>
                                    <th>Fri.</th>
                                    <th style="width:24px">Sat.</th>
                                    <th style="width:24px">Sun.</th>
                            </tr>
                            <tr class="class_1">
                                    <th rowspan="2">8:00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_2">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_3">
                                    <th rowspan="2">10:00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_4">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_5">
                                    <th rowspan="2">中 午</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_6">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_7">
                                    <th rowspan="2">14：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_8">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_9">
                                    <th rowspan="2">16：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_10">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_11">
                                    <th rowspan="3">18：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_12">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            <tr class="class_13">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                            </tr>
                            </tbody>
                    </table>
                    </div>
                    </div>
          </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
