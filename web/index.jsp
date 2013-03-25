<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>海角教育</title>
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
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/index.js"></script>

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
<section id="content">
	
	<!--<div class="box-slider" >
		<div class="flexslider">
		  <ul class="slides">
			<li> <img alt="" src="images/1.jpg" width=940 height=448></li>
			<li> <img alt="" src="images/2.jpg" width=940 height=448></li>
		  </ul>
		</div>
	  </div>-->
        <div class="login-bar" style="border:2px solid #CCC;" >
            <!--<div id ="dispalypanel" style="width:600px;height: 400px;"><img src="images/1.jpg" width=600 height=400></div>-->
            <div style="height:400px;width:694px;float:left;overflow: hidden;"><img alt="" src="images/1.jpg" width=700 height=400></div>
            <div class="login-panel" style="border:1px solid #CCC;margin: 0px 0px 0px 0px;">
                <div class='login-func'>
                    <div id ='regText'class='login-reg' style="font-size: 32px;color: black;opacity: 1;border-right:1px solid #CCC;">用户注册</div>
                    <div id='logText' class='login-log' style="font-size: 32px;opacity: 0.5;border-left:1px solid #CCC;border-bottom:1px solid #CCC;background-color: #e8e7de   ">用户登录</div>
                </div>                
                <div id='reg-panel'>
                <s:form action="register.action"> <br/>
                    <s:textfield name="account"  placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;" ></s:textfield>
                    <s:password name="password1"  placeholder="请输入密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                    <s:password name="password2" placeholder="请重复密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                    <s:submit cssClass="login-btn" style="font-size: 20px; margin: 20px 20px 0px 0px" value="注册"></s:submit>
                </s:form>
                </div>
                <div id='log-panel' style='display:none;'>
                    <s:form action="login.action">
                        <s:textfield name="account" placeholder="请输入邮箱" autofocus="autofocus" style="margin: 50px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:textfield>
                        <s:password name="password" placeholder="请输入密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                        <s:submit cssClass="login-btn" style="font-size: 20px; margin: 20px 70px 0px 60px;background-color:  " value="登录"></s:submit>
                    </s:form>
                </div>
            </div>
        </div>
	  <div class="box-slogan">
		<h3>欢迎来到海角家教！</h3>
		<p>在线面对面的家教体验，为偏远地区学生获得优秀教学资源提供了便利。（详细介绍待定）</p>
	  </div>
</section>
<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
