<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Register</title>
<meta charset="utf-8">
<!--css-->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen">
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>



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
  <div class="main">
    <article class="side-bar">
      <p/>
			<li>用户账号 ： 
		  <input type="text" name="username" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
			<li>用户密码 ：
		  <input type="password" name="password" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
		  <li>确认密码 ：
		  <input type="password" name="password_confirm" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
		  <li>用户类型 ：
		  <select name="type" class="wbkuang" style="width:140px;"> 
		   <option value="0"></option>
		   <option value="1">学生</option>
		   <option value="2">学生家长</option>
		   <option value="3">大学生家教</option>
		   <option value="4">职业教师</option>
		  </select>
		  </li>
		  <p/>
		  <li>真实姓名 ：
		  <input type="text" name="realname" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
		  <li>联系方式 ：
		  <input type="text" name="contact" class="wbkuang" style="width:120px;" /> 
		  </li>
		  <p/>
		  <p/>
		  立即注册
    </article>
    <div class="clear"></div>
  </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
