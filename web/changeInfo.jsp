<%-- 
    Document   : changeInfo
    Created on : 2013-4-10, 16:19:38
    Author     : bx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>海角教育</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/datepicker.css">
<!--js-->
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
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
<link rel="stylesheet" href="css/tooltik.css">
<div class="container">
    <div class="row">
        <div class="span3">
            <ul class="nav nav-list bs-docs-sidenav">
                <li class="active"><a href="#1" data-toggle="tab">修改资料<i class="icon-chevron-right pull-right"></i></a></li>
                <li><a href="#2" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                <li><a href="#3" data-toggle="tab">修改头像<i class="icon-chevron-right pull-right"></i></a></li>
                <li><a href="#4" data-toggle="tab">高级设置<i class="icon-chevron-right pull-right"></i></a></li>
           </ul>
        </div>
        <div class="span9">
            <div class="tab-content">
                <div class="tab-pane fade active in" id='1'>
                    <s:form action="changeInfo.action">
                        <s:if test="#session.userType == 'teacher'">
                            <h3>老师您好，修改你的详细信息吧^ ^</h3>
                            <hr/>
                            <dl>
                                <dt>姓名</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="name" placeholder="请输入您的大名" value="%{#session.teacher.name}" autofocus="autofocus" /></dd>
                                <dt>性别</dt>
                                <dd><s:radio list="{'男', '女'}" name="sex" value="%{#session.teacher.sex}"/></dd>
                                <dt>生日</dt>
                                <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{#session.teacher.birthday}"/></dd>
                                <dt>省份</dt>
                                <dd><s:select cssClass="span4" headerKey="" headerValue="请选择你出生的省份" name="province" value="%{#session.teacher.province}" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/></dd>
                                <dt>大学</dt>
                                <dd> <s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的大学" value="%{#session.teacher.school}"/></dd>
                                <dt>手机</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{#session.teacher.tel}"/></dd>                                
                                <br/>
                                <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="teacherChange"/></dd>
                            </dl>
                        </s:if>
                        <s:if test="#session.userType == 'student'">
                            <h3>同学您好，修改你的详细信息吧^ ^</h3>
                            <hr/>
                            <dl>
                                <dt>姓名</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="name" placeholder="请输入您的大名" value="%{#session.student.name}"  autofocus="autofocus"/></dd>
                                <dt>性别</dt>
                                <dd><s:radio list="{'男', '女'}" name="sex" value="%{#session.student.sex}"/></dd>
                                <dt>生日</dt>
                                <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{#session.student.birthday}"/></dd>
                                <dt>省份</dt>
                                <dd><s:select cssClass="span4" headerKey="" headerValue="请选择你出生的省份" name="province" value="%{#session.student.province}" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/></dd>
                                <dt>大学</dt>
                                <dd> <s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的大学" value="%{#session.student.school}"/></dd>
                                <dt>手机</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{#session.student.tel}"/></dd>   
                                <dd><s:radio list="{'父母的手机', '我的手机'}" name="telType" value="%{#session.student.telType}"/></dd>
                                <br/>
                                <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="studentChange"/></dd>
                            </dl>
                        </s:if>
                    </s:form>
                </div>
                <div class="tab-pane fade"  id='2'>
                    <s:form action="changeInfo.action">
                        <h3>修改密码</h3>
                        <hr/>
                        <dl>
                            <dt>旧密码</dt>
                            <dd><s:textfield cssClass="span4" type="text" name="oldpwd" placeholder="请输入旧密码" autofocus="autofocus"/></dd>
                            <dt>新密码</dt>
                            <dd><s:textfield cssClass="span4" type="text" name="newpwd" placeholder="请输入新密码" autofocus="autofocus"/></dd>
                            <dt>确认密码</dt>
                            <dd><s:textfield cssClass="span4" type="text" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus"/></dd>
                            <br/>
                            <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="changePassword"/></dd>
                        </dl>
                    </s:form>
                </div>
                <div class="tab-pane fade"  id='3'>
                    <s:if test="#session.userType=='student'">
                        <img src="<s:property value="#session.student.picUrl"/>" style="height: 230px;width: 230px;"/>
                    </s:if>
                    <s:if test="#session.userType=='teacher'">
                        <img src="<s:property value="#session.teacher.picUrl"/>" style="height: 230px;width: 230px;"/>
                    </s:if>
                </div>
                <div class="tab-pane fade"  id='4'>
                    <s:form action="changeIntro.action">
                        <s:if test="#session.userType == 'teacher'">
                            <s:textarea name="intro" value="%{#session.teacher.intro}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                        </s:if>
                        <s:if test="#session.userType == 'student'">
                            <s:textarea name="intro" value="%{#session.student.intro}" autofocus="autofocus" style="margin:10px 0px 0px 60px;height: 30px;width: 300px;font-size: 20px;"/><br/>
                        </s:if>
                        <s:submit cssClass="login-btn" style="font-size: 20px; margin: 40px 0px 0px 0px;" value="提交"/>
                    </s:form>
                </div>
            </div>
        </div>
    </div>      
  </div>
</div>
<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
