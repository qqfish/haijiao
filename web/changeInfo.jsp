<%-- 
    Document   : changeInfo
    Created on : 2013-4-10, 16:19:38
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
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/datepicker.css">
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
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
<jsp:include page="changeInfoList.jsp"/>
<section id="content">
<div class="ic"></div>
  <div id="regpanel" style="height:600px; width: 800px;margin:auto;border: 1px solid #CCC;box-shadow:4px 4px 4px #c5c2a9;-moz-box-shadow: 4px 4px 4px #c5c2a9;-webkit-box-shadow:4px 4px 4px #c5c2a9;background-color: white;">
      <s:form action="changeInfo.action">
            <s:if test="#session.userType == 'teacher'">
                <div style="font-size: 36px;margin:20px 0px 0px 40px">老师您好，修改你的详细信息吧^ ^</div>
                名字<s:textfield type="text" name="name" placeholder="请输入您的大名" value="%{#session.teacher.name}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                性别<s:radio list="{'男', '女'}" name="sex" value="%{#session.teacher.sex}"/><br/>
                生日<s:textfield type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" autofocus="autofocus" value="%{#session.teacher.birthday}" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                省份<s:select headerKey="" headerValue="请选择你出生的省份" name="province" value="%{#session.teacher.province}" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                大学<s:textfield type="text" name="school" placeholder="请输入您就读的大学" value="%{#session.teacher.school}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                手机<s:textfield type="text" name="tel" placeholder="请输入您的手机号" value="%{#session.teacher.tel}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交" method="teacherChange"/>
            </s:if>
            <s:if test="#session.userType == 'student'">
                <div style="font-size: 36px;margin:20px 0px 0px 40px">同学您好，修改你的详细信息吧^ ^</div>
                名字<s:textfield type="text" name="name" placeholder="请输入您的大名" value="%{#session.student.name}"  autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                性别<s:radio list="{'男', '女'}" name="sex" value="%{#session.student.sex}"/><br/>
                生日<s:textfield type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" autofocus="autofocus" value="%{#session.student.birthday}" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                学校<s:textfield type="text" name="school" placeholder="请输入您就读的学校" value="%{#session.student.school}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                年级<s:select headerKey="" headerValue="请选择你的年级" name="grade" value="%{#session.student.grade}" list="{'一年级', '二年级', '三年级', '四年级', '五年级', '六年级', '初一', '初二', '初三', '高一', '高二', '高三'}" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                手机<s:textfield type="text" name="tel" placeholder="请输入您或您父母的手机号" value="%{#session.student.tel}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                <s:radio list="{'父母的手机', '我的手机'}" name="telType" value="%{#session.student.telType}"/><br/>
                <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交" method="studentChange"/>
            </s:if>
        </s:form>
  </div>
</section>
<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
