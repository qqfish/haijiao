<%-- 
    Document   : changePassword
    Created on : 2013-4-10, 17:06:55
    Author     : Jerry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>海角教育</title>
<meta charset="utf-8">
<!--css-->
<link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/grid.css" type="text/css" media="screen">
<link rel="icon" href="../images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="../css/flexslider.css" type="text/css" media="screen">
<!--js-->
<script type="text/javascript" src="../js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="../js/superfish.js"></script>
<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="../js/reg.js"></script>



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
<jsp:include page="changeInfoList.jsp"/>
<section id="content">
<div class="ic"></div>
  <div id="regpanel" style="height:600px; width: 800px;margin:auto;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
      <s:form action="changeInfo.action">
            <s:textfield type="text" name="oldpwd" placeholder="请输入旧密码" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:textfield type="text" name="newpwd" placeholder="请输入新密码" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:textfield type="text" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
            <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交" method="changePassword"/>
        </s:form>
  </div>
</section>
<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
