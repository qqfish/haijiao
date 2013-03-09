<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Class</title>
<meta charset="utf-8">
<!--css-->
<link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="../css/grid.css" type="text/css" media="screen">
<link rel="icon" href="../images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen">
<!--js-->
<script type="text/javascript" src="../js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="../js/superfish.js"></script>
<script type="text/javascript" src="../js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/haijiao.js"></script>

<script language="javascript">
var ss;
window.onload=function()
{
var w=document.documentElement.clienxtWidth;//可见区域宽度
var h=document.documentElement.clientHeight;//可见区域高度
ss=document.getElementById('side');
//alert(w);

ss.style.height=h-52+"px";
}
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
<!--<!--%@ include file="WEB-INF/jspf/header.jspf"%>-->
  <div class="line-top"></div>
<!--==============================content=================================-->
<section id="content"> 
    
    <div>
    <ul class="sf-menu" style="float: right;">
        <li><a>指针</a></li>
        <li><a>画笔</a></li>
        <li><a>拖拽</a></li>
        <li><a class="favor-close-sidebar">书签</a></li>
        <li><a class="user-close-sidebar">用户</a></li>
        <li><a class="file-close-sidebar">文件</a></li>
        <li><a class="close-sidebar" >关闭侧边栏</a></li>
    </ul>
    </div> 
    <hr style="width:1366px;height:2px;margin:0px 0px 0px 0px;float:right"></hr>
    <div class="side-bar" id ="side" style="float: bottom;">   
        <div id="favor-content" style="display:none ">收藏</div>
        <div id="user-content" style="display:none ">用户</div>
        <div id="file-content" style="display: none">文件</div>        
    </div>
    
</section>
<!--==============================footer=================================-->
<!--%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>