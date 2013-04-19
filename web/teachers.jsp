<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Teachers</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="js/teachers.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">

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
<div class="container" style="background-color: white;">
    <div class="span9 offset1">
        <h3>搜索结果</h3>
        <div class="accordion" id="choice">
            <div class="accordion-group">
          <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse"  href="#collapseOne">
              搜索筛选
            </a>
          </div>
          <div id="collapseOne" class="accordion-body collapse">
            <div class="accordion-inner">
                <button type="button" class="btn btn-danger">年级</button>
                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                    <button type="button" value="0" class="btn" data-toggle="button">小学</button>
                    <button type="button" value="1" class="btn" data-toggle="button">六年级</button>
                    <button type="button" value="2" class="btn" data-toggle="button">初一</button>
                    <button type="button" value="3" class="btn" data-toggle="button">初二</button>
                    <button type="button" value="4" class="btn" data-toggle="button">初三</button>
                    <button type="button" value="5" class="btn" data-toggle="button">高一</button>
                    <button type="button" value="6" class="btn" data-toggle="button">高二</button>
                    <button type="button" value="7" class="btn" data-toggle="button">高三</button>
                </div>
                <p></p>
                <button type="button" class="btn btn-danger">学科</button>
                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                    <button type="button" value="0" class="btn" data-toggle="button">语文</button>
                    <button type="button" value="1" class="btn" data-toggle="button">数学</button>
                    <button type="button" value="2" class="btn" data-toggle="button">英语</button>
                    <button type="button" value="3" class="btn" data-toggle="button">物理</button>
                    <button type="button" value="4" class="btn" data-toggle="button">化学</button>
                    <button type="button" value="5" class="btn" data-toggle="button">生物</button>
                    <button type="button" value="6" class="btn" data-toggle="button">政治</button>
                    <button type="button" value="7" class="btn" data-toggle="button">历史</button>
                    <button type="button" value="8" class="btn" data-toggle="button">地理</button>
                </div>
                <p></p>
                <button type="button" class="btn btn-danger">网络</button>
                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                    <button type="button" value="0" class="btn" data-toggle="button">电信</button>
                    <button type="button" value="1" class="btn" data-toggle="button">网通</button>
                    <button type="button" value="2" class="btn" data-toggle="button">教育网</button>
                </div>                
            </div>
          </div>
        </div>
        </div>
    </div>
  <div  id="resultPanel" class="span9">
      <s:iterator value="teacherlist" id="list">
          <s:a action="getTeacherInfo.action" id="resultBar" cssClass="result-bar">
              <s:param name="teacherEmail"><s:property value="email"/></s:param>
              <figure id="personImage" class="figureBar">
                  <img src="<s:property value="picUrl"/>" style="height: 150px;width: 150px;"/>
              </figure>
              <div id="teacherName" style="height:50px;width: 350px;float:left;font-size: 36px;color: #000000;margin: 10px 0px 0px 15px;line-height: 50px"><s:property value="name"/></div>
              <div id="teacherScore" class="label-1" style="font-size: 10px; margin: 0px 0px 0px 13px;background-color: transparent;border: 1px solid #db3222;color: #db3222">评分：<s:property value="score"/></div>
              <div id="teacherCourses" style="height: 30px;width:390px;float:left;margin: 0px 0px 0px 10px;">
                  <s:iterator value="lessons" status="st">
                      <div class="label-1" style="height: 25px;margin:2px 0px 0px 2px"><s:property value="name"/></div>
                  </s:iterator>
              </div>
              <div id="teacherIcons" style="height: 30px;width:390px;float:left;margin: 0px 0px 0px 10px;">
                  <div  style="height: 20px;width: 20px;margin:2px 0px 0px 5px;float:left;border:1px solid #000;border-radius: 5px"></div>
                  <div  style="height: 20px;width: 20px;margin:2px 0px 0px 5px;float:left;border:1px solid #000;border-radius: 5px"></div>
                  <div  style="height: 20px;width: 20px;margin:2px 0px 0px 5px;float:left;border:1px solid #000;border-radius: 5px"></div>
              </div>
          </s:a>
      </s:iterator>
  </div>
</div>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
