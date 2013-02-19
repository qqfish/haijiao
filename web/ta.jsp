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
<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>

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
<section id="content"><div class="ic"></div>
  <div class="border-horiz"></div>
  
  <div class="container_12">
	<article class="side-bar">
		<figure class="box-img"><img src="images/page2-img1.jpg " alt="" /></figure>
	</article>
    <article class="grid_8">
      <div class="padd-1">
        <h3>AAA</h3>
      </div>
      <div class="main">
		教师的详细简介。。。。。。。。。
	  </div>
	  <div class="main">
		<h3>教学视频</h3>
		<video width="667" height="375" controls>
		<source src="1.mp4"  type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"' />
		<source src="FILE_NAME.ogv"  type="video/ogg" />
		<object width="667" height="375" type="application/x-shockwave-flash" data="FILE_NAME.swf">
		<param name="movie" value="FILE_NAME.swf" />
		<param name="flashvars" value="controlbar=over&amp;image=PLACEHOLDER_FILE_NAME.jpg&amp;file=1.mp4" />
		<img src="PLACEHOLDER_FILE_NAME.jpg" width="250" height="152" alt="ALT_HERE" title="TITLE_HERE" />
		</object>
		</video>
		</div>
    </article>
    <div class="clear"></div>
  </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
