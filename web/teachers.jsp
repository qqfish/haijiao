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
      <p/>
			<li>教师姓名 ： 
		  <input type="text" name="username" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
		  <li>教授科目 ：
		  <select name="type" class="wbkuang" style="width:140px;"> 
		   <option value="0"></option>
		   <option value="1">语文</option>
		   <option value="2">数学</option>
		   <option value="3">物理</option>
		   <option value="4">化学</option>
		  </select>
		  </li>
		  <p/>
		  <li>教授年级 ：
		  <select name="type" class="wbkuang" style="width:140px;"> 
		   <option value="0"></option>
		   <option value="1">小学</option>
		   <option value="2">初中</option>
		   <option value="3">高中</option>
		   <option value="4">大学</option>
		  </select>
		  </li>
		  <p/>
		  <p/>
		  搜索
    </article>
    <article class="grid_8">
      <div class="padd-1">
        <h3>搜索结果</h3>
      </div>
      <ul class="list-teachers">
        <li>
          <figure class="box-img"><img src="images/page2-img1.jpg " alt="" /></figure>
          <div class="overflow">
            <h4>AAA</h4>
            <p>AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</p>
            <a href="ta.jsp" class="btn">Read more</a> </div>
          <div class="clear"></div>
        </li>
        <li>
          <figure class="box-img"><img src="images/page2-img2.jpg " alt="" /></figure>
          <div class="overflow">
            <h4>BBB</h4>
            <p>BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB</p>
            <a href="#" class="btn">Read more</a> </div>
          <div class="clear"></div>
        </li>
        <li>
          <figure class="box-img"><img src="images/page2-img3.jpg " alt="" /></figure>
          <div class="overflow">
            <h4>CCC</h4>
            <p>CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC</p>
            <a href="#" class="btn">Read more</a> </div>
          <div class="clear"></div>
        </li>
      </ul>
    </article>
    <div class="clear"></div>
  </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
