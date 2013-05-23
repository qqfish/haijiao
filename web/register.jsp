<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="css/validate.css">
        <link rel="stylesheet" href="css/style.css">
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/reg.js"></script>
        <script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>

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
        <div class="container wrapper">
            <div class="container-fluid">
                <div class="span11 module">
                <s:form action="changeInfo.action">
                    <s:if test="#session.userType == 'teacher'">
                        <h2 class="offset2">老师您好，请填写您的详细信息^ ^</h2>
                        <hr/>
                        <dl class="dl-horizontal offset1">
                            <dt>姓名</dt>
                            <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的全名" autofocus="autofocus"/></dd>
                            <div id="name_tip" class="validateTip"></div>
                            <p></p>
                            <dt>性别</dt>
                            <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex"/></dd>
                            <dd>
                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio" style="margin-top:-3px;">
                                    <button id="maleButton" type="button" value="0" class="btn btn-small" data-toggle="button">男</button>
                                    <button id="femaleButton" type="button" value="1" class="btn btn-small" data-toggle="button">女</button>
                                </div>
                            </dd>
                            <p></p>
                            <dt>出生地</dt>
                            <dd><s:select cssClass="span4" headerValue="请选择你出生的省份" name="province" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/></dd>
                            <p></p>
                            <dt>毕业学校</dt>
                            <dd><s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的学校" /></dd>
                            <p></p>
                            <dt>网络环境</dt>
                            <dd><s:select cssClass="span4" headerValue="请选择你的网络环境" name="net" list="{'电信', '联通', '教育网', '其他'}"/></dd>
                            <br/>
                            <br/>
                            <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="teacherRegister"/></dd>
                        </dl>                
                    </s:if>
                    <s:if test="#session.userType == 'student'">
                        <h2>同学您好，修改你的详细信息吧^ ^</h2>
                        <hr/>
                        <dl class="dl-horizontal">
                            <dt>姓名</dt>
                            <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的全名" autofocus="autofocus"/></dd>
                            <div id="name_tip" class="validateTip"></div>
                            <dt>性别</dt>
                            <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex"/></dd>
                            <dd>
                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                    <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                </div>
                            </dd>
                            <p></p>
                            <dt>年级</dt>
                            <dd><s:select cssClass="span4" headerValue="请选择你的年级" name="grade" list="{'小学', '六年级', '初一', '初二', '初三', '高一', '高二', '高三'}"/></dd>
                            <dt>就读学校</dt>
                            <dd><s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的学校" /></dd>
                            <br/>
                            <br/>
                            <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="studentRegister"/></dd>
                        </dl>
                    </s:if>
                </s:form>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
