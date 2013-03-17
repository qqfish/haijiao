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
<script type="text/javascript" src="js/teachers.js"></script>

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
    <div id="seacrchBar" style="height: 100px;width: 1200px;margin:auto;background-color:gainsboro;overflow: hidden;">
        <div style="height:99px;width:1200px;float: left;margin:0px 0px 0px 200px;">
            <s:textfield name="search" placeholder="" autofocus="autofocus" style="margin: 30px 0px 0px 100px;height: 30px;width: 600px;font-size: 20px;"></s:textfield>
            <a id="search-btn" href="#" class="label-btn" style="height: 25px;margin:40px 0px 0px 2px">搜索</a> 
        </div>  
        <div id="searchChoice" style="height:99px;width:1200px;margin:0px 0px 0px 300px;float: left">
            <div style="height: 33px;width: 600px;">
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px;background-color: #db3222;color: #fff">年级</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">小学</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">六年级</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">初一</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">初二</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">初三</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">高一</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">高二</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">高三</a>
                
            </div>
            <div style="height: 33px;width: 600px;">
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px;background-color: #db3222;color: #fff">学科</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">语文</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">数学</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">英文</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">物理</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">化学</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">生物</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">地理</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">历史</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">政治</a>                
            </div>
            <div style="height: 33px;width: 600px;">
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px;background-color: #db3222;color: #fff">网络</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">电信</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">网通</a>
                <a href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 2px">教育网</a>
            </div>
        </div> 
            <a id="close-btn" href="#" class="label-btn" style="height: 25px;margin:2px 0px 0px 300px">收起</a>   
    </div>
  <div  id="resultPanel" style="height: 600px;width: 1200px;margin:20px 0px 0px 30px;">
      <a href="#" id="resultBar" class="result-bar">
          <figure id="personImage" style="">
              <img src="images/page2-img1.jpg"/>
          </figure>
      </a>
      <a href="#" id="resultBar" class="result-bar">          
      </a>
      <a href="#" id="resultBar" class="result-bar">          
      </a>
      <a href="#" id="resultBar" class="result-bar">          
      </a>
  </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
