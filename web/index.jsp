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
<link rel="stylesheet" href="bootstrap/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

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
        <div class="login-bar" style="" >
            <!--<div id ="dispalypanel" style="width:600px;height: 400px;"><img src="images/1.jpg" width=600 height=400></div>-->
            <div style="height:400px;width:694px;float:left;overflow: hidden;"><img alt="" src="images/1.jpg" width=700 height=400></div>
            <div class="login-panel" style="border:1px solid #CCC;">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#reg-panel" data-toggle="tab" style="height: 50px;width:172px;line-height: 50px;font-size: 32px;border-radius: 0px;border-left: 0px">注册</a></li>
                    <li><a href="#log-panel" data-toggle="tab" style="height: 50px;width:173px;line-height: 50px;font-size: 32px;border-radius: 0px;border-right: 0px">登录</a></li>
                  </ul>  
                <div class="tab-content">
                <div class="tab-pane fade active in" id='reg-panel'>
                    <s:form action="register.action">
                        <s:textfield name="email"  placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;" ></s:textfield>
                        <s:password name="password1"  placeholder="请输入密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                        <s:password name="password2" placeholder="请重复密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                       <a id="reg-btn" data-toggle="modal" data-target="#choosemodal" class="login-btn" style="font-size: 20px; margin: 20px 70px 0px 60px;border:0px">注册</a>
                       <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
            <div class="modal-header" style="height: 50px">
                <a class="close" data-dismiss="modal">×</a>
                <h3 style="margin-top:-20px">选择您的身份</h3>
            </div>       
            <div class="modal-body">
                <div id="leftbar" style="height:250px;width:250px;float:left;margin:0px 0px 0px 30px;border:1px solid #CCC">        
                    <s:submit value="老师" method="teacher"></s:submit>     
                </div>     
                <div id="rightbar" style="height:250px;width:250px;float:left;margin:0px 0px 0px 40px;border:1px solid #CCC">      
                    <s:submit value="学生" method="student"></s:submit>
                </div> 
            </div>        
        </div>
                    </s:form>
                </div>
                <div class="tab-pane fade"  id='log-panel'>
                    <s:form action="login.action">
                        <s:textfield name="email" placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:textfield>
                        <s:password name="password" placeholder="请输入密码" autofocus="autofocus" style="margin: 20px 0px 0px 40px;height: 30px;width: 300px;font-size: 20px;"></s:password>
                        <s:submit cssClass="login-btn" style="font-size: 20px; margin: 80px 70px 0px 60px;border:0px" value="登录"></s:submit>
                    </s:form>
                </div>
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
