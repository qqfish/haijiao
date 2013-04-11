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
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/datepicker.css">
<!--js-->
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>

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
<section id="content">
<div class="ic"></div>
  <div id="regpanel" class="container" style="width:800px;border: 1px solid #CCC;box-shadow:3px 3px 3px #c5c2a9;-moz-box-shadow: 3px 3px 3px #c5c2a9;-webkit-box-shadow:3px 3px 3px #c5c2a9;background-color: white;">
      <s:form action="register.action">
            <s:if test="#session.userType == 'teacher'">
                <div style="font-size: 36px;margin:20px 0px 0px 40px">老师您好，请填写您的详细信息吧^ ^</div>
            </s:if>
            <s:if test="#session.userType == 'student'">
                <div style="font-size: 36px;margin:20px 0px 0px 40px">同学您好，请填写您的详细信息吧^ ^</div>
            </s:if>
            <hr style="height: 1px;width: auto;margin:10px auto"/>
            <s:textfield type="text" name="name" placeholder="请输入您的大名" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:textfield type="text" name="sex" placeholder="请输入您的性别" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:textfield type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:if test="#session.userType == 'teacher'">
                <s:textfield type="text" name="school" placeholder="请输入您就读的大学" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:textfield type="text" name="tel" placeholder="请输入您的手机号" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交"/>
            </s:if>
            <s:if test="#session.userType == 'student'">
                <s:textfield type="text" name="school" placeholder="请输入您在读的学校" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:textfield type="text" name="grade" placeholder="请输入您在读的年级" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:textfield type="text" name="tel" placeholder="请输入您或您父母的手机号" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交"/>
            </s:if>
        </s:form>
  </div>
</section>
<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
