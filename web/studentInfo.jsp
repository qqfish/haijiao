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
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen">
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/teachers.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>

<script>	
			jQuery(window).load(function() {								
			jQuery('.flexslider').flexslider({
				animation: "fade",			
				slideshow: true,			
				slideshowSpeed: 7000,
				animationDuration: 600,
				prevText: "",
				nextText: "",
				controlNav: false		
			})	  
			
					
      });
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
<%@ include file="WEB-INF/jspf/header.jspf"%>
<!--==============================content=================================-->
<section id="content" style="height:1400px">
    <div id="sideInfo" style="height: 600px; width: 250px;margin: 0px 0px 0px 50px ;float: left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <figure id="personImage" class="figureBar" style="height: 230px; width:230px;">
              <img src="images/page2-img2.jpg" style="height: 230px;width: 230px;"/>
        </figure>
        <a class="label" href="register.jsp" style="height: 25px;margin:10px 0px 0px 10px;">修改资料</a>
        <div id="teacherName" style="height:50px;width: auto;float:right;font-size: 36px;color: #000000;margin: 0px 10px 0px 0px;line-height: 50px"><s:property value="user.name"/></div>          
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div id="teacherCourses" style="height: 30px;width:250px;float:left;margin: 0px 0px 10px 10px;">
              <div class="label" style="height: 25px;margin:2px 0px 0px 2px;">年级</div>
        </div>
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div style="margin:0px 0px 0px 20px ">这个人很懒 什么都没留下。</div>
        <hr style="height: 1px;width: 230px;margin:10px auto"/>
        <div style="margin:40px 0px 0px 40px ">您近期还有7堂课</div>
        <a href="teachers.jsp" class="login-btn" style="font-size: 20px; margin: 40px 0px 0px 40px;padding: 10px 40px;float: left">我要上课</a>
    </div>
    <div id="topbar" style="height:100px;width: 900px;margin: 0px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
    </div>
    <div id="middlebar" style="height:300px;width: 900px;margin: 20px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <div id="leftbar" style="height:300px;width:600px;float:left;margin:0px 0px 0px 0px">
            课程表
        </div>
        <hr style="height: 300px;width: 1px;float:left;margin:0px 0px 0px 20px"/>
        <div id="rightbar" style="height:260px;width:200px;float:left;margin:20px 0px 0px 20px">
            
        </div>
    </div>
    <div id="infobar" style="height:250px;width: 900px;margin: 20px 0px 0px 20px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
        <div style="font-size: 36px;margin:20px 0px 0px 40px">我的老师</div>
        <hr style="height: 1px;width: 860px;margin:10px auto"/>
        <div class="box-slider" style="margin: 0px 0px 0px -60px">
		<div class="flexslider">
		  <ul class="slides">
                     <li><a href="teacherInfo.jsp" id="resultBar" class="result-bar" style="width:300px;margin:0px 0px 0px 50px;background-color: gainsboro;">
                      <figure id="personImage" class="figureBar">
                          <img src="images/page2-img1.jpg" style="height: 150px;width: 150px;"/>
                      </figure>
                      <div id="teacherName" style="height:50px;width: 100px;float:left;font-size: 36px;color: #000000;margin: 10px 0px 0px 15px;line-height: 50px">AAA</div>
                      </a>
                         <a href="teacherInfo.jsp" id="resultBar" class="result-bar" style="width:300px;margin:0px 0px 0px 50px;background-color: gainsboro;">
                      <figure id="personImage" class="figureBar">
                          <img src="images/page2-img1.jpg" style="height: 150px;width: 150px;"/>
                      </figure>
                      <div id="teacherName" style="height:50px;width: 100px;float:left;font-size: 36px;color: #000000;margin: 10px 0px 0px 15px;line-height: 50px">AAA</div>
                      </a>
                     </li>
		<li> <a href="teacherInfo.jsp" id="resultBar" class="result-bar" style="width:300px;margin:0px 0px 0px 50px;background-color: gainsboro;">
                      <figure id="personImage" class="figureBar">
                          <img src="images/page2-img1.jpg" style="height: 150px;width: 150px;"/>
                      </figure>
                      <div id="teacherName" style="height:50px;width: 100px;float:left;font-size: 36px;color: #000000;margin: 10px 0px 0px 15px;line-height: 50px">AAA</div>
                      </a></li>
		  </ul>
		</div>
	</div>
    </div>
    <div id="billbar" style="height:300px;width: 900px;margin: 20px 0px 0px 320px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
         <div style="font-size: 36px;margin:20px 0px 0px 40px">我的账单</div>
        <hr style="height: 1px;width: 860px;margin:10px auto"/>
    </div>
    <div id="commentbar" style="height:300px;width: 900px;margin: 20px 0px 0px 320px;float:left;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
         <div style="font-size: 36px;margin:20px 0px 0px 40px">评论</div>
        <hr style="height: 1px;width: 860px;margin:10px auto"/>
    </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
